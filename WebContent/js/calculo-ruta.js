var directionsService;

function initMap() {
  directionsService = new google.maps.DirectionsService();
  console.log('Servicio de rutas inicializado');
}


function setVehiculos(origenEnvio, pesoEnvio, espacioEnvio){
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
          
          if(vehiculos==null||vehiculos.length==0){
          		$('#spinner1').hide();
            	$('#camion').show();
            	return;
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
                  
                  listaDatosRutas = {
                      placa: vehiculos[i].placa,
                      distancia: response.routes[0].legs[0].distance.value,
                      duracion: response.routes[0].legs[0].duration.value,
                      distanciaT: response.routes[0].legs[0].distance.text,
                      duracionT: response.routes[0].legs[0].duration.text
                    };
                      
                } else {
                  console.log('Error calculando la ruta de: ' + vehiculos[i].placa);
                  
                  listaDatosRutas = {
                          placa: vehiculos[i].placa,
                          distancia: 'NA',
                          duracion: 'NA',
                          distanciaT: 'NA',
                          duracionT: 'NA'
                        };
                  
                }
                
                option = document.createElement('option');
                option.text = 'Placa: ' + listaDatosRutas.placa + ' - Distancia: ' + listaDatosRutas.distanciaT+ ' - Duración: ' + listaDatosRutas.duracionT;
                option.value = listaDatosRutas.placa;
                console.log(option.text);
                select.add(option);
                
                if(i==vehiculos.length-1){
                	$('#spinner1').hide();
                  	$('#camion').show();
              }
                
              });
          } // for
          
        }).fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutes');
        });
    });
  }


function setTrailers(origenEnvio, pesoEnvio, espacioEnvio) {
		    
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
	        
	        
	        if(trailers==null||trailers.length==0){
            	$('#spinner2').hide();
              	$('#trailer').show();
              	return;
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

                  listaDatosRutas = {
                      patente: trailers[i].patente,
                      distancia: response.routes[0].legs[0].distance.value,
                      duracion: response.routes[0].legs[0].duration.value,
                      distanciaT: response.routes[0].legs[0].distance.text,
                      duracionT: response.routes[0].legs[0].duration.text
                    };
                  
                } else {
                  console.log('Error calculando la ruta de: ' + trailers[i].patente);
                  
                  listaDatosRutas = {
                          patente: trailers[i].patente,
                          distancia: 'NA',
                          duracion: 'NA',
                          distanciaT: 'NA',
                          duracionT: 'NA'
                        };
                  
                }
                
                option = document.createElement('option');
                option.text = 'Patente: ' + listaDatosRutas.patente + ' - Distancia: ' + listaDatosRutas.distanciaT+ ' - Duración: ' + listaDatosRutas.duracionT;
                option.value = listaDatosRutas.patente;
                select.add(option);
                
                if(i==trailers.length-1){
                	$('#spinner2').hide();
                  	$('#trailer').show();
              }
                
              });
          } // for
          
        }).fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutesTrailer');	    
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
