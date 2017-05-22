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
	public final void GETResourceHTMLPageNoRedirect303(){
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GETNO303.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22";
			String name = "GETResourceHTMLPageNoRedirect303";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getLocation().contains("/doc/"));
			assertEquals(requestBean.getStatus(), 303);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public final void GETResourceHTMLDocNoRedirect303(){
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GETNO303.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02";
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceHTMLDocNoRedirect303";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getLocation().contains("/doc/es/"));
			assertEquals(requestBean.getStatus(), 303);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceHTMLDoc(){
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02";
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceHTMLDoc";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"XXX"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceHTMLPage(){
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "id/sector-publico/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22";
			String name = "GETResourceHTMLDoc";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"XXX"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDoc(){
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "doc/es/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02";
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceDirectlyDoc";
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
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
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
	public final void GETResourceDirectlyDataRDFXMLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "data/es/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceDirectlyDataRDFXMLContent";
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
	public final void GETResourceDirectlyDataRDFXMLContentHTMLHeader (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "data/es/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.rdf";
			String name = "GETResourceDirectlyDataRDFXMLContentHTMLHeader";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"Trololo"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataTTLContentHTMLHeader (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "data/es/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.ttl";
			String name = "GETResourceDirectlyDataTTLContentHTMLHeader";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"Trololo"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataJSONLDContentHTMLHeader (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "data/es/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.jsonld";
			String name = "GETResourceDirectlyDataJSONLDContentHTMLHeader";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"Trololo"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//  Si la respuesta contiene lo que buscamos, ya implica una respuesta 200, luego este test es redundante	
	
//	@Test
//	public final void GETResourceRDFXML200 (){ 
//		try {
//			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
//			String method = Methodtype.GET.methodtypevalue();
//			String accept = MIMEtype.RDFXML.mimetypevalue();
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
//			String name = "GETResourceRDFXML200";
//			Map<String, String> parameters = new HashMap<String, String>();
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
//			HttpManager.getInstance().doRequest(requestBean);
//			assertEquals(requestBean.getStatus(), 200);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public final void GETResourceJSONLDContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.JSONLD.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceJSONLDContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains("\"@value\" : \"jefe grupo inspector pesca\""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceN3Content (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.N3.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceN3Content";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"<http://contsem.unizar.es/def/sector-publico/pproc#managingDepartment> "
					+ "<http://es.euskadi.eus/id/sector-publico/departamento/desarrollo-economico-e-infraestructuras> ;"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceNQuadsContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.NQuads.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceNQuadsContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"<http://es.euskadi.eus/dataset/id/relaciones-de-puestos-de-trabajo-de-los-departamentos-y-organismos-autonomos-de-la-administracion-de-la-comunidad-autonoma>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceNTriplesContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.NTriples.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceNTriplesContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"<http://es.euskadi.eus/id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://purl.org/cerif/frapo/EmploymentContract> ."));
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
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceRDFJSON200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"\"value\" : \"http://es.euskadi.eus/id/sector-publico/departamento/desarrollo-economico-e-infraestructuras\","));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTriGContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.TriG.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceTriGContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"GETResourceTriGContent Response no genera Trig, si no RDF/XML"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTriXContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.TriX.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceTriXContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"<uri>http://dbpedia.org/ontology/occupation</uri>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceTurtleContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.Turtle.mimetypevalue();
			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceTurtleContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"<http://dbpedia.org/ontology/occupation> \"jefe grupo inspector pesca\"@es ;"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETClassRDFXMLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento/Hotel";
			String name = "GETClassRDFXMLContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"<owl:ObjectProperty rdf:about=\"http://dbpedia.org/ontology/country\">"));
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
	public final void GETPropertyRDFXMLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento/precio";
			String name = "GETPropertyRDFXMLContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains(
					"</owl:Class>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETPropertyHTML200 (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento/precio";
			String name = "GETPropertyHTML200";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public final void GETOntologyHTML200 (){ 
//		try {
//			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
//			String method = Methodtype.GET.methodtypevalue();
//			String accept = MIMEtype.HTML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
//			//String pathUri = "def/Euskadipedia"; // mas realista?
//			String name = "GETOntologyHTML200";
//			Map<String, String> parameters = new HashMap<String, String>();
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
//			HttpManager.getInstance().doRequest(requestBean);
//			assertEquals(requestBean.getStatus(), 200);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public final void GETOntologyHTMLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			//String pathUri = "def/Euskadipedia"; // mas realista?
			String name = "GETOntologyHTMLContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains("http://opendata.aragon.es/def/Aragopedia"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public final void GETOntologyRDFXML200 (){ 
//		try {
//			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
//			String method = Methodtype.GET.methodtypevalue();
//			String accept = MIMEtype.RDFXML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
//			//String pathUri = "def/Euskadipedia"; // mas realista?
//			String name = "GETOntologyRDFXML200";
//			Map<String, String> parameters = new HashMap<String, String>();
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
//			HttpManager.getInstance().doRequest(requestBean);
//			assertEquals(requestBean.getStatus(), 200);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public final void GETOntologyRDFXMLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			//String pathUri = "def/Euskadipedia"; // mas realista?
			String name = "GETOntologyRDFXMLContent";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			String resultsPathName = PropertiesManager.getInstance().getProperty("lod.report.path") + requestBean.getTestName();
			File file = new File(resultsPathName);
			String response_string = FileUtils.readFileToString(file);
			assertTrue(response_string.contains("<owl:Ontology rdf:about=\"http://opendata.aragon.es/def/Aragopedia\">"));
			
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