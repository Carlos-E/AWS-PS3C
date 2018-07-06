var directionsService;
var dataSet;
var getRoutes;
var list = [];

function initMap() {
  directionsService = new google.maps.DirectionsService();

  function calculateRoute(directionsService, placa, origin, destination, callback) {
    directionsService.route(
      {
        origin: origin,
        destination: destination,
        travelMode: 'DRIVING'
      },
      function(response, status) {
        if (status === 'OK') {
          // console.log(JSON.stringify(response, null, 2));
          //console.log(JSON.stringify(response.routes[0].legs[0].distance, null, 2));
          //console.log(JSON.stringify(response.routes[0].legs[0].duration, null, 2));
          callback({
            placa: placa,
            distancia: response.routes[0].legs[0].distance,
            duracion: response.routes[0].legs[0].duration
          });
        } else {
        	return "fuera";
          console.log('Ah ocurrido un error con calculando las rutas');
        }
      }
    );
  }
  getRoutes = (ruta) => {
    $(document).ready(function() {
      $.ajax({
        url: '/scanTable',
        data: { tabla: 'ubicaciones' },
        type: 'POST',
        dataType: 'json'
      }).done(function(ubicacionesCamiones) {
          console.log("Ubicaciones: ",JSON.stringify(ubicacionesCamiones, null, 2));
          list = [];
          let envioLatLong = ruta;
          //'10.390467,-75.5014747';
          let itemsProcessed = 0;
          ubicacionesCamiones.forEach(function callback(ubicacionCamion, index, ubicacionesCamiones) {
            calculateRoute(
              directionsService,
              ubicacionCamion.placa,
              ubicacionCamion.latitud + ',' + ubicacionCamion.longitud,
              envioLatLong,
              function(listItem) {
                list.push(listItem);
                itemsProcessed++;
                if (itemsProcessed === ubicacionesCamiones.length) {
                  //HACER LAS COSAS AQUI
                	var select = document.getElementById('asignado');
                	var listaDistancia = [];
                	var listaDistanciaOrd = [];
                	var listaOrdenada = []
                	console.log(list); 
                	for (var i=0;i<list.length;i++){
                		listaDistancia.push(list[i].distancia.value)
                	}
                	listaDistanciaOrd = test(listaDistancia);
                	for (var i=0;i<list.length;i++){
                		for (var j=0;j<listaDistanciaOrd.length;j++){
                			if(listaDistanciaOrd[i] == list[j].distancia.value){
                				listaOrdenada.push(list[j]);
                			}
                    	}
                	}
                	console.log(listaOrdenada);
                	for (var i=0;i<listaOrdenada.length;i++){
                		var option = document.createElement("option");
                		option.text = "Placa: "+listaOrdenada[i].placa+" Distancia: "+listaOrdenada[i].distancia.text;
                    	select.add(option);
                	}
                }
              }
            );
          });
        })
        .fail(function(xhr, status, errorThrown) {
          Android.showToast('Algo ha fallado');
          console.log('Failed Request To Servlet /getEnvios');
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










