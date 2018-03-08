var datos = function (data) {
	var enero = 0, febrero = 0, marzo = 0, abril = 0, mayo = 0, junio = 0, julio = 0, agosto = 0, septiembre = 0, octubre = 0, noviembre = 0, diciembre = 0;
	for (var i = 0; i < data.length; i++) {
		console.log(data[i].fecha)
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
	console.log(data);
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
	
	$("#spinner-1").attr('class', 'fa fa-circle-notch fa-spin');
    $("#spinner-1").fadeIn("slow");

$.ajax({
	url: "/fechaEnvios",
	type: "GET",
	dataType: "json",
}).done(function (response) {
	console.log(response);

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
		
    $("#spinner-1").fadeOut("slow");

}).fail(function (xhr, status, errorThrown) {
    $("#spinner-1").attr('class', 'fa fa-exclamation-triangle');
    
   // makeChart();

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
	
	$("#spinner-2").attr('class', 'fa fa-circle-notch fa-spin');
    $("#spinner-2").fadeIn("slow");
		
	var randomColor = function(){
		let random = (min,max) => Math.floor((Math.random() * max) + min);
		let rgb = `rgb(212,${random(0,230)},${random(0,100)})`;
		console.log(rgb);
		return rgb;
	}

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
			console.log(empresas);
			
			let pieData = { datasets: [{
				data: [],
				backgroundColor: [],
				label: 'Envios'
			}],
			labels: []};

			let numeroenvios = 0;

			empresas.forEach(function (empresa, i, empresas) {
				console.log(empresa.nit);
				
				numeroenvios = 0;

				envios.forEach(function (envio, j, envios) {
					console.log(envio.empresa);

					if (envio.empresa.toLowerCase() === empresa.nit.toLowerCase()) {
						numeroenvios++;
					}
				});
				pieData.datasets['0'].data.push(numeroenvios.toString());
				pieData.datasets['0'].backgroundColor.push(randomColor());
				pieData.labels.push(empresa.nombre);
			});
			
			console.log(pieData);
			
	        $("#spinner-2").fadeOut("slow");

			let ctx = document.getElementById('pie-chart').getContext('2d');
			let chart = new Chart(ctx, {
			    type: 'pie',
			    data: pieData,
			    options: {
					responsive: true,
					legend: {display:false}
				}
			});

		}).fail(function (xhr, status, errorThrown) {
	        $("#spinner-2").attr('class', 'fa fa-exclamation-triangle');
			console.log('Failed Request To Servlet /scanTable')
			// makePie();
		});

	}).fail(function (xhr, status, errorThrown) {
        $("#spinner-2").attr('class', 'fa fa-exclamation-triangle');
		console.log('Failed Request To Servlet /scanTable')
		// makePie();
	});

}
