var directionsService;
var dataSet;
var getRoutes;
var listaTiempo = [];
var listaOrdenada = [];
function initMap() {

  directionsService = new google.maps.DirectionsService();

  getRoutes = (origenEnvio) => {
	  var listaDatosRutas = [];
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
        			            distancia: response.routes[0].legs[0].distance.value,
        			            duracion: response.routes[0].legs[0].duration.value,
        			            distanciaT: response.routes[0].legs[0].distance.text,
        			            duracionT: response.routes[0].legs[0].duration.text
        			          })
        			          
        			         listaTiempo.push(response.routes[0].legs[0].duration.value); 
        			          
    			            //Este if solo se ejecuta en el ultimo ciclo del FOR(al menos eso creo)
        			          if(i==vehiculos.length-1){
        			        	  
            			        console.log('listaDatosRutas: ' + JSON.stringify(listaDatosRutas,null,2));
            			        console.log('listaTiempo: ' + JSON.stringify(listaTiempo,null,2));

        			        	listaDatosRutas = quickSort(listaDatosRutas,0,listaDatosRutas.length-1,'duracion');
          			        	
        			        	console.log('listaDatosRutas(ordenada?): ' + JSON.stringify(listaDatosRutas,null,2));

        			        	var select = document.getElementById('asignado');
        			        	for(let l=select.length;l>1;l--){
        			        		console.log("se elimina :"+select.length);
        			        		console.log("se elimina :"+select.options[l-1].text);
        			        		select.remove(l-1);
        			        	}
        			        	for (let k=0;k<listaDatosRutas.length;k++){
        			        		let option = document.createElement("option");
        			        		option.text = "Placa: "+listaDatosRutas[k].placa+" - Distancia: "+listaDatosRutas[k].distanciaT;
        			        		console.log(option.text);
        			        		select.add(option);
        			        	}
        			          }
    			        	
    			        } else {
    			          console.log('Error calculando la ruta de: '+vehiculos[i].placa);
    			        }
    			      }
    			    );
    		  
    	  }
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

function swap(items, firstIndex, secondIndex){
    var temp = items[firstIndex];
    items[firstIndex] = items[secondIndex];
    items[secondIndex] = temp;
}

function partition(items, left, right, criteria) {

    let pivot   = items[Math.floor((right + left) / 2)];
    let  i       = left;
    let  j       = right;

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

function quickSort(items, left, right,criteria) {

    let index;

    if (items.length > 1) {

        index = partition(items, left, right,criteria);

        if (left < index - 1) {
            quickSort(items, left, index - 1,criteria);
        }

        if (index < right) {
            quickSort(items, index, right,criteria);
        }

    }

    return items;
}