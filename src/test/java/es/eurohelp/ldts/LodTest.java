package es.eurohelp.ldts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author grozadilla
 * @author Mikel Egaña Aranguren
 *
 */
public class LodTest  {

	LinkedDataRequestBean requestBean;
	static List<LinkedDataRequestBean> tests = new ArrayList<LinkedDataRequestBean>();

	@Test
	public final void GETSPARQLHTML200 () {
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "sparql";
			String name = "GETSPARQLHTML200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public final void GETResourceHTMLPageRedirect303(){
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22";
			String name = "GETResourceHTMLPageRedirect303";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceHTMLDocRedirect303(){
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceHTMLDocRedirect303";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceRDFXMLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceRDFXMLContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			
			// TODO: meter el content del HTTP entity en el Bean, para no tener que leer desde disco
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"<ContractEconomicConditions xmlns=\"http://contsem.unizar.es/def/sector-publico/pproc#\" "
					+ "rdf:datatype=\"http://www.w3.org/2001/XMLSchema#long\">3670496</ContractEconomicConditions>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceRDFXML200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceRDFXML200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceJSONLD200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.JSONLD.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceJSONLD200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceN3200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.N3.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceN3200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceNQuads200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.NQuads.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceNQuads200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceNTriples200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.NTriples.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceNTriples200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceRDFJSON200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFJSON.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceRDFJSON200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTriG200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.TriG.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceTriG200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTriX200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.TriX.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceTriX200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTurtle200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.Turtle.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceTurtle200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETClassRDFXML200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento/Hotel";
			String name = "GETClassRDFXML200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETClassHTML200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento/Hotel";
			String name = "GETClassHTML200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyHTML200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			//String pathUri = "def/Euskadipedia"; // mas realista?
			String name = "GETOntologyHTML200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyRDFXML200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			//String pathUri = "def/Euskadipedia"; // mas realista?
			String name = "GETOntologyRDFXML200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void SPARQLPOSTNamedGraphsMetadataCSV200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.POST.methodtypevalue();
			String accept = MIMEtype.CSV.mimetypevalue();
			String pathUri = "sparql";
			String name = "SPARQLPOSTNamedGraphsMetadataCSV200";
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"SELECT * WHERE { ?sub ?pred ?obj .} LIMIT 10");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void SPARQLPOSTNamedGraphsMetadataCSVContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.POST.methodtypevalue();
			String accept = MIMEtype.CSV.mimetypevalue();
			String pathUri = "sparql";
			String name = "SPARQLPOSTNamedGraphsMetadataCSVContent";
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
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			
			// TODO: meter el content del HTTP entity en el Bean, para no tener que leer desde disco
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"http://es.euskadi.eus/dataset/id/registro-de-contratos-del-sector-publico-de-euskadi,"
					+ "http://www.w3.org/1999/02/22-rdf-syntax-ns#type,http://rdfs.org/ns/void#Dataset"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void SPARQLPOSTMassiveCSV200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.POST.methodtypevalue();
			String accept = MIMEtype.CSV.mimetypevalue();
			String pathUri = "sparql";
			String name = "SPARQLPOSTMassiveCSV200";
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"SELECT ?g ?p ?o "
					+ "WHERE { "
					+ "	?g ?p ?o ."
					+ "} LIMIT 145000");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void SPARQLPOSTInsert400 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.POST.methodtypevalue();
			String accept = MIMEtype.CSV.mimetypevalue();
			String pathUri = "sparql";
			String name = " SPARQLPOSTInsert400";
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"INSERT DATA { "
					+ "GRAPH <http://lod.eurohelp.es> "
					+ "{ "
					+ "<http://lod.eurohelp.es/mikel> <http://lod.eurohelp.es/position> <http://lod.eurohelp.es/analist> "
					+ "} }");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(),400);
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