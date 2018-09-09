var directionsService;
var dataSet;
var calculateRoute;
var getRoutes;

function initMap() {

	directionsService = new google.maps.DirectionsService;

	console.log('Servicio de mapas inicializado');
}
				
				calculateRoute = (origin, destination, i,tipoEnvio) => {
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
							
							if(tipoEnvio=='origen'){
								rows[i].insertCell(1).innerHTML = 'Para '+tipoEnvio+':<br>Distancia ' + response.routes[0].legs[0].distance.text + "<br>Duraci&oacute;n " + response.routes[0].legs[0].duration.text;
							}else if(tipoEnvio=='destino'){
								rows[i].insertCell(1).innerHTML = 'Para '+tipoEnvio+':<br>Distancia ' + response.routes[0].legs[0].distance.text + "<br>Duraci&oacute;n " + response.routes[0].legs[0].duration.text;
							}
							//rows[i].insertCell(rows[i].cells.length).innerHTML = 'Distancia ' + response.routes[0].legs[0].distance.text + '<br>Duraci&oacute;n ' + response.routes[0].legs[0].duration.text;

							
						} else {
							if (typeof Android != 'undefined') {
								Android.showToast('Directions request failed due to '+ status);
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
						calculateRoute(miUbicacion, envio.origenLatLong, index, "origen");
						calculateRoute(miUbicacion, envio.destinoLatLong, index, "destino");
					});

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
								{ title: "Env&iacute;o" }
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