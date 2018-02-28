var randomScalingFactor = function() {
	return Math.round(Math.random() * 1000 + 3)
};

var lineChartData = {
		labels : [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio" ],
		datasets : [ {
			label : "My First dataset",
			fillColor : "rgba(128,130,228,0.6)",
			strokeColor : "rgba(128,130,228,1)",
			pointColor : "rgba(128,130,228,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(128,130,228,1)",
			data : [ "0", randomScalingFactor(),
					randomScalingFactor(), randomScalingFactor(),
					randomScalingFactor(), randomScalingFactor(),
					randomScalingFactor() ]
		} ]
	};

console.log("Random: ",lineChartData.datasets['0'].data.toString());

$.get( "/fechaEnvios", function( data ) {
	
	lineChartData.datasets['0'].data = ['0','0','0','0','0','0','0'];
	
	console.log('After Get: ' + lineChartData.datasets['0'].data.toString());

		var startCharts = function() {
			var chart1 = document.getElementById("line-chart").getContext("2d");
			window.myLine = new Chart(chart1).Line(lineChartData, {
				responsive : true,
				scaleLineColor : "rgba(0,0,0,.2)",
				scaleGridLineColor : "rgba(0,0,0,.05)",
				scaleFontColor : "#c5c7cc "
			});
		};
		window.setTimeout(startCharts(), 1000);

	});