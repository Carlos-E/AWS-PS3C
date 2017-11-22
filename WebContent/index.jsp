<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
		//response.sendRedirect("/login.jsp");
	}
	session.setAttribute("pagina", "PROTORIPO SOFTWARE DE CARGA COMPARTIDA");
%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="/head.jsp" />
<title>PS3C</title>

</head>

<body class="fondo">

	<div>

		<!-- Header -->
		<div class="container-fluid">
			<jsp:include page="/header.jsp" />
		</div>

		<!--  Barra de navegacion -->
		<div class="container-fluid">
			<jsp:include page="/navbar.jsp" />
		</div>


		<!-- Carrusel o pasador de imagenes    -->
		<br />

		<div class="container">

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="http://ns2.delva.cl/sitiomantenimiento.png" style="width: 500px; height: 400px;">
				</div>

			</div>

		</div>

		<br />

		<jsp:include page="/footer.jsp" />

	</div>

</body>
</html>
