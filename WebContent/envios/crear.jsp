<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/login.jsp");
	}
	session.setAttribute("pagina", "Crear Envíos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">

<head>
<jsp:include page="/head.jsp" />
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
</head>

<body>
	<!-- INICIO -->
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!-- INICIO NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>
	<%
		ArrayList<Usuario> listaUsuarios = ControladorBD.escanearTabla("usuarios");
		ArrayList<Usuario> listaClientes = new ArrayList<Usuario>();
		ArrayList<Empresa> listaEmpresas = ControladorBD.escanearTabla("empresas");
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getRol().equals("cliente")) {
				if (!ControladorBD.estaOcupado(listaUsuarios.get(i).getNombre(), "null")) {
					listaClientes.add(listaUsuarios.get(i));
				}
			}
		}
	%>
	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<!-- INICIO CONTAINER -->

			<div class="card-block">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>

				<form class="form" action="/envios/crear" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">origen</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="origen" placeholder="origen" id="origen" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">destino</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="destino" placeholder="destino" id="destino" required>
						</div>
					</div>
					<div class="form-group row">
						<%
							if (session.getAttribute("rol").equals("admin") || session.getAttribute("rol").equals("empleado")) {
						%>
						<label class="col-md-2 col-form-label text-capitalize">cliente</label>
						<div class="col-md-4">
							<select class="form-control" name="cliente" id="cliente" required>
								<option value="" selected>Seleccionar...</option>
								<%
									for (int i = 0; i < listaClientes.size(); i++) {
								%>
								<option value="<%out.print(listaClientes.get(i).getUsuario());%>">
									<%
										out.print(listaClientes.get(i).getNombre());
									%>
								</option>
								<%
									}
								%>
							</select>
						</div>
						<%
							}
						%>
						<label class="col-md-2 col-form-label text-capitalize">Empresa</label>
						<div class="col-md-4">
							<select class="form-control" name="empresa" id="empresa" required>
								<option value="" selected>Seleccionar...</option>
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
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">tipo</label>
						<div class="col-md-4">
							<select class="custom-select" name="tipo" required>
								<option value="" selected>Seleccionar...</option>
								<option value="normal">normal</option>
								<option value="ligero">ligero</option>
								<option value="fragil">fragil</option>
								<option value="perecedero">perecedero</option>
							</select>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">espacio</label>
						<div class="col-md-4">
							<input class="form-control" type="number" name="espacio" placeholder="en metros cubicos" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Peso maximo</label>
						<div class="col-md-4">
							<input class="form-control" type="number" name="peso" placeholder="peso en kg" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Descripci&oacute;n</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="descripcion" placeholder="descripci&oacute;n" required>
						</div>
					</div>
					<input type="text" id="destinoLatLong" name="destinoLatLong" style="display: none">
					<input type="text" id="origenLatLong" name="origenLatLong" style="display: none">
					<!-- <input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
					<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
					<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
					<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none"> -->
					<div class="modal-footer">
						<button type="submit" name="submit" class="btn btn-primary btn-md float-right">Registrar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>

			<!-- /FIN CONTAINER -->
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

	<!--  INICIO FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!--  /FIN FOOTER CON SCRIPTS -->
	<!-- /FIN -->

	<script src="/js/calculo-ruta.js?v=1.1.7"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&libraries=places&callback=initMap"></script>
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
								
								/* document.getElementById('latitud_Destino').value = place.geometry.location
										.lat();
								document.getElementById('longitud_Destino').value = place.geometry.location
										.lng();
								 */
								document.getElementById('destinoLatLong').value = place.geometry.location
										.lat()
										+ "," + place.geometry.location.lng();
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
								console.log(latlon);
								getRoutes(latlon);
								/* document.getElementById('latitud_Origen').value = place.geometry.location
										.lat();
								document.getElementById('longitud_Origen').value = place.geometry.location
										.lng();
								 */
								document.getElementById('origenLatLong').value = place.geometry.location
								.lat()
								+ "," + place.geometry.location.lng();
							});
		};
	</script>
</body>

</html>