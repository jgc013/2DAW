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

	<%
	if (request.getAttribute("contenido") == null || ((String) request.getAttribute("contenido")).isEmpty()) {
	%>
	<jsp:include page="/menu.jsp" />
	<%
	} else {
	%>
	<jsp:include page='<%=(String) request.getAttribute("contenido")%>' />
	<%
	}
	%>




</body>
</html>
