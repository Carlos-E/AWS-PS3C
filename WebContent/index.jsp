<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
		//response.sendRedirect("/login.jsp");
	}
	session.setAttribute("pagina", "PROTOTIPO SOFTWARE DE CARGA COMPARTIDA");
%>
<!DOCTYPE html>
<html lang="es">

<head>

<!--  HEAD -->
<jsp:include page="/head.jsp" />
<!--  ./HEAD -->
<title>PS3C</title>

</head>

<body>

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER -->
	<section class="row">
		<div class="col-md-12 col-lg-8">


			<div class="jumbotron">
				<button type="button" class="close" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="mb-4">Hola, Bienvenido!</h1>

				<p class="lead">Prototipo software de control de carga compartida.</p>
				<p>Este es un proyecto que tiene como finalidad ayudar en la logistica de transporte de empresas en Cartagena, mejorar la utilizacion de recursos y automatizar procesos de transporte, entre otros.</p>
			</div>
			<div class="card mb-4">

				<div class="card-block">

					<h3 class="card-title">Env&iacute;os</h3>
					<h6 class="card-subtitle mb-2 text-muted">Ultimos env&iacute;os</h6>
					<div class="canvas-wrapper">
						<canvas class="chart" id="line-chart" height="426" width="1282" style="width: 641px; height: 213px;"></canvas>
					</div>
				</div>
			</div>

		</div>
		<div class="col-md-12 col-lg-4">
			<div class="card mb-4">

				<div class="card-header">Universidad De Cartagena</div>
				<div class="card-block">
					<p>La Universidad de Cartagena ha sido el espacio de formación de los jóvenes del Caribe colombiano desde el siglo XIX. Su historia e importancia se expresan desde los albores de la independencia y en el sueño de los libertadores Simón Bolívar y Francisco de Paula Santander, organizadores
						del novel Estado colombiano. Ellos visionaron la educación como el medio ideal para la formación de las nuevas generaciones que conducirían los destinos de la República.</p>
				</div>
			</div>
		</div>
	</section>
	</main>

	<!--  FOOTER -->
	<jsp:include page="/footer.jsp" />

	<script>
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
	</script>

</body>

</html>