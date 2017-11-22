/**
 * 
 */

function habilita(form) {
	form.intereses[0].disabled = false;
	form.intereses[1].disabled = false;
	form.intereses[2].disabled = false;
}

function deshabilita(form) {
	form.intereses[0].disabled = true;
	form.intereses[1].disabled = true;
	form.intereses[2].disabled = true;
}


function inicioRastreo() {


$.getJSON('../mapeoDeMercancia', function(data) {
	for (var i = 0, len = data.length; i < len; i++) {
		console.log(data[i]);
	}
	ponerMarcadores(data);
});

setInterval(function() {

	//document.getElementById("parrafo").innerHTML = "Tiempo transcurrido en segundos: "
	//		+ i * 5;
	//i++;

	$.getJSON('../mapeoDeMercancia', function(data) {
		//alert(data);

		for (var i = 0, len = data.length; i < len; i++) {
			console.log(data[i]);
		}

		//Objeto JSON pasado a String?
		//var objetoJSON = JSON.stringify(data);

		//$('#data').text(data+" "+ i);
		//$('#data').text(objetoJSON+" "+ i);

		//json_object = JSON.parse(data); //convert to an object

		ponerMarcadores(data);

	});

	// method to be executed;
}, 5000);

}