<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

table {
    border-collapse: collapse;
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

<form action="report">
    <input type="submit" value="Ver informe detallado" />
</form>

</body>
</html>