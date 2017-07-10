<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url var="logo" value="/resources/img/opendataeuskadi.jpg"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultados</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<style type="text/css">

table {
    border-collapse: collapse;
    width: 30%;
    margin: 0px auto;
    float: none;
}

table, th, td {
    border: 1px solid black;
}
th {
    text-align: left;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<img class="img-responsive center-block" src="${logo}" alt="logo">
		</div>
	</div>

<table>
	<caption>Resultados</caption>
		<thead>
			<tr>
				<th>Tipo</th>
				<th>Registro</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Nº de test ejecutados</td>
				<td>${nTest}</td>
			</tr>
			<tr>
				<td>Fallos registrados</td>
				<td>${fails}</td>
			</tr>
			<tr>
				<td>Tiempo de ejecución</td>
				<td>${runTime}</td>
			</tr>
		</tbody>
</table>

<br>

<div class="row">
	<div class="col-xs-12 text-center">
		<a class="btn btn-primary" href="<c:url value='/report'/>">Ver informe detallado</a>
	</div>
</div>


</body>
</html>