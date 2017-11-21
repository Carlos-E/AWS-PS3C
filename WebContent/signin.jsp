<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style-signin.css">
<script src="js/controlador.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="img/icon.ico" />
<title>PS3C Registro</title>
</head>
<body>
<div class="wrapper">
	<div class="container">
		<h1>Registro</h1>
		<form name="form" class="form" action="signin" method="post">
			<input name="usuario" id="usuario" type="text" placeholder="Nombre de Usuario*">
			<input name="clave1" id="clave1" type="password" placeholder="Password*">
			<input name="calve2" id="clave2" type="password" placeholder="Repetir-Password*">
			<input name="nombres" id="nombres" type="text" placeholder="Nombres*">
			<input name="apellidos" id="apellidos" type="text" placeholder="Apellidos">
			<input name="correo" id="correo" type="email" placeholder="E-Mail*">
			<input name="direccion" id="direccion" type="text" placeholder="Direccion">
			<input name="telefono" id="telefono" type="text" placeholder="Telefono">
			<input type="submit" value="Registrar" id="submit">
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
