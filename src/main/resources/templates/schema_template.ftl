<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
    		background-color: rgb(38, 90, 136);
    		color: #fff;
    		text-align: center;
		}
		
		ul {
  			list-style-type: none;
  		}
  		
    </style>
  </head>

  <body class="container-fluid">
  <header class="col-xs-12 text-center">
    <h1>${title}</h1>
    <h2>${dateTime}</h2>
  </header>

    <hr/>
    
    <div class="row">
		<div class="col-xs-12 text-center">
			<a class="btn btn-primary btn-sm glyphicon glyphicon-home" href="<c:url value='./'/>"></a>
		</div>
	</div>
    
    <h3>Test realizados:</h3>
    
    POST:
    
    <#list tests as test>
    	<#if test.method == "POST">

    				[<a href=#${test.testIndex}>${test.name}</a>]

	   	</#if>
    </#list>
    <br/>
    GET:
    
    <#list tests as test>
    	<#if test.method == "GET" || test.method == "GETNO303" >

    				[<a href=#${test.testIndex}>${test.name}</a>]

	   	</#if>
    </#list>

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
   	<#list tests as test>
   		<tr>
   			<td><input type="checkbox" name="test" value="${test.name}" unchecked></td>
	   		<td id=${test.testIndex}>${test.name}</td>
	   		<td>${test.comment}</td>
	   		<td><a href=${test.completeUri}>${test.completeUri}</a></td>
	   		<td>${test.method}</td>
	   		<td>${test.accept}</td>
	   		<#if test.status == 0>
	   			<td style="color:green; text-align: center;"><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span></td>
	   		</#if>
	   		<#if test.status == 1>
	   			<td style="color:red; text-align: center;"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></td>
	   		</#if>
	   		<td style="text-align: center;"><a style="color:black;" href="/static/${test.testName}"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a></td>
    	</tr>
    </#list>
    
    </tbody>
    
   </table>
   <br>
   <div class="form-group text-center">
   <button type="submit" class="btn btn-primary">Ejecutar</button>
   </div>

   </form>
   
</body>
</html>
