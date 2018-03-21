<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	int i = 0;
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Seguimiento de Mercancía");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<title>Prueba Rutas</title>
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	font-size: 1rem;
}

#floating-panel {
	font-size: 1rem;
	position: absolute;
	bottom: 2rem;
	left: 1rem;
	max-width: 50%;
	z-index: 5;
	background-color: #fff;
	padding: 0.5rem;
	border: 1px solid #999;
	text-align: center;
	font-family: 'Roboto', 'sans-serif';
	line-height: 30px;
	padding-left: 10px;
}
</style>
</head>
<body>
	<div id="floating-panel">
		<b>Origen: </b>
		<select id="start">
			<option value="8.772299, -75.861037">Puche</option>
			<option value="barranquilla, atlantico">Barranquilla</option>
		</select>
		<b>Destino: </b>
		<select id="end">
			<option value="santamarta, magdalena">Santamarta</option>
			<option value="10.3904916,-75.5014576">Carlos</option>
		</select>
	</div>


	<div id="map"></div>


	<script>
		function initMap() {
			var directionsService = new google.maps.DirectionsService;
			var directionsDisplay = new google.maps.DirectionsRenderer;
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 16,
				center : {
					lat : 10.4002813,
					lng : -75.5435451
				}
			});
			directionsDisplay.setMap(map);

			var onChangeHandler = function() {
				calculateAndDisplayRoute(directionsService, directionsDisplay);
			};
			document.getElementById('start').addEventListener('change',
					onChangeHandler);
			document.getElementById('end').addEventListener('change',
					onChangeHandler);
		}

		function calculateAndDisplayRoute(directionsService, directionsDisplay) {
			directionsService.route({
				origin : document.getElementById('start').value,
				destination : document.getElementById('end').value,
				travelMode : 'DRIVING'
			}, function(response, status) {
				if (status === 'OK') {
					directionsDisplay.setDirections(response);
				} else {
					window.alert('Directions request failed due to ' + status);
				}
			});
		}
	</script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&callback=initMap">
		
	</script>
</body>
</html>