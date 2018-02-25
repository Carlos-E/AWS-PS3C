<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Modificar Usuario");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Usuario</title>

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

	<div class="container">

		<%
			ArrayList<usuario> listaUsuario = ControladorBD.escanearTabla("usuarios");
			if (session.getAttribute("busca") != "cliente" && session.getAttribute("busca") != "empleado"
					&& session.getAttribute("busca") != "conductor") {
		%>
		<%-- Falta por modificar --%>
		<form id="form" name="form" action="/buscar" method="post" class="form-horizontal">


			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<div class="form-group">
					
						<div class="col-sm-2">
							<label class="control-label  for="usuarios">Usuarios: </label>
						</div>
						
						<div class="col-sm-9">
							<select class="form-control" id="subject" name="usuario" tabindex="4">
								<%
									for (int i = 0; i < listaUsuario.size(); i++) {
								%>
								<option value="<%out.print(listaUsuario.get(i).getNombre().replaceAll(" ", ""));%>">
									<%
										out.print(listaUsuario.get(i).getNombre().replaceAll(" ", ""));
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
				usuario usuario = new usuario();
				usuario = (usuario) com.logica.ControladorBD.getItem("usuarios", "usuario",
						session.getAttribute("obj").toString());
		%>
		<form id="form" name="form" action="/eliminarUsuario" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<%
						//Nombre de los campos del form
							String[] input = { "nombre", "rol" };
							String[] value = { usuario.getNombre(), usuario.getRol() };
							String[] inputsh = { "claveOld", "clave", "repita clave" };
							String[] inputs = { "apellido", "telefono", "direccion", "correo" };
							String[] values = { usuario.getApellido(), usuario.getTelefono(), usuario.getDireccion(),
									usuario.getCorreo() };
							com.logica.Dibujar.inputs(out, input, value);
							com.logica.Dibujar.inputsHidden(out, inputsh);
							com.logica.Dibujar.inputs(out, inputs, values);
					%>

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
	</div>

	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>

</body>

</html>