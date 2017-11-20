<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>PS3C</title>
<!-- Comment -->
<jsp:include page="/head.jsp" />
</head>
<body>
	<!--  Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />
	<!-- Carrusel o pasador de imagenes    -->
	<div class="fondo"><br><br>	
		<div class="container">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
				</ol>
 
				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="http://cemaplast.com/mantenimiento.jpg"
							style="width: 500px; height: 400px;">
					</div>

					<div class="item">
						<img src="http://www.generadormemes.com/media/created/2rc2nn.jpg"
							style="width: 500px; height: 400px;">
					</div>

				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>						
		</div>
		<jsp:include page="/footer.jsp" />
	</div>
	
</body>
</html>
