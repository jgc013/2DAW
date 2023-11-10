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
		
		
		
			<a class="navbar-brand" href="#">Buscar Empleado a Editar</a> 
			
			
			
			<a class="btn btn-outline-light" href="MainServlet?accion= ">Volver</a>
		</div>
	</nav>
	<div class="container mt-5">
		<form
			action="MainServlet?opcion=noEncontrado&accion=editarEmpleado&accion2=na"
			method="post">
			<div class="mb-3">
				<label for="dni" class="form-label">DNI del Empleado:</label> <input
					type="text" id="dni" name="dni" class="form-control">
				<c:if test="${not empty requestScope.errorDNI}">
					<div class="text-danger">${requestScope.errorDNI}</div>
				</c:if>
			</div>
			<button type="submit" class="btn btn-primary">Buscar
				Empleado</button>
		</form>

		<c:if test="${requestScope.actualizacionExitosa}">
			<div class="mt-4 alert alert-success">Empleado editado con
				éxito.</div>
		</c:if>
	</div>

	<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>