var map;
var markers = [];
var calculateAndDisplayRoute;
var directionsService;
var directionsDisplay;

function initMap() {

	directionsService = new google.maps.DirectionsService;
	directionsDisplay = new google.maps.DirectionsRenderer;

	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : 10.4015094,
			lng : -75.4959313
		},
		zoom : 13,
		fullscreenControl : true
	});
	
	directionsDisplay.setMap(map);

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
			title : data[i].placa,
			label : data[i].placa,
			LatLng : data[i].latitud+','+data[i].longitud
			});

		markers.push(marker);
		marker.setMap(map);

		google.maps.event.addListener(marker, 'click', function() {
			getVehiculo(this.label);
			calculateAndDisplayRoute(this.label,this.LatLng,directionsService, directionsDisplay);
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

// RUTAS

function getEnvios(placa,callback) {
  $.getJSON('/getEnviosVehiculo?placa='+placa, function(response, textStatus, jqXHR) {
  callback(response)
  });
}

calculateAndDisplayRoute = (placa,origin,directionsService, directionsDisplay) => {
	
	 console.log('placa: ' +placa);
	 console.log('origen: '+origin);
	
	 getEnvios(placa,(envios)=>{
		 					 
		 if(envios==null||envios.length==0){
			 console.log('Envios vacios o no existentes');
			 return;
		 }

		 var waypoints = [];
	     for (let i = 0; i < envios.length; i++) {
			 if(!envios[i].chequeoCarga){
				 waypoints.push({
					 location: envios[i].origenLatLong,
					 stopover: true
				 });
			 }
			 if(!envios[i].chequeoDescarga){
				 waypoints.push({
					 location: envios[i].destinoLatLong,
					 stopover: true
				 });
	    	 }
	     }

		 directionsService.route(
		    {
			  origin: origin,
		      destination: '10.4032238,-75.5060763',
		      waypoints: waypoints,
		      optimizeWaypoints: true,
		      travelMode: 'DRIVING'
		    },
		    function(response, status) {
		      if (status === 'OK') {
				console.log('status OK');
		        directionsDisplay.setDirections(response);
		      } else {
		        if (typeof Android != 'undefined') {
		          Android.showToast('Directions request failed due to ' + status);
		        }else{
					console.log('status FAIL');
		        }
		      }
		    }
		  );
		 
	 }); 
	 
}