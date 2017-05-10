package es.eurohelp.ldts;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author grozadilla
 *
 */
public class LodTest  {

	LinkedDataRequestBean requestBean;
	static List<LinkedDataRequestBean> tests = new ArrayList<LinkedDataRequestBean>();
	
	/**
	 * Test method for {@link es.eurohelp.ldts.HttpManager#doRequest(es.eurohelp.ldts.LinkedDataRequestBean)}.
	 */
	@Test
	public final void testSparqlExecute() {
		try {	
			String method = Methodtype.POST.methodtypevalue();
			String accept = MIMEtype.CSV.mimetypevalue();
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String pathUri = "sparql"; //without '/'
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query","SELECT * WHERE { ?sub ?pred ?obj .} LIMIT 10");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Test method for {@link es.eurohelp.ldts.HttpManager#doRequest(es.eurohelp.ldts.LinkedDataRequestBean)}.
	 */
	@Test
	public final void testDataExecute() {
		try {	
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String pathUri = "data/turismo/alojamiento/Hotel/HotelAbando";  
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Test method for {@link es.eurohelp.ldts.HttpManager#doRequest(es.eurohelp.ldts.LinkedDataRequestBean)}.
	 */
	@Test
	public final void testRedirectExecute() {
		try {	
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String pathUri = "id/turismo/alojamiento/Hotel/HotelAbando";  
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 302);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Rule
	public TestRule watchman = new TestWatcher() {
 
		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}
 
		@Override
		protected void succeeded(Description description) {
			try {
				requestBean.setStatus(0);
				tests.add(requestBean);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
 
		@Override
		protected void failed(Throwable e, Description description) {
			try {
				requestBean.setStatus(1);
				tests.add(requestBean);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	};
	
	@AfterClass
	public static void createReport() {
		try {
			ReportManager.getInstance().createReport(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
