<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	padding-bottom: 40.00%;
}
</style>

</head>

<body class="fondo">


	<!-- Header  -->
	<!--  Container de la Barra de navegacion -->
	
	<!-- Container del mapa -->
	
	<div >
	<jsp:include page="/head.jsp" />
	<jsp:include page="/header.jsp" />
	<jsp:include page="/navbar.jsp" />
	
		<div class="container">
			<div class="embed-responsive embed-responsive-16by9">
				<div id="map" class="embed-responsive-item"
					style="border: 1px solid black"></div>
			</div>

		</div>

		<br>

		<!-- Container de los botones -->
		<div class="container">

			<button id="botonlocalizar" onclick="localizar()"
				class="btn btn-primary">Localizarte</button>

		</div>
		<div class="container">

			<button id="botonlocalizar" onclick="ponerMarcadores()"
				class="btn btn-primary">Marcadores</button>

		</div>
		
		<!-- JavaScript del mapa -->
		<script>
			$.getJSON('/mapeoDeMercancia', function(data) {
				for (var i = 0, len = data.length; i < len; i++) {
					console.log(data[i]);
				}
				ponerMarcadores(data);
			});
			setInterval(function() {
				$.getJSON('/mapeoDeMercancia', function(data) {
					for (var i = 0, len = data.length; i < len; i++) {
						console.log(data[i]);
					}
					ponerMarcadores(data);
				});
			}, 5000);
		</script>

		<script src="/js/map.js">
			
		</script>

		<script async defer
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwUOXR0TZ7pyQhLJAuA6_U6Ffg92YMkLk&callback=initMap">
			
		</script>

	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>