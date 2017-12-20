<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<jsp:include page="/head.jsp" />

<link rel="shortcut icon" href="img/icon.ico" />

<title>PS3C Registro</title>

</head>

<body class="fondo">

	<div class="container">

		<h1>Registro</h1>
		<br>

		<form id="form" name="form" class="form-horizontal" action="signin" method="post">

			<!-- 
			<input name="usuario" id="usuario" type="text" placeholder="Nombre de Usuario*">
			<input name="clave1" id="clave1" type="password" placeholder="Password*">
			<input name="calve2" id="clave2" type="password" placeholder="Repetir-Password*">
			<input name="nombres" id="nombres" type="text" placeholder="Nombres*">
			<input name="apellidos" id="apellidos" type="text" placeholder="Apellidos">
			<input name="correo" id="correo" type="email" placeholder="E-Mail*">
			<input name="direccion" id="direccion" type="text" placeholder="Direccion">
			<input name="telefono" id="telefono" type="text" placeholder="Telefono">
			<input type="submit" value="Registrar" id="submit">

 -->
			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<%
						com.logica.Dibujar.input(out, "usuario");
						String[] inputs = { "clave1", "clave2" };
						com.logica.Dibujar.inputsHidden(out, inputs);
						com.logica.Dibujar.input(out, "nombres");
						com.logica.Dibujar.input(out, "apellidos");
						com.logica.Dibujar.input(out, "correo");
						com.logica.Dibujar.input(out, "direccion");
						com.logica.Dibujar.input(out, "telefono");
					%>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde -->
					<button id="submit" type="submit" name="submit" value="Registrar" class="btn btn-primary">Registrar</button>
				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo -->
					<button formaction="/login.jsp" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>
				</div>
				<div class="col-sm-8"></div>
			</div>

		</form>
	</div>

</body>

</html>
