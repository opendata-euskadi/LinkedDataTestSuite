/**
 *
 */
package es.eurohelp.ldts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * Utilities to http connections
 *
 * @author grozadilla
 *
 */
public class HttpManager {

	private static HttpManager INSTANCE = null;
	private BufferedHttpEntity bfHttpEntity;

	/**
	 * Get a HttpManager instance
	 *
	 * @return HttpManager instance
	 */
	public synchronized static HttpManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HttpManager();
		}
		return INSTANCE;
	}

	/**
	 * Executes an Http request
	 *
	 * @param requestBean
	 *            request configuration
	 * @return the response of the request
	 */
	public HttpResponse doRequest(final LinkedDataRequestBean requestBean) {
		System.out.println("*********************");
		System.out.println("Uri: " + requestBean.getCompleteUri());
		System.out.println("Method: " + requestBean.getMethod());
		System.out.println("Accept: " + requestBean.getAccept());
		System.out.println("Params: " + requestBean.getParameters().toString());

		final HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = null;
		HttpGet httpget = null;
		HttpResponse response = null;

		try {

			if ("POST".equals(requestBean.getMethod())) {
				httppost = new HttpPost(requestBean.getCompleteUri());
				httppost.setHeader("accept", requestBean.getAccept());
				httppost.setHeader("content-type", "application/x-www-form-urlencoded");

				final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				final Map<String, String> parameters = requestBean.getParameters();
				final Iterator<String> iterator = parameters.keySet().iterator();

				while (iterator.hasNext()) {
					final String key = iterator.next();
					postParameters.add(new BasicNameValuePair(key, parameters.get(key)));
				}

				httppost.setEntity(new UrlEncodedFormEntity(postParameters));

				/* VERSIÓN NO OPTIMIZADA (EJECUTABA PETICIÓN 2 VECES)
				final String content = EntityUtils.toString(httpclient.execute(httppost).getEntity());
				requestBean.setResponseString(content);
				*/

				response = httpclient.execute(httppost);

			} else {
				String completeUri = requestBean.getCompleteUri();
				int numParam = 0;

				final Map<String, String> parameters = requestBean.getParameters();

				final Iterator<String> iterator = parameters.keySet().iterator();

				while (iterator.hasNext()) {
					if (numParam == 0) {
						completeUri = completeUri + "?";
					} else {
						completeUri = completeUri + "&";
					}
					final String key = iterator.next();
					completeUri = completeUri + key + "=" + parameters.get(key);
					numParam++;
				}

				httpget = new HttpGet(completeUri);
				httpget.setHeader("accept", requestBean.getAccept());

				if ("GETNO303".equals(requestBean.getMethod())) {
					final HttpParams params = new BasicHttpParams();
					params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
					httpget.setParams(params);
				}

				/* VERSIÓN NO OPTIMIZADA (EJECUTABA PETICIÓN 2 VECES)
				final String content = EntityUtils.toString(httpclient.execute(httppost).getEntity());
				requestBean.setResponseString(content);
				*/

				response = httpclient.execute(httpget);

				if ("GETNO303".equals(requestBean.getMethod())
						&& response.getFirstHeader("Location") != null) {
					final String LocationHEaderValue = response.getFirstHeader("Location").getValue();
					System.out.println("Location: " + LocationHEaderValue);
					requestBean.setLocation(LocationHEaderValue);
				}

				// File file = new File(resultsPath +
				// requestBean.getTestName());
				//
				// // if file doesn't exists, then create it
				// if (!file.exists()) {
				// file.createNewFile();
				// }

			}

			requestBean.setStatus(response.getStatusLine().getStatusCode());

			final String resultsPath = PropertiesManager.getInstance().getProperty("lod.report.path");

			System.out.println("Status: " + requestBean.getStatus());
			System.out.println("Test name: " + requestBean.getName());
			System.out.println("Response file: " + resultsPath + requestBean.getTestName());
			System.out.println("*********************");

			//Con la clase BufferedHttpEntity podemos consumir varias veces la HttpEntity(non-repeatable) de la response
			bfHttpEntity = new BufferedHttpEntity(response.getEntity());

			// redirect the output
			InputStream aInStream = null;
			FileOutputStream aOutStream = null;

			try {
				aInStream = bfHttpEntity.getContent();

				final File file = new File(resultsPath + requestBean.getTestName());

				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				aOutStream = new FileOutputStream(file);

				IOUtils.copy(aInStream, aOutStream);

			} catch (final IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (aInStream != null)
					aInStream.close();
				if (aOutStream != null)
					aOutStream.close();
			}

		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		try {
			final String responseString = EntityUtils.toString(bfHttpEntity);
			requestBean.setResponseString(responseString);
		} catch (final ParseException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return response;
	}
}
