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
<title>Generar Reporte</title>

<jsp:include page="/head.jsp" />
<% session.setAttribute("pagina", "Generar reporte"); %>
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

			<form id="form" name="form" action="/reportarProblema" method="post"
				class="form-horizontal">

				<%
					//Nombre de los campos del form
					//String[] inputs = { "nota" };
					//com.logica.Dibujar.inputs(out, inputs);
					
					%>
					
					<div class="form-group">
					<label class="control-label col-sm-1" for="name">Nota:</label>
					<div class="col-sm-5">
					  <textarea class="form-control" rows="5" id="nota" name="nota" placeholder="Nota"></textarea>
					</div>
					</div>

				<div class="col-sm-1"></div>

				<button name="submit" id="submit" type="submit"
					class="btn btn-primary">Reportar</button>
			</form>
		</div> 
		<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>