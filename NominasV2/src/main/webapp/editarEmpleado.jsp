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
			<a class="navbar-brand" href="#">Editar Empleado</a> <a
				class="btn btn-outline-light"
				href="MainServlet?accion=editarEmpleado">Volver</a>
		</div>
	</nav>
	<div class="container mt-5">
		<form action="MainServlet?opcion=encontrado&accion=editarEmpleado"
			method="post">
			<div class="mb-3">
				<label for="dni" class="form-label">DNI:</label> <input type="text"
					class="form-control" name="dni"
					value="${empty param.dni ? empleado.dni : param.dni}">
				<c:if test="${not empty requestScope.errorDNI}">
					<div class="text-danger">${requestScope.errorDNI}</div>
				</c:if>
			</div>
			<div class="mb-3">
				<label for="nombre" class="form-label">Nombre:</label> <input
					type="text" class="form-control" id="nombre" name="nombre"
					value="${empty param.nombre ? empleado.nombre : param.nombre}">
				<c:if test="${not empty requestScope.errorNombre}">
					<div class="text-danger">${requestScope.errorNombre}</div>
				</c:if>
			</div>
			<div class="mb-3">
				<label for="sexo" class="form-label">Sexo:</label> <input
					type="text" class="form-control" id="sexo" name="sexo"
					value="${empty param.sexo ? empleado.sexo : param.sexo}">
				<c:if test="${not empty requestScope.errorSexo}">
					<div class="text-danger">${requestScope.errorSexo}</div>
				</c:if>
			</div>
			<div class="mb-3">
				<label for="categoria" class="form-label">Categoría:</label> <input
					type="number" class="form-control" id="categoria" name="categoria"
					value="${empty param.categoria ? empleado.categoria : param.categoria}">
				<c:if test="${not empty requestScope.errorCategoria}">
					<div class="text-danger">${requestScope.errorCategoria}</div>
				</c:if>
			</div>
			<div class="mb-3">
				<label for="anyos" class="form-label">Años Trabajados:</label> <input
					type="number" class="form-control" id="anyos" name="anyos"
					value="${empty param.anyos ? empleado.anyos : param.anyos}">
				<c:if test="${not empty requestScope.errorAnyos}">
					<div class="text-danger">${requestScope.errorAnyos}</div>
				</c:if>
			</div>
			<button type="submit" class="btn btn-primary">Actualizar</button>
		</form>
	</div>

	<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>