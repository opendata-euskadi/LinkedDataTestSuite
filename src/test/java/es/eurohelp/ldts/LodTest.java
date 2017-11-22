package es.eurohelp.ldts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
 * @author Mikel Egaña Aranguren
 *
 */
public class LodTest  {

	LinkedDataRequestBean requestBean;
	static List<LinkedDataRequestBean> tests = new ArrayList<LinkedDataRequestBean>();

	@Test
	public final void GETSPARQLHTML200 () {
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "sparql";
			final String name = "GETSPARQLHTML200";
			final String comment = "Ir directo a formulario SPARQL";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public final void GETResourceHTMLPageNoRedirect303(){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GETNO303.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22";
			final String name = "GETResourceHTMLPageNoRedirect303";
			final String comment = "Recurso con pagina bonita, pero sin seguir el 303";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			System.out.println(requestBean.getLocation());
			assertTrue(requestBean.getLocation().contains("/page/"));
			assertEquals(requestBean.getStatus(), 303);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETkosHTMLPage (){
	       try {
	            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
	            final String method = Methodtype.GET.methodtypevalue();
	            final String accept = MIMEtype.HTML.mimetypevalue();
	            final String pathUri = "kos/dominio.rdf";
	            final String name = "GETkosHTMLPage";
	            final String comment = "Obtener documento SKOS pidiendo documento Web";
	            final Map<String, String> parameters = new HashMap<String, String>();
	            requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
	            HttpManager.getInstance().doRequest(requestBean);
	            assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/kos/ds-contrataciones"));
	        } catch (final IOException e) {
	            e.printStackTrace();
	        }
	}
	    
	
	@Test
	public final void GETResourceHTMLPage(){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22";
			final String name = "GETResourceHTMLPage";
			final String comment = "Recurso con pagina bonita que no existe todavia";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
//			System.out.println(requestBean.getLocation());
//			System.out.println(requestBean.getResponseString());
			assertTrue(requestBean.getResponseString().contains("<span>Lehendakaritza</span>"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceHTMLDocNoRedirect303(){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GETNO303.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "id/medio-ambiente/calidad-del-aire/elemento/ICAEstacion-2017-01-26";
//			String pathUri = "id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02";
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceHTMLDocNoRedirect303";
			final String comment = "Recurso con pagina fea, pero sin seguir 303";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getLocation().contains("/doc/es/"));
			assertEquals(requestBean.getStatus(), 303);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceHTMLDoc(){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "id/medio-ambiente/calidad-del-aire/elemento/ICAEstacion-2017-01-26";
//			String pathUri = "id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02";
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceHTMLDoc";
			final String comment = "Recurso con pagina fea";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("ICAEstacion</a>"));
//			assertTrue(requestBean.getResponseString().contains("<h1>CO-2017-01-02</h1>"));




		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceDirectlyDoc(){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "doc/es/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02";
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceDirectlyDoc";
			final String comment = "Recurso con pagina fea, ir directamente a pagina /doc sin pasar por /id";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceRDFXMLContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.RDFXML.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceRDFXMLContent";
			final String comment = "Obtener recurso en RDF/XML, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<ContractEconomicConditions xmlns=\"http://contsem.unizar.es/def/sector-publico/pproc#\" "
					+ "rdf:datatype=\"http://www.w3.org/2001/XMLSchema#long\">3670496</ContractEconomicConditions>"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceDirectlyDataRDFXMLContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.RDFXML.mimetypevalue();
			final String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceDirectlyDataRDFXMLContent";
			final String comment = "Obtener recurso en RDF/XML directamente de /data, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<ContractEconomicConditions xmlns=\"http://contsem.unizar.es/def/sector-publico/pproc#\" "
					+ "rdf:datatype=\"http://www.w3.org/2001/XMLSchema#long\">3670496</ContractEconomicConditions>"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceDirectlyDataTTLContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.Turtle.mimetypevalue();
			final String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceDirectlyDataTTLContent";
			final String comment = "Obtener recurso en TTL directamente de /data, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate> \"2000-03-31\"^^xsd:date ;"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceDirectlyDataJSONLDContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.JSONLD.mimetypevalue();
			final String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceDirectlyDataJSONLDContent";
			final String comment = "Obtener recurso en JSONLD directamente de /data, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"\"@value\" : \"10.0\""));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceDirectlyDataRDFXMLContentHTMLHeader (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.rdf";
			final String name = "GETResourceDirectlyDataRDFXMLContentHTMLHeader";
			final String comment = "Obtener recurso en RDF/XML (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<occupation xmlns=\"http://dbpedia.org/ontology/\" xml:lang=\"es\">jefe grupo inspector pesca</occupation>"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceDirectlyDataTTLContentHTMLHeader (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.ttl";
			final String name = "GETResourceDirectlyDataTTLContentHTMLHeader";
			final String comment = "Obtener recurso en TTL (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate> \"2000-03-31\"^^xsd:date ;"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceDirectlyDataJSONLDContentHTMLHeader (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.jsonld";
			final String name = "GETResourceDirectlyDataJSONLDContentHTMLHeader";
			final String comment = "Obtener recurso en JSON-LD (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"\"@value\" : \"2000-03-31\""));
		} catch (final IOException e) {
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
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.JSONLD.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceJSONLDContent";
			final String comment = "Obtener recurso en JSON-LD, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("\"@value\" : \"jefe grupo inspector pesca\""));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceN3Content (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.N3.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceN3Content";
			final String comment = "Obtener recurso en N3, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<http://contsem.unizar.es/def/sector-publico/pproc#managingDepartment> "
					+ "<http://es.euskadi.eus/id/sector-publico/departamento/desarrollo-economico-e-infraestructuras> ;"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public final void GETResourceNQuadsContent (){
//		try {
//			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
//			String method = Methodtype.GET.methodtypevalue();
//			String accept = MIMEtype.NQuads.mimetypevalue();
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
//			String name = "GETResourceNQuadsContent";
//			String comment = "Obtener recurso en NQuads, parsear contenido";
//			Map<String, String> parameters = new HashMap<String, String>();
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
//			HttpManager.getInstance().doRequest(requestBean);
//			assertTrue(requestBean.getResponseString().contains(
//					"<http://es.euskadi.eus/dataset/id/relaciones-de-puestos-de-trabajo-de-los-departamentos-y-organismos-autonomos-de-la-administracion-de-la-comunidad-autonoma>"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public final void GETResourceNTriplesContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.NTriples.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceNTriplesContent";
			final String comment = "Obtener recurso en NTriples, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<http://es.euskadi.eus/id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://purl.org/cerif/frapo/EmploymentContract> ."));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceRDFJSON200 (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.RDFJSON.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceRDFJSON200";
			final String comment = "Obtener recurso en RDF/JSON, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"\"value\" : \"http://es.euskadi.eus/id/sector-publico/departamento/desarrollo-economico-e-infraestructuras\","));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

//	@Test
//	public final void GETResourceTriGContent (){
//		try {
//			String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
//			String method = Methodtype.GET.methodtypevalue();
//			String accept = MIMEtype.TriG.mimetypevalue();
//			String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
//			String name = "GETResourceTriGContent";
//			String comment = "Obtener recurso en TriG, parsear contenido";
//			Map<String, String> parameters = new HashMap<String, String>();
//			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
//			HttpManager.getInstance().doRequest(requestBean);
//			assertTrue(requestBean.getResponseString().contains(
//					"GETResourceTriGContent Response no genera Trig, si no RDF/XML"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public final void GETResourceTriXContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.TriX.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceTriXContent";
			final String comment = "Obtener recurso en TriX, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<uri>http://dbpedia.org/ontology/occupation</uri>"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETResourceTurtleContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.Turtle.mimetypevalue();
			final String pathUri = "id/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09";
			final String name = "GETResourceTurtleContent";
			final String comment = "Obtener recurso en TTL, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<http://dbpedia.org/ontology/occupation> \"jefe grupo inspector pesca\"@es ;"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETClassRDFXMLContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.RDFXML.mimetypevalue();
			final String pathUri = "def/euskadipedia/Hotel";
			final String name = "GETClassRDFXMLContent";
			final String comment = "Obtener clase en RDF/XML, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"<owl:ObjectProperty rdf:about=\"http://euskadi.eus/def/euskadipedia#inHistoricTerritory\">"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETClassHTML200 (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "def/euskadipedia/Hotel";
			final String name = "GETClassHTML200";
			final String comment = "Obtener clase en HTML, sin parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETClassHTMLAnchorContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "def/euskadipedia.html#Hotel";
			final String name = "GETClassHTML200";
			final String comment = "Obtener clase en HTML con ancla, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public final void GETPropertyRDFXMLContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.RDFXML.mimetypevalue();
			final String pathUri = "def/euskadipedia#inHistoricTerritory";
			final String name = "GETPropertyRDFXMLContent";
			final String comment = "Obtener propiedad en RDF/XML, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"http://euskadi.eus/def/euskadipedia#inHistoricTerritory"));
			assertTrue(requestBean.getResponseString().contains(
					"<owl:ObjectProperty"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETPropertyHTML200 (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "def/euskadipedia#inHistoricTerritory";
			final String name = "GETPropertyHTML200";
			final String comment = "Obtener propiedad en HTML, sin parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (final IOException e) {
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
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			final String pathUri = "def/euskadipedia.html"; // mas realista?
			final String name = "GETOntologyHTMLContent";
			final String comment = "Obtener ontologia en HTML, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/def/euskadipedia/0.0.1"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETOntologyHTMLContentFileExtensionHTML (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			final String pathUri = "def/euskadipedia.html"; // mas realista?
			final String name = "GETOntologyHTMLContentFileExtensionHTML";
			final String comment = "Obtener ontologia en HTML con .html, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("<a name=\"http://euskadi.eus/def/euskadipedia#Hotel\"></a>"));
		} catch (final IOException e) {
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
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.HTML.mimetypevalue();
			final String pathUri = "def/euskadipedia.owl"; // Segun la NTI
			//String pathUri = "def/Euskadipedia"; // mas realista?
			final String name = "GETOntologyRDFXMLContentHTMLHeader";
			final String comment = "Obtener ontologia en OWL (por extension, no cabecera, cabecera HTML), parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("<owl:ObjectProperty rdf:about=\"http://euskadi.eus/def/euskadipedia#inHistoricTerritory\">"));

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETOntologyRDFXMLContentFileExtensionOWL (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.RDFXML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			final String pathUri = "def/euskadipedia.owl"; // mas realista?
			final String name = "GETOntologyRDFXMLContentFileExtensionOWL ";
			final String comment = "Obtener ontologia en RDF/XML con .owl, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
//			assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/def/euskadipedia/0.0.1"));
			assertTrue(requestBean.getResponseString().contains("<owl:Ontology rdf:about=\"http://euskadi.eus/def/euskadipedia\">"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void GETOntologyRDFXMLContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.GET.methodtypevalue();
			final String accept = MIMEtype.RDFXML.mimetypevalue();
//			String accept = MIMEtype.HTML.mimetypevalue();
//			String pathUri = "def/turismo/alojamiento"; // Segun la NTI
			final String pathUri = "def/euskadipedia.html"; // mas realista?
			final String name = "GETOntologyRDFXMLContent";
			final String comment = "Obtener ontologia en RDF/XML, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains("<owl:Ontology rdf:about=\"http://euskadi.eus/def/euskadipedia\">"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void SPARQLPOSTNamedGraphsMetadataCSVContent (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.POST.methodtypevalue();
			final String accept = MIMEtype.CSV.mimetypevalue();
			final String pathUri = "sparql";
			final String name = "SPARQLPOSTNamedGraphsMetadataCSVContent";
			final String comment = "Consulta sobre datos y metadatos, parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"SELECT DISTINCT ?a ?b WHERE { ?a ?b ?g GRAPH ?g {?sub ?pred ?obj}}");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertTrue(requestBean.getResponseString().contains(
					"http://es.euskadi.eus/distribution/calidad-aire-en-euskadi-2017,http://www.w3.org/ns/sparql-service-description#namedGraph"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void SPARQLPOSTMassiveCSV200 (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.POST.methodtypevalue();
			final String accept = MIMEtype.CSV.mimetypevalue();
			final String pathUri = "sparql";
			final String name = "SPARQLPOSTMassiveCSV200";
			final String comment = "Consulta masiva, sin parsear contenido";
			final Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"SELECT ?g ?p ?o "
					+ "WHERE { "
					+ "	?g ?p ?o ."
					+ "} LIMIT 145000");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(), 200);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void SPARQLPOSTInsert400 (){
		try {
			final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
			final String method = Methodtype.POST.methodtypevalue();
			final String accept = MIMEtype.CSV.mimetypevalue();
			final String pathUri = "sparql";
			final String name = " SPARQLPOSTInsert400";
			final String comment = "Insertar datos, deberia fallar";
			final Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("query",
					"INSERT DATA { "
					+ "GRAPH <http://lod.eurohelp.es> "
					+ "{ "
					+ "<http://lod.eurohelp.es/mikel> <http://lod.eurohelp.es/position> <http://lod.eurohelp.es/analist> "
					+ "} }");
			requestBean = new LinkedDataRequestBean(method,accept, baseUri, pathUri, name, comment, parameters);
			HttpManager.getInstance().doRequest(requestBean);
			assertEquals(requestBean.getStatus(),400);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Rule
	public TestRule watchman = new TestWatcher() {

		@Override
		public Statement apply(final Statement base, final Description description) {
			return super.apply(base, description);
		}

		@Override
		protected void succeeded(final Description description) {
			try {
				requestBean.setStatus(0);
				tests.add(requestBean);
			} catch (final Exception e1) {
				System.out.println(e1.getMessage());
			}finally {
				//System.out.println(tests.indexOf(requestBean));
				requestBean.setTestIndex(tests.indexOf(requestBean));
			}
		}

		@Override
		protected void failed(final Throwable e, final Description description) {
			try {
				requestBean.setStatus(1);
				tests.add(requestBean);
			} catch (final Exception e2) {
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
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}


}