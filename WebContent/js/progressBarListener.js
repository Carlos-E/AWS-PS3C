
$(document).ready(function() {
	
	let href = ['index.jsp'];

	let urls = ['carlos','/mapeoDeMercancia','/getNumReports','/disponibilidadDeTrailers','/disponibilidadDeVehiculos','/usuarios/conductoresDisponibles','/vehiculos/remolquesDisponibles','http://cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json']


$(document).ajaxSend(function(event, xhr, settings ){
	
	for(let i=0;i<href.length;i++){
		if(window.location.href.endsWith(href[i])){
			return;
		}
	}
	
	for(let i=0;i<urls.length;i++){
		if ( settings.url === urls[i] ) {
		 	return;
		 }
	}

	fillProgressBar(document.getElementById("progressBar"),50);
});

$( document ).ajaxComplete(function(event, xhr, settings  ) {
		
	for(let i=0;i<href.length;i++){
		if(window.location.href.endsWith(href[i])){
			return;
		}
	}
	for(let i=0;i<urls.length;i++){
		if ( settings.url.endsWith(urls[i])) {
		 	return;
		 }
	}
	
	fillProgressBar(document.getElementById("progressBar"),100);
});


});