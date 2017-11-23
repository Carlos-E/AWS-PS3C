<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Empleado</title>

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
		<div id="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="clases.*"%>
			<%
				if (session.getAttribute("busca") != "empleado") {
			%>
			<form id="form" name="form" action="/buscar" method="post"
				class="form-horizontal">

				<%
					//Nombre de los campos del form
						String[] inputs = { "empleado" };
						com.logica.Dibujar.inputs(out, inputs);
				%>
				<div class="col-sm-2"></div>

				<button type="submit" name="submit" class="btn btn-primary">Buscar</button>
			</form>
			<%
				} else {
					usuario usuario = new usuario();
					usuario = (usuario) com.logica.ControladorBD.getItem("usuarios", "usuario",
							session.getAttribute("obj").toString());
			%>
			<form id="form" name="form" class="form"
				action="../modificarEmpleado" method="post">
				<%
					//Nombre de los campos del form
						String[] inputsh = { "claveOld", "clave1", "clave2" };
						String[] inputs = { "nombre", "apellido", "telefono", "direccion", "correo" };
						String[] values = { usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(),
								usuario.getDireccion(), usuario.getCorreo() };
						com.logica.Dibujar.inputs(out, inputs, values);
						com.logica.Dibujar.inputsHidden(out, inputsh);
				%>
				<input name="nombre" id="nombre" type="text"
					value="<%out.print(usuario.getNombre());%>"> <input
					name="rol" id="rol" type="text"
					value="<%out.print(usuario.getRol());%>"> <input
					name="claveOld" id="claveOld" type="password"
					placeholder="Viejo-Password*"> <input name="calve1"
					id="clave1" type="password" placeholder="Nuevo-Password*">
				<input name="calve2" id="clave2" type="password"
					placeholder="Repetir-Password*"> <input name="apellido"
					id="apellido" type="text"
					value="<%out.print(usuario.getApellido());%>"> <input
					name="telefono" id="telefono" type="text"
					value="<%out.print(usuario.getTelefono());%>"> <input
					name="direccion" id="direccion" type="text"
					value="<%out.print(usuario.getDireccion());%>"> <input
					name="correo" id="correo" type="text"
					value="<%out.print(usuario.getCorreo());%>"> <input
					name="submit" id="modificar" type="submit" value="Modificar">
			</form>
			<form id="form" name="form" class="form" action="../cancelar"
				method="post">
				<input name="submit" id="modificar" type="submit" value="Cancelar">
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