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
<title>Crear Envio</title>
<jsp:include page="/head.jsp" />
<style type="text/css">
#map {
	height: 100%;
}

/*Propiedad de bootstrap sobreescrita aqui para la dimension del mapa*/
.embed-responsive-16by9 {
	padding-bottom: 40.00%;
}
</style>

</head>
<body class="fondo">

	<!-- Header -->
	<div class="container-fluid">
		<jsp:include page="/header.jsp" />
	</div>

	<!--  Barra de navegacion -->
	<div class="container-fluid">
		<jsp:include page="/navbar.jsp" />
	</div>

	<div class="container">

		<form id="form" name="form" action="/envio" method="post" class="form-horizontal">

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
		<br>
	</div>
	<div class="container-fluid">

		<jsp:include page="/footer.jsp" />
	</div>
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