<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/login-movil.jsp");
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

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<%@ page import="com.logica.*"%>
			<%@ page import="clases.*"%>
			<%
				usuario usuario = new usuario();
				usuario = (usuario) com.logica.ControladorBD.getItem("usuarios", "usuario",
						session.getAttribute("username").toString());
			%>
			<nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2">
				<h1 class="site-title">
					<a id="nav-brand-link" href="/index.jsp">
						<em class="fa fa-truck fa-2x"></em>
						PS3C
					</a>
				</h1>
				<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">
					<em class="fa fa-bars"></em>
				</a>
				<ul class="nav nav-pills flex-column sidebar-nav">
					<li class="nav-item">
						<a class="nav-link active" href="/index.jsp">
							<em class="fa fa-tachometer-alt"></em>
							Inicio
							<span class="sr-only">(current)</span>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/transportador/envios.jsp">
							Env&iacute;os
							<em class="fa fa-paper-plane">&nbsp;</em>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/transportador/reportes.jsp">
							Reportes
							<em class="fa fa-newspaper">&nbsp;</em>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/transportador/mapa.jsp">
							Mapa
							<em class="fa fa-map">&nbsp;</em>
						</a>
					</li>

				</ul>
				<a href="/logoutMovil" class="nav-link">
					<em class="fa fa-power-off"></em>
					Cerrar Sesi&oacute;n
				</a>
			</nav>
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER -->
	<section class="row">

		<div class="col-md-12 col-lg-7">

			<div class="card mb-3">

				<div class="card-header">PS3C ANDROID</div>
				<div class="card-block">
					<p align="justify">Esta herramienta cuenta con seguimiento de transporte por medio de una aplicaci&oacute;n movil Android. La aplicaci&oacute;n posee un gestor de env&iacute;os donde los trasportadores pueden confirmar la recogida y entrega del env&iacute;o, as&iacute; como una
						opci&oacute;n para generar reportes, ya sea del estado de las v&iacute;as o eventualidades, tamb&iacute;en la aplicacion sugiere una ruta con un mapa siempre y cuando el env&iacute;o tenga las ubicaciones correctamente ingresadas.</p>
					<p></p>
				</div>

			</div>

		</div>

	</section>
	</main>

	<!--  FOOTER -->
	<jsp:include page="/footer.jsp" />


	<script>
		
	</script>

</body>

</html>