var directionsService;
var trailersDisponibles = true;
var vehiculosDisponibles = true;
var listVehiculos = [];
var listTrailers = [];
var setVehiculo;
var setTrailer;
var pesoEnvioG = 0;
var espacioEnvioG = 0;
var validacion = 0;

function initMap() {
  directionsService = new google.maps.DirectionsService();
  console.log('Servicio de rutas inicializado');
}

function setVehiculos(origenEnvio, pesoEnvio, espacioEnvio){
    console.log('Calculando Rutas Con Origen De Envio');
        
    return $.ajax({
        url: '/disponibilidadDeVehiculos',
        data: { 
        	origenEnvio: origenEnvio,
        	pesoEnvio: pesoEnvio, 
        	espacioEnvio: espacioEnvio 
        },
        type: 'POST',
        dataType: 'json'
      }).done(function(vehiculos) {
          //console.log('Vehiculos: ' + JSON.stringify(vehiculos, null, 2));
          pesoEnvioG = pesoEnvio;
          espacioEnvioG = espacioEnvio;
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
        	  	//castSugerirVehiculos(origenEnvio, pesoEnvio, espacioEnvio)
              //elHombreClave('vehiculo',origenEnvio, pesoEnvio, espacioEnvio);          
          		$('#spinner1').hide();
          		$('#labelSuge').show();
            	$('#camion').show();
              vehiculosDisponibles = false;
              //validacionDeDisponibilidad(pesoEnvio, espacioEnvio);
              sugerirVehiculos(pesoEnvio, espacioEnvio);
            	return;
          }else{
            vehiculosDisponibles = true;
//        	  $('#labelSuge').hide();
            document.getElementById('labelSugerenciaVehiculo').innerHTML="";
            document.getElementById('pSugerenciaVehiculo').innerHTML="";
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
  }


function setTrailers(origenEnvio, pesoEnvio, espacioEnvio) {   
		
        return $.ajax({
	      url: '/disponibilidadDeTrailers',
	      data: {
            origenEnvio: origenEnvio,
	        	pesoEnvio: pesoEnvio, 
	        	espacioEnvio: espacioEnvio 
	        },
	      type: 'POST',
	      dataType: 'json'
	    }).done(function(trailers) {
          //console.log('Trailers: ' + JSON.stringify(trailers, null, 2));
	    	pesoEnvioG = pesoEnvio;
	    	espacioEnvioG = espacioEnvio;
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
                //elHombreClave('trailer',origenEnvio, pesoEnvio, espacioEnvio);                      	
                $('#spinner2').hide();
                $('#labelSuge').show();
                $('#trailer').show();
                trailersDisponibles = false;
                //validacionDeDisponibilidad(pesoEnvio, espacioEnvio); 
                sugerirTrailers(pesoEnvio, espacioEnvio)
                return;
	        }else{
            trailersDisponibles = true;
        	  //$('#labelSuge').hide();
            document.getElementById('labelSugerenciaTrailer').innerHTML="";
            document.getElementById('pSugerenciaTrailer').innerHTML="";
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



//Sugerencias

function sugerirVehiculos(pesoEnvio, espacioEnvio){
	
	return $.ajax({
		url: '/vehiculos/sugerir/carlos',
	    data: {
	    pesoEnvio: pesoEnvio, 
	    espacioEnvio: espacioEnvio 
	    },
	    type: 'GET',
	    dataType: 'json'
	    }).done(function(lista) {
	    		    	
	    	document.getElementById("labelSugerenciaVehiculo").innerHTML = "Sugerencia:";
	    	label = document.getElementById("pSugerenciaVehiculo");
	    	
	    	if(lista.length!=0){
	    		var aux = "Se sugiere dividir el envío en "+lista.length+" paquetes, agrup&aacute;ndolos de la siguiente forma:<br>";       
	        
	    		for (let i = 0; i < lista.length; i++) { 
	    			aux += (i+1)+" - En: "+lista[i].id+", ~"+lista[i].pesoAAsignar+"Kg, ~"+lista[i].espacioAAsignar+"m<sup>3</sup><br>";
	    		} 
	        	
	    		label.innerHTML = aux;
	    	}else{
	    		label.innerHTML = "Se sugiere esperar a que uno o varios camiones se encuentren disponibles, ya que no hay una configuraci&oacute;n posible de embalaje en este momento.";
	    	}
	   
	    }).fail(function(xhr, status, errorThrown) {
	    	console.log('Failed getRoutesTrailer');	    
	    }); 
}


function sugerirTrailers(pesoEnvio, espacioEnvio){
	 
   return $.ajax({
      url: '/traileres/sugerir/carlos',
      data: {
        pesoEnvio: pesoEnvio, 
        espacioEnvio: espacioEnvio 
        },
      type: 'GET',
      dataType: 'json'
    }).done(function(lista) {	    	
    	document.getElementById("labelSugerenciaTrailer").innerHTML = "Sugerencia:";
      
    	label = document.getElementById("pSugerenciaTrailer");
      
    	if(lista.length!=0){
    	  var aux = "Se sugiere dividir el envío en "+lista.length+" paquetes, agrup&aacute;ndolos de la siguiente forma:<br>";       
       
    	  for (let i = 0; i < lista.length; i++) { 
    		  aux += (i+1)+" - En: "+lista[i].id+", ~"+lista[i].pesoAAsignar+"Kg, ~"+lista[i].espacioAAsignar+"m<sup>3</sup><br>";
    	  } 
       
    	  label.innerHTML = aux;
     
    	}else{
    		label.innerHTML = "Se sugiere esperar a que uno o varios tr&aacute;ileres se encuentren disponibles, ya que no hay una configuraci&oacute;n posible de embalaje en este momento.";
    	}
      
      }).fail(function(xhr, status, errorThrown) {
        console.log('Failed getRoutesTrailer');	    
      });
 
}









//
//function validacionDeDisponibilidad(pesoEnvio, espacioEnvio){
//  $.when(setTrailer, setVehiculo).then(function(){
//    //if(validacion<=0){
//      
//      validacion++;
//    //}else{
//      sugerir(pesoEnvio, espacioEnvio);
//      validacion=0;
//    //}
//    
//  });
//}
//
//function sugerir(pesoEnvio, espacioEnvio){
//  console.log("ejecucion de funcion sugerir()"); 
//  console.log("los trailers son: "+trailersDisponibles+" los vehiculos son: "+ vehiculosDisponibles); 
//  if(!trailersDisponibles&&!vehiculosDisponibles){
//    $.ajax({
//      url: '/sugerir',
//      data: {
//        pesoEnvio: pesoEnvio, 
//        espacioEnvio: espacioEnvio 
//        },
//      type: 'GET',
//      dataType: 'json'
//    }).done(function(lista) {
//      label = document.getElementById("sugerencia");
//      if(lista.length!=0){
//        var aux = "Se sugiere dividir el envío en "+lista.length+" paquetes, agrupandolos de la siguiente forma:<br>";       
//        for (let i = 0; i < lista.length; i++) { 
//          console.log(lista[i].id);     
//           aux += (i+1)+" - En: "+lista[i].id+", "+lista[i].pesoAAsignar+"Kg, "+lista[i].espacioAAsignar+"m<sup>3</sup><br>";
//        } 
//        label.innerHTML = aux;
//      }else{
//        label.innerHTML = "Se sugiere esperar a que un cami&oacute;n se encuentre disponible, ya que no hay una configuraci&oacute;n posible de embalaje";
//      }
//      console.log('segun aqui debe mandarla lista');	
//      console.log(JSON.stringify(lista.length, null, 2)); 
//      pesoEnvio=0;   
//      espacioEnvio=0;
//      }).fail(function(xhr, status, errorThrown) {
//        console.log('Failed getRoutesTrailer');	    
//      });
//  }else{
//    console.log("Por lo menos hay un camion o trailer disponible"); 
//  }
//}
//
//
//function llenadoDeVehiculos(vehiculos){
//  listVehiculos = vehiculos;
//  console.log(listVehiculos);
//} 
//function llenadoDeTrailers(trtailers){
//  listTrailers = trtailers;
//  console.log(listTrailers);
//} 
//
//


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
