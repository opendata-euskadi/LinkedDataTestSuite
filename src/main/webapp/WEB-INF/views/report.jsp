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
    <h2>22-jun-2017 09:54:26</h2>

    <hr/>
    
    <h3>Test realizados:</h3>
    
    POST:
    
    <br/>
    GET:
    

    				[<a href=#0>GETResourceDirectlyDataRDFXMLContent</a>]


    				[<a href=#1>GETResourceDirectlyDataTTLContent</a>]


    				[<a href=#2>GETResourceDirectlyDataJSONLDContent</a>]


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
	   		<td id=0>GETResourceDirectlyDataRDFXMLContent</td>
	   		<td>Obtener recurso en RDF/XML directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09</a></td>
	   		<td>GET</td>
	   		<td>application/rdf+xml</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="GETapplication_rdf+xmldata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09GETResourceDirectlyDataRDFXMLContent">view response</a></td>
    	</tr>
   		<tr>
	   		<td id=1>GETResourceDirectlyDataTTLContent</td>
	   		<td>Obtener recurso en TTL directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09</a></td>
	   		<td>GET</td>
	   		<td>text/turtle</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="GETtext_turtledata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09GETResourceDirectlyDataTTLContent">view response</a></td>
    	</tr>
   		<tr>
	   		<td id=2>GETResourceDirectlyDataJSONLDContent</td>
	   		<td>Obtener recurso en JSONLD directamente de /data, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09>http://es.euskadi.eus:8008/data/sector-publico/puestos-trabajo/contrato/1-gobierno-vasco-donostia-easo-10-3024.0-2016-05-09</a></td>
	   		<td>GET</td>
	   		<td>application/ld+json</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="GETapplication_ld+jsondata_sector-publico_puestos-trabajo_contrato_1-gobierno-vasco-donostia-easo-10-3024_0-2016-05-09GETResourceDirectlyDataJSONLDContent">view response</a></td>
    	</tr>
    
    </tbody>
    
   </table>

</body>
</html>
