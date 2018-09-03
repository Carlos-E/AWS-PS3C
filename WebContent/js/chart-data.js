var datos = function (data) {
	var enero = 0, febrero = 0, marzo = 0, abril = 0, mayo = 0, junio = 0, julio = 0, agosto = 0, septiembre = 0, octubre = 0, noviembre = 0, diciembre = 0;
	for (var i = 0; i < data.length; i++) {
		switch (extraerMes(data[i].fecha)) {
			case "01":
				enero++;
				break;
			case "02":
				febrero++;
				break;
			case "03":
				marzo++;
				break;
			case "04":
				abril++;
				break;
			case "05":
				mayo++;
				break;
			case "06":
				junio++;
				break;
			case "07":
				julio++;
				break;
			case "08":
				agosto++;
				break;
			case "09":
				septiembre++;
				break;
			case "10":
				octubre++;
				break;
			case "11":
				noviembre++;
				break;
			case "12":
				diciembre++;
				break;
			default:
				console.log("algo salio mal con los meses");
				break;
		}
	}
	data = [enero, febrero, marzo, abril, mayo, junio, julio, agosto,
		septiembre, octubre, noviembre, diciembre];
	return data;
};

var extraerMes = function (data) {
	var fecha = data.split(" ");
	var amd = fecha[0];
	var mes = amd.split("-");
	return mes[1];
}

var lineChartData = {
	labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
		"Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
	datasets: [{
		label: "Dataset",
	    backgroundColor: 'rgba(213, 108, 62,0.85)',
	    borderColor: "rgb(255, 204, 1)",
	    pointBackgroundColor: "rgb(255, 204, 1)",
	    pointBorderColor: "#fff",
	    pointHoverBackgroundColor: "rgba(159,204,0,0.8)",
	    pointHoverBorderColor: "rgba(159,204,0,1)",
		data: ['0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0']
	}]
};

function makeChart(){
	
	console.log('Making Chart');
	

$.ajax({
	url: "/fechaEnvios",
	type: "GET",
	dataType: "json",
}).done(function (response) {

		lineChartData.datasets['0'].data = datos(response);
		
		let ctx = document.getElementById('line-chart').getContext('2d');
		let chart = new Chart(ctx, {
		    type: 'line',
		    data: lineChartData,
		    options: {					
		    	responsive: true,
				legend: {display:false}
		    	}
		});
		
	fillProgressBar(document.getElementById("progressBar"),25);


}).fail(function (xhr, status, errorThrown) {
	console.log('Failed Request To Servlet /scanTable');
});

}

var pieDataExample = {datasets: [{
	data: ['1','2','3'],
	backgroundColor: ['0','0','0'],
	label: 'Envios'
}],
labels: ['1','2','3']};

function makePie() {
	
	console.log('Making Pie');

	$.ajax({
		url: "/scanTable",
		data: {
			tabla: 'envios'
		},
		type: "POST",
		dataType: "json",
	}).done(function (envios) {

		$.ajax({
			url: "/scanTable",
			data: {
				tabla: 'empresas'
			},
			type: "POST",
			dataType: "json",
		}).done(function (empresas) {
			
			let data = { datasets: [{
				data: [],
				backgroundColor: [],
				label: 'Envios'
			}],
			labels: []};

			let contador = 0;

			empresas.forEach(function (empresa, i, empresas) {
				
				contador = 0;

				envios.forEach(function (envio, j, envios) {
					if (envio.empresa.toLowerCase() === empresa.nit.toLowerCase()) {
						contador++;
					}
				});
				data.datasets['0'].data.push(contador.toString());
				data.datasets['0'].backgroundColor.push(randomColor());
				data.labels.push(empresa.nombre);
			});
						
	    	fillProgressBar(document.getElementById("progressBar"),25);

			let ctx = document.getElementById('pie-chart').getContext('2d');
			let chart = new Chart(ctx, {
			    type: 'pie',
			    data: data,
			    options: {
					responsive: true,
					legend: {display:false}
				}
			});

		}).fail(function (xhr, status, errorThrown) {
			console.log('Failed Request To Servlet /scanTable')
		});

	}).fail(function (xhr, status, errorThrown) {
		console.log('Failed Request To Servlet /scanTable')
	});

}

function makeDoughnut() {
	
	console.log('Making doughnut');
	
	$("#spinner-3").attr('class', 'fa fa-circle-notch fa-spin');
    $("#spinner-3").fadeIn("slow");

	$.ajax({
		url: "/scanTable",
		data: {
			tabla: 'vehiculos'
		},
		type: "POST",
		dataType: "json",
	}).done(function (camiones) {
		
//		console.log(camiones);

		$.ajax({
			url: "/scanTable",
			data: {
				tabla: 'empresas'
			},
			type: "POST",
			dataType: "json",
		}).done(function (empresas) {
			
			let data = { datasets: [{
				data: [],
				backgroundColor: [],
				label: 'Envios'
			}],
			labels: []};

			let contador = 0;

			empresas.forEach(function (empresa, i, empresas) {
				
				contador = 0;

				camiones.forEach(function (camion, j, camiones) {

					if (camion.empresa.toLowerCase() === empresa.nit.toLowerCase()) {
						//Numero de envios por empresa
						contador++;
					}
				});
				data.datasets['0'].data.push(contador.toString());
				data.datasets['0'].backgroundColor.push(randomColor());
				data.labels.push(empresa.nombre);
			});
						
	        fillProgressBar(document.getElementById("progressBar"),25);


			let ctx = document.getElementById('doughnut-chart').getContext('2d');
			let chart = new Chart(ctx, {
			    type: 'doughnut',
			    data: data,
			    options: {
					responsive: true,
					legend: {display:false}
				}
			});

		}).fail(function (xhr, status, errorThrown) {
			console.log('Failed Request To Servlet /scanTable')
		});

	}).fail(function (xhr, status, errorThrown) {
		console.log('Failed Request To Servlet /scanTable')
	});

}

var randomColor = function(){
	let random = (min,max) => Math.floor((Math.random() * max) + min);
	let rgb = `rgb(255,${random(85,120)},${random(0,60)})`;
	return rgb;
}
