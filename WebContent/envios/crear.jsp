<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Crear Envíos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Crear Env&iacute;o</title>
<jsp:include page="/head.jsp" />
</head>
<body>

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">

			<div class="card-block">
				<h3 class="card-title">Crear Env&iacute;o</h3>
				<form class="form" action="#">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">origen</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="origen" placeholder="origen" id="origen">
						</div>
						<label class="col-md-2 col-form-label text-capitalize">destino</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="destino" placeholder="destino" id="destino">
						</div>
					</div>
					<div class="form-group row">

						<%
							if (session.getAttribute("rol") == "admin" || session.getAttribute("rol") == "empleado") {
						%>
						<label class="col-md-2 col-form-label text-capitalize">cliente</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="cliente" placeholder="cliente" >
						</div>
						<%
							}
						%>
						<label class="col-md-2 col-form-label text-capitalize">tipo</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tipo" placeholder="tipo">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">espacio</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="espacio" placeholder="espacio">
						</div>
					</div>
					<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
					<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
					<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
					<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none">
					<div class="form-group">
						<div class="col-12 widget-right">
							<button name="submit" id="submit" type="submit" class="btn btn-danger btn-md float-right" formaction="/cancelar">Cancelar</button>
						</div>
						<div class="col widget-right">
							<button type="submit" name="submit" class="btn btn-primary btn-md float-right">Registrar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section> <section class="row"> <!--  FOOTER --> <jsp:include page="/footer.jsp" /> <!--  ./FOOTER --> </section> </section> </main>

	<form id="form" name="form" action="/envio" method="post" class="form-horizontal" hidden>

		<div class="row">

			<div class="col-sm-6">
				<!-- Nombre de los campos del form  -->
				<%
					com.logica.Dibujar.input(out, "origen");
				%>
				<%
					com.logica.Dibujar.input(out, "destino");
				%>
				<%
					if (session.getAttribute("rol") == "admin" || session.getAttribute("rol") == "empleado") {

						//Nombre de los campos del form
						com.logica.Dibujar.input(out, "cliente");

					}
				%>
				<%
					com.logica.Dibujar.input(out, "tipo");
					com.logica.Dibujar.input(out, "espacio");
				%>
				<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
				<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
				<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
				<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none">
				<%
					System.out.println();
				%>
			</div>

			<div class="col-sm-6"></div>

		</div>

		<div class="row">

			<div class="col-sm-1"></div>

			<div class="col-sm-1">
				<button type="submit" name="submit" class="btn btn-primary">Registrar</button>
			</div>

			<div class="col-sm-1">
				<button name="submit" id="submit" type="submit" class="btn btn-danger" formaction="/cancelar">Cancelar</button>
			</div>

			<div class="col-sm-8"></div>

		</div>

	</form>

	<!--  SCRIPTS -->
	<jsp:include page="/scripts.jsp" />
	<!--  ./SCRIPTS -->

	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwUOXR0TZ7pyQhLJAuA6_U6Ffg92YMkLk&libraries=places"></script>
	<script type="text/javascript">
		google.maps.event.addDomListener(window, 'load', intilize);
		function intilize() {
			var autocomplete = new google.maps.places.Autocomplete(document
					.getElementById("destino"));
			google.maps.event
					.addListener(
							autocomplete,
							'place_changed',
							function() {
								var place = autocomplete.getPlace();
								var latlon = place.geometry.location.lat()
										+ "," + place.geometry.location.lng();
								document.getElementById('latitud_Destino').value = place.geometry.location
										.lat();
								document.getElementById('longitud_Destino').value = place.geometry.location
										.lng();
							});
		};
	</script>
	<script type="text/javascript">
		google.maps.event.addDomListener(window, 'load', intilize);
		function intilize() {
			var autocomplete = new google.maps.places.Autocomplete(document
					.getElementById("origen"));
			google.maps.event
					.addListener(
							autocomplete,
							'place_changed',
							function() {
								var place = autocomplete.getPlace();
								var latlon = place.geometry.location.lat()
										+ "," + place.geometry.location.lng();
								document.getElementById('latitud_Origen').value = place.geometry.location
										.lat();
								document.getElementById('longitud_Origen').value = place.geometry.location
										.lng();
							});
		};
	</script>
</body>
</html>