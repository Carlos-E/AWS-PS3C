<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	int i = 0;
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Seguimiento de Mercancía");
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
	height: 90vh;
	width: 90vw;
	margin: auto auto;
}

#embeded {
	height: 90vh;
	width: 90vw;
	margin: auto auto;
}
</style>

</head>

<body>

	<div class="row">
		<div class="col-md-12 col-lg-12">
			<!-- Container del mapa -->
			<div class="container">
				<div id="map" class="embed-responsive-item" style="border: 1px solid black"></div>
			</div>
		</div>
	</div>
	<!-- 
		<br>
		<div class="container">
			<button id="botonlocalizar" onclick="localizar()" class="btn btn-primary">Localizarte</button>
		</div>
	</div>
	 -->
	<div class="row">

		<div class="col-md-12 col-lg-12">
			<!-- Container del mapa -->
			<div class="container">
				<iframe id="embeded" width="600" height="450" frameborder="0" style="border: 0" src="https://www.google.com/maps/embed/v1/place?q=urbanizacion%20britania&key=AIzaSyCBKeFEezcbwUAD_pfyNBEXOn4xWuugg6M" allowfullscreen></iframe>
			</div>
		</div>

	</div>

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