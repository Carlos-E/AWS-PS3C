var directionsService;
var getRoutes;

function initMap() {
  directionsService = new google.maps.DirectionsService();

  getRoutes = (origenEnvio, pesoEnvio, espacioEnvio) => {
    let listaDatosRutas = [];
    console.log('Calculando Rutas Con Origen De Envio');

    $(document).ready(function() {
      $.ajax({
        url: '/disponibilidadDeVehiculos',
        data: { 
        	origenEnvio: origenEnvio,
        	pesoEnvio: pesoEnvio, 
        	espacioEnvio: espacioEnvio 
        },
        type: 'POST',
        dataType: 'json'
      }).done(function(vehiculos) {
          console.log('Vehiculos: ' + JSON.stringify(vehiculos, null, 2));
          
          // Limpiar select
          $('#camion').find('option').remove()
          let select = document.getElementById('camion');

          let option;
          
          option = document.createElement('option');
          option.text = 'Seleccionar...';
          option.value = '';
          select.add(option);
         
          option = document.createElement('option');
          option.text = 'ninguno';
          option.value = 'ninguno';
          select.add(option);
          // Limpiar select
          
          if(vehiculos.length==0){
          		$('#spinner1').hide();
            	$('#camion').show();
          }

          for (let i = 0; i < vehiculos.length; i++) {

            // Se llama directamente al servicio
            directionsService.route(
              {
                origin: vehiculos[i].latitud + ',' + vehiculos[i].longitud,
                destination: origenEnvio,
                travelMode: 'DRIVING'
              },
              function(response, status) {
                if (status === 'OK') {
                  console.log('Duracion de: ' + vehiculos[i].placa);
                  console.log(JSON.stringify(response.routes[0].legs[0].duration, null, 2));

                  /*
					 * listaDatosRutas.push({ placa: vehiculos[i].placa,
					 * distancia: response.routes[0].legs[0].distance.value,
					 * duracion: response.routes[0].legs[0].duration.value,
					 * distanciaT: response.routes[0].legs[0].distance.text,
					 * duracionT: response.routes[0].legs[0].duration.text });
					 */
                  
                  listaDatosRutas = {
                      placa: vehiculos[i].placa,
                      distancia: response.routes[0].legs[0].distance.value,
                      duracion: response.routes[0].legs[0].duration.value,
                      distanciaT: response.routes[0].legs[0].distance.text,
                      duracionT: response.routes[0].legs[0].duration.text
                    };
                  
                  option = document.createElement('option');
                  option.text = 'Placa: ' + listaDatosRutas.placa + ' - Distancia: ' + listaDatosRutas.distanciaT+ ' - Duración: ' + listaDatosRutas.duracionT;
                  option.value = listaDatosRutas.placa;
                  console.log(option.text);
                  select.add(option);
                      
                } else {
                  console.log('Error calculando la ruta de: ' + vehiculos[i].placa);
                }
                
                if(i==vehiculos.length-1){
                	$('#spinner1').hide();
                  	$('#camion').show();
              }
                
              });
          } // for

          /*
			 * setTimeout(function() {
			 * 
			 * listaDatosRutas = quickSort(listaDatosRutas, 0,
			 * listaDatosRutas.length - 1, 'duracion');
			 * 
			 * console.log('Camiones validos:');
			 *  // Llenar select for (let k = 0; k < listaDatosRutas.length;
			 * k++) { option = document.createElement('option'); option.text =
			 * 'Placa: ' + listaDatosRutas[k].placa + ' - Distancia: ' +
			 * listaDatosRutas[k].distanciaT; option.value =
			 * listaDatosRutas[k].placa; console.log(option.text);
			 * select.add(option); }
			 *  }, 2500); // SetTimeOut
			 */
          
        })
        .fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutes');
        })
        .always(function(xhr, status) {	        
        });
    });
  };

  console.log('Servicio de rutas inicializado');
}


function setTrailers(origenEnvio, pesoEnvio, espacioEnvio) {
	
	  $(document).ready(function() {
	    $.ajax({
	      url: '/disponibilidadDeTrailers',
	      data: {
            origenEnvio: origenEnvio,
	        	pesoEnvio: pesoEnvio, 
	        	espacioEnvio: espacioEnvio 
	        },
	      type: 'POST',
	      dataType: 'json'
	    }).done(function(trailers) {
	        console.log('Trailers: ' + JSON.stringify(trailers, null, 2));

	        // Borrar select completo
            $('#trailer').find('option').remove();
	        let select = document.getElementById('trailer');
	        
	        let option;

	        option = document.createElement('option');
	        option.text = 'Seleccionar...';
	        option.value = '';
	        select.add(option);
	        
	        option = document.createElement('option');
	        option.text = 'ninguno';
	        option.value = 'ninguno';
	        select.add(option);

	        console.log('Trailers validos:');
	        
	        if(0==trailers.length){
            	$('#spinner2').hide();
              	$('#trailer').show();
	        }
	        
          for (let i = 0; i < trailers.length; i++) {

            // Se llama directamente al servicio
            directionsService.route(
              {
                origin: trailers[i].latitud + ',' + trailers[i].longitud,
                destination: origenEnvio,
                travelMode: 'DRIVING'
              },
              function(response, status) {
                if (status === 'OK') {
                  console.log('Duracion de: ' + trailers[i].patente);
                  console.log(JSON.stringify(response.routes[0].legs[0].duration, null, 2));

                  /*
					 * listaDatosRutas.push({ placa: vehiculos[i].placa,
					 * distancia: response.routes[0].legs[0].distance.value,
					 * duracion: response.routes[0].legs[0].duration.value,
					 * distanciaT: response.routes[0].legs[0].distance.text,
					 * duracionT: response.routes[0].legs[0].duration.text });
					 */
                  
                  listaDatosRutas = {
                      patente: trailers[i].patente,
                      distancia: response.routes[0].legs[0].distance.value,
                      duracion: response.routes[0].legs[0].duration.value,
                      distanciaT: response.routes[0].legs[0].distance.text,
                      duracionT: response.routes[0].legs[0].duration.text
                    };
                  
                  option = document.createElement('option');
                  option.text = 'Patente: ' + listaDatosRutas.patente + ' - Distancia: ' + listaDatosRutas.distanciaT+ ' - Duración: ' + listaDatosRutas.duracionT;
                  option.value = listaDatosRutas.patente;
                  console.log(option.text);
                  select.add(option);
                  
                 
                  
                } else {
                  console.log('Error calculando la ruta de: ' + trailers[i].patente);
                }
                
                if(i==trailers.length-1){
                	$('#spinner2').hide();
                  	$('#trailer').show();
              }
                
              });
          } // for

          /*
			 * setTimeout(function() {
			 * 
			 * listaDatosRutas = quickSort(listaDatosRutas, 0,
			 * listaDatosRutas.length - 1, 'duracion');
			 * 
			 * console.log('Camiones validos:');
			 *  // Llenar select for (let k = 0; k < listaDatosRutas.length;
			 * k++) { option = document.createElement('option'); option.text =
			 * 'Placa: ' + listaDatosRutas[k].placa + ' - Distancia: ' +
			 * listaDatosRutas[k].distanciaT; option.value =
			 * listaDatosRutas[k].placa; console.log(option.text);
			 * select.add(option); }
			 *  }, 2500); // SetTimeOut
			 */
          
        })
        .fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutes');
        });
	    
	  });
}





// QuickSort
/*
function swap(items, firstIndex, secondIndex) {
  var temp = items[firstIndex];
  items[firstIndex] = items[secondIndex];
  items[secondIndex] = temp;
}

function partition(items, left, right, criteria) {
  let pivot = items[Math.floor((right + left) / 2)];
  let i = left;
  let j = right;

  while (i <= j) {
    while (items[i][criteria] < pivot[criteria]) {
      i++;
    }

    while (items[j][criteria] > pivot[criteria]) {
      j--;
    }

    if (i <= j) {
      swap(items, i, j);
      i++;
      j--;
    }
  }

  return i;
}

function quickSort(items, left, right, criteria) {
  let index;

  if (items.length > 1) {
    index = partition(items, left, right, criteria);

    if (left < index - 1) {
      quickSort(items, left, index - 1, criteria);
    }

    if (index < right) {
      quickSort(items, index, right, criteria);
    }
  }

  return items;
}
*/
