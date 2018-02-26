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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/img/favicon.ico">
<title>PS3C</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- Icons -->
<!-- <link href="./css/font-awesome.css" rel="stylesheet"> -->
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<!-- Custom styles for this template -->
<link href="/css/style.css" rel="stylesheet">

<link rel="stylesheet" href="/css/theme-teal.css" id="theme-css">
<!-- End Theme Switcher -->
</head>

<body>
	<div class="container-fluid" id="wrapper">
		<div class="row">

			<!--  Barra de navegacion -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./Barra de navegacion -->


			<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> 
			
			<!-- HEADER --> <jsp:include page="/header.jsp" /> <!--  HEADER -->

			<section class="row">
				<div class="col-sm-12">
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
									<!-- <div class="dropdown card-title-btn-container">
											<button class="btn btn-sm btn-subtle" type="button">
												<em class="fa fa-list-ul"></em> View All</button>
											<button class="btn btn-sm btn-subtle dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
												<em class="fa fa-cog"></em>
											</button>
											<div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
												<a class="dropdown-item" href="/index.html#">
													<em class="fa fa-search mr-1"></em> More info</a>
												<a class="dropdown-item" href="/index.html#">
													<em class="fa fa-thumb-tack mr-1"></em> Pin Window</a>
												<a class="dropdown-item" href="/index.html#">
													<em class="fa fa-remove mr-1"></em> Close Window</a>
											</div>
										</div> -->
									<h6 class="card-subtitle mb-2 text-muted">Ultimos env&iacute;os</h6>
									<div class="canvas-wrapper">
										<canvas class="chart" id="line-chart" height="426" width="1282" style="width: 641px; height: 213px;"></canvas>
									</div>
								</div>
							</div>

						</div>
						<div class="col-md-12 col-lg-4">
							<div class="card">

								<div class="card-header">Universidad De Cartagena</div>
								<div class="card-block">
									<p>La Universidad de Cartagena ha sido el espacio de formación de los jóvenes del Caribe colombiano desde el siglo XIX. Su historia e importancia se expresan desde los albores de la independencia y en el sueño de los libertadores Simón Bolívar y Francisco de Paula Santander,
										organizadores del novel Estado colombiano. Ellos visionaron la educación como el medio ideal para la formación de las nuevas generaciones que conducirían los destinos de la República.</p>
								</div>
							</div>
						</div>
					</section>
					<section class="row">
						<div class="col-12 mt-1 mb-4">
							Template by
							<a href="https://www.medialoot.com/">Medialoot</a>
						</div>
					</section>
				</div>
			</section>
			</main>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


	<script src="/js/chart.min.js"></script>
	<script src="/js/chart-data.js"></script>
	<script src="/js/custom.js"></script>
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

	<script src="./js/tether.min.js"></script>

</body>

</html>