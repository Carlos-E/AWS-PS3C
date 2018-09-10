<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/movil/login.jsp");
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
<jsp:include page="/movil/head.jsp" />
<!--  ./HEAD -->
<title>PS3C</title>

</head>

<body>

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/movil/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/movil/header.jsp" /> <!--  ./HEADER -->
	<section class="row">

		<div class="col-md-12 col-lg-7">
			
			<div class="card mb-3">

				<div class="card-header">PS3C ANDROID</div>
				<div class="card-block">
					<p align="justify">Esta herramienta cuenta con seguimiento de transporte por medio de una aplicaci&oacute;n m&oacute;vil Android. La aplicaci&oacute;n posee un gestor de env&iacute;os donde los trasportadores pueden confirmar la recogida y entrega del env&iacute;o, as&iacute; como una
						opci&oacute;n para generar reportes, ya sea del estado de las v&iacute;as o eventualidades, tamb&iacute;en la aplicacion sugiere una ruta con un mapa siempre y cuando el env&iacute;o tenga las ubicaciones correctamente ingresadas.</p>
					<p>
						Eres conductor o camionero?
						<a href="https://s3.amazonaws.com/ps3c/app.apk" target="_blank">
							Descargala aqu&iacute;!!
							<em class="fa fa-download"></em>
						</a>
					</p>
				</div>

			</div>

		</div>

	</section>
	</main>

	<!--  FOOTER -->
	<jsp:include page="/movil/footer.jsp" />


	<script>
		if (typeof Android != 'undefined') {
			Android.setPlaca('<% out.print(session.getAttribute("placa").toString()); %>');
			Android.toggleBestUpdates(true);
		}
	</script>

</body>

</html>