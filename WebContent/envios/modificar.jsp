<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Modificar Envíos</title>
<jsp:include page="/head.jsp" />
<%
	session.setAttribute("pagina", "Modificar Envíos");
%>
</head>

<body class="fondo">

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">

		<%
			ArrayList<envio> listaEnvio = ControladorBD.escanearTabla("envios");
		%>
		<%
			if (session.getAttribute("busca") != "mercancia") {
		%>
		<form id="form" name="form" action="/buscar" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<div class="form-group">

						<div class="col-sm-2">
							<label class="control-label " for="camiones"> Mercancia: </label>
						</div>

						<div class="col-sm-10">
							<select class="form-control" id="subject" name="envio" tabindex="4">
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

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde -->
					<button type="submit" name="submit" class="btn btn-primary">Buscar</button>

				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo -->
				</div>
				<div class="col-sm-8"></div>
			</div>

		</form>
		<%
			} else {
				envio envio = new envio();
				envio = (envio) com.logica.ControladorBD.getItem("envios", "usuario",
						session.getAttribute("obj1").toString(), "fecha", session.getAttribute("obj2").toString());
		%>

		<!-- SEGUNDO FORM -->
		<form id="form" name="form" class="form" action="../modificarMercancia" method="post">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<div class="form-horizontal">

						<%
							//Nombre de los campos del form			
								String[] inputs = {"espacio", "tipo", "empresa", "estado", "usuario", "destino", "origen",
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

		<%
			}
		%>

	<!-- /CONTAINER -->
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

</body>

</html>