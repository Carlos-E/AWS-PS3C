<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/error.jsp");
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
				<h1 class="mb-1">Hola, Bienvenido!</h1>
				<p class="lead">Prototipo software de control de carga compartida.</p>
				<p>Este es un proyecto que tiene como finalidad ayudar en la logistica de transporte de empresas en Cartagena, mejorar la utilizacion de recursos y automatizar procesos de transporte, entre otros.</p>
			</div>

			<div class="card">
				<div class="card-block">

					<h3 class="card-title">
						Env&iacute;os
						<i id="spinner-1" class="fa fa-circle-notch fa-spin" style="font-size: 30px"></i>
					</h3>
					<h6 class="card-subtitle mb-1 text-muted">Ultimos env&iacute;os</h6>
					<div class="canvas-wrapper">
						<canvas class="chart" id="line-chart" height="426" width="1282" style="width: 641px; height: 213px;"></canvas>
					</div>
				</div>
			</div>

		</div>

		<div class="col-md-12 col-lg-4">

			<div class="card mt-4">
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

			<div class="card mt-4">

				<div class="card-header">PS3C ANDROID</div>
				<div class="card-block">
					<p>Esta herramienta cuenta con seguimiento a transporte en tiempo real por medio de una aplicaci&oacute;n movil Android. La aplicaci&oacute;n posee un gestor de env&iacute;os donde los trasportadores pueden confirmar la recogida y entrega del env&iacute;o, as&iacute; como una opci&oacute;n
						para generar reportes, ya sea del estado de las v&iacute;as o eventualidades, tamb&iacute;en la aplicacion sugiere una ruta con un mapa siempre y cuando el env&iacute;o tenga las ubicaciones correctamente ingresadas.</p>
				</div>

			</div>

		</div>
	</section>
	</main>

	<!--  FOOTER -->
	<jsp:include page="/footer.jsp" />

	<script src="/js/chart.min.js"></script>
	<script src="/js/chart-data.js"></script>

	<script>
		makeChart();
		makePie();
	</script>

</body>

</html>