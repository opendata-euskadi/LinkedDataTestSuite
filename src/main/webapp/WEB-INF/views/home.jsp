<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url var="logo" value="/resources/img/opendataeuskadi.jpg"/>
<spring:url var="estilos" value="/resources/css/estilos.css"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset ="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Linked Data Test Suite</title>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>	
	<link rel="stylesheet" type="text/css" href="${estilos}">
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<img class="img-responsive center-block" src="${logo}" alt="logo">
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 text-center">
			<a class="btn btn-primary" href="<c:url value='/test'/>">Iniciar pruebas</a>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 text-center">
			<a class="btn btn-primary" href="<c:url value='/junit/'/>">Definir / Modificar Junit</a>
		</div>
	</div>
</body>
</html>