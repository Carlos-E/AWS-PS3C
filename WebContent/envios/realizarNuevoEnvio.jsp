<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
session.setAttribute("pagina", "Envíos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Nuevo Envio</title>
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
<body>
	<!-- Header  -->
	<!--  Container de la Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />
	<!-- Contenido -->
	<div class="fondo">
	<br>
	<br>
		<div class="container" >
			<form id="form" name="form" action="/envio" method="post" class="form-horizontal">
				<%
					//Nombre de los campos del form
					String[] inputs = {"destino", "origen"};
					com.logica.Dibujar.inputs(out, inputs);

					if (session.getAttribute("rol") == "admin" || session.getAttribute("rol") == "empleado") {

						//Nombre de los campos del form
						String[] inputs2 = {"cliente"};
						com.logica.Dibujar.inputs(out, inputs2);

					}
				%>
				<script type="text/javascript"
					src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwUOXR0TZ7pyQhLJAuA6_U6Ffg92YMkLk&libraries=places"></script>
				<script type="text/javascript">
					google.maps.event.addDomListener(window, 'load', intilize);
					function intilize() {
						var autocomplete = new google.maps.places.Autocomplete(
								document.getElementById("destino"));
						google.maps.event
								.addListener(
										autocomplete,
										'place_changed',
										function() {
											var place = autocomplete.getPlace();
											var latlon = place.geometry.location
													.lat()
													+ ","
													+ place.geometry.location
															.lng();
											document
													.getElementById('latitud_Destino').value = place.geometry.location
													.lat();
											document
													.getElementById('longitud_Destino').value = place.geometry.location
													.lng();
										});
					};
				</script>
				<script type="text/javascript">
					google.maps.event.addDomListener(window, 'load', intilize);
					function intilize() {
						var autocomplete = new google.maps.places.Autocomplete(
								document.getElementById("origen"));
						google.maps.event
								.addListener(
										autocomplete,
										'place_changed',
										function() {
											var place = autocomplete.getPlace();
											var latlon = place.geometry.location
													.lat()
													+ ","
													+ place.geometry.location
															.lng();
											document
													.getElementById('latitud_Origen').value = place.geometry.location
													.lat();
											document
													.getElementById('longitud_Origen').value = place.geometry.location
													.lng();
										});
					};
				</script>
				<%
					String[] inputs3 = { "tipo", "espacio" };
					com.logica.Dibujar.inputs(out, inputs3);
				%>
				<input type="text" id="longitud_Destino" name="longitud_Destino"
					style="display: none"> <input type="text"
					id="latitud_Destino" name="latitud_Destino" style="display: none">
				<input type="text" id="latitud_Origen" name="latitud_Origen"
					style="display: none"> <input type="text"
					id="longitud_Origen" name="longitud_Origen" style="display: none">
				<%
					System.out.println();
				%>
				<div class="col-sm-2"></div>
				<button name="submit" id="submit" type="submit"
					class="btn btn-primary">Enviar</button>
			</form>
			<div class="col-sm-2"></div>
			<form id="form" name="form" action="/cancelar" method="post" class="form-horizontal" >
				<button name="submit" id="cancelar" type="submit"
					class="btn btn-danger">Cancelar</button>
			</form>			
		</div>
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>