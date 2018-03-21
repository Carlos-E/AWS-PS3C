var map;
var guia = 0;
var datonuevo, datoviejo;
var ultimalatitud, ultimalongitud;
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
		fullscreenControl: true
	});
}

function localizar() {

	 var infoWindow = new google.maps.InfoWindow({
	// map : map
	 });

	// Try HTML5 geolocation.
	if (navigator.geolocation) {

		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};

			// infoWindow.setPosition(pos);
			// infoWindow.setContent('Tu');
			map.setCenter(pos);
			map.setZoom(14);

			var marker = new google.maps.Marker({
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
	
	console.log("Borrando Marcadores");
	deleteMarkers();
	
	
	for (var i = 0;  i < data.length; i++) {

		console.log(data[i].placa);
		console.log(data[i].latitud);
		console.log(data[i].longitud);

//		if (data[i].latitud == data[i].latitud && data[i].longitud == data[i].longitud) {
//
//			console.log(data[i].placa
//					+ " Ubicacion igual, pasando al siguiente");
//
//			continue;
//		}

		console.log("Antes Marcador");

		var marker = new google.maps.Marker({
			map : map,
			position : {
				lat : data[i].latitud,
				lng : data[i].longitud
			},
			title : 'Marcador',
			label : data[i].placa
		});
		
		markers.push(marker);
		marker.setMap(map);

		console.log("Despues Marcador");

	}

}

// Adds a marker to the map and push to the array.
function addMarker(location) {
	var marker = new google.maps.Marker({
		position : location,
		map : map
	});
	markers.push(marker);
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
	for (var i = 0; i < markers.length; i++) {
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

