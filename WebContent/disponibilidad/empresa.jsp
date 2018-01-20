<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Listar Empresas</title>
<%
	session.setAttribute("pagina", "Listar Empresas");
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
		<%@ page import="com.logica.*"%>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="clases.*"%>
		<%
			ArrayList<empresa> listaEmpresa = ControladorBD.escanearTabla("empresas");
		%>
		<style>
th, td {
	color: white;
}
</style>
		<table class="table table-bordered" style="background-color: rgba(0, 0, 0, 0.75);">
			<thead>
				<tr>
					<th>Nit</th>
					<th>Nombre</th>
					<th>Rut</th>
					<th>Correo</th>
					<th>Direccion</th>
					<th>Telefono</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < listaEmpresa.size(); i++) {
				%>
				<tr>
					<td>
						<strong>
							<%
								out.println(listaEmpresa.get(i).getNit());
							%>
						</strong>
					</td>
					<td>
						<%
							out.println(listaEmpresa.get(i).getNombre());
						%>
					</td>
					<td>
						<%
							out.println(listaEmpresa.get(i).getRut());
						%>
					</td>
					<td>
						<%
							out.println(listaEmpresa.get(i).getCorreo());
						%>
					</td>
					<td>
						<%
							out.println(listaEmpresa.get(i).getDireccion());
						%>
					</td>
					<td>
						<%
							out.println(listaEmpresa.get(i).getTelefono());
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