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
public class LodTest {

    private static final String urifea = "eli/es-pv/a/1978/06/05/(2)/dof";
    private static final String uribonita = "id/public-sector/government/GovernmentalAdministrativeRegion/euskadi";
    private static final String uridistribution = "distribution/bopv-european-legislation-identifier-eli/lod";
    private static final String urigraph = "graph/bopv-european-legislation-identifier-eli";

    LinkedDataRequestBean requestBean;
    static List<LinkedDataRequestBean> tests = new ArrayList<LinkedDataRequestBean>();

    @Test
    public final void GETResourceHTMLPageNoRedirect303() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GETNO303.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceHTMLPageNoRedirect303";
            final String comment = "Recurso con pagina bonita, pero sin seguir el 303";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getLocation().contains("/page/"));
            assertEquals(requestBean.getStatus(), 303);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETErrorRDF() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = "trololo";
            final String name = "GETErrorRDF";
            final String comment = "Obtener un recurso que no existe, en RDF";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("404 Not Found"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETErrorHTML() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "trololo";
            final String name = "GETErrorHTML";
            final String comment = "Obtener un recurso que no existe, en HTML";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("404 Not Found"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceHTMLPage() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceHTMLPage";
            final String comment = "Recurso con pagina bonita";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("zerbitzuak</title>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceHTMLDocNoRedirect303() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GETNO303.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = urifea;
            final String name = "GETResourceHTMLDocNoRedirect303";
            final String comment = "Recurso con pagina fea, pero sin seguir 303";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getLocation().contains("/doc/"));
            assertEquals(requestBean.getStatus(), 303);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceHTMLDoc() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = urifea;
            final String name = "GETResourceHTMLDoc";
            final String comment = "Recurso con pagina fea";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("dof</a>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceDirectlyDoc() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = urifea;
            final String name = "GETResourceDirectlyDoc";
            final String comment = "Recurso con pagina fea, ir directamente a pagina /doc sin pasar por /id";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceRDFXMLContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceRDFXMLContent";
            final String comment = "Obtener recurso en RDF/XML, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<rdf:Description rdf:about=\"http://data.euskadi.eus/id/public-sector/government/GovernmentalAdministrativeRegion/euskadi\">"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceDirectlyDataRDFXMLContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = uribonita.replace("id", "data");
            final String name = "GETResourceDirectlyDataRDFXMLContent";
            final String comment = "Obtener recurso en RDF/XML directamente de /data, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<rdf:Description rdf:about=\"http://data.euskadi.eus/id/public-sector/government/GovernmentalAdministrativeRegion/euskadi\">"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceDirectlyDataTTLContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.Turtle.mimetypevalue();
            final String pathUri = uribonita.replace("id", "data");
            final String name = "GETResourceDirectlyDataTTLContent";
            final String comment = "Obtener recurso en TTL directamente de /data, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<http://data.euskadi.eus/id/public-sector/government/GovernmentalAdministrativeRegion/euskadi> <http://schema.org/mainEntityOfPage> <http://www.euskadi.eus> ;"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceDirectlyDataJSONLDContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.JSONLD.mimetypevalue();
            final String pathUri = uribonita.replace("id", "data");
            final String name = "GETResourceDirectlyDataJSONLDContent";
            final String comment = "Obtener recurso en JSONLD directamente de /data, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("\"@id\" : \"http://www.euskadi.eus\""));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceDirectlyDataRDFXMLContentHTMLHeader() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = uribonita.replace("id", "data") + ".rdf";
            final String name = "GETResourceDirectlyDataRDFXMLContentHTMLHeader";
            final String comment = "Obtener recurso en RDF/XML (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString()
                    .contains("<occupation xmlns=\"http://dbpedia.org/ontology/\" xml:lang=\"es\">jefe grupo inspector pesca</occupation>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceDirectlyDataTTLContentHTMLHeader() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = uribonita.replace("id", "data") + ".ttl";
            final String name = "GETResourceDirectlyDataTTLContentHTMLHeader";
            final String comment = "Obtener recurso en TTL (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString()
                    .contains("<http://contsem.unizar.es/def/sector-publico/pproc#formalizedDate> \"2000-03-31\"^^xsd:date ;"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceDirectlyDataJSONLDContentHTMLHeader() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = uribonita.replace("id", "data") + ".jsonld";
            final String name = "GETResourceDirectlyDataJSONLDContentHTMLHeader";
            final String comment = "Obtener recurso en JSON-LD (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("\"@value\" : \"2000-03-31\""));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceJSONLDContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.JSONLD.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceJSONLDContent";
            final String comment = "Obtener recurso en JSON-LD, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("\"@id\" : \"http://www.euskadi.eus\""));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceN3Content() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.N3.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceN3Content";
            final String comment = "Obtener recurso en N3, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<http://data.euskadi.eus/id/public-sector/government/GovernmentalAdministrativeRegion/euskadi> <http://schema.org/mainEntityOfPage> <http://www.euskadi.eus> ;"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceNTriplesContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.NTriples.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceNTriplesContent";
            final String comment = "Obtener recurso en NTriples, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<http://data.euskadi.eus/id/public-sector/government/GovernmentalAdministrativeRegion/euskadi> <http://schema.org/mainEntityOfPage> <http://www.euskadi.eus> ."));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceRDFJSON200() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFJSON.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceRDFJSON200";
            final String comment = "Obtener recurso en RDF/JSON, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("\"value\" : \"http://www.euskadi.eus\","));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceTriXContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.TriX.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceTriXContent";
            final String comment = "Obtener recurso en TriX, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("<uri>http://www.euskadi.eus</uri>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceTurtleContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.Turtle.mimetypevalue();
            final String pathUri = uribonita;
            final String name = "GETResourceTurtleContent";
            final String comment = "Obtener recurso en TTL, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<http://data.euskadi.eus/id/public-sector/government/GovernmentalAdministrativeRegion/euskadi> <http://schema.org/mainEntityOfPage> <http://www.euskadi.eus> ;"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETDistributionTurtleContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.Turtle.mimetypevalue();
            final String pathUri = uridistribution;
            final String name = "GETDistributionTurtleContent";
            final String comment = "Obtener distribution en TTL, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<http://data.euskadi.eus/dataset/bopv-european-legislation-identifier-eli/> <http://www.w3.org/ns/dcat#distribution> <http://data.euskadi.eus/distribution/bopv-european-legislation-identifier-eli/lod>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETDistributionHTMLDoc() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = uridistribution;
            final String name = "GETDistributionHTMLDoc";
            final String comment = "Pagina fea de distribution";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("http://data.euskadi.eus/graph/bopv-european-legislation-identifier-eli"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETGraphTurtleContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.Turtle.mimetypevalue();
            final String pathUri = urigraph;
            final String name = "GETGraphTurtleContent";
            final String comment = "Obtener grafo en TTL, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("<http://data.euskadi.eus/graph/bopv-european-legislation-identifier-eli>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETGraphHTMLDoc() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri.lang");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = urigraph;
            final String name = "GETGraphHTMLDoc";
            final String comment = "Grafo pagina fea";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            // assertTrue(requestBean.getResponseString().contains("dof</a></p>"));
            assertTrue(requestBean.getResponseString().contains("NO funciona"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void SPARQLPOSTNamedGraphsMetadataCSVContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.POST.methodtypevalue();
            final String accept = MIMEtype.CSV.mimetypevalue();
            final String pathUri = "sparql/";
            final String name = "SPARQLPOSTNamedGraphsMetadataCSVContent";
            final String comment = "Consulta sobre datos y metadatos, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("query", "SELECT DISTINCT ?a ?b WHERE { ?a ?b ?g GRAPH ?g {?sub ?pred ?obj}}");
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "http://data.euskadi.eus/distribution/bopv-european-legislation-identifier-eli/lod,http://www.w3.org/ns/sparql-service-description#namedGraph"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void SPARQLPOSTMassiveCSV200() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.POST.methodtypevalue();
            final String accept = MIMEtype.CSV.mimetypevalue();
            final String pathUri = "sparql/";
            final String name = "SPARQLPOSTMassiveCSV200";
            final String comment = "Consulta masiva, sin parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("query", "SELECT ?g ?p ?o " + "WHERE { " + "	?g ?p ?o ." + "} LIMIT 145000");
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void SPARQLPOSTSPARQLXMLResultsFormat() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.POST.methodtypevalue();
            final String accept = MIMEtype.SPARQLXMLResultsFormat.mimetypevalue();
            final String pathUri = "sparql/";
            final String name = "SPARQLPOSTSPARQLXMLResultsFormat";
            final String comment = "Consulta respuesta en application/sparql-results+xml";
            final Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("query", "SELECT ?g ?p ?o WHERE { ?g ?p ?o .} LIMIT 100");
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("applicability</uri>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void SPARQLPOSTSPARQLJSONResultsFormat() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.POST.methodtypevalue();
            final String accept = MIMEtype.SPARQLJSONResultsFormat.mimetypevalue();
            final String pathUri = "sparql/";
            final String name = "SPARQLPOSTSPARQLJSONResultsFormat";
            final String comment = "Consulta respuesta en application/sparql-results+json";
            final Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("query", "SELECT ?g ?p ?o WHERE { ?g ?p ?o .} LIMIT 100");
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("value"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void SPARQLPOSTInsert400() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.POST.methodtypevalue();
            final String accept = MIMEtype.CSV.mimetypevalue();
            final String pathUri = "sparql/";
            final String name = " SPARQLPOSTInsert400";
            final String comment = "Insertar datos, deberia fallar";
            final Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("query", "INSERT DATA { " + "GRAPH <http://lod.eurohelp.es> " + "{ "
                    + "<http://lod.eurohelp.es/mikel> <http://lod.eurohelp.es/position> <http://lod.eurohelp.es/analist> " + "} }");
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 400);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void SPARQLGETPARAMETERQUERYSELECT() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.CSV.mimetypevalue();
            final String pathUri = "sparql/?query=SELECT+*%0AWHERE+%7B%0A%09%3Fs+%3Fp+%3Fo%0A%7D%0ALIMIT+100";
            final String name = "SPARQLGETPARAMETERQUERYSELECT";
            final String comment = "Consulta SPARQL mediante GET ?query=";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void SPARQLGETHTML200() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "sparql/";
            final String name = "SPARQLGETHTML200";
            final String comment = "Ir directo a formulario SPARQL";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceTriGContent() {
        try {
            String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            String method = Methodtype.GET.methodtypevalue();
            String accept = MIMEtype.TriG.mimetypevalue();
            String pathUri = uribonita;
            String name = "GETResourceTriGContent";
            String comment = "Obtener recurso en TriG, parsear contenido";
            Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("GETResourceTriGContent Response no genera Trig, si no RDF/XML"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETResourceNQuadsContent() {
        try {
            String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            String method = Methodtype.GET.methodtypevalue();
            String accept = MIMEtype.NQuads.mimetypevalue();
            String pathUri = uribonita;
            String name = "GETResourceNQuadsContent";
            String comment = "Obtener recurso en NQuads, parsear contenido";
            Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains(
                    "<http://data.euskadi.eus/id/public-sector/government/GovernmentalAdministrativeRegion/euskadi> <http://schema.org/mainEntityOfPage> <http://www.euskadi.eus> ."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETkosHTMLPage() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "kos/dominio.rdf";
            final String name = "GETkosHTMLPage";
            final String comment = "Obtener documento SKOS pidiendo documento Web";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/kos/ds-contrataciones"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETkosRDF() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = "kos/dominio.rdf";
            final String name = "GETkosRDF";
            final String comment = "Obtener documento SKOS pidiendo RDF";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/kos/ds-contrataciones"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETClassRDFXMLContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = "def/euskadipedia/Hotel";
            final String name = "GETClassRDFXMLContent";
            final String comment = "Obtener clase en RDF/XML, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString()
                    .contains("<owl:ObjectProperty rdf:about=\"http://euskadi.eus/def/euskadipedia#inHistoricTerritory\">"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETClassHTML200() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "def/euskadipedia/Hotel";
            final String name = "GETClassHTML200";
            final String comment = "Obtener clase en HTML, sin parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETClassHTMLAnchorContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "def/euskadipedia.html#Hotel";
            final String name = "GETClassHTML200";
            final String comment = "Obtener clase en HTML con ancla, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETPropertyRDFXMLContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = "def/euskadipedia#inHistoricTerritory";
            final String name = "GETPropertyRDFXMLContent";
            final String comment = "Obtener propiedad en RDF/XML, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/def/euskadipedia#inHistoricTerritory"));
            assertTrue(requestBean.getResponseString().contains("<owl:ObjectProperty"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETPropertyHTML200() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "def/euskadipedia#inHistoricTerritory";
            final String name = "GETPropertyHTML200";
            final String comment = "Obtener propiedad en HTML, sin parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETOntologyHTML200() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "def/euskadipedia";
            final String name = "GETOntologyHTML200";
            final String comment = "Obtener ontologia en HTML, sin parsear contenido";
            Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETOntologyHTMLContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "def/euskadipedia.html";
            final String name = "GETOntologyHTMLContent";
            final String comment = "Obtener ontologia en HTML, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/def/euskadipedia/0.0.1"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETOntologyHTMLContentFileExtensionHTML() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            // String pathUri = "def/turismo/alojamiento"; // Segun la NTI
            final String pathUri = "def/euskadipedia.html"; // mas realista?
            final String name = "GETOntologyHTMLContentFileExtensionHTML";
            final String comment = "Obtener ontologia en HTML con .html, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("<a name=\"http://euskadi.eus/def/euskadipedia#Hotel\"></a>"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETOntologyRDFXML200() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = "def/euskadipedia";
            final String name = "GETOntologyRDFXML200";
            final String comment = "Obtener ontologia en RDF/XML, parsear contenido";
            Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertEquals(requestBean.getStatus(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETOntologyRDFXMLContentHTMLHeader() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.HTML.mimetypevalue();
            final String pathUri = "def/euskadipedia.owl";
            final String name = "GETOntologyRDFXMLContentHTMLHeader";
            final String comment = "Obtener ontologia en OWL (por extension, no cabecera, cabecera HTML), parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString()
                    .contains("<owl:ObjectProperty rdf:about=\"http://euskadi.eus/def/euskadipedia#inHistoricTerritory\">"));

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETOntologyRDFXMLContentFileExtensionOWL() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = "def/euskadipedia.owl";
            final String name = "GETOntologyRDFXMLContentFileExtensionOWL ";
            final String comment = "Obtener ontologia en RDF/XML con .owl, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            // assertTrue(requestBean.getResponseString().contains("http://euskadi.eus/def/euskadipedia/0.0.1"));
            assertTrue(requestBean.getResponseString().contains("<owl:Ontology rdf:about=\"http://euskadi.eus/def/euskadipedia\">"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void GETOntologyRDFXMLContent() {
        try {
            final String baseUri = PropertiesManager.getInstance().getProperty("lod.baseUri");
            final String method = Methodtype.GET.methodtypevalue();
            final String accept = MIMEtype.RDFXML.mimetypevalue();
            final String pathUri = "def/euskadipedia.html"; 
            final String name = "GETOntologyRDFXMLContent";
            final String comment = "Obtener ontologia en RDF/XML, parsear contenido";
            final Map<String, String> parameters = new HashMap<String, String>();
            requestBean = new LinkedDataRequestBean(method, accept, baseUri, pathUri, name, comment, parameters);
            HttpManager.getInstance().doRequest(requestBean);
            assertTrue(requestBean.getResponseString().contains("<owl:Ontology rdf:about=\"http://euskadi.eus/def/euskadipedia\">"));
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
            } finally {
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
            } finally {
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