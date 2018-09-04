var map;
var markers = [];

function initMap() {

	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer;

	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : 10.4015094,
			lng : -75.4959313
		},
		zoom : 13,
		fullscreenControl : true
	});

}

function localizar() {

	let infoWindow = new google.maps.InfoWindow({
	// map : map
	});

	// Try HTML5 geolocation.
	if (navigator.geolocation) {

		navigator.geolocation.getCurrentPosition(function(position) {
			let pos = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};

			// infoWindow.setPosition(pos);
			// infoWindow.setContent('Tu');
			map.setCenter(pos);
			map.setZoom(14);

			let marker = new google.maps.Marker({
				map : map,
				position : pos,
				title : 'Tu Posicion',
				label : 'Tu'
			});

			marker.setMap(map);

		}, function() {
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoWindow, map.getCenter());
	}

}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow
			.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
					: 'Error: Your browser doesn\'t support geolocation.');
}

function ponerMarcadores(data) {

	deleteMarkers();

	for (let i = 0; i < data.length; i++) {

		let myLatlng = new google.maps.LatLng(parseFloat(data[i].latitud),
				parseFloat(data[i].longitud));

		let marker = new google.maps.Marker({
			map : map,
			position : myLatlng,
			title : 'Marcador',
			label : data[i].placa
			});

		markers.push(marker);
		marker.setMap(map);

		google.maps.event.addListener(marker, 'click', function() {
			getVehiculo(this.label);
		});
	}

}


function getVehiculo(plate) {
	
	console.log('getVehiculo');

	$.ajax({
		url : "/vehicle/read",
		data : {
			plate : plate
		},
		type : "POST",
		dataType : "json",
	}).done(function(vehiculo) {
		console.log(JSON.stringify(vehiculo,null,2));
		
		$('#myModal').modal('show');
		$('#myModalTitle').html(vehiculo.placa.toUpperCase());

		$('#myModalBody').html(`
		<p>Estado: ${vehiculo.estado}
		<br>Envios pendientes: ${vehiculo.numEnviosPendientes}
		<br>Conductor: ${vehiculo.usuario}
		<br>Tipo: ${vehiculo.tipo}
		<br>Trailer: ${vehiculo.trailer!=null ? vehiculo.trailer : "ninguno"}
		<br>Peso Maximo: ${vehiculo.pesoMax}
		    - Disponible: ${vehiculo.pesoDisponible}
		<br>Espacio Maximo: ${vehiculo.espacioMax}
		    - Disponible: ${vehiculo.espacioDisponible}</p>`);

	}).fail(function(xhr, status, errorThrown) {
		console.log(JSON.stringify(xhr,null,2));
		console.log(JSON.stringify(status,null,2));
		console.log(JSON.stringify(errorThrown,null,2));

	});

}

// Adds a marker to the map and push to the array.
function addMarker(location) {
	let marker = new google.maps.Marker({
		position : location,
		map : map
	});
	markers.push(marker);
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
	for (let i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
	setMapOnAll(null);
}

// Shows any markers currently in the array.
function showMarkers() {
	setMapOnAll(map);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
	clearMarkers();
	markers = [];
}

