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
                	console.log(list);
                	var distaNum = parseFloat(list[0].distancia.value);
                	var vehiculo = "";
                	console.log(distaNum);
                	for (var i=0;i<list.length;i++){
                		if(distaNum>=parseFloat(list[i].distancia.value)){
                			distaNum=parseFloat(list[i].distancia.value);
                			vehiculo = vehiculo+","+list[i].placa;
                		}
                		vehiculo = vehiculo+","+list[i].placa;
                	}
                	console.log(vehiculo);
                	document.getElementById('asignado').value = vehiculo;
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
