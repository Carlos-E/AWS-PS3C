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
<title>Reporte de Envios</title>
<% session.setAttribute("pagina", "Reportes"); %>
<jsp:include page="/head.jsp" />

</head>
<body>
	<!-- Header  -->
	<!--  Container de la Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />
	<div class="fondo"><br><br>
		<div class="container" >
			<%@ page import="com.logica.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="clases.*"%>
			<%
				ArrayList<reporte> listaReporte = ControladorBD.escanearTabla("reportes");
			%>
			<style> td{color: white;}</style>

			<table class="table table-bordered" style="background-color: rgba(0, 0, 0, 0.75);" >
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
						<td><strong> <%
 	out.println(listaReporte.get(i).getHora());
 %>
						</strong></td>
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
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>