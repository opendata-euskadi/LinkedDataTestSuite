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
		}
    </style>
  </head>

  <body>
    <h1>${title}</h1>

    <hr />


	<table>
   	<thead>
   		<th>Test name</th>
   		<th>Target Uri</th>
   		<th>HTTP Method</th>
   		<th>Accept Header</th>
   		<th>Status</th>
   		<th>Result file</th>
   	</thead>
   	<tbody>
   	
   	<#list tests as test>
   		<tr>
   		<td>${test.name}</td>
   		<td>${test.completeUri}</td>
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
