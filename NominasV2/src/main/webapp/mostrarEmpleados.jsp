<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<link href="styles/styles.css" rel="stylesheet" >
<title>MostrarEmpleados</title>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<nav class="navbar navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="#">Lista de Empleados</a> <a
			class="btn btn-outline-light" href="MainServlet?accion= ">Volver</a>
	</div>
</nav>
<div class="container mt-5">

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años Trabajados</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="empleado" items="${empleados}">
				<tr>
					<td>${empleado.dni}</td>
					<td>${empleado.nombre}</td>
					<td>${empleado.sexo}</td>
					<td>${empleado.categoria}</td>
					<td>${empleado.anyos}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</div>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>


