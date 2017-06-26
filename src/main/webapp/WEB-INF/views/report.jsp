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
    <h2>26-jun-2017 10:26:28</h2>

    <hr/>
    
    <h3>Test realizados:</h3>
    
    POST:
    
    <br/>
    GET:
    

    				[<a href=#1>GETResourceDirectlyDataJSONLDContentHTMLHeader</a>]


    				[<a href=#1>GETOntologyHTMLContent</a>]


    				[<a href=#2>GETOntologyRDFXMLContentHTMLHeader</a>]


	<table>
	   	<thead>
	   	<tr>
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
   	
   		<tr>
	   		<td id=1>GETResourceDirectlyDataJSONLDContentHTMLHeader</td>
	   		<td>Obtener recurso en JSON-LD (por extension, no cabecera, cabecera HTML) directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.jsonld>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09.jsonld</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="GETtext_htmldata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09_jsonldGETResourceDirectlyDataJSONLDContentHTMLHeader">view response</a></td>
    	</tr>
   		<tr>
	   		<td id=1>GETOntologyHTMLContent</td>
	   		<td>Obtener ontologia en HTML con .html, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadipedia.html>http://es.euskadi.eus:8008/def/euskadipedia.html</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="GETtext_htmldef_euskadipedia_htmlGETOntologyHTMLContent">view response</a></td>
    	</tr>
   		<tr>
	   		<td id=2>GETOntologyRDFXMLContentHTMLHeader</td>
	   		<td>Obtener ontologia en OWL (por extension, no cabecera, cabecera HTML), parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadi.owl>http://es.euskadi.eus:8008/def/euskadi.owl</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="GETtext_htmldef_euskadi_owlGETOntologyRDFXMLContentHTMLHeader">view response</a></td>
    	</tr>
    
    </tbody>
    
   </table>

</body>
</html>
