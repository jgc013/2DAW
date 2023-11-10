<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<link href="styles/styles.css" rel="stylesheet">
<title>MostrarEmpleados</title>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="#">Menú Principal</a>
			<c:if test="${requestScope.login2}">
				<a class="btn btn-outline-light btn-success"
					href="MainServlet?accion2=iniciarSesion">Iniciar sesión</a>
			</c:if>

		</div>
	</nav>
	<div class="container mt-5">
		<ul class="list-group">
			<li class="list-group-item"><a
				href="MainServlet?accion=listarEmpleados" class="text-dark">Mostrar
					Empleados</a></li>
			<li class="list-group-item"><a
				href="MainServlet?accion=mostrarSalario" class="text-dark">Mostrar
					Salario</a></li>
			<li class="list-group-item"><a
				href="MainServlet?accion=editarEmpleado" class="text-dark">Modificar
					Empleado</a></li>
			<li class="list-group-item"><a
				href="MainServlet?accion=crearEmpleado" class="text-dark">Crear
					Empleado</a></li>
			<li class="list-group-item"><a
				href="MainServlet?accion=eliminarEmpleado" class="text-dark">Eliminar
					Empleado</a></li>
		</ul>
		<c:if test="${requestScope.noLogado}">
			<div class="mt-4 alert alert-danger">Necesitas iniciar sesion.</div>
		</c:if>
	</div>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>