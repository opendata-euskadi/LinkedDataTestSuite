package es.eurohelp.ldts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.eurohelp.ldts.HttpManager;
import es.eurohelp.ldts.LinkedDataRequestBean;
import es.eurohelp.ldts.ReportManager;
import es.eurohelp.ldts.controller.TestController;

/**
 * @author grozadilla
 * @author Mikel Egaña Aranguren
 * @author ssantamariap
 *
 */

public class LodTest  {
	
	private static final Logger logger = LoggerFactory.getLogger(LodTest.class);
	private static List<LinkedDataRequestBean> tests = new ArrayList<LinkedDataRequestBean>();
	private static int executionCount = 0;
	
	LinkedDataRequestBean requestBean;
	XMLUtils xmlUtils = new XMLUtils();
	String xmlContainsNode = "";
	
	@Test
	public final void GETSPARQLHTML200 () {
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceHTMLPageNoRedirect303(){
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getLocation().contains(xmlContainsNode));
			assertEquals(requestBean.getStatus(), 303);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public final void GETResourceHTMLDocNoRedirect303(){
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getLocation().contains(xmlContainsNode));
			assertEquals(requestBean.getStatus(), 303);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceHTMLDoc(){
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceHTMLPage(){
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDoc(){
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceRDFXMLContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataRDFXMLContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataTTLContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataJSONLDContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataRDFXMLContentHTMLHeader (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataTTLContentHTMLHeader (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataJSONLDContentHTMLHeader (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceJSONLDContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceN3Content (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceNQuadsContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceNTriplesContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceRDFJSON200 (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTriGContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTriXContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTurtleContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETClassRDFXMLContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETClassHTML200 (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETClassHTMLAnchorContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public final void GETPropertyRDFXMLContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETPropertyHTML200 (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public final void GETOntologyHTMLContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyHTMLContentFileExtensionHTML (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyRDFXMLContentHTMLHeader (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyRDFXMLContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyRDFXMLContentFileExtensionOWL (){ 
		try {
			//requestBean = xmlUtils.getXMLData(new Object(){}.getClass().getEnclosingMethod().getName());
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public final void SPARQLPOSTNamedGraphsMetadataCSVContent (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"SELECT DISTINCT ?g ?p ?o "
					+ "WHERE { "
					+ "	?g ?p ?o ."
					+ "	GRAPH ?g "
					+ "		{"
					+ "			?sub ?pred ?obj"
					+ "		} "
					+ "}");
			requestBean.setParameters(parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(xmlContainsNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void SPARQLPOSTMassiveCSV200 (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"SELECT ?g ?p ?o "
					+ "WHERE { "
					+ "	?g ?p ?o ."
					+ "} LIMIT 145000");
			requestBean.setParameters(parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void SPARQLPOSTInsert400 (){ 
		try {
			requestBean = xmlUtils.getXMLData(method.getMethodName());
			xmlContainsNode = xmlUtils.getXmlContainsNode(method.getMethodName());
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"INSERT DATA { "
					+ "GRAPH <http://lod.eurohelp.es> "
					+ "{ "
					+ "<http://lod.eurohelp.es/mikel> <http://lod.eurohelp.es/position> <http://lod.eurohelp.es/analist> "
					+ "} }");
			requestBean.setParameters(parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(),400);
		} catch (Exception e) {
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
				requestBean.setRunningGroupID(TestController.runningGroupID);
				tests.add(requestBean);
				requestBean.setTestIndex(tests.indexOf(requestBean));
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
 
		@Override
		protected void failed(Throwable e, Description description) {
			try {
				requestBean.setStatus(1);
				requestBean.setRunningGroupID(TestController.runningGroupID);
				tests.add(requestBean);
				requestBean.setTestIndex(tests.indexOf(requestBean));
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	};
	
	@Rule
	public TestName method = new TestName();
	
	@AfterClass
	public static void createReport() {
		
		executionCount++;
		logger.info("Contador: " + executionCount + " Nº de test: " + TestController.testCount);
		
		//Generating report just when all single test have been runned.
		if(executionCount == TestController.testCount){
			executionCount = 0;
			try {
				logger.info("REPORT");
				ReportManager.getInstance().createReport(tests);	
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}	
	}
	
}