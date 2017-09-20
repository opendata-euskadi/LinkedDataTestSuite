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
 * @author ssantamariap
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
	public HttpResponse doRequest(LinkedDataRequestBean requestBean) {
		System.out.println("*********************");
		System.out.println("Uri: " + requestBean.getCompleteUri());
		System.out.println("Method: " + requestBean.getMethod());
		System.out.println("Accept: " + requestBean.getAccept());
		System.out.println("Params: " + requestBean.getParameters().toString());

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = null;
		HttpGet httpget = null;
		HttpResponse response = null;

		try {

			if ("POST".equals(requestBean.getMethod())) {
				httppost = new HttpPost(requestBean.getCompleteUri());
				httppost.setHeader("accept", requestBean.getAccept());
				httppost.setHeader("content-type", "application/x-www-form-urlencoded");

				ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				Map<String, String> parameters = requestBean.getParameters();
				Iterator<String> iterator = parameters.keySet().iterator();

				while (iterator.hasNext()) {
					String key = iterator.next();
					postParameters.add(new BasicNameValuePair(key, parameters.get(key)));
				}

				httppost.setEntity(new UrlEncodedFormEntity(postParameters));
				
				response = httpclient.execute(httppost);
				
			} else {
				String completeUri = requestBean.getCompleteUri();
				int numParam = 0;

				Map<String, String> parameters = requestBean.getParameters();

				Iterator<String> iterator = parameters.keySet().iterator();

				while (iterator.hasNext()) {
					if (numParam == 0) {
						completeUri = completeUri + "?";
					} else {
						completeUri = completeUri + "&";
					}
					String key = iterator.next();
					completeUri = completeUri + key + "=" + parameters.get(key);
					numParam++;
				}

				httpget = new HttpGet(completeUri);
				httpget.setHeader("accept", requestBean.getAccept());

				if ("GETNO303".equals(requestBean.getMethod())) {
					HttpParams params = new BasicHttpParams();
					params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
					httpget.setParams(params);
				}

				response = httpclient.execute(httpget);

				if ("GETNO303".equals(requestBean.getMethod())) {
					String LocationHEaderValue = response.getFirstHeader("Location").getValue();
					System.out.println("Location: " + LocationHEaderValue);
					requestBean.setLocation(LocationHEaderValue);
				}

			}
			
			requestBean.setStatus(response.getStatusLine().getStatusCode());

			String resultsPath = PropertiesManager.getInstance().getProperty("lod.report.path");

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

				File file = new File(resultsPath + requestBean.getTestName());

				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				aOutStream = new FileOutputStream(file);

				IOUtils.copy(aInStream, aOutStream);

			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (aInStream != null)
					aInStream.close();
				if (aOutStream != null)
					aOutStream.close();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			String responseString = EntityUtils.toString(bfHttpEntity);
			requestBean.setResponseString(responseString);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
