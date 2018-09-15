<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Actualizar perfil");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
<jsp:include page="/head.jsp" />
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

			<div class="card-block" id="form">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form id="myForm" class="form" action="/usuarios/modificar" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Usuario</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="usuario" placeholder="usuario" id="usuario" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Rol</label>
						<div class="col-md-4">
							<select class="custom-select" name="rol" required>
								<option value="" selected>Seleccionar...</option>
								<option value="cliente">cliente</option>
								<option value="conductor">conductor</option>
								<option value="empleado">empleado</option>
								<%
									if (session.getAttribute("rol").equals("admin")) {
								%>
								<option value="admin">admin</option>
								<%
									}
								%>
							</select>
						</div>
					</div>
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
						<label class="col-md-2 col-form-label text-capitalize">Contrase&ntilde;a antigua</label>
						<div class="col-md-4">
							<input class="form-control" type="password" name="claveOld" placeholder="clave antigua" id="claveOld" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Contrase&ntilde;a</label>
						<div class="col-md-4">
							<input class="form-control" type="password" name="clave" placeholder="clave" id="clave" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Repita contrase&ntilde;a</label>
						<div class="col-md-4">
							<input class="form-control" type="password" name="repita clave" placeholder="repita clave" id="repita-clave" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Telefono</label>
						<div class="col-md-4">
							<input class="form-control" type="number" name="telefono" placeholder="telefono" id="telefono" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Direcci&oacute;n</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="direccion" placeholder="direcci&oacute;n" id="direccion" required>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Correo</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="correo" placeholder="correo" id="correo" required>
						</div>
					</div>
					<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
					<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
					<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
					<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none">
					<div class="modal-footer">
						<button id="submit" type="submit" class="btn btn-primary btn-md float-right">Modificar</button>
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
	<script>
		$(document).ready(function() {

			$.ajax({
				url : "/usuarios/leer",
				type : "GET",
				dataType : "json",
			}).done(function(response) {
				console.log(response);

				let objeto = response;
				$('#usuario').val(objeto.usuario);
				$('#nombre').val(objeto.nombre);
				$('#rol').val(objeto.rol);
				$('#apellido').val(objeto.apellido);
				$('#telefono').val(objeto.telefono);
				$('#direccion').val(objeto.direccion);
				$('#correo').val(objeto.correo);

			}).fail(function(xhr, status, errorThrown) {
				alert("Algo ha salido mal");
				console.log('Failed Request To Servlet /scanTable')
			});

		});
	</script>
</body>
</html>