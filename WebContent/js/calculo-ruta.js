var directionsService;
var getRoutes;

function initMap() {

  directionsService = new google.maps.DirectionsService();

  getRoutes = (origenEnvio, pesoEnvio, espacioEnvio) => {
	  let listaDatosRutas = [];
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
    		  
    		  if(vehiculos[i].tipo == 'remolque'){
    			  console.log(vehiculos[i].placa+' es un remolque, descartado');
    			  continue;
    		  }
    		  
    		  if(vehiculos[i].peso < pesoEnvio || vehiculos[i].espacio < espacioEnvio){
		        	console.log(vehiculos[i].placa+' no soporta las dimensiones del envio');
		        	continue;
    		  }
    		  
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
        			          });
    			        	
    			        } else {
    			          console.log('Error calculando la ruta de: '+vehiculos[i].placa);
    			        }
    			      }
    			    );
    		  
    	  }// for
    	  
    	  
    	  setTimeout(function(){
    		  
	        	listaDatosRutas = quickSort(listaDatosRutas,0,listaDatosRutas.length-1,'duracion');
	        	
	        	// Limpiar select
	        	let select = document.getElementById('asignado');
	            $("#asignado option").remove();

	        	// Poner Seleccionar... de primero
	        	let option = document.createElement("option");
	        	option.text = 'Selecionar...';
	        	option.value = '';   	
	        	select.add(option);
	        	
        		console.log('Datos validos:');

	        	// Llenar select
	        	for (let k=0;k<listaDatosRutas.length;k++){
	        		let option = document.createElement("option");
	        		option.text = "Placa: "+listaDatosRutas[k].placa+" - Distancia: "+listaDatosRutas[k].distanciaT;
		        	option.value = listaDatosRutas[k].placa;   	
	        		console.log(option.text);
	        		select.add(option);
	        	}
  	  },3000);//SetTimeOut 
    	  
        })
        .fail(function(xhr, status, errorThrown) {
          console.log('Failed getRoutes');
        })
        .always(function(xhr, status) {
          $('#spinner').fadeOut('slow');
        });
    });
  };

  console.log('Servicio de rutas inicializado');
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