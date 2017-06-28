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
    <h2>28-jun-2017 10:26:04</h2>

    <hr/>
    
    <h3>Test realizados:</h3>
    
    POST:
    
    <br/>
    GET:
    

    				[<a href=#4>GETPropertyHTML200</a>]


    				[<a href=#5>GETOntologyHTMLContent</a>]


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
   			<td><input type="checkbox" name="test" value="GETPropertyHTML200" unchecked></td>
	   		<td id=4>GETPropertyHTML200</td>
	   		<td>Obtener propiedad en HTML, sin parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadipedia/precio>http://es.euskadi.eus:8008/def/euskadipedia/precio</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:green;">OK</td>
	   		<td><a href="/static/GETtext_htmldef_euskadipedia_precioGETPropertyHTML200">view response</a></td>
    	</tr>
   		<tr>
   			<td><input type="checkbox" name="test" value="GETOntologyHTMLContent" unchecked></td>
	   		<td id=5>GETOntologyHTMLContent</td>
	   		<td>Obtener ontologia en HTML, parsear contenido</td>
	   		<td><a href=http://es.euskadi.eus:8008/def/euskadipedia>http://es.euskadi.eus:8008/def/euskadipedia</a></td>
	   		<td>GET</td>
	   		<td>text/html</td>
	   			<td style="color:red;">ERROR</td>
	   		<td><a href="/static/GETtext_htmldef_euskadipediaGETOntologyHTMLContent">view response</a></td>
    	</tr>
    
    </tbody>
    
   </table>
   <input type="submit" value="Ejecutar">
   </form>

</body>
</html>
