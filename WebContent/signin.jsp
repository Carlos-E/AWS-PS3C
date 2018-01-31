<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/head.jsp" />
<link rel="shortcut icon" href="/img/icon.ico" />

<!-- CSS -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style-login.css" rel="stylesheet">

<title>PS3C</title>

</head>
<body>

	<div class="container">

		<div class="row" id="pwd-container">
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<section class="login-form">
				<form method="post" action="/signin" role="login">
					<h1>Registrar</h1>
					Usuario:<br><input type="text" name="usuario" placeholder="Usuario" required class=" input-sm" value="" /><br>
					Password:<br><input type="password" name="clave1" class=" input-sm" id="password" placeholder="Contraseña" required="" />
					Repetir-Password:<br><input type="password" name="clave2" class=" input-sm" id="password" placeholder="Contraseña" required="" />
					Nombres:<br><input type="text" name="nombres" placeholder="nombres" required class=" input-sm" value="" /><br>
					Apellidos:<br><input type="text" name="apellidos" placeholder="apellidos" required class=" input-sm" value="" /><br>
					Correo:<br><input type="text" name="correo" placeholder="correo" required class=" input-sm" value="" /><br>
					Direccion:<br><input type="text" name="direccion" placeholder="direccion" required class=" input-sm" value="" /><br>
					Telefono:<br><input type="text" name="telefono" placeholder="telefono" required class=" input-sm" value="" /><br>
					
					<button type="submit" name="go" class="btn btn-lg btn-primary btn-block">
						<span class="glyphicon glyphicon-log-in"></span>
						Ingresar
					</button>
					<!-- Boton Rojo -->
					<button name="submit" id="submit" type="submit" class="btn btn-lg btn-block btn-danger" formaction="/cancelar">Cancelar</button>
						<!--  or <a href="#">reset password</a>   -->

				</form>

				</section>
			</div>

			<div class="col-md-4"></div>

		</div>


	</div>

	<!-- Scripts -->
	<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/login.js"></script>
	<!-- Scripts -->

</body>

</html>