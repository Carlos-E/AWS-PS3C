
$(document).ready(function() {

$(document).ajaxSend(function(event, xhr, settings ){
	
	let href = ['index.jsp'];
	
	for(let i=0;i<href.length;i++){
		if(window.location.href.endsWith(href[i])){
			return;
		}
	}
	
	let urls = ['/mapeoDeMercancia','/getNumReports']

	for(let i=0;i<urls.length;i++){
		if ( settings.url === urls[i] ) {
		 	return;
		 }
	}

	fillProgressBar(document.getElementById("progressBar"),50);
});

$( document ).ajaxComplete(function(event, xhr, settings  ) {
	
	let href = ['index.jsp'];
	
	for(let i=0;i<href.length;i++){
		if(window.location.href.endsWith(href[i])){
			return;
		}
	}

	let urls = ['/mapeoDeMercancia','/getNumReports']

	for(let i=0;i<urls.length;i++){
		if ( settings.url === urls[i] ) {
		 	return;
		 }
	}
	
	fillProgressBar(document.getElementById("progressBar"),100);
});


});