<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	int i = 0;
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Mapeo de Mercancía");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/head.jsp" />
<title>Mapa</title>

<jsp:include page="/head.jsp" />

<style type="text/css">
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map {
	height: 100%;
}

/*Propiedad de bootstrap sobreescrita aqui para la dimension del mapa*/
.embed-responsive-16by9 {
	padding-bottom: 35.00%;
}
</style>

</head>

<body class="fondo">

	<!-- Header  -->
	<div class="container-fluid">
		<jsp:include page="/header.jsp" />
	</div>

	<!--  Barra de navegacion -->
	<div class="container-fluid">
		<jsp:include page="/navbar.jsp" />
	</div>


	<!-- Container del mapa -->
	<div class="container-fluid">

		<div class="container-fluid">
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
	
	 -->

	<!-- Footer -->
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