<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	int i = 0;
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Seguimiento de mercancía");
%>
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">

<!--  HEAD -->
<jsp:include page="/movil/head.jsp" />
<!--  ./HEAD -->

<title>Rutas</title>
<style>
/* Always set the map height explicitly to define the size of the div element that contains the map. */
#map {
	height: 100%;
	margin: 0;
	padding: 0;
	overflow: hidden !important;
}

/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	overflow: hidden !important;
}

.floating-panel {
	position: absolute;
	z-index: 5;
	text-align: right;
	font-size: 0.9rem !important;
}

.row {
	padding-top: 0.25rem;
}

.card {
	padding: 0.1rem;
	height: fit-content;
	width: fit-content;
}

.card-header {
	padding: 0rem;
	margin: 0;
}

.card-block {
	padding: 0.1rem;
	margin: 0;
}

.btn-light {
	font-size: 0.6rem !important;
}
</style>

<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js" integrity="sha384-SlE991lGASHoBfWbelyBPLsUlwY1GwNDJo3jSJO04KZ33K2bwfV9YBauFfnzvynJ" crossorigin="anonymous"></script>

</head>

<body>

	<div class="floating-panel" style="top: 0.35rem; left: 0.7rem;">
		<div class="row">
			<div class="col">
				<button id="sync" class="btn btn-light" >
					<i id="spinner" class="fas fa-sync-alt" style="font-size: 1rem;"></i>
				</button>
			</div>
		</div>
		</div>

	<div class="floating-panel" style="top: 0.35rem; right: 0.7rem;">
		
		<div class="row">
			<div class="col">
				<!-- 				<a class="btn btn-light" href="https://www.google.com/maps/dir/?api=1&destination="+destination+"&travelmode=driving&dir_action=navigate" onclick="Android.openOnGoogleMaps(document.getElementById('end').value)">Abrir en Google Maps</a>
 -->
				<a id="goMaps" class="btn btn-light">Abrir en google maps</a>
			</div>
		</div>

		
	</div>


	<div class="floating-panel" style="bottom: 2.5rem; left: 0.8rem;">

		<div class="row" style="font-size: 0.5rem;">
			<div class="col">
				<div class="card">
					<div class="card-block">

						<div class="row">
							<div class="col">
								Lat:
								<span id="lat">10.390309096147115</span>
							</div>
						</div>

						<div class="row">
							<div class="col">
								Lng:
								<span id="lng">-75.5014747</span>
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

		<div class="row" style="font-size: 0.65rem !important;">
			<div class="col">
				<div class="card">

					<div class="card-block">
						<span for="recogido">Env&iacute;os:</span>
						<select id="envios" class="selectpicker">
						</select>
						<span></span>
					</div>

				</div>
			</div>
		</div>

	</div>

	<input id="end" value="0.0,0.0" hidden>

	<div id="map"></div>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&language=es&callback=initMap"></script>
	
	<script src="/movil/js/map.js?v=1.1.7"></script>

</body>

</html>