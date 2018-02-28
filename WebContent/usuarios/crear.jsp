<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Crear Usuario");
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
	<section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<div class="card-block">
				<h3 class="card-title">Crear Env&iacute;o</h3>
				<form class="form" action="/envio" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">usuario</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="usuario" placeholder="usuario" id="usuario" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">destino</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="destino" placeholder="destino" id="destino" required>
						</div>
					</div>
					<div class="form-group row">
						<%
							if (session.getAttribute("rol") == "admin" || session.getAttribute("rol") == "empleado") {
						%>
						<label class="col-md-2 col-form-label text-capitalize">contraseña</label>
						<div class="col-md-4">
							<input class="form-control" type="password" name="clave1" placeholder="contraseña" required>
						</div>
						<%
							}
						%>
						<%
							//out.print(session.getAttribute("rol").toString());
						%>
						<label class="col-md-2 col-form-label text-capitalize">tipo</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tipo" placeholder="tipo" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">espacio</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="espacio" placeholder="espacio" required>
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


<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Crear Usuario</title>

<jsp:include page="/head.jsp" />

</head>
<body class="fondo">

	<-- Header  ->
	<div class="container-fluid">
		<jsp:include page="/header.jsp" />
	</div>
	<!--  Container de la Barra de navegacion ->
	<div class="container-fluid">
		<jsp:include page="/navbar.jsp" />
	</div>
	<div class="container-fluid">
		<form id="form" name="form" action="/empleado" method="post" class="form-horizontal">

			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<%
							//Nombre de los campos del form
							String[] inputs = { "usuario" };
							com.logica.Dibujar.inputs(out, inputs);
							String[] inputs2 = { "clave1", "clave2" };
							com.logica.Dibujar.inputsHidden(out, inputs2);
							String[] inputs3 = { "nombre", "apellidos", "correo", "direccion", "telefono", "rol" };
							com.logica.Dibujar.inputs(out, inputs3);
						%>
					</div>
					<div class="col-sm-6"></div>
				</div>
				<div class="row">
					<div class="col-sm-1"></div>
					<div class="col-sm-1">
						<!-- Boton Verde ->
						<button type="submit" name="submit" class="btn btn-primary">Registrar</button>
					</div>
					<div class="col-sm-1">
						<!-- Boton Rojo ->
						<button name="submit" id="submit" type="submit" class="btn btn-danger" formaction="/cancelar">Cancelar</button>
					</div>
					<div class="col-sm-8"></div>
				</div>
			</div>

		</form>
	</div>
	<jsp:include page="/footer.jsp" /> -->

</body>
</html>