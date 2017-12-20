<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
session.setAttribute("pagina", "Agregar Empresa");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agregar Empresa</title>

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
			<br>
			<br>
			<form id="form" name="form" action="/empresa" method="post"
				class="form-horizontal">
				<%
				//Nombre de los campos del form
				String[] inputs = { "nombre", "correo", "nit", "rut", "telefono", "direccion" };
				com.logica.Dibujar.inputs(out, inputs);
			%>
				<div class="col-sm-2"></div>
				<button type="submit" name="submit" id="submit"
					class="btn btn-primary">Registrar</button>
				<button formaction="/cancelar" name="submit" id="cancelar" type="submit"
						class="btn btn-danger">Cancelar</button>
			</form>
		</div>
		<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>