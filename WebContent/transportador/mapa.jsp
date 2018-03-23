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

<title>Rutas</title>
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
}

.row {
	padding: 0.1rem;
	padding-top: 0.25rem;
}

.card {
	height: fit-content;
	width: fit-content;
	/* max-width: 50vw; */
}

.card-header {
	padding: 0rem;
	margin: 0;
}

.card-block {
	padding: 0.1rem;
	margin: 0;
}

p {
	padding: 0;
	margin: 0;
}
</style>

<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js" integrity="sha384-SlE991lGASHoBfWbelyBPLsUlwY1GwNDJo3jSJO04KZ33K2bwfV9YBauFfnzvynJ" crossorigin="anonymous"></script>

</head>
<body>

	<div class="floating-panel" style="top: 0.25rem; right: 0.5rem;">
		<div class="row">
			<div class="col">
<!-- 				<a class="btn btn-light" href="https://www.google.com/maps/dir/?api=1&destination="+destination+"&travelmode=driving&dir_action=navigate" onclick="Android.openOnGoogleMaps(document.getElementById('end').value)">Abrir en Google Maps</a>
 -->				<a class="btn btn-light" href="https://www.google.com/maps/dir/?api=1&destination=10.4227348,-75.5529205&travelmode=driving&dir_action=navigate">Abrir en Google Maps</a>
			</div>
		</div>

		<div class="row">
			<div class="col">
				<button class="btn btn-light" id="sync">
					<i class="fa fa-sync-alt"></i>
				</button>
			</div>
		</div>
	</div>


	<div class="floating-panel" style="bottom: 2rem; left: 0.5rem;">

		<div class="row">
			<div class="col">
				<div class="card">

					<div class="card-block">
						<span for="recogido">Env&iacute;os:</span>

						<select id="envios">
							<option value="">...</option>
							<option value="1">1..</option>
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
								<span for="recogido">Recogido:</span>
								<input type="checkbox" value="Recogido" id="recogido">
							</div>

						</div>

						<div class="row">
							<div class="col">
								<span for="entregado">Entregado:</span>
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
						Lat:
						<span id="lat">10.4001778</span>
						<br>
						Lng:
						<span id="lng">-75.5657678</span>
					</div>

				</div>
			</div>
		</div>

	</div>

	<!-- <input id="start" value="8.772299, -75.861037" hidden> -->
	<input id="end" value="10.4233996,-75.5554203" hidden>

	<div id="map"></div>

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

			var onEventHandler = function() {
				calculateAndDisplayRoute(directionsService, directionsDisplay);
			};

			document.getElementById('envios').addEventListener('change',
					onEventHandler);
			document.getElementById('sync').addEventListener('click',
					onEventHandler);

			function recalculate() {
				calculateAndDisplayRoute(directionsService, directionsDisplay);
			}

			setTimeout(function() {
				calculateAndDisplayRoute(directionsService, directionsDisplay);
			}, 2000);
		}

		function calculateAndDisplayRoute(directionsService, directionsDisplay) {
			directionsService.route({
				origin : document.getElementById('lat').innerHTML + ","
						+ document.getElementById('lng').innerHTML,
				destination : document.getElementById('end').value,
				travelMode : 'DRIVING'
			}, function(response, status) {
				if (status === 'OK') {
					directionsDisplay.setDirections(response);
					console.log(JSON.stringify(response, null, 2));
				} else {
					Android.showToast('Directions request failed due to '
							+ status);
				}
			});
		}
		
		var BestIsOn = true;
		var GPSIsOn = false;
		var networkIsOn = false;

		Android.toggleBestUpdates(BestIsOn);
		Android.toggleGPSUpdates(GPSIsOn);
		Android.toggleNetworkUpdates(networkIsOn);

		setInterval(function() {

			var coords = Android.getBestLocation();

			document.getElementById('lat').innerHTML = coords.split(",")[0];
			document.getElementById('lng').innerHTML = coords.split(",")[1];

		}, 1000);
	</script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&callback=initMap">
		
	</script>
</body>
</html>