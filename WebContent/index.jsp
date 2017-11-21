<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
		//response.sendRedirect("/login.jsp");
	}
	session.setAttribute("pagina", "PROTORIPO SOFTWARE DE CARGA COMPARTIDA");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="/head.jsp" />
<title>PS3C</title>

</head>

<body>

	<!--  Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />

	<!-- Carrusel o pasador de imagenes    -->
	<div class="fondo">
		<br />

		<div class="container">

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="http://cemaplast.com/mantenimiento.jpg" style="width: 500px; height: 400px;">
				</div>

			</div>
			

		</div>
		<br />
		<jsp:include page="/footer.jsp" />

	</div>
	

</body>
</html>
