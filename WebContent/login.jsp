<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="shortcut icon" href="img/icon.ico" />
<title>PS3C </title>

<link rel="stylesheet" href="css/style-login.css">

</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1>Iniciar Sesión</h1>
			<form class="form" action="/login" method="post">
				<input type="text" placeholder="Usuario" name="uname"> <input
					type="password" placeholder="Contraseña" name="pass"> <input
					type="submit" value="Ingresar" id="submit">

			</form>

			<form action="signin.jsp">
				<input type="submit" value="Registrar Aqui">
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

</body>
</html>