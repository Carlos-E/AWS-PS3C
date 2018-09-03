<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/login.jsp");
	}
	/* 	session.setAttribute("pagina",
				"<span style=\"color: rgba(255, 210, 20, 1); text-shadow: 1px 1px black;\"><em class=\"fa fa-truck fa-lg\" style=\"color: rgb(219, 130, 92);\"></em>PS3C</span>");
	 */ session.setAttribute("pagina", "Inicio");
%>
<!DOCTYPE html>
<html lang="es">

<head>
<style>
@import url('https://fonts.googleapis.com/css?family=Faster+One');

.titulo h1 {
	/* font-family: 'Faster One', cursive;
	font-weight: normal; */
	
}
</style>
<!--  HEAD -->
<jsp:include page="/head.jsp" />
<!--  ./HEAD -->
<title>PS3C</title>

</head>

<body>

<div id="progressBar" class="progress-bar progress-bar-striped progress-bar-animated bg-warning" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 0%;position: fixed;left: auto;top: -1px;z-index:10000;"></div>
 
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER -->
	<section class="row">

		<div class="col-md-12 col-lg-7">

			<div class="jumbotron">
				<button type="button" class="close" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="mb-1">Hola, bienvenido!</h1>
				<p class="lead">
					Prototipo Software de Control de Carga Compartida (<span>PS3C</span>)
				</p>
				<p align="justify">Este es un proyecto que tiene como finalidad ayudar en la log&iacute;stica de transporte de empresas en Cartagena, mejorar la utilizaci&oacute;n de recursos y automatizar procesos de transporte, entre otros.</p>
			</div>

			<div class="card mb-3">
				<div class="card-block">

					<h3 class="card-title">
						Env&iacute;os
						<i id="spinner-1" class="fa fa-circle-notch fa-spin" style="font-size: 30px"></i>
					</h3>
					<h6 class="card-subtitle mb-1 text-muted">&Uacute;ltimos env&iacute;os</h6>
					<div class="canvas-wrapper">
						<canvas class="chart" id="line-chart" height="426" width="1282" style="width: 641px; height: 213px;"></canvas>
					</div>
				</div>
			</div>

			<div class="card mb-3">

				<div class="card-header">PS3C ANDROID</div>
				<div class="card-block">
					<p align="justify">Esta herramienta cuenta con seguimiento de transporte por medio de una aplicaci&oacute;n movil Android. La aplicaci&oacute;n posee un gestor de env&iacute;os donde los trasportadores pueden confirmar la recogida y entrega del env&iacute;o, as&iacute; como una
						opci&oacute;n para generar reportes, ya sea del estado de las v&iacute;as o eventualidades, tamb&iacute;en la aplicaci&oacute;n sugiere una ruta con un mapa siempre y cuando el env&iacute;o tenga las ubicaciones correctamente ingresadas.</p>
					<p>
						¿Eres conductor o camionero?
						<a href="https://s3.amazonaws.com/ps3c/app.apk" target="_blank">
							Descargala Aqu&iacute;!!
							<em class="fa fa-download"></em>
						</a>
					</p>
				</div>

			</div>

		</div>

		<div class="col-md-12 col-lg-5">

			<div class="card mb-3">
				<div class="card-block">
					<h3 class="card-title">
						Env&iacute;os
						<i id="spinner-2" class="fa fa-circle-notch fa-spin" style="font-size: 30px"></i>
					</h3>
					<h6 class="card-subtitle mb-2 text-muted">Por empresa</h6>
					<div class="canvas-wrapper">
						<canvas class="chart" id="pie-chart" height="368" width="738" style="width: 369px; height: 184px;"></canvas>
					</div>
					<h6 class="card-subtitle mb-2 text-muted"></h6>
					<div class="canvas-wrapper" id="empresas-colores"></div>

				</div>
			</div>

			<div class="card mb-3">
				<div class="card-block">

					<h3 class="card-title">
						Veh&iacute;culos
						<i id="spinner-3" class="fa fa-circle-notch fa-spin" style="font-size: 30px"></i>
					</h3>
					<h6 class="card-subtitle mb-1 text-muted">Por empresa</h6>
					<div class="canvas-wrapper">
						<canvas class="chart" id="doughnut-chart" height="426" width="1282" style="width: 641px; height: 213px;"></canvas>
					</div>
				</div>
			</div>

		</div>
	</section>
	</main>

	<!--  FOOTER -->
	<jsp:include page="/footer.jsp" />

	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.2.1/Chart.min.js"></script>
	<script src="/js/chart-data.js"></script>

	<script>
		setTimeout(function() {
			makeChart();
			makePie();
			makeDoughnut();
		}, 1000);

		var audio = new Audio('/audio/song.mp3');
		//audio.play();
		
		function spinSpinner(){
			document.querySelector('#nav-brand-link').innerHTML = '<em class="fa fa-truck fa-spin fa-2x quick"></em> PS3C';
			
			setTimeout(
					function() {
						document.querySelector('#nav-brand-link').innerHTML = '<em class="fa fa-truck fa-2x"></em> PS3C';
					}, 3000);
		}
		
		//spinSpinner();

	</script>

</body>

</html>