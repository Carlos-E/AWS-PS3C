var directionsService;
var dataSet;
var getRoutes;
var listaDatosRutas = [];
var listaTiempo = [];
var listaOrdenada = [];
function initMap() {

  directionsService = new google.maps.DirectionsService();

  getRoutes = (origenEnvio) => {
	  console.log('Calculando Rutas Con Origen De Envio');
	  
    $(document).ready(function() {
      $.ajax({
        url: '/scanTable',
        data: { tabla: 'vehiculos' },
        type: 'POST',
        dataType: 'json'
      }).done(function(vehiculos) {
    	  console.log('Vehiculos: '+JSON.stringify(vehiculos,null,2));
    	  
    	  for(let i=0;i<vehiculos.length;i++){
    		  
    		  // Se llama directamente al servicio
    		  directionsService.route(
    			      {
    			        origin: vehiculos[i].latitud+','+vehiculos[i].longitud,
    			        destination: origenEnvio,
    			        travelMode: 'DRIVING'
    			      },
    			      function(response, status) {
    			        if (status === 'OK') {
    			        	
    			        	console.log('Duracion de: '+vehiculos[i].placa);
    			            console.log(JSON.stringify(response.routes[0].legs[0].duration, null, 2));

    			            listaDatosRutas.push({
        			            placa: vehiculos[i].placa,
        			            distancia: response.routes[0].legs[0].distance,
        			            duracion: response.routes[0].legs[0].duration
        			          })
        			          
        			         listaTiempo.push(response.routes[0].legs[0].duration.value); 
        			          
    			            //Este if solo se ejecuta en el ultimo ciclo del FOR(al menos eso creo)
        			          if(i==vehiculos.length-1){
        			        	  
            			          console.log('listaDatosRutas: ' + JSON.stringify(listaDatosRutas,null,2));
            			          console.log('listaTiempo: ' + JSON.stringify(listaTiempo,null,2));

        			        	// AQUI AQUI AQUI AQUI AQUI AQUI AQUI AQUI
          			        	console.log('Haciendo cosas');
          			        		listaTiempo = test(listaTiempo);
        			        	  let select = document.getElementById('asignado');
        			        	  for(var x=0;x<listaTiempo.length;x++){
        			        		  for(let j=0;j<listaDatosRutas.length;j++){
        			        			  if(listaTiempo[x]==listaDatosRutas[j].duracion.value){
        			        				  listaOrdenada.push(listaDatosRutas[j]);
        			        				  console.log(listaDatosRutas);
        			        			  }
        			        		  }
        			        	  }
        			        	  for (let k=0;k<listaOrdenada.length;k++){
        			        		  let option = document.createElement("option");
        			        		  option.text = "Placa: "+listaOrdenada[k].placa+"Distancia: "+listaOrdenada[k].distancia.text;
        			        		  console.log(option.text);
        			        		  select.add(option);
        			        	  }
        			        	  
            			        	console.log('Lista ordenada: '+JSON.stringify(listaOrdenada,null,2));
        			        	  
        			          }
    			        	
    			        } else {
    			          console.log('Error calculando la ruta de: '+vehiculos[i].placa);
    			        }
    			      }
    			    );
    		  
    	  }
    	 
    	  
// var select = document.getElementById('asignado');
// var listaDistancia = [];
// var listaDistanciaOrd = [];
// var listaOrdenada = []
// console.log(list);
// var latlon = list[1].latitud+","+list[1].longitud;
// console.log(latlon);
                	/*
					 * for (var i=0;i<list.length;i++){ var latlon =
					 * list[i].latitud.value+","+list[i].longitud.value;
					 * console.log(latlon); calculateRoute(directionsService,
					 * latlon, ruta) } listaDistanciaOrd = test(listaDistancia);
					 * for (var i=0;i<list.length;i++){ for (var j=0;j<listaDistanciaOrd.length;j++){
					 * if(listaDistanciaOrd[i] == list[j].distancia.value){
					 * listaOrdenada.push(list[j]); } } }
					 * console.log(listaOrdenada); for (var i=0;i<listaOrdenada.length;i++){
					 * var option = document.createElement("option");
					 * option.text = "Placa: "+listaOrdenada[i].placa+"
					 * Distancia: "+listaOrdenada[i].distancia.text;
					 * select.add(option); }
					 */

        })
        .fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutes');
        })
        .always(function(xhr, status) {
          $('#spinner').fadeOut('slow');
        });
    });
  };

  console.log('Mapa Inicializado');
}

function test(arrel){
	var arreglo = arrel;
	quicksort(0,(arreglo.length -1));
function quicksort(primero,ultimo){
	i = primero
    j = ultimo
    pivote = arreglo[parseInt((i+j)/2)];
    do{
        while(arreglo[i]<pivote){
        	i++;
            j--;
        }
        if(i<=j){
        	aux=arreglo[j];
            arreglo[j] = arreglo[i]
            arreglo[i] = aux
            i++;
            j--;
        }
    }while(i<j);
    if(primero<j){
    	quicksort(primero,j);
    }
    if(ultimo>i){
    	quicksort(i,ultimo); 
    }
}
return arreglo;
}
