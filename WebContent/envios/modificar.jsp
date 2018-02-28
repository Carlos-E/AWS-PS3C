<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Modificar Envíos");
	ArrayList<envio> listaEnvio = ControladorBD.escanearTabla("envios");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="/head.jsp" />
<title>Modificar Envíos</title>
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

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<!-- INICIO CONTAINER -->

			<div class="card-block" id="buscar-form">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form class="form" name="form" method="post">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Custom Select</label>
						<div class="col-md-9">
							<select class="custom-select form-control" id="select">
								<%
									for (int i = 0; i < listaEnvio.size(); i++) {
								%>
								<option value="<%out.print(listaEnvio.get(i).getUsuario() + " : " + listaEnvio.get(i).getFecha());%>">
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
					<div class="modal-footer">
						<button id="buscar" type="button" data-toggle="modal" data-target="#" class="btn btn-primary btn-md float-right">Buscar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>

			<div class="card-block" id="modificar-form" hidden="true">
				<h3 class="card-title">Crear Env&iacute;o</h3>
				<form class="form-modificar" action="/modificarMercancia" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">cliente</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="cliente" placeholder="" id="cliente" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">fecha</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="fecha" placeholder="" id="fecha" readonly>
						</div>
					</div>
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
						<label class="col-md-2 col-form-label text-capitalize">usuario</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="usuario" placeholder="usuario" id="usuario" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">tipo</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tipo" placeholder="tipo" id="tipo" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">espacio</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="espacio" placeholder="espacio" id="espacio" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">estado</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="estado" placeholder="estado" id="estado" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">tiempoCarga</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tiempoCarga" placeholder="tiempoCarga" id="tiempoCarga" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">tiempoDescarga</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tiempoDescarga" placeholder="tiempoDescarga" id="tiempoDescarga" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">empresa</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="empresa" placeholder="empresa" id="empresa" required>
						</div>
						
					</div>
					<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
					<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
					<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
					<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none">
					<div class="modal-footer">
						<button id="modificar" type="submit" name="submit" class="btn btn-primary btn-md float-right">Modificar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>
			<%-- 
			<!-- SEGUNDO FORM -->
			<form id="form" name="form" class="form" action="../modificarMercancia" method="post">

				<div class="row">

					<div class="col-sm-6">

						<!-- INPUTS -->
						<div class="form-horizontal">

							<%
								//Nombre de los campos del form			
									String[] inputs = {"espacioX", "tipoX", "empresa", "estadoX", "usuarioX", "destinoX", "origenX",
											"tiempoCarga", "tiempoDescarga"};
									String[] values = {envio.getEspacio(), envio.getTipo(), envio.getEmpresa(), envio.getEstado(),
											envio.getUsuario(), envio.getDestino(), envio.getOrigen(), envio.getTiempoCarga(),
											envio.getTiempoDescargaUsuario()};
									com.logica.Dibujar.inputs(out, inputs, values);
							%>
						</div>

					</div>

					<div class="col-sm-6"></div>

				</div>

				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-1">
						<!-- Boton Verde -->
						<button name="submit" id="submit" type="submit" class="btn btn-primary">Modificar</button>

					</div>
					<div class="col-sm-1">
						<!-- Boton Rojo -->
						<button formaction="/cancelar" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>

					</div>
					<div class="col-sm-8"></div>
				</div>

			</form>

 --%>


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

	<!-- INICIO FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!--  /FIN FOOTER CON SCRIPTS -->
	<!-- /FIN -->


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
	<script>
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
	<script>
		$(document).ready(function() {

			$('#buscar').click(function() {

				let selected = $('#select').find(":selected").val();

				var campos = selected.split(' : ');
				var usuario = campos[0];
				var fecha = campos[1];

				$.ajax({
					url : "/trueBuscar",
					data : {
						modificar : 'envios',
						tabla : 'envios',
						usuario : usuario,
						fecha : fecha
					},
					type : "POST",
					dataType : "json",
				}).done(function(response) {
					console.log(response);

					$('#cliente').val(usuario);
					$('#fecha').val(fecha);

					$('#origen').val(response.origen);
					$('#destino').val(response.destino);
					$('#usuario').val(response.usuario);
					$('#tipo').val(response.tipo);
					$('#espacio').val(response.espacio);
					$('#estado').val(response.estado);
					$('#tiempoCarga').val(response.tiempoCarga);
					$('#tiempoDescarga').val(response.tiempoDescargaUsuario);
					$('#empresa').val(response.empresa);

					$('#buscar-form').hide();
					$('#modificar-form').removeAttr('hidden');
					$('#modificar-form').show();

				}).fail(function(xhr, status, errorThrown) {
					$('#buscar-form').show();
					$('#modificar-form').hide();
					alert("Algo a salido mal");
					console.log('Failed Request To Servlet')
				}).always(function(xhr, status) {
				});

			});

		})
	</script>

</body>

</html>