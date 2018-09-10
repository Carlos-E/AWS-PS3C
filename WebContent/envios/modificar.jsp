<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression"%>

<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Modificar envíos");
	/* 	ArrayList<envio> listaEnvio = ControladorBD.escanearTabla("envios");
	 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
						<label class="col-md-3 col-form-label">Seleccione el env&iacute;o</label>
						<div class="col-md-9">
							<select class="custom-select form-control" id="select">
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<button id="buscar" type="button" class="btn btn-primary btn-md float-right">Buscar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>

			<div class="card-block" id="form" hidden="true">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form id="myForm" class="form" action="/envios/modificar" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Cliente</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="cliente" placeholder="" id="cliente" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Fecha</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="fecha" placeholder="" id="fecha" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Origen</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="origen" placeholder="origen" onchange="uno()" id="origen" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Destino</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="destino" placeholder="destino" id="destino" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Usuario</label>
						<div class="col-md-4">
							<!-- 							<input class="form-control" type="text" name="usuario" placeholder="usuario" id="usuario" required>
 -->
							<select class="form-control" name="usuario" id="usuario" required>
								<option value="" selected>Seleccionar...</option>

								<%
									List<Usuario> usuarios = new DB().scan(Usuario.class, new DynamoDBScanExpression());

									for (int i = 0; i < usuarios.size(); i++) {
										if (usuarios.get(i).getRol().equals("cliente")) {
								%>
								<option value="<%out.print(usuarios.get(i).getCorreo());%>">
									<%
										out.print(usuarios.get(i).getNombre());
									%>
								</option>
								<%
									}
									}
								%>
							</select>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Tipo</label>
						<div class="col-md-4">

							<select class="custom-select" name="tipo" id="tipo" required>
								<option value="" selected>Seleccionar...</option>
								<option value="normal">normal</option>
								<option value="ligero">ligero</option>
								<option value="fragil">fragil</option>
								<option value="perecedero">perecedero</option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">
							Espacio(m
							<sup>3</sup>
							)
						</label>
						<div class="col-md-4">
							<input class="form-control" name="espacio" placeholder="en metros c&uacute;bicos" pattern="^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$" onchange="uno()" id="espacio" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Estado</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="estado" id="estado" disabled>

							<!-- <select class="custom-select" name="estado" id="estado" required>
								<option value="" selected>Seleccionar...</option>
								<option value="no asignado">no asignado</option>
								<option value="asignado">asignado</option>
								<option value="en tránsito">en tr&aacute;nsito</option>
								<option value="entregado">entregado</option>
							</select> -->
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Peso(Kg)</label>
						<div class="col-md-4">
							<input class="form-control" name="peso" placeholder="en kilogramos" pattern="^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$" onchange="uno()" id="peso" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Descripci&oacute;n</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="descripcion" placeholder="descripcion" id="descripcion" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Empresa</label>
						<div class="col-md-4">
							<select class="form-control" name="empresa" id="empresa" required>
								<option value="" selected>Seleccionar...</option>
								<%
									List<Empresa> listaEmpresas = new DB().scan(Empresa.class, new DynamoDBScanExpression());

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
						<label class="col-md-2 col-form-label text-capitalize">Veh&iacute;culo o tr&aacute;iler asignado</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="asignado" placeholder="asignado" id="asignado" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Veh&iacute;culo</label>
						<div class="col-md-4">
							<input id="rCamion" type="radio" name="asignacion" onclick="mostrar()" checked="true" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Tr&aacute;iler</label>
						<div class="col-md-4">
							<input id="rTrailer" type="radio" name="asignacion" onclick="ocultar()" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Veh&iacute;culos disponibles</label>
						<div class="col-md-4">
							<i id="spinner1" class="fa fa-circle-notch fa-spin" style="font-size: 35px"></i>
							<select id="camion" class="form-control" name="camion">
							</select>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Tr&aacute;ileres disponibles</label>
						<div class="col-md-4">
							<i id="spinner2" class="fa fa-circle-notch fa-spin" style="font-size: 35px"></i>
							<select id="trailer" class="form-control" name="trailer" disabled="true">
							</select>
						</div>
					</div>

					<div id="labelSuge" class="form-group row">
							<label class="col-md-2 col-form-label text-capitalize">Sugerencia:</label>
							<div class="col-md-4">
								<p align="justify" id="sugerenciaVehiculo" name="sugerenciaVehiculo">Sugerencias</p>
							</div>
							<label class="col-md-2 col-form-label text-capitalize">Sugerencia:</label>
							<div class="col-md-4">
								<p align="justify" id="sugerenciaTrailer" name="sugerenciaTrailer">Sugerencias</p>
							</div>
					</div>

					<input type="text" id="destinoLatLong" name="destinoLatLong" style="display: none">
					<input type="text" id="origenLatLong" name="origenLatLong" style="display: none">

					<div class="modal-footer">
						<button type="reset" class="btn btn-secondary float-left">
							<i class="fas fa-eraser fa-lg"></i>
						</button>
						<button id="submit" type="submit" class="btn btn-primary btn-md float-right">Confirmar</button>
						<button id="atras" type="button" data-target="#" class="btn btn-danger btn-md float-right">Atras</button>
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
					<form action="/cancelar" method="post">
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
	<script>
	
	var lista;
	var table = 'envios';
	
	var scanTable;
	var fillSelect;
	var fillInputs;
	
	$(document).ready(function() {

		scanTable = (table, callback) => {

						$.ajax({
							url : "/scanTable",
							data : {
								tabla : table
							},
							type : "POST",
							dataType : "json",
						}).done(function(response) {
							console.log(response);
							
							lista = response;

							callback(response);

						}).fail(function(xhr, status, errorThrown) {
							alert("Algo ha salido mal");
							console.log('Failed Request To Servlet /scanTable')
						});

					}

		fillSelect = (list) => {
			$('#select').find('option').remove();
			$(list).each(function() {
			$('#select')
				.append($("<option>")
					.attr('value',this.usuario + ' : '+ this.fecha)
					.text(this.usuario + ' : '+ this.fecha));
			});
			
			if(getParameterByName('select') != null ){
				$('#select').val(getParameterByName('select'));
				fillInputs();
			}
		}

		fillInputs = () => {

			let selectedIndex = $('#select').prop('selectedIndex');
	
			console.log(lista[selectedIndex]);

			let objeto = lista[selectedIndex];

			let asignado = "ninguno";

			if (objeto.trailer != "ninguno") {
				asignado = objeto.trailer;
			} else if (objeto.camion != "ninguno") {
				asignado = objeto.camion;
			} else {
				asignado = "ninguno";
			}
						
			$('#cliente').val(objeto.usuario);
			$('#fecha').val(objeto.fecha);
			$('#asignado').val(asignado);
			$('#origen').val(objeto.origen);
			$('#destino').val(objeto.destino);
			$('#usuario').val(objeto.usuario);
			$('#peso').val(objeto.peso);
			$('#tipo').val(objeto.tipo);
			$('#espacio').val(objeto.espacio);
			$('#estado').val(objeto.estado);
			$('#tiempoCarga').val(objeto.tiempoCarga);
			$('#tiempoDescarga').val(objeto.tiempoDescargaUsuario);
			$('#empresa').val(objeto.empresa);
			$('#camion').val(objeto.camion);
			$('#trailer').val(objeto.trailer);
			$('#descripcion').val(objeto.descripcion);
			$('#destinoLatLong').val(objeto.destinoLatLong);
			$('#origenLatLong').val(objeto.origenLatLong);
			$('#buscar-form').hide();
			$('#form').removeAttr('hidden');
			$('#form').show();
			uno();
	}

	$('#buscar').click(fillInputs);

	$('#atras').click(function() {
		$('#buscar-form').show();
		$('#form').hide();
	});
					
	scanTable('envios',function(list){
		fillSelect(list);
	});
					
});
</script>
	<script type="text/javascript">
		function mostrar() {
			document.getElementById('trailer').value = "ninguno";
			document.getElementById('trailer').disabled = true;
			document.getElementById('camion').disabled = false;
		}

		function ocultar() {
			document.getElementById('camion').value = "ninguno";
			document.getElementById('camion').disabled = true;
			document.getElementById('trailer').disabled = false;
		}
	</script>
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

								document.getElementById('destinoLatLong').value = latlon;
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

								document.getElementById('origenLatLong').value = latlon;
							});
		};
	</script>
	<script type="text/javascript">
		var i = 0;
		function uno() {

			$('#spinner1').fadeIn('slow');
			$('#spinner2').fadeIn('slow');
			$('#camion').hide();
			$('#trailer').hide();

			var peso = document.getElementById('peso').value;
			var espacio = document.getElementById('espacio').value;
			if (peso != "" && espacio != "") {
				setTimeout(
						function() {
							var latlon = document
									.getElementById('origenLatLong').value;
							if (latlon != "") {

								setVehiculos(latlon, parseFloat(peso),
										parseFloat(espacio));
								setTrailers(latlon, parseFloat(peso),
										parseFloat(espacio));

							}
						}, 1000);
			}
		}
	</script>

</body>

</html>