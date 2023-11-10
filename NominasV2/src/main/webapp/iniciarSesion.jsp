<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<link href="styles/styles.css" rel="stylesheet">
<title>Iniciar Sesión</title>

<link href="resources/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
	<nav class="navbar navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="#">Iniciar Sesión</a>
			<a class="btn btn-outline-light" href="MainServlet?accion= ">Volver</a>
		</div>
	</nav>

	<div class="container mt-5">
		<form action="MainServlet?accion2=iniciarSesion" method="post">
			<div class="mb-3">
				<label for="usuario" class="form-label">Usuario:</label> <input
					type="text" id="usuario" name="usuario" class="form-control">
			</div>
			<div class="mb-3">
				<label for="contrasena" class="form-label">Contraseña:</label> <input
					type="password" id="contrasena" name="contrasena" class="form-control">
			</div>
			<button type="submit" class="btn btn-primary">Iniciar Sesión</button>
		</form>

		<c:if test="${requestScope.errorLogin}">
			<div class="mt-4 alert alert-danger">El usuario no existe</div>
		</c:if>
		<c:if test="${requestScope.errorLogin2}">
			<div class="mt-4 alert alert-danger">El usuario y la contraseña no coinciden.</div>
		</c:if>
		
		<c:if test="${requestScope.login}">
			<div class="mt-4 alert alert-success" id="sesionIniciada">Sesión Iniciada. Tienes 30 minutos de sesion</div>
		</c:if>
		
		
	</div>

	<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>
