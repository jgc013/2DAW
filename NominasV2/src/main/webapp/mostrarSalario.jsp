
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<link href="styles/styles.css" rel="stylesheet">
<title>Menú Principal</title>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<nav class="navbar navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="#">Mostrar Salario</a> <a
				class="btn btn-outline-light" href="MainServlet?accion=mostrarSalario">Volver</a>
		</div>
	</nav>

	<div class="container" id="container">
		<h1 class="my-4">Salario del Empleado</h1>
		<div class="alert alert-info" role="alert">
			El salario del empleado es:
			<%=request.getAttribute("salario")%>
		</div>
	</div>

	<script src="resources/js/bootstrap.min.js"></script>

</body>

</html>