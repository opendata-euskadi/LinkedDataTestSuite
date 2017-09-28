<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url var="logo" value="/resources/img/opendataeuskadi.jpg" />
<spring:url var="gestor" value="/resources/js/GestorJunitEditor.js" />
<spring:url var="estilos" value="/resources/css/estilos.css" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<!-- JavaScript -->
<script src="//cdn.jsdelivr.net/alertifyjs/1.10.0/alertify.min.js"></script>
<!-- CSS -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/alertify.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- Default theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/default.min.css" />
<!-- Semantic UI theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/semantic.min.css" />
<!-- Bootstrap theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/bootstrap.min.css" />
<!--RTL version-->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/alertify.rtl.min.css" />
<!-- Default theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/default.rtl.min.css" />
<!-- Semantic UI theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/semantic.rtl.min.css" />
<!-- Bootstrap theme -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/bootstrap.rtl.min.css" />
<!-- Fuente -->
<link href="https://fonts.googleapis.com/css?family=Bellefair"
	rel="stylesheet">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${estilos}">
<script src="${gestor}"></script>
<title>LinkedDataTestSuite</title>
</head>
<body>
	<div class="blended_grid">
		<div class="pageHeader">
			<nav class="navbar navbar-light bg-faded">
				<a class="navbar-brand" href="#"> <img src="${logo}" width="162"
					height="142" alt="">
				</a>
			</nav>
		</div>
		<div class="pageContent">
			<c:url value="/junit/procesarFormulario" var="destino" />
			<form:form method="POST" action="${destino}" commandName="junit"
				class="form-group">
				Nombre: <form:input path="comentario" type="text"
					class="form-control" /> 
				Comentario:<form:input path="comentario" type="text"
					class="form-control" /> 
				Base Uri: <form:input path="baseUri" type="text"
					class="form-control" /> 
				Path Uri: <form:input path="pathUri" type="text"
					class="form-control" /> 
				Method: <form:select path="method" class="form-control"
					id="exampleFormControlSelect1">
					<form:option value="-">-</form:option>
					<form:option value="GET">GET</form:option>
					<form:option value="POST">POST</form:option>
					<form:option value="GETN0303">GETNO303</form:option>
				</form:select> 
				Accept: 
					<form:select path="accept" class="selectpicker form-control"
					id="exampleFormControlSelect2">
					<form:option value="-">-</form:option>
					<form:option value="CSV">CSV</form:option>
					<form:option value="RDFXML">RDF/XML</form:option>
					<form:option value="Turtle">Turtle</form:option>
					<form:option value="TriX">TriX</form:option>
					<form:option value="TriG">TriG</form:option>
					<form:option value="JSONLD">JSON/LD</form:option>
					<form:option value="NQUADS">NQuads</form:option>
					<form:option value="NTriples">NTriples</form:option>
					<form:option value="N3">N3</form:option>
					<form:option value="RDFJSON">RDF/JSON</form:option>
					<form:option value="BinaryRDF">BinaryRDF</form:option>
					<form:option value="SPARQLXMLResultsFormat">SPARQLXMLResultsFormat</form:option>
					<form:option value="SPARQLJSONResultsFormat">SPARQLJSONResultsFormat</form:option>
					<form:option value="SPARQLBooleanResults">SPARQLBooleanResults</form:option>
					<form:option value="SPARQLBinaryResults">SPARQLBinaryResults</form:option>
					<form:option value="TABVAL">TABVAL</form:option>
					<form:option value="HTML">HTML</form:option>
				</form:select> Añadir prueba:
				<div id="Pruebas">
					<div class="PruebasCategorias" id="pruebasSelectorTipoPrueba1">
						<%-- 					<form:select path="tipoPrueba" class="selectpicker form-control"
							id="1" onchange="setTipoAssert(id)">
							<form:option value="-">-</form:option>
							<form:option value="Equals">Equals</form:option>
							<form:option value="NotEquals">Not Equals</form:option>
							<form:option value="AssertTrue">AssertTrue</form:option>
							<form:option value="AssertFalse">AssertFalse</form:option>
						</form:select>  <form:select path="objetoPrueba" class="selectpicker form-control"
							id="pruebaSelectorArgumento1">
							<form:option value="-">-</form:option>
							<form:option value="Equals" disabled="true">requestBean.getStatus()</form:option>
							<form:option value="Assert" disabled="true">requestBean.getLocation()</form:option>
							<form:option value="Assert" disabled="true">requestBean.getResponseString()</form:option>
						</form:select>
						<!--  <input type="text" class="form-control"
								placeholder="Introduce valor parametro"> --> --%>
					</div>
				</div>
				<div id="botonAnadirPruebas">
					<button type="button" class="btn btn-primary btn-circle"
						onclick="anadirPrueba()">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
				<%-- <div id="Parametros">
					Añadir parámetro:
					<div id="parametro1" class="parametro"
						style="display: inline-flex;">
						<form:input path="idParametro" type="text" class="form-control"
							placeholder="Introduce su nombre" />
						<form:input path="valorParametro" type="text" class="form-control"
							placeholder="Introduce su valor" />
					</div> 
				</div>
				<div id="botonAnadirParametros">
					<button type="button" class="btn btn-primary btn-circle"
						onclick="anadirParametro()">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>--%>
				<div class="text-center">
					<button type="submit" class="btn btn-primary btn-xs">
						Guardar <span class="glyphicon glyphicon-floppy-disk"></span>
					</button>
				</div>
			</form:form>
		</div>
	</div>
	</div>
</body>
</html>