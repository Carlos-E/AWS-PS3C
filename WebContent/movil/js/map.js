var directionsService;
var directionsDisplay;
var map;

var envios = [];

function initMap() {
  directionsService = new google.maps.DirectionsService();
  directionsDisplay = new google.maps.DirectionsRenderer();

  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 13,
    center: {
      lat: 10.4049383,
      lng: -75.497829
    },
    mapTypeControl: false,
    mapTypeControlOptions: {
      style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
      mapTypeIds: ['roadmap', 'terrain']
    },    
    zoomControl: false,
    streetViewControl: false,
    fullscreenControl: false
  });

  directionsDisplay.setMap(map);

  document.getElementById('sync').addEventListener('click', function() {
	  
	  getEnvios(()=>{
    	directionsDisplay.setMap(map);
        if (document.getElementById('entregado').checked) {
          directionsDisplay.setMap(null);
        } else {
          calculateAndDisplayRoute(directionsService, directionsDisplay);
        }
    });

  });

  document.getElementById('envios').addEventListener('change', function() {
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
        Android.showToast('Envío completado');
      }
    } else {
      document.getElementById('entregado').checked = false;
      if (document.getElementById('recogido').checked) {
        document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;
      }
      calculateAndDisplayRoute(directionsService, directionsDisplay);
    }
  });

  document.getElementById('recogido').addEventListener('change', function() {
    // /////ACTUALIZAR ENVIO
    let cliente = envios[document.getElementById('envios').selectedIndex].usuario;
    let fecha = envios[document.getElementById('envios').selectedIndex].fecha;
    // /////ACTUALIZAR ENVIO

    directionsDisplay.setMap(map);
    if (document.getElementById('recogido').checked) {
      document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;

      envios[document.getElementById('envios').selectedIndex].chequeoCarga = true;

      updateShipment('/chequeo/recogida', cliente, fecha, true);

      // //
      calculateAndDisplayRoute(directionsService, directionsDisplay);
    } else {
      document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].origenLatLong;

      envios[document.getElementById('envios').selectedIndex].chequeoCarga = false;
      envios[document.getElementById('envios').selectedIndex].chequeoDescarga = false;

      document.getElementById('entregado').checked = false;

      updateShipment('/chequeo/recogida', cliente, fecha, false);

      // //
      calculateAndDisplayRoute(directionsService, directionsDisplay);
    }
  });

  document.getElementById('entregado').addEventListener('change', function() {
    // /////ACTUALIZAR ENVIO
    let cliente = envios[document.getElementById('envios').selectedIndex].usuario;
    let fecha = envios[document.getElementById('envios').selectedIndex].fecha;
    // /////ACTUALIZAR ENVIO

    directionsDisplay.setMap(map);
    if (document.getElementById('entregado').checked) {
      envios[document.getElementById('envios').selectedIndex].chequeoDescarga = true;
      envios[document.getElementById('envios').selectedIndex].chequeoCarga = true;

      document.getElementById('recogido').checked = true;
      document.getElementById('recogido').disable = true;
      directionsDisplay.setMap(null);

      updateShipment('/chequeo/entrega', cliente, fecha, true);

      if (typeof Android != 'undefined') {
        Android.showToast('Envío completado');
      }
    } else {
      document.getElementById('end').value = envios[document.getElementById('envios').selectedIndex].destinoLatLong;

      envios[document.getElementById('envios').selectedIndex].chequeoDescarga = false;

      document.getElementById('recogido').checked = true;
      document.getElementById('recogido').disable = false;

      updateShipment('/chequeo/entrega', cliente, fecha, false);
      // //
      calculateAndDisplayRoute(directionsService, directionsDisplay);
    }
  });
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
  directionsService.route(
    {
      origin: document.getElementById('lat').innerHTML + ',' + document.getElementById('lng').innerHTML,
      destination: document.getElementById('end').value,
      travelMode: 'DRIVING'
    },
    function(response, status) {
      if (status === 'OK') {
        directionsDisplay.setDirections(response);
        // console.log(JSON.stringify(response, null, 2));
        if (typeof Android != 'undefined') {
          Android.showToast('Distancia: ' + response.routes[0].legs[0].distance.text);
          Android.showToast('Duración: ' + response.routes[0].legs[0].duration.text);
        }
      } else {
        if (typeof Android != 'undefined') {
          Android.showToast('Directions request failed due to ' + status);
        }
      }
	  $('#spinner').removeClass('fa-spin');
    }
  );
}

document.getElementById('goMaps').addEventListener('click', function() {
  location.href =
    'https://www.google.com/maps/dir/?api=1&destination=' +
    document.getElementById('end').value +
    '&travelmode=driving&dir_action=navigate';
});

function getEnvios(callback) {
	
	$('#spinner').addClass('fa-spin');
	
  $.getJSON('/getEnvios', function(response, textStatus, jqXHR) {
	  
    console.log('Envios:\n' + JSON.stringify(response, null, 2));
    console.log('Length:\n' + JSON.stringify(response.length, null, 2));

    if (response != null && response.length != 0) {
      if (typeof Android != 'undefined') {
        Android.showToast('Envíos descargados correctamente');
      }

      envios = response;

      directionsDisplay.setMap(map);

      $('#envios').html('');
      $(envios).each(function() {
        $('#envios').append(
        	$('<option>')
    		.css('word-break','break-all')
        		.attr('value', this.usuario + ' : ' + this.fecha)
        		.text( this.cliente.nombre.toUpperCase()+' '+this.cliente.apellido.toUpperCase()+'-'+this.fecha)
        );
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
          Android.showToast('Envío completado');
        }
      } else {
        document.getElementById('entregado').checked = false;
      }
    } else {
      if (typeof Android != 'undefined') {
        Android.showToast('No tienes envíos asignados');
      } else {
        alert('No tienes envíos asignados');
      }
    }
    
    callback();
  });
}

function updateShipment(url, client, date, value) {
  $.ajax({
    url: url,
    data: {
      client: client,
      date: date,
      value: value
    },
    type: 'POST',
    dataType: 'json'
  })
    .done(function(response) {
      console.log('Envío actualizado correctamente');

      if (typeof Android != 'undefined') {
        Android.showToast('Envío actualizado correctamente');
      }
    })
    .fail(function(xhr, status, errorThrown) {
      console.log('Failed Request To Servlet /updateShipment');

      if (typeof Android != 'undefined') {
        Android.showToast('Error: envío no actualizado');
      }
    });
}

var coords = '0.0,0.0';

if (typeof Android != 'undefined') {
  setInterval(function() {
    coords = Android.getBestLocation();

    document.getElementById('lat').innerHTML = coords.split(',')[0];
    document.getElementById('lng').innerHTML = coords.split(',')[1];
  }, 1000);
}

getEnvios(()=>{
	directionsDisplay.setMap(map);
    if (document.getElementById('entregado').checked) {
      directionsDisplay.setMap(null);
    } else {
      calculateAndDisplayRoute(directionsService, directionsDisplay);
    }
});
