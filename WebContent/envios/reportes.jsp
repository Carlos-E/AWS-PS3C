<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reporte de Envios</title>
<%
	session.setAttribute("pagina", "Reportes");
%>
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

		<%
			ArrayList<reporte> listaReporte = ControladorBD.escanearTabla("reportes");
		%>
		<style>
th, td {
	color: white;
}
</style>
		<table class="table table-bordered" style="background-color: rgba(0, 0, 0, 0.60);">
			<thead>
				<tr>
					<th>Hora</th>
					<th>Nota</th>
					<th>Autor</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < listaReporte.size(); i++) {
				%>
				<tr>
					<td>
						<strong>
							<%
								out.println(listaReporte.get(i).getHora());
							%>
						</strong>
					</td>
					<td>
						<%
							out.println(listaReporte.get(i).getNota());
						%>
					</td>
					<td>
						<%
							out.println(listaReporte.get(i).getUsuario());
						%>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>