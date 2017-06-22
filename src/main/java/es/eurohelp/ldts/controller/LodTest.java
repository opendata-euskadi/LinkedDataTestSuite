package es.eurohelp.ldts.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import es.eurohelp.ldts.HttpManager;
import es.eurohelp.ldts.LinkedDataRequestBean;
import es.eurohelp.ldts.MIMEtype;
import es.eurohelp.ldts.Methodtype;
import es.eurohelp.ldts.PropertiesManager;
import es.eurohelp.ldts.ReportManager;


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
			String comment = "Ir directo a formulario SPARQL";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
			String comment = "Recurso con pagina bonita, pero sin seguir el 303";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getLocation().contains("/page/"));
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
			String comment = "Recurso con pagina fea, pero sin seguir 303";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
			String comment = "Recurso con pagina fea";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("<h1>CO-2017-01-02</h1>"));
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
			String comment = "Recurso con pagina bonita que no existe todavia";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Recurso con pagina fea, ir directamente a pagina /doc sin pasar por /id";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
			String comment = "Obtener recurso en RDF/XML, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceDirectlyDataRDFXMLContent";
			String comment = "Obtener recurso en RDF/XML directamente de /data, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<ContractEconomicConditions xmlns=\"http://contsem.unizar.es/def/sector-publico/pproc#\" "
					+ "rdf:datatype=\"http://www.w3.org/2001/XMLSchema#long\">3670496</ContractEconomicConditions>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataTTLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.Turtle.mimetypevalue();
			String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceDirectlyDataTTLContent";
			String comment = "Obtener recurso en TTL directamente de /data, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate> \"2000-03-31\"^^xsd:date ;"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETResourceDirectlyDataJSONLDContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.JSONLD.mimetypevalue();
			String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			String name = "GETResourceDirectlyDataJSONLDContent";
			String comment = "Obtener recurso en JSONLD directamente de /data, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"\"@value\" : \"10.0\""));
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
			String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.rdf";
			String name = "GETResourceDirectlyDataRDFXMLContentHTMLHeader";
			String comment = "Obtener recurso en RDF/XML (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<occupation xmlns=\"http://dbpedia.org/ontology/\" xml:lang=\"es\">jefe grupo inspector pesca</occupation>"));
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
			String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.ttl";
			String name = "GETResourceDirectlyDataTTLContentHTMLHeader";
			String comment = "Obtener recurso en TTL (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate> \"2000-03-31\"^^xsd:date ;"));
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
			String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.jsonld";
			String name = "GETResourceDirectlyDataJSONLDContentHTMLHeader";
			String comment = "Obtener recurso en JSON-LD (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"\"@value\" : \"2000-03-31\""));
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
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
			String comment = "Obtener recurso en JSON-LD, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("\"@value\" : \"jefe grupo inspector pesca\""));
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
			String comment = "Obtener recurso en N3, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Obtener recurso en NQuads, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Obtener recurso en NTriples, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Obtener recurso en RDF/JSON, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Obtener recurso en TriG, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Obtener recurso en TriX, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Obtener recurso en TTL, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String pathUri = "def/euskadipedia/Hotel";
			String name = "GETClassRDFXMLContent";
			String comment = "Obtener clase en RDF/XML, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String pathUri = "def/euskadipedia/Hotel";
			String name = "GETClassHTML200";
			String comment = "Obtener clase en HTML, sin parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETClassHTMLAnchorContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "def/euskadipedia.html#Hotel";
			String name = "GETClassHTML200";
			String comment = "Obtener clase en HTML con ancla, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
			String pathUri = "def/euskadipedia/precio";
			String name = "GETPropertyRDFXMLContent";
			String comment = "Obtener propiedad en RDF/XML, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String pathUri = "def/euskadipedia/precio";
			String name = "GETPropertyHTML200";
			String comment = "Obtener propiedad en HTML, sin parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			String pathUri = "def/euskadipedia"; // mas realista?
			String name = "GETOntologyHTMLContent";
			String comment = "Obtener ontologia en HTML, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("http://opendata.aragon.es/def/Aragopedia"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyHTMLContentFileExtensionHTML (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			String pathUri = "def/euskadipedia.html"; // mas realista?
			String name = "GETOntologyHTMLContent";
			String comment = "Obtener ontologia en HTML con .html, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("http://opendata.aragon.es/def/Aragopedia"));
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
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
//			HttpManager.getInstance().doRequest(requestBean);
//			assertEquals(requestBean.getStatus(), 200);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public final void GETOntologyRDFXMLContentHTMLHeader (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.HTML.mimetypevalue();
			String pathUri = "def/euskadi.owl"; // Segun la NTI
			//String pathUri = "def/Euskadipedia"; // mas realista?
			String name = "GETOntologyRDFXMLContentHTMLHeader";
			String comment = "Obtener ontologia en OWL (por extension, no cabecera, cabecera HTML), parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("<owl:Ontology rdf:about=\"http://opendata.aragon.es/def/Aragopedia\">"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyRDFXMLContent (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			String pathUri = "def/euskadipedia.owl"; // mas realista?
			String name = "GETOntologyRDFXMLContent";
			String comment = "Obtener ontologia en RDF/XML, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("<owl:Ontology rdf:about=\"http://opendata.aragon.es/def/Aragopedia\">"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void GETOntologyRDFXMLContentFileExtensionOWL (){ 
		try {
			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			String method = Methodtype.GET.methodtypevalue();
			String accept = MIMEtype.RDFXML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			String pathUri = "def/euskadipedia.owl"; // mas realista?
			String name = "GETOntologyRDFXMLContentFileExtensionOWL ";
			String comment = "Obtener ontologia en RDF/XML con .owl, parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("<owl:Ontology rdf:about=\"http://opendata.aragon.es/def/Aragopedia\">"));
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
			String comment = "Consulta sobre datos y metadatos, parsear contenido";
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
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
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
			String comment = "Consulta masiva, sin parsear contenido";
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"SELECT ?g ?p ?o "
					+ "WHERE { "
					+ "	?g ?p ?o ."
					+ "} LIMIT 145000");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
			String comment = "Insertar datos, deberia fallar";
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"INSERT DATA { "
					+ "GRAPH <http://lod.eurohelp.es> "
					+ "{ "
					+ "<http://lod.eurohelp.es/mikel> <http://lod.eurohelp.es/position> <http://lod.eurohelp.es/analist> "
					+ "} }");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
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
			}finally {
				//System.out.println(tests.indexOf(requestBean));
				requestBean.setTestIndex(tests.indexOf(requestBean));
			}
		}
 
		@Override
		protected void failed(Throwable e, Description description) {
			try {
				requestBean.setStatus(1);
				tests.add(requestBean);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}finally {
				//System.out.println(tests.indexOf(requestBean));
				requestBean.setTestIndex(tests.indexOf(requestBean));
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