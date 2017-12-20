<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
session.setAttribute("pagina", "Agregar Camion");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Camion</title>

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
		<br> <br>
		<div class="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="clases.*"%>
			<%
				ArrayList<usuario> listaUsuarios = ControladorBD.escanearTabla("usuarios");
				ArrayList<camion> listaCamiones = ControladorBD.escanearTabla("camiones");
				ArrayList<usuario> listaConductor = new ArrayList<usuario>();
				ArrayList<empresa> listaEmpresas = ControladorBD.escanearTabla("empresas");
				for (int i = 0; i < listaUsuarios.size(); i++) {
					if (listaUsuarios.get(i).getRol().equals("conductor")) {
						if (!ControladorBD.estaOcupado(listaUsuarios.get(i).getNombre(), "null")) {
							listaConductor.add(listaUsuarios.get(i));
						}
					}
				}
			%>
			<form id="form" name="form" action="/camion" method="post"
				class="form-horizontal">
				<%
					//Nombre de los campos del form
					String[] inputs = {"destino", "placa"};
					com.logica.Dibujar.inputs(out, inputs);
				%>
				<div class="form-group">
					<label class="control-label col-sm-2" for="destino">Camion:</label>
					<div class="col-sm-9">
						<input type="radio" name="tipo" onclick="mostrar()" value="camion">
					</div>
				</div>
				<!-- <input type="radio" name="tipo" onclick="mostrar()" value="camion" checked="checked">
	    	   -->
				<div class="form-group">
					<label class="control-label col-sm-2" for="destino">Remolque:</label>
					<div class="col-sm-9">
						<input type="radio" name="tipo" onclick="ocultar()"
							value="remolque" checked="checked">
					</div>
				</div>
				<!-- <input type="radio" name="tipo" onclick="ocultar()" value="remolque">
			 -->

				<div id="oculto" style="display: none;">
					<%
						//Nombre de los campos del form
						String[] inputs2 = {"capacidad", "espacio"};
						com.logica.Dibujar.inputs(out, inputs2);
					%>

				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="destino">Empresa:</label>
					<div class="col-sm-9">
						<select class="form-control" name="empresa" id="empresa">
							<%
								for (int i = 0; i < listaEmpresas.size(); i++) {
							%>
							<option value="<%out.print(listaEmpresas.get(i).getNit());%>">
								<%
									out.print(listaEmpresas.get(i).getNombre());
								%>
							</option>
							<%
								}
							%>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="destino">Conductor:</label>
					<div class="col-sm-9">
						<select class="form-control" name="conductor" id="conductor">
							<%
								for (int i = 0; i < listaConductor.size(); i++) {
							%>
							<option
								value="<%out.print(listaConductor.get(i).getUsuario());%>">
								<%
									out.print(listaConductor.get(i).getUsuario());
								%>
							</option>
							<%
								}
							%>
						</select>
					</div>
				</div>
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
					function mostrar() {
						document.getElementById('oculto').style.display = 'block';
					}

					function ocultar() {
						document.getElementById('oculto').style.display = 'none';
					}
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
				<input type="text" id="longitud_Destino" name="longitud_Destino"
					style="display: none"> <input type="text"
					id="latitud_Destino" name="latitud_Destino" style="display: none">
				<input type="text" id="latitud_Origen" name="latitud_Origen"
					style="display: none"> <input type="text"
					id="longitud_Origen" name="longitud_Origen" style="display: none">
				<%
					//Nombre de los campos del form
					String[] inputs4 = { "origen" };
					com.logica.Dibujar.inputs(out, inputs4);
				%>
				<div class="col-sm-2"></div>
				<button type="submit" name="submit" class="btn btn-primary">Registrar</button>
				<button formaction="/cancelar" name="submit" id="cancelar" type="submit"
						class="btn btn-danger">Cancelar</button>
			</form>
		</div>
		<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>