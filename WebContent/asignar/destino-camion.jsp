<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Asignar Ruta a Cami&oacute;n");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Asignar Destino-Cami&oacute;n</title>

<jsp:include page="/head.jsp" />

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
		<%@ page import="com.logica.*"%>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="clases.*"%>
		<%
			ArrayList<Camion> listaCamion = com.logica.ControladorBD.escanearTabla("camiones");
		%>
			<form id="form" name="form" class="form" action="/destinoACamion" method="post">
				<table class="table">
					<thead>
						<tr>
							<td style="border: 0px !important;">
								<div id="contact-form">
									<span>Cami&oacute;n: </span>
									<select class="form-control" id="subject" name="trailer">
										<%
											for (int i = 0; i < listaCamion.size(); i++) {
										%>
										<option value="<%out.print(listaCamion.get(i).getPlaca());%>">
											<%
												out.print(listaCamion.get(i).getPlaca());
											%>
										</option>
										<%
											}
										%>
									</select>
								</div>
							</td>
							<td style="border: 0px !important;">
								<div id="contact-form2">
									<span>Destino: </span>
									<br>
									<input class="form-control" id="destino" type="text" name="destino">
								</div>
							</td>
						</tr>
				</table>

				<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCwUOXR0TZ7pyQhLJAuA6_U6Ffg92YMkLk&libraries=places"></script>
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
				<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
				<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
				<!-- <input class="submit" id="subject" type="submit" name="asignar" value="Asignar" >
                 -->
				<button id="subject" type="submit" name="asignar" value="Asignar" class="btn btn-primary">Asignar</button>
				<button formaction="/cancelar" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>
			</form>
		</div>
	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>