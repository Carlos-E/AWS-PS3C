<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Crear Empresa");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Crear Empresa</title>

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
		
		<form id="form" name="form" action="/empresa" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->

					<%
						//Nombre de los campos del form
						String[] inputs = { "nombre", "correo", "nit", "rut", "telefono", "direccion" };
						com.logica.Dibujar.inputs(out, inputs);
					%>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde -->
					<button type="submit" name="submit" id="submit" class="btn btn-primary">Registrar</button>

				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo -->
					<button formaction="/cancelar" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>

				</div>
				<div class="col-sm-9"></div>
			</div>

		</form>
	</div>

	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>

</body>
</html>