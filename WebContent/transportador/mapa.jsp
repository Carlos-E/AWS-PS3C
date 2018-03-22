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

<!--  HEAD -->
<jsp:include page="/head.jsp" />
<!--  ./HEAD -->

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
}

.floating-panel {
	position: absolute;
	z-index: 5;
	text-align: right;
	line-height: 15px;
}

.row {
	padding-top: 0.5rem;
}

.card {
	width: fit-content;
}

.card-header {
	padding: 0rem;
	margin: 0;
}

.card-block {
	padding: 0.5rem;
	margin: 0;
}

p {
	padding: 0;
	margin: 0;
}
</style>
</head>
<body>

	<div class="floating-panel" style="bottom: 2rem; left: 0.5rem;">

		<div class="row">
			<div class="col">
				<div class="card">

					<div class="card-block">
						<select id="envios">
							<option value=""></option>
						</select>
						<span></span>
					</div>

				</div>
			</div>
		</div>

		<div class="row">
			<div class="col">

				<div class="card">

					<div class="card-block">

						<div class="row">
							<div class="col">
								<label for="recogido">Recogido:</label>
								<input type="checkbox" value="Recogido" id="recogido">
							</div>

						</div>

						<div class="row">
							<div class="col">
								<label for="entregado">Entregado:</label>
								<input type="checkbox" value="Entregado" id="entregado">
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>

		<div class="row">
			<div class="col">
				<div class="card">

					<div class="card-block">
						<label id="coords"> </label>
					</div>

				</div>
			</div>
		</div>

	</div>

	<select id="start" hidden>
		<option value="8.772299, -75.861037">Puche</option>
	</select>
	<select id="end" hidden>
		<option value="10.3904916,-75.5014576">Carlos</option>
	</select>

	<div id="map"></div>

	<script type="text/javascript">
		var BestIsOn = true;
		var GPSIsOn = false;
		var networkIsOn = false;

		Android.toggleBestUpdates(BestIsOn);
		Android.toggleGPSUpdates(GPSIsOn);
		Android.toggleNetworkUpdates(networkIsOn);

		setInterval(function() {

			document.getElementById('coords').innerHTML = Android
					.getBestLocation()
		}, 5000);
	</script>

	<script>
		function initMap() {

			var directionsService = new google.maps.DirectionsService;
			var directionsDisplay = new google.maps.DirectionsRenderer;

			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 13,
				center : {
					lat : 10.4049383,
					lng : -75.497829
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

			setTimeout(function() {
				calculateAndDisplayRoute(directionsService, directionsDisplay);
			}, 2000);
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
					Android.showToast('Directions request failed due to '
							+ status);
				}
			});
		}
	</script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&callback=initMap">
		
	</script>
</body>
</html>