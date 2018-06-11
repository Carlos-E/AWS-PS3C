<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Crear Trailer");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<jsp:include page="/head.jsp" />
<title><%out.print(session.getAttribute("pagina").toString());%></title>
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
		ArrayList<Usuario> listaUsuarios = ControladorBD.escanearTabla("usuarios");
		ArrayList<Camion> listaCamiones = ControladorBD.escanearTabla("camiones");
		ArrayList<Usuario> listaConductor = new ArrayList<Usuario>();
		ArrayList<Empresa> listaEmpresas = ControladorBD.escanearTabla("empresas");
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
				<form class="form" action="/trailer" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Patente</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="patente" placeholder="patente" id="patente" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Tipo</label>
						<div class="col-md-4">
							<select class="custom-select" name="tipo" id="tipo" required>
								<option value="" selected>Seleccionar...</option>
								<option value="RABON (1 EJE)">RABON (1 EJE)</option>
								<option value="TORTON (2 EJES)">TORTON (2 EJES)</option>
								<option value="CAJA CERRADA DE 53 PIES">CAJA CERRADA DE 53 PIES</option>
								<option value="CAJA CERRADA DE 48 PIES">CAJA CERRADA DE 48 PIES</option>
								<option value="FULL / DOBLE SEMIREMOLQUE">FULL / DOBLE SEMIREMOLQUE</option>
								<option value="CAJA REFRIGERADA">CAJA REFRIGERADA</option>
								<option value="PLATAFORMA">PLATAFORMA</option>
								<option value="AUTOTANQUE / PIPA">AUTOTANQUE / PIPA</option>
								<option value="AUTOTANQUE PARA ASFALTO / GRANEL">AUTOTANQUE PARA ASFALTO / GRANEL</option>
								<option value="JAULA A GRANEL / GRANELERA">JAULA A GRANEL / GRANELERA</option>
								<option value="JAULA GANADERA">JAULA GANADERA</option>
								<option value="JAULA ENLONADA / CORTINA">JAULA ENLONADA / CORTINA</option>
								<option value="LOW BOY / CAMA BAJA">LOW BOY / CAMA BAJA</option>
								<option value="MADRINA / PORTA VEHÍCULOS">MADRINA / PORTA VEHÍCULOS</option>
								<option value="TOLVA">TOLVA</option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Destino</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="destino" placeholder="destino" id="destino" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Origen</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="origen" placeholder="origen" id="origen" required>
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
						<label class="col-md-2 col-form-label text-capitalize">Cami&oacute;n:</label>
						<div class="col-md-4">
							<select class="form-control" name="camion" id="camion">
								<%
									for (int i = 0; i < listaCamiones.size(); i++) {
										if (!ControladorBD.estaOcupado("null", listaCamiones.get(i).getPlaca())
												&& listaCamiones.get(i).getTipo().equals("remolque")) {
											ControladorBD.actualizarValor("camiones", "placa", listaCamiones.get(i).getPlaca(), "estado",
													"Asignado");
								%>
								<option value="<%out.print(listaCamiones.get(i).getPlaca());%>">
									<%
										out.print(listaCamiones.get(i).getPlaca());
									%> conducido por:
									<%
										out.print(listaCamiones.get(i).getUsuario());
									%>
								</option>
								<%
									}
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Peso maximo</label>
						<div class="col-md-4">
							<input class="form-control" type="number" name="peso" placeholder="peso en kg" id="peso" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Espacio</label>
						<div class="col-md-4">
							<input class="form-control" type="number" name="espacio" placeholder="en metros cubicos" id="espacio" required>
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
			document.getElementById('oculto').style.display = '';
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