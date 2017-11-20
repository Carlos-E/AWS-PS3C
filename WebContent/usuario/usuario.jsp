<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/style-usuario.css">
<script src="../js/controlador.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos de Usuario</title>
</head>
<body>
<div class="wrapper">
	<div class="container">
		<h1>Usuario</h1>
		
		<%@ page import="com.logica.*" %>
		<%@ page import="clases.*" %>
		<% usuario usuario = (clases.usuario) ControladorBD.getItem("usuarios","usuario",session.getAttribute("username").toString()); %>
		<% if (session.getAttribute("rol") == null) {
			response.sendRedirect("/login.jsp");
		} %>
		<form name="form" class="form" action="../index.jsp" method="post">
		    <input name="usuario" id="usuario" type="text" placeholder="<% out.println(session.getAttribute("username").toString());%>" readonly="readonly">
		    <input name="claveOld" id="clave1" type="password" placeholder="Viejo-Password*">
			<input name="calve1" id="clave2" type="password" placeholder="Nuevo-Password*">
			<input name="calve2" id="clave2" type="password" placeholder="Repetir-Password*" >
			<input name="nombres" id="nombres" type="text" placeholder="<% out.print(usuario.getNombre());%>">
			<input name="apellidos" id="apellidos" type="text" placeholder="<% out.print(usuario.getApellido());%>">
			<input name="correo" id="correo" type="email" placeholder="<% out.print(usuario.getCorreo());%>">
			<input name="direccion" id="direccion" type="text" placeholder="<% out.print(usuario.getDireccion());%>">
			<input name="telefono" id="telefono" type="text" placeholder="<% out.print(usuario.getTelefono());%>">
			<input type="submit" value="Regresar" id="submit">
		</form>
	</div>
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>
</body>
</html>