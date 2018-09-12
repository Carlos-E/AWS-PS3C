var directionsService;
var dataSet;
var calculateRoute;
var getRoutes;

var table;

function initMap() {
	
	directionsService = new google.maps.DirectionsService;
	console.log('Servicio de mapas inicializado');
}
				
				calculateRoute = (origin, destino, i,tipoEnvio,callback) => {
					directionsService.route({
						origin: origin,
						destination: destino,
						travelMode: 'DRIVING'
					}, function (response, status) {
						if (status === 'OK') {
														
							callback('Para '+tipoEnvio+':<br>Distancia ' + response.routes[0].legs[0].distance.text + "<br>Duraci&oacute;n " + response.routes[0].legs[0].duration.text);
							
						} else {
							if (typeof Android != 'undefined') {
								Android.showToast('El calculo de las rutas ha fallado, intentalo de nuevo');
							}
							
							console.log('Ah ocurrido un error con la ruta');
							
							callback('NA');

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
					
					if (typeof Android == 'undefined') {
						miUbicacion = '10.3898543,-75.5058491';
					}
					
					if(envios==null || 0==envios.length){
						$("#spinner").fadeOut("slow");
					}
					
					var newData = [];
				
					for(let i=0;i<envios.length;i++){
						
						calculateRoute(miUbicacion, envios[i].origenLatLong, i, "origen",(data)=>{
							
							let row = [];
							
							row.push('<select class="custom-select">' +
									"<option>" + envios[i].fecha + "</option>" +
									"<option disabled>Cliente: " + envios[i].cliente.nombre.toUpperCase()+' '+envios[i].cliente.apellido.toUpperCase() + "</option>" +
									"<option disabled>Origen: " + envios[i].origen + "</option>" +
									"<option disabled>Destino: " + envios[i].destino + "</option>" +
									"<option disabled>Tipo: " + envios[i].tipo + "</option>" +
									"<option disabled>Peso: " + envios[i].peso +' Kg'+ "</option>" +
									"<option disabled>Espacio: " + envios[i].espacio +' m<sup>3</sup>'+ "</option>" +
									"<option disabled>Descripci&oacute;n: " + envios[i].descripcion + "</option>" +
									"</select>");
							
							row.push(data);
							
							calculateRoute(miUbicacion, envios[i].destinoLatLong, i, "destino",(data)=>{
								row.push(data);

								newData.push(row);
								console.log(JSON.stringify(row,null,2));

								if(i==envios.length-1){
									console.log('drawing table again')
									table.clear().rows.add(newData).draw();
									$("#spinner").fadeOut("slow");
								}
							});
							
						});
					}

				}


				$(document).ready(function () {

					$.ajax({
						url: "/getEnvios",
						type: "GET",
						dataType: "json",
					}).done(function (response) {

						dataSet = [];

						table = $('#table').DataTable({
							data: dataSet,
							language: {
								url: "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
							},
							columns: [
								{ title: "Env&iacute;o" },
								{ title: "Origen" },
								{ title: "Destino" }
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
					});

				});