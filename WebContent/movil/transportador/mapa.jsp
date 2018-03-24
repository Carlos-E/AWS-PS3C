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
				<jsp:include page="/movil/head.jsp" />
				<!--  ./HEAD -->

				<title>Rutas</title>
				<style>
					/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */

					#map {
						height: 100%;
					}

					/* Optional: Makes the sample page fill the window. */

					html,
					body {
						height: 100%;
						margin: 0;
						padding: 0;
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

				<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js" integrity="sha384-SlE991lGASHoBfWbelyBPLsUlwY1GwNDJo3jSJO04KZ33K2bwfV9YBauFfnzvynJ"
				 crossorigin="anonymous"></script>

			</head>

			<body>

				<div class="floating-panel" style="top: 0.25rem; right: 0.5rem;">
					<div class="row">
						<div class="col">
							<!-- 				<a class="btn btn-light" href="https://www.google.com/maps/dir/?api=1&destination="+destination+"&travelmode=driving&dir_action=navigate" onclick="Android.openOnGoogleMaps(document.getElementById('end').value)">Abrir en Google Maps</a>
 -->
							<a id="goMaps" class="btn btn-light">Abrir en Google Maps</a>
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

									<div class="row">
										<div class="col">
											Lat:
											<span id="lat">10.390467</span>
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

				</div>

				<input id="end" value="10.4233778,-75.5532186" hidden>

				<div id="map"></div>

				<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
				 crossorigin="anonymous"></script>

				<script>
					var directionsService;
					var directionsDisplay;
					var map;

					var envios = ['...'];

					function initMap() {

						directionsService = new google.maps.DirectionsService;
						directionsDisplay = new google.maps.DirectionsRenderer;

						map = new google.maps.Map(document.getElementById('map'), {
							zoom: 13,
							center: {
								lat: 10.4049383,
								lng: -75.497829
							}
						});

						directionsDisplay.setMap(map);

						document.getElementById('sync').addEventListener('click', function () {
							directionsDisplay.setMap(map);
							if (document.getElementById('entregado').checked) {
								directionsDisplay.setMap(null);
								if (typeof Android != 'undefined') {
									Android.showToast('Envio completado');
								}
								return;
							}
							getEnvios();
							calculateAndDisplayRoute(directionsService, directionsDisplay);
						});


						document.getElementById('envios').addEventListener('change', function () {
							directionsDisplay.setMap(map);

							console.log(envios[document.getElementById('envios').selectedIndex]);

							if (envios[document.getElementById('envios').selectedIndex].chequeoCarga) {
								document.getElementById('recogido').checked = true;
								document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;

							} else {
								document.getElementById('recogido').checked = false;
								document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].origenLatLong;
							}

							if (envios[document.getElementById('envios').selectedIndex].chequeoDescarga) {
								document.getElementById('entregado').checked = true;
								directionsDisplay.setMap(null);
								if (typeof Android != 'undefined') {
									Android.showToast('Envio completado');
								}
							} else {
								document.getElementById('entregado').checked = false;
								if (document.getElementById('recogido').checked) {
									document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;
								}
								calculateAndDisplayRoute(directionsService, directionsDisplay);

							}

						});

						/* document.getElementById('recogido').addEventListener('click', function () {
						///////ACTUALIZAR ENVIO
							let cliente = envios[document.getElementById('envios').selectedIndex].usuario;
							let fecha = envios[document.getElementById('envios').selectedIndex].fecha;
							updateShipment(cliente,fecha,'chequeoCarga', document.getElementById('recogido').checked);
							///////ACTUALIZAR ENVIO
						}); */

						document.getElementById('recogido').addEventListener('change', function () {
							///////ACTUALIZAR ENVIO
							let cliente = envios[document.getElementById('envios').selectedIndex].usuario;
							let fecha = envios[document.getElementById('envios').selectedIndex].fecha;
							updateShipment(cliente, fecha, 'chequeoCarga', document.getElementById('recogido').checked);
							///////ACTUALIZAR ENVIO

							directionsDisplay.setMap(map);
							if (document.getElementById('recogido').checked) {
								document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;

								envios[document.getElementById('envios').selectedIndex].chequeoCarga = true
								////
								calculateAndDisplayRoute(directionsService, directionsDisplay);
							} else {
								document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].origenLatLong;

								envios[document.getElementById('envios').selectedIndex].chequeoCarga = false

								envios[document.getElementById('envios').selectedIndex].chequeoDescarga = false;

								document.getElementById('entregado').checked = false;
								////
								calculateAndDisplayRoute(directionsService, directionsDisplay);
							}
						});



						/* document.getElementById('entregado').addEventListener('click', function () {
						///////ACTUALIZAR ENVIO
							let cliente = envios[document.getElementById('envios').selectedIndex].usuario;
							let fecha = envios[document.getElementById('envios').selectedIndex].fecha;
							updateShipment(cliente,fecha,'chequeoDescarga', document.getElementById('entregado').checked);
							///////ACTUALIZAR ENVIO
						}); */

						document.getElementById('entregado').addEventListener('change', function () {
							///////ACTUALIZAR ENVIO
							let cliente = envios[document.getElementById('envios').selectedIndex].usuario;
							let fecha = envios[document.getElementById('envios').selectedIndex].fecha;
							updateShipment(cliente, fecha, 'chequeoDescarga', document.getElementById('entregado').checked);
							///////ACTUALIZAR ENVIO


							directionsDisplay.setMap(map);
							if (document.getElementById('entregado').checked) {
								//ENTREGADO o DESCARGA
								envios[document.getElementById('envios').selectedIndex].chequeoDescarga = true;
								//RECOGIDO o CARGA
								envios[document.getElementById('envios').selectedIndex].chequeoCarga = true
								document.getElementById('recogido').checked = true;
								document.getElementById('recogido').disable = true;
								directionsDisplay.setMap(null);
								if (typeof Android != 'undefined') {
									Android.showToast('Envio completado');
								}
							} else {
								document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;

								envios[document.getElementById('envios').selectedIndex].chequeoDescarga = false;

								document.getElementById('recogido').checked = true;
								document.getElementById('recogido').disable = false;

								////
								calculateAndDisplayRoute(directionsService, directionsDisplay);
							}
						});

					}

					function calculateAndDisplayRoute(directionsService, directionsDisplay) {
						directionsService.route({
							origin: document.getElementById('lat').innerHTML + ","
								+ document.getElementById('lng').innerHTML,
							destination: document.getElementById('end').value,
							travelMode: 'DRIVING'
						}, function (response, status) {
							if (status === 'OK') {
								directionsDisplay.setDirections(response);
								//console.log(JSON.stringify(response, null, 2));
								if (typeof Android != 'undefined') {
									Android.showToast('Distancia: ' + response.routes[0].legs[0].distance.text);
									Android.showToast('Duración: ' + response.routes[0].legs[0].duration.text);
								}

							} else {
								if (typeof Android != 'undefined') {
									Android.showToast('Directions request failed due to '
										+ status);
								}
							}
						});
					}

					document
						.getElementById('goMaps')
						.addEventListener(
							'click',
							function () {
								location.href = 'https://www.google.com/maps/dir/?api=1&destination='
									+ document.getElementById('end').value
									+ '&travelmode=driving&dir_action=navigate'
							});


					function getEnvios() {

						$.ajax({
							url: "/getEnvios",
							type: "GET",
							dataType: "json",
						}).done(function (response) {
							console.log('Envios:\n' + JSON.stringify(response, null, 2));

							if (response !== null) {
								directionsDisplay.setMap(map);

								envios = response;

								$('#envios').html('');
								$(envios).each(function () {
									$('#envios').append($("<option>").attr('value', this.usuario + ' : ' + this.fecha).text(this.usuario + ' : ' + this.fecha));
								});

								if (envios[document.getElementById('envios').selectedIndex].chequeoCarga) {
									document.getElementById('recogido').checked = true;
									document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;
								} else {
									document.getElementById('recogido').checked = false;
									document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].origenLatLong;
								}

								if (envios[document.getElementById('envios').selectedIndex].chequeoDescarga) {
									document.getElementById('entregado').checked = true;
									directionsDisplay.setMap(null);
									if (typeof Android != 'undefined') {
										Android.showToast('Envio completado');
									}
								} else {
									document.getElementById('entregado').checked = false;
									document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;
								}
							}

							if (typeof Android != 'undefined') {
								Android.showToast('Envio descargado');
							}

						}).fail(function (xhr, status, errorThrown) {
							console.log('Failed Request To Servlet /getEnvios')
							if (typeof Android != 'undefined') {
								Android.showToast('Error: envio no actualizado');
							}
						}).always(function (xhr, status) {
						});

					}

					function updateShipment(client, date, key, value) {

						$.ajax({
							url: "/updateShipment",
							data: {
								client: client,
								date: date,
								key: key,
								value: value
							},
							type: "POST",
							dataType: "json",
						}).done(function (response) {

							console.log('Envio actualizado correctamente');

							if (typeof Android != 'undefined') {
								Android.showToast('Envio actualizado correctamente');
							}

						}).fail(function (xhr, status, errorThrown) {
							console.log('Failed Request To Servlet /updateShipment')
							if (typeof Android != 'undefined') {
								Android.showToast('Error: envio no actualizado');
							}
						}).always(function (xhr, status) {
						});

					}

					getEnvios();
				</script>

				<script>
					if (typeof Android != 'undefined') {

						var BestIsOn = true;
						var GPSIsOn = false;
						var networkIsOn = false;

						Android.toggleBestUpdates(BestIsOn);
						Android.toggleGPSUpdates(GPSIsOn);
						Android.toggleNetworkUpdates(networkIsOn);

						setInterval(function () {

							var coords = Android.getBestLocation();

							document.getElementById('lat').innerHTML = coords.split(",")[0];
							document.getElementById('lng').innerHTML = coords.split(",")[1];

						}, 1000);
					}
				</script>

				<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&callback=initMap"></script>
			</body>

			</html>