<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Empresa</title>
<jsp:include page="/head.jsp" />
<% session.setAttribute("pagina", "Modificar Mercancía"); %>
</head>
<body>
	<!-- Header  -->
	<!--  Container de la Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />
	<div class="fondo">
	<br><br>
		<div class="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="clases.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%
				ArrayList<envio> listaEnvio = ControladorBD.escanearTabla("envios");
			%>
			<%
				if (session.getAttribute("busca") != "mercancia") {
			%>
			<form id="form" name="form" action="/buscar" method="post"
				class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-2" for="camiones">
						Mercancia </label>
					<div class="col-sm-9">
						<select class="form-control" id="subject" name="envio"
							tabindex="4">
							<%
								for (int i = 0; i < listaEnvio.size(); i++) {
							%>
							<option
								value="<%out.print(listaEnvio.get(i).getUsuario() + " : " + listaEnvio.get(i).getFecha());%>">
								<%
									out.print(listaEnvio.get(i).getUsuario() + " : " + listaEnvio.get(i).getFecha());
								%>
							</option>
							<%
								}
							%>
						</select>
					</div>
				</div>
				<div class="col-sm-2"></div>

				<button type="submit" name="submit" class="btn btn-primary">Buscar</button>
			</form>
			<%
				} else {
					envio envio = new envio();
					envio = (envio) com.logica.ControladorBD.getItem("envios", "usuario",
							session.getAttribute("obj1").toString(), "fecha", session.getAttribute("obj2").toString());
			%>
			<form id="form" name="form" class="form"
				action="../modificarMercancia" method="post">
				<%
					//Nombre de los campos del form			
						String[] inputs = {"espacio", "tipo", "empresa", "estado", "usuario", "destino", "origen",
								"tiempoCarga", "tiempoDescarga"};
						String[] values = {envio.getEspacio(), envio.getTipo(), envio.getEmpresa(), envio.getEstado(),
								envio.getUsuario(), envio.getDestino(), envio.getOrigen(), envio.getTiempoCarga(),
								envio.getTiempoDescargaUsuario()};
						com.logica.Dibujar.inputs(out, inputs, values);
				%>
				<div class="col-sm-2"></div>
				<div class="form-vertical">
					<button name="submit" id="submit" type="submit"
						class="btn btn-primary">Modificar</button>
				</div>
			</form>
			<div class="col-sm-2"></div>
			<form id="form" name="form" action="/cancelar" method="post">
				<div class="form-vertical">
					<button name="submit" id="cancelar" type="submit"
						class="btn btn-danger">Cancelar</button>
				</div>
			</form>
			<%
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
												+ place.geometry.location.lng();
										document
												.getElementById('latitud_Destino').value = place.geometry.location
												.lat();
										document
												.getElementById('longitud_Destino').value = place.geometry.location
												.lng();
									});
				};
			</script>
			<script>
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
												+ place.geometry.location.lng();
										document
												.getElementById('latitud_Origen').value = place.geometry.location
												.lat();
										document
												.getElementById('longitud_Origen').value = place.geometry.location
												.lng();
									});
				};
			</script>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>