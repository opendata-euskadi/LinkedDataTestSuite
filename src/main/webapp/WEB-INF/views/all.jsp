<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url var="logo" value="/resources/img/opendataeuskadi.jpg" />
<html>
<head>
<title>Menú</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<img class="img-responsive center-block" src="${logo}" alt="logo">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 text-center">
			<a class="btn btn-primary btn-sm glyphicon glyphicon-home"
				href="<c:url value='./'/>"></a>
		</div>
	</div>

	<br>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script>
		$(document).ready(
				function() {
					$('#checkall').click(
							function() {
								var checked = $(this).prop('checked');
								$('#checkboxes').find('input:checkbox').prop(
										'checked', checked);
							});
				})
	</script>

	<div class="row">
		<div class="col-xs-12 text-center">
			<input type="checkbox" id="checkall" checked> Seleccionar
			todos
		</div>
	</div>

	<c:choose>
		<c:when test="${not empty lista}">
			<div id="checkboxes">
				<form:form action="run" method="post">
					<c:forEach var="test" items="${lista}">
						<div class="col-xs-3">
							<input type="checkbox" name="test" value="${test}" checked>${test}<br>
						</div>
					</c:forEach>

					<br>

					<div class="row">
						<div class="col-xs-12 text-center">
							<button type="submit" class="btn btn-primary">Ejecutar
								test seleccionados</button>
						</div>
					</div>
				</form:form>
			</div>
		</c:when>
	</c:choose>

</body>
</html>