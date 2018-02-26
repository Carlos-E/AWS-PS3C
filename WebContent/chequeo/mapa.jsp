<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	int i = 0;
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Mapeo de Mercancía");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>

<jsp:include page="/head.jsp" />
<title>Mapa</title>

<style type="text/css">
html, body {
	/* 	height: 100%;
 */
	margin: 0;
	padding: 0;
}

#map {
	height: 70vh;
	width: 80vw;
}

</style>

</head>

<body>

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <section class="row">
	<div class="col-md-12 col-lg-12">
		<!-- Container del mapa -->
		<div class="embed-responsive embed-responsive-16by9">
			<div id="map" class="embed-responsive-item" style="border: 1px solid black"></div>
		</div>
	</div>

	<!-- 
		<br>
		<div class="container">
			<button id="botonlocalizar" onclick="localizar()" class="btn btn-primary">Localizarte</button>
		</div>
	</div>
	 --> </section> </main>

	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />

	<!-- JavaScript del mapa -->
	<script>
		$.getJSON('/mapeoDeMercancia', function(data) {
			for (var i = 0; i < data.length; i++) {
				console.log(data[i]);
			}
			ponerMarcadores(data);
		});
		setInterval(function() {
			$.getJSON('/mapeoDeMercancia', function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
				}
				ponerMarcadores(data);
			});
		}, 5000);
	</script>

	<script src="/js/map.js">
		
	</script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwUOXR0TZ7pyQhLJAuA6_U6Ffg92YMkLk&callback=initMap">
		
	</script>

</body>
</html>