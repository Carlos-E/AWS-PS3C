<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Crear Camión");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<jsp:include page="/head.jsp" />
<title>Crear Env&iacute;o</title>
</head>
<body>
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>
	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> 
	<!--  HEADER --> 
		<jsp:include page="/header.jsp" /> 
	<!--  ./HEADER --> 
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
	<section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<div class="card-block">
				<h3 class="card-title"><% out.print(session.getAttribute("pagina").toString()); %></h3>
				<form class="form" action="/empleado" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Destino</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="destino" placeholder="destino" id="destino" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Origen</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="origen" placeholder="origen" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Placa</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="placa" placeholder="placa" id="placa" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Cami&oacute;n:</label>
						<div class="col-md-4">
							<input type="radio" name="tipo" onclick="mostrar()" value="camion">
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Remolque:</label>
						<div class="col-md-4">
							<input type="radio" name="tipo" onclick="ocultar()" value="remolque" checked="checked">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Empresa</label>
						<div class="col-md-4">
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
						<label class="col-md-2 col-form-label text-capitalize">Conductor</label>
						<div class="col-md-4">
						<select class="form-control" name="conductor" id="conductor">
								<%
									for (int i = 0; i < listaConductor.size(); i++) {
								%>
								<option value="<%out.print(listaConductor.get(i).getUsuario());%>">
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
					<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
					<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
					<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
					<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none">
					<div class="modal-footer">
						<button type="submit" name="submit" class="btn btn-primary btn-md float-right">Registrar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>

		</div>
	</div>
	</section> </main>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Confirmaci&oacute;n</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Desea cancelar?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
					<form name="form" action="/cancelar" method="post">
						<button type="submit" class="btn btn-danger btn-md float-right">Cancelar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />













	<!--



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Crear Camión</title>

<jsp:include page="/head.jsp" />

</head>
<body class="fondo">
 Header 
	<div class="container-fluid">
		<jsp:include page="/header.jsp" />
	</div>

	<!--  Barra de navegacion 
	<div class="container-fluid">
		<jsp:include page="/navbar.jsp" />
	</div>


	<div class="container">

		<form id="form" name="form" action="/camion" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS 

					<%
						//Nombre de los campos del form
						String[] inputs = {"destino", "placa"};
						com.logica.Dibujar.inputs(out, inputs);
					%>

					<div class="form-group">
						<label class="control-label col-sm-2" for="destino">Cami&oacute;n:</label>
						<div class="col-sm-9">
							<input type="radio" name="tipo" onclick="mostrar()" value="camion">
						</div>
					</div>
					<!-- <input type="radio" name="tipo" onclick="mostrar()" value="camion" checked="checked"> -->
					<div class="form-group">
						<label class="control-label col-sm-2" for="destino">Remolque:</label>
						<div class="col-sm-9">
							<input type="radio" name="tipo" onclick="ocultar()" value="remolque" checked="checked">
						</div>
					</div>
					<!-- <input type="radio" name="tipo" onclick="ocultar()" value="remolque"> -

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
								<option value="<%out.print(listaConductor.get(i).getUsuario());%>">
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

					<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
					<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
					<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
					<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none">
					<%
						//Nombre de los campos del form
						String[] inputs4 = {"origen"};
						com.logica.Dibujar.inputs(out, inputs4);
					%>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde 
					<button type="submit" name="submit" class="btn btn-primary">Registrar</button>
				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo --
					<button formaction="/cancelar" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>
				</div>
				<div class="col-sm-8"></div>
			</div>
		</form>
	</div>

	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
-->
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