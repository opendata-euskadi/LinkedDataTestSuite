<!DOCTYPE html>
<html>

  <head>
    <title>${title}</title>
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
  
    <h1>${title}</h1>
    <h2>${dateTime}</h2>

    <hr/>
    
    <h3>Test realizados:</h3>
    
    POST:
    
    <#list tests as test>
    	<#if test.method == "POST">
	   		<ul>
    			<li>
    				<a href=#${test.testIndex}>${test.name}</a>
    			</li>
    		</ul>
	   	</#if>
    </#list>
    
    GET:
    
    <#list tests as test>
    	<#if test.method == "GET" || test.method == "GETNO303" >
	   		<ul>
    			<li>
    				<a href=#${test.testIndex}>${test.name}</a>
    			</li>
    		</ul>
	   	</#if>
    </#list>

	<table>
	   	<thead>
	   		<th>Test name</th>
	   		<th>Test comment</th>
	   		<th>Target Uri</th>
	   		<th>HTTP Method</th>
	   		<th>Accept Header</th>
	   		<th>Status</th>
	   		<th>Result file</th>
	   	</thead>
   	<tbody>
   	
   	<#list tests as test>
   		<tr>
	   		<td id=${test.testIndex}>${test.name}</td>
	   		<td>${test.comment}</td>
	   		<td><a href=${test.completeUri}>LINK</a></td>
	   		<td>${test.method}</td>
	   		<td>${test.accept}</td>
	   		<#if test.status == 0>
	   			<td style="color:green;">OK</td>
	   		</#if>
	   		<#if test.status == 1>
	   			<td style="color:red;">ERROR</td>
	   		</#if>
	   		<td><a href="${test.testName}">view response</a></td>
    	</tr>
    </#list>
    
    </tbody>
    
   </table>

</body>
</html>
