<!DOCTYPE html>
<html>

  <head>
    <title>Linked Data Test Suite Report</title>
    <meta charset="UTF-8" />
    <style type="text/css">
      span.h {
        padding-left: 0px;
        font-weight: bold;
      }
      span {
        display: block;
        padding-left: 10px;
      }
      
      table {
    	border-collapse: collapse;
    	width:100%;
		}

		table, th, td {
    		border: 1px solid black;
		}
		
		th{
			background-color:grey;
			padding: 4px;
    		background-color: rgba(40, 30, 226, 0.59);
    		color: #fff;
		}
		
		ul {
  			list-style-type: none;
  		}
  		
    </style>
  </head>

  <body>
  
    <h1>Linked Data Test Suite Report</h1>
    <h2>28-jun-2017 09:48:33</h2>

    <hr/>
    
    <h3>Test realizados:</h3>
    
    POST:
    

    				[<a href=#12>SPARQLPOSTNamedGraphsMetadataCSVContent</a>]

    <br/>
    GET:
    

    				[<a href=#3>GETResourceDirectlyDataRDFXMLContent</a>]


    				[<a href=#4>GETResourceDirectlyDataTTLContent</a>]


    				[<a href=#5>GETResourceDirectlyDataJSONLDContent</a>]


    				[<a href=#6>GETResourceDirectlyDataRDFXMLContentHTMLHeader</a>]


    				[<a href=#7>GETResourceDirectlyDataTTLContentHTMLHeader</a>]


    				[<a href=#8>GETResourceDirectlyDataJSONLDContentHTMLHeader</a>]


    				[<a href=#9>GETOntologyHTMLContent</a>]


    				[<a href=#10>GETOntologyRDFXMLContentHTMLHeader</a>]


    				[<a href=#11>GETOntologyRDFXMLContentFileExtensionOWL </a>]


    				[<a href=#13>GETClassHTML200</a>]


    				[<a href=#14>GETSPARQLHTML200</a>]


    				[<a href=#17>GETResourceHTMLDoc</a>]


    				[<a href=#18>GETResourceHTMLDoc</a>]


    				[<a href=#19>GETResourceDirectlyDoc</a>]


    				[<a href=#15>GETResourceHTMLPageNoRedirect303</a>]


    				[<a href=#16>GETResourceHTMLDocNoRedirect303</a>]


	<table>
	   	<thead>
	   	<tr>
	   		<th></th>
	   		<th>Test name</th>
	   		<th>Test comment</th>
	   		<th>Target Uri</th>
	   		<th>HTTP Method</th>
	   		<th>Accept Header</th>
	   		<th>Status</th>
	   		<th>Result file</th>
	   	</tr>
	   	</thead>
   	<tbody>
   	
   	<form action="run" method="post">
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceDirectlyDataRDFXMLContent" unchecked></td>
	   		<td id=3>GETResourceDirectlyDataRDFXMLContent</td>
	   		<td>Obtener recurso en RDF/XML directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09</a></td>
	   		<td>GET</td>
	   		<td>application/rdf+xml</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETapplication_rdf+xmldata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09GETResourceDirectlyDataRDFXMLContent">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceDirectlyDataTTLContent" unchecked></td>
	   		<td id=4>GETResourceDirectlyDataTTLContent</td>
	   		<td>Obtener recurso en TTL directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09</a></td>
	   		<td>GET</td>
	   		<td>text/turtle</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_turtledata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09GETResourceDirectlyDataTTLContent">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceDirectlyDataJSONLDContent" unchecked></td>
	   		<td id=5>GETResourceDirectlyDataJSONLDContent</td>
	   		<td>Obtener recurso en JSONLD directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09</a></td>
	   		<td>GET</td>
	   		<td>application/ld+json</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETapplication_ld+jsondata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09GETResourceDirectlyDataJSONLDContent">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceDirectlyDataRDFXMLContentHTMLHeader" unchecked></td>
	   		<td id=6>GETResourceDirectlyDataRDFXMLContentHTMLHeader</td>
	   		<td>Obtener recurso en RDF/XML (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.rdf>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.rdf</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmldata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09_rdfGETResourceDirectlyDataRDFXMLContentHTMLHeader">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceDirectlyDataTTLContentHTMLHeader" unchecked></td>
	   		<td id=7>GETResourceDirectlyDataTTLContentHTMLHeader</td>
	   		<td>Obtener recurso en TTL (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.ttl>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.ttl</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmldata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09_ttlGETResourceDirectlyDataTTLContentHTMLHeader">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceDirectlyDataJSONLDContentHTMLHeader" unchecked></td>
	   		<td id=8>GETResourceDirectlyDataJSONLDContentHTMLHeader</td>
	   		<td>Obtener recurso en JSON-LD (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.jsonld>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.jsonld</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmldata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09_jsonldGETResourceDirectlyDataJSONLDContentHTMLHeader">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETOntologyHTMLContent" unchecked></td>
	   		<td id=9>GETOntologyHTMLContent</td>
	   		<td>Obtener ontologia en HTML con .html, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadipedia.html>http://es.euskadi.eus:8008/def/euskadipedia.html</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="/static/GETtext_htmldef_euskadipedia_htmlGETOntologyHTMLContent">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETOntologyRDFXMLContentHTMLHeader" unchecked></td>
	   		<td id=10>GETOntologyRDFXMLContentHTMLHeader</td>
	   		<td>Obtener ontologia en OWL (por extension, no cabecera, cabecera HTML), parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadi.owl>http://es.euskadi.eus:8008/def/euskadi.owl</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="/static/GETtext_htmldef_euskadi_owlGETOntologyRDFXMLContentHTMLHeader">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETOntologyRDFXMLContentFileExtensionOWL " unchecked></td>
	   		<td id=11>GETOntologyRDFXMLContentFileExtensionOWL </td>
	   		<td>Obtener ontologia en RDF/XML con .owl, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadipedia.owl>http://es.euskadi.eus:8008/def/euskadipedia.owl</a></td>
	   		<td>GET</td>
	   		<td>application/rdf+xml</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="/static/GETapplication_rdf+xmldef_euskadipedia_owlGETOntologyRDFXMLContentFileExtensionOWL ">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETClassHTML200" unchecked></td>
	   		<td id=13>GETClassHTML200</td>
	   		<td>Obtener clase en HTML, sin parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadipedia/Hotel>http://es.euskadi.eus:8008/def/euskadipedia/Hotel</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmldef_euskadipedia_HotelGETClassHTML200">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETSPARQLHTML200" unchecked></td>
	   		<td id=14>GETSPARQLHTML200</td>
	   		<td>Ir directo a formulario SPARQL</td>
	   		<td><a href=http://es.euskadi.eus:8008/sparql>http://es.euskadi.eus:8008/sparql</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmlsparqlGETSPARQLHTML200">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceHTMLDoc" unchecked></td>
	   		<td id=17>GETResourceHTMLDoc</td>
	   		<td>Recurso con pagina fea</td>
	   		<td><a href=http://es.euskadi.eus:8008/id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02>http://es.euskadi.eus:8008/id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmlid_medio-ambiente_calidad-del-aire_elemento_CO-2017-01-02GETResourceHTMLDoc">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceHTMLDoc" unchecked></td>
	   		<td id=18>GETResourceHTMLDoc</td>
	   		<td>Recurso con pagina bonita que no existe todavia</td>
	   		<td><a href=http://es.euskadi.eus:8008/id/sector-publico/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22>http://es.euskadi.eus:8008/id/sector-publico/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="/static/GETtext_htmlid_sector-publico_contrato_asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22GETResourceHTMLDoc">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceDirectlyDoc" unchecked></td>
	   		<td id=19>GETResourceDirectlyDoc</td>
	   		<td>Recurso con pagina fea, ir directamente a pagina /doc sin pasar por /id</td>
	   		<td><a href=http://es.euskadi.eus:8008/doc/es/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02>http://es.euskadi.eus:8008/doc/es/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmldoc_es_medio-ambiente_calidad-del-aire_elemento_CO-2017-01-02GETResourceDirectlyDoc">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceHTMLPageNoRedirect303" unchecked></td>
	   		<td id=15>GETResourceHTMLPageNoRedirect303</td>
	   		<td>Recurso con pagina bonita, pero sin seguir el 303</td>
	   		<td><a href=http://es.euskadi.eus:8008/id/sector-publico/puestos-trabajo/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22>http://es.euskadi.eus:8008/id/sector-publico/puestos-trabajo/contrato/asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22</a></td>
	   		<td>GETNO303</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETNO303text_htmlid_sector-publico_puestos-trabajo_contrato_asesor-de-la-secretaria-general-de-presidencia-aldekoa-de-la-torre-jon-andoni-lehendakaritza-lehendakaritza-2016-06-22GETResourceHTMLPageNoRedirect303">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETResourceHTMLDocNoRedirect303" unchecked></td>
	   		<td id=16>GETResourceHTMLDocNoRedirect303</td>
	   		<td>Recurso con pagina fea, pero sin seguir 303</td>
	   		<td><a href=http://es.euskadi.eus:8008/id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02>http://es.euskadi.eus:8008/id/medio-ambiente/calidad-del-aire/elemento/CO-2017-01-02</a></td>
	   		<td>GETNO303</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETNO303text_htmlid_medio-ambiente_calidad-del-aire_elemento_CO-2017-01-02GETResourceHTMLDocNoRedirect303">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="SPARQLPOSTNamedGraphsMetadataCSVContent" unchecked></td>
	   		<td id=12>SPARQLPOSTNamedGraphsMetadataCSVContent</td>
	   		<td>Consulta sobre datos y metadatos, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/sparql>http://es.euskadi.eus:8008/sparql</a></td>
	   		<td>POST</td>
	   		<td>text/csv</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="/static/POSTtext_csvsparqlSPARQLPOSTNamedGraphsMetadataCSVContent">view response</a></td>
    	</tr>
    
    </tbody>
    
   </table>
   <input type="submit" value="Ejecutar">
   </form>

</body>
</html>
