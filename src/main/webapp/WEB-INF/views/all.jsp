<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Menú</title>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
$(document).ready(function() {
  $('#checkall').click(function() {
    var checked = $(this).prop('checked');
    $('#checkboxes').find('input:checkbox').prop('checked', checked);
  });
})
</script>

<input type="checkbox" id="checkall" checked>
<c:choose>
	<c:when test="${not empty lista}">
		<div id="checkboxes">
			<form:form action="execute" method="post">
				<c:forEach var="test" items="${lista}">
	  				<input type="checkbox" name="test" value="${test}" checked>${test}<br>
				</c:forEach>
	  		<input type="submit" value="Iniciar">
			</form:form>
		</div>
	</c:when>
</c:choose>

</body>
</html>