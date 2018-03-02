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
		label: "My First dataset",
		fillColor: "rgba(128,130,228,0.6)",
		strokeColor: "rgba(128,130,228,1)",
		pointColor: "rgba(128,130,228,1)",
		pointStrokeColor: "#fff",
		pointHighlightFill: "#fff",
		pointHighlightStroke: "rgba(128,130,228,1)",
		data: ['0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0']
	}]
};

console.log("Random: ", lineChartData.datasets['0'].data.toString());

$.get("/fechaEnvios", function (data) {

	lineChartData.datasets['0'].data = datos(data);

	console.log('After Get: ' + lineChartData.datasets['0'].data.toString());

	var startCharts = function () {
		var chart1 = document.getElementById("line-chart").getContext("2d");
		window.myLine = new Chart(chart1).Line(lineChartData, {
			responsive: true,
			scaleLineColor: "rgba(0,0,0,.2)",
			scaleGridLineColor: "rgba(0,0,0,.05)",
			scaleFontColor: "#c5c7cc "
		});
	};
	window.setTimeout(startCharts(), 1000);

});

var pieDataExample = [{
	value: 300,
	color: "#8082e4",
	highlight: "#7376df",
	label: "Value 1"
}, {
	value: 50,
	color: "#a0a0a0",
	highlight: "#999999",
	label: "Value 2"
}];

function makePie() {
	console.log('Making Pie');
	let pieData = [];
	
	var randomColor = function(){
		let random = (min,max) => Math.floor((Math.random() * max) + min);
		let rgb = `rgb(${random(1,170)},${random(1,200)},255)`;
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

			empresas.forEach(function (empresa, i, empresas) {
				console.log(empresa.nit);

				let data = {
					value: 0,
					color: randomColor(),
					highlight: "#999999",
					label: empresa.nombre
				};

				envios.forEach(function (envio, j, envios) {
					console.log(envio.empresa);

					if (envio.empresa.toLowerCase() === empresa.nit.toLowerCase()) {
						data.value++;
					}
				});
				pieData.push(data);
			});
			
			pieData = [
				{
					value: 300,
					color: randomColor(),
					highlight: "#7376df",
					label: "Value 1"
				},
				{
					value: 50,
					color: randomColor(),
					highlight: "#999999",
					label: "Value 2"
				},
				{
					value: 100,
					color:randomColor(),
					highlight: "#cccccc",
					label: "Value 3"
				},
				{
					value: 120,
					color: randomColor(),
					highlight: "#eeeeee",
					label: "Value 4"
				}

			];
			
			console.log(pieData);

			var pieChart = document.getElementById("pie-chart").getContext("2d");
			window.myPie = new Chart(pieChart).Pie(pieData, {
				responsive: true,
				segmentShowStroke: false
			});

		}).fail(function (xhr, status, errorThrown) {
			alert("Algo ha salido mal");
			console.log('Failed Request To Servlet /scanTable')
		})

	}).fail(function (xhr, status, errorThrown) {
		alert("Algo ha salido mal");
		console.log('Failed Request To Servlet /scanTable')
	})

}
