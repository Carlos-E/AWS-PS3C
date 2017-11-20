<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reportar Problema</title>

<jsp:include page="/head.jsp" />
<% session.setAttribute("pagina", "Generar Reporte"); %>
</head>
<body>

	<!-- Header  -->
	<!--  Container de la Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />
	<!-- Contenido -->
	<div class="fondo"> <br> <br>
		<div class="container">

			<form id="form" name="form" action="/reportarProblema" method="post"
				class="form-horizontal">

				<%
					//Nombre de los campos del form
					String[] inputs = { "nota" };
					com.logica.Dibujar.inputs(out, inputs);
				%>

				<div class="col-sm-2"></div>

				<button name="submit" id="submit" type="submit"
					class="btn btn-primary">Reportar</button>
			</form>

		</div>
		<br> <br> <br> <br> <br>
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>