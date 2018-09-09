var directionsService;
var trailersDisponibles = true;
var vehiculosDisponibles = true;
var listVehiculos = [];
var listTrailers = [];
var setVehiculo;
var setTrailer;
var pesoEnvioG = 0;
var espacioEnvioG = 0;
function initMap() {
  directionsService = new google.maps.DirectionsService();
  console.log('Servicio de rutas inicializado');
}


function setVehiculos(origenEnvio, pesoEnvio, espacioEnvio){
    console.log('Calculando Rutas Con Origen De Envio');

    $(document).ready(function() {
      setVehiculo = $.ajax({
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
              //validacionDeDisponibilidad(origenEnvio, pesoEnvio, espacioEnvio);
            	return;
          }else{
            vehiculosDisponibles = true;
        	  $('#labelSuge').hide();
            document.getElementById('sugerencia').innerHTML="";
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
        setTrailer = $.ajax({
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
                //validacionDeDisponibilidad(origenEnvio, pesoEnvio, espacioEnvio); 
                return;
	        }else{
            trailersDisponibles = true;
        	  $('#labelSuge').hide();
            document.getElementById('sugerencia').innerHTML="";
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

function elHombreClave(que, origenEnvio, pesoEnvio, espacioEnvio){
  switch(que){
    case'vehiculo':
      var testVehiculos = $.ajax({
	      url: '/vehiculos/sugerir',
	      data: {
          origenEnvio: origenEnvio,
	        pesoEnvio: pesoEnvio, 
	        espacioEnvio: espacioEnvio 
	        },
	      type: 'GET',
	      dataType: 'json'
	    }).done(function(vehiculos) {
        console.log('segun aqui debe mandarla lista de vehiculos');	 
        llenadoDeVehiculos(vehiculos);      
        }).fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutesVehiculos');	    
        });    
    break;
    case'trailer':
     // var listTrailers = castSugerirTrailers(origenEnvio, pesoEnvio, espacioEnvio);
     var testTrailers = $.ajax({
      url: '/trailers/sugerir',
      data: {
        origenEnvio: origenEnvio,
        pesoEnvio: pesoEnvio, 
        espacioEnvio: espacioEnvio 
        },
      type: 'GET',
      dataType: 'json'
    }).done(function(trailers) {
      console.log('segun aqui debe mandarla lista de trailers');	 
      llenadoDeTrailers(trailers);      
      }).fail(function(xhr, status, errorThrown) {
        console.log('Failed getRoutesTrailer');	    
      }); 
    break;
  }
	if(!trailersDisponibles&&!vehiculosDisponibles){
    $.when(testVehiculos,testTrailers).done(function(){
      var listaMAMONA = [];
      for(var i=0;i<listVehiculos.length;i++){
    	  listaMAMONA.push(listVehiculos[i]);
      }
      var num =listVehiculos.length + listTrailers.length;
      for(var i=listVehiculos.length;i<listVehiculos.length + listTrailers.length;i++){
    	  listaMAMONA.push(listTrailers[i]);
      }
      console.log("el mierderoooooooooooooo<br>"+ listaMAMONA.length);
    }); 
  }

}


function castSugerirVehiculos(origenEnvio, pesoEnvio, espacioEnvio) {	
		  $.ajax({
	      url: '/vehiculos/sugerir',
	      data: {
          origenEnvio: origenEnvio,
	        pesoEnvio: pesoEnvio, 
	        espacioEnvio: espacioEnvio 
	        },
	      type: 'GET',
	      dataType: 'json'
	    }).done(function(vehiculos) {
        /*label = document.getElementById("sugerencia");
        if(vehiculos.length!=0){
          var aux = "Se sugiere divir el envío en "+vehiculos.length+" paquetes, con los siguinetes vehículos: <br>";       
          for (let i = 0; i < vehiculos.length; i++) { 
            console.log(vehiculos[i].placa)     
             aux += (i+1)+" - "+vehiculos[i].placa+"<br>";
          } 
          label.innerHTML = aux;
        }else{
          label.innerHTML = "Se sugiere esperar a que un camion se encuentre disponible, ya que no hay una configuracion posible de embalaje";
        }*/
        console.log('segun aqui debe mandarla lista');	
        console.log(JSON.stringify(vehiculos.length, null, 2));
        aja(vehiculos);  
        return vehiculos;      
        }).fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutesTrailer');	    
        });
}

  $.when(setTrailer, setVehiculo).then(function(){
    sugerir();
  });



function sugerir(){
  console.log("ejecucion de funcion sugerir()"); 
  console.log("los trailers son: "+trailersDisponibles+" los vehiculos son: "+ vehiculosDisponibles); 
  if(!trailersDisponibles&&!vehiculosDisponibles){
    $.ajax({
      url: '/sugerir',
      data: {
        pesoEnvio: pesoEnvioG, 
        espacioEnvio: espacioEnvioG 
        },
      type: 'GET',
      dataType: 'json'
    }).done(function(lista) {
      label = document.getElementById("sugerencia");
      if(lista.length!=0){
        var aux = "Se sugiere divir el envío en "+lista.length+" paquetes, cuales paquetes? ESTOOS PAQUETEEESS!! <br>";       
        for (let i = 0; i < lista.length; i++) { 
          console.log(lista[i].id);     
           aux += (i+1)+" - "+lista[i].id+"<br>";
        } 
        label.innerHTML = aux;
      }else{
        label.innerHTML = "Se sugiere esperar a que un camion se encuentre disponible, ya que no hay una configuracion posible de embalaje";
      }
      console.log('segun aqui debe mandarla lista');	
      console.log(JSON.stringify(lista.length, null, 2)); 
      pesoEnvio=0;   
      espacioEnvio=0;
      }).fail(function(xhr, status, errorThrown) {
        console.log('Failed getRoutesTrailer');	    
      });
  }
}


function llenadoDeVehiculos(vehiculos){
  listVehiculos = vehiculos;
  console.log(listVehiculos);
} 
function llenadoDeTrailers(trtailers){
  listTrailers = trtailers;
  console.log(listTrailers);
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
