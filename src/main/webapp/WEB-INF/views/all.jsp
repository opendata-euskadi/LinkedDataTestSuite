<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<c:choose>
<c:when test="${not empty lista}">
<form:form action="execute" method="post">
<c:forEach var="test" items="${lista}">
  <input type="checkbox" name="test" value="${test}" checked>${test}<br>
</c:forEach>
  <input type="submit" value="Iniciar">
</form:form>
</c:when>
</c:choose>

<!--  
<c:choose>
	<c:when test="${not empty lista}">
		<c:forEach var="test" items="${lista}">
			<div>					
				<p>${test}</p>				
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>No hay test</p>
	</c:otherwise>
</c:choose>
--> 

</body>
</html>