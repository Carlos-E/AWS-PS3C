<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
	if (session.getAttribute("rol") == null) {
		//response.sendError(400, "Acceso incorrecto"); //cambiar
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Listar env&iacute;os");
%>
		<!DOCTYPE html>
		<html lang="es">

		<head>
			<title>
				<%
		out.print(session.getAttribute("pagina").toString());
	%>
			</title>
			<jsp:include page="/movil/head.jsp" />
		</head>

		<body>
			<!-- INICIO -->
			<div class="container-fluid" id="wrapper">
				<div class="row">
					<!-- INICIO NAVBAR -->
					<jsp:include page="/movil/navbar.jsp" />
					<!--  ./NAVBAR -->
				</div>
			</div>
			<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto">
				<!--  HEADER -->
				<jsp:include page="/movil/header.jsp" />
				<!--  ./HEADER -->
				<section class="row">
					<div class="col-md-12 col-lg-12">

						<div class="card mb-4">

							<div class="card-block">
								<h3 class="card-title">
									Env&iacute;os
									<i id="spinner" class="fa fa-circle-notch fa-spin" style="font-size: 30px"></i>
								</h3>
								<h6 class="text-muted mb-4"></h6>
								<div id="example_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
									<div class="row">
										<div class="col-sm-12 col-md-6">
											<div class="dataTables_length" id="example_length"></div>
										</div>
										<div class="col-sm-12 col-md-6">
											<div id="example_filter" class="dataTables_filter"></div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12 small">
											<table id="table" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info"
											 style="width: 100%;font-size: 0.65rem !important;">

											</table>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12 col-md-5">
											<div class="dataTables_info" id="example_info" role="status" aria-live="polite"></div>
										</div>
										<div class="col-sm-12 col-md-7">
											<div class="dataTables_paginate paging_simple_numbers" id="example_paginate"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /FIN CONTAINER -->

					</div>
				</section>
			</main>
			<!-- Modal -->
			<!--  FOOTER CON SCRIPTS -->
			<jsp:include page="/movil/footer.jsp" />
			<!-- /FIN -->

			<script>
				var directionsService;
				var dataSet;

				function initMap() {

					directionsService = new google.maps.DirectionsService;

					function calculateRoute(directionsService, origin, destination, i,tipoEnvio) {
						directionsService.route({
							origin: origin,
							destination: destination,
							travelMode: 'DRIVING'
						}, function (response, status) {
							if (status === 'OK') {
								//console.log(JSON.stringify(response.routes[0].legs[0].distance.text, null, 2));
								//console.log(JSON.stringify(response.routes[0].legs[0].duration.text, null, 2));
								i++;
								let rows = document.getElementById("table").rows;
								//rows[i].insertCell(rows[i].cells.length).innerHTML = 'Distancia ' + response.routes[0].legs[0].distance.text + '<br>Duraci&oacute;n ' + response.routes[0].legs[0].duration.text;
								rows[i].insertCell(rows[i].cells.length).innerHTML = 'Para '+tipoEnvio+':<br>Distancia ' + response.routes[0].legs[0].distance.text + "<br>Duraci&oacute;n " + response.routes[0].legs[0].duration.text;

								
							} else {
								if (typeof Android != 'undefined') {
									Android.showToast('Directions request failed due to '
										+ status);
								}
								console.log('Ah ocurrido un error con las rutas');
							}
						});
					}

					getRoutes = (envios) => {

						let miUbicacion = '0.0,0.0';
						let coords = '0.0,0.0';
						
						if (typeof Android != 'undefined') {
							coords = Android.getBestLocation();
						}
						
						if (coords != '0.0,0.0') {
							miUbicacion = coords;
						}

						document.getElementById("table").rows[0].insertCell(1).outerHTML = '<td>Origen</td>';
						document.getElementById("table").rows[0].insertCell(2).outerHTML = '<td>Destino</td>';

						envios.forEach(function callback(envio, index, envios) {
							calculateRoute(directionsService, miUbicacion, envio.origenLatLong, index, "origen");
							calculateRoute(directionsService, miUbicacion, envio.destinoLatLong, index, "destino");
						});

					}

					console.log('Servicio de mapas inicializado');
				}


				$(document).ready(function () {

					$.ajax({
						url: "/getEnvios",
						type: "GET",
						dataType: "json",
					}).done(function (response) {
						//console.log(JSON.stringify(response, null, 2));

						dataSet = [];

						if (response !== null) {

							response.forEach(element => {

								var aux = '<select class="custom-select">' +
									"<option>" + element.fecha + "</option>" +
									"<option disabled>Cliente: " + element.usuario + "</option>" +
									"<option disabled>Origen: " + element.origen + "</option>" +
									"<option disabled>Destino: " + element.destino + "</option>" +
									"<option disabled>Tipo: " + element.tipo + "</option>" +
									"<option disabled>Espacio: " + element.espacio + "</option>" +
									"<option disabled>Descripcion: " + element.descripcion + "</option>" +
									"</select>";

								dataSet.push([
									aux
								]);
							});

						}

						$('#table').DataTable({
							data: dataSet,
							language: {
								url: "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
							},
							columns: [
								{ title: "Envio" }
							],
							columnDefs: [
							    { width: "50%", targets: 0 }
							],
							initComplete: function (settings, json) {
								getRoutes(response);
								$("#table_info").hide();
							}
						});


					}).fail(function (xhr, status, errorThrown) {
						if (typeof Android != 'undefined') {
							Android.showToast('Algo ha fallado');
						}
						console.log('Failed Request To Servlet /getEnvios');
					}).always(function (xhr, status) {
						$("#spinner").fadeOut("slow");
					});

				});
			</script>
			<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&callback=initMap"></script>

		</body>

		</html>