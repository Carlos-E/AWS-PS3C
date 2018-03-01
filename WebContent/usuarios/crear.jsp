<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<div class="card-block">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form class="form" action="/usuarios/crear" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Nombre</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="nombre" placeholder="nombre" id="nombre" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Apellido</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="apellido" placeholder="apellido" id="apellido" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Contraseña</label>
						<div class="col-md-4">
							<input class="form-control" type="password" name="clave1" placeholder="contraseña" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Repetir-Contraseña</label>
						<div class="col-md-4">
							<input class="form-control" type="password" name="clave2" placeholder="contraseña" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Correo</label>
						<div class="col-md-4">
							<input class="form-control" type="email" name="correo" placeholder="correo" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Direcci&oacute;n</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="direccion" placeholder="direccion" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Telefono</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="telefono" placeholder="telefono" required>
						</div>
						<%
							if (session.getAttribute("rol") == "admin" || session.getAttribute("rol") == "empleado") {
						%>
						<label class="col-md-2 col-form-label text-capitalize">Rol</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="rol" placeholder="rol" required>
						</div>
						<%
							}
						%>
						<%
							//out.print(session.getAttribute("rol").toString());
						%>
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
	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
		<!-- /FIN -->
	
</body>
</html>