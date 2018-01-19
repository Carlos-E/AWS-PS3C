<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

<title>Listar Usuarios</title>
<%
	session.setAttribute("pagina", "Listar Usuarios");
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
			ArrayList<usuario> listaUsuario = ControladorBD.escanearTabla("usuarios");
		%>
		<style>
th, td {
	color: white;
}
</style>
		<table class="table table-bordered" style="background-color: rgba(0, 0, 0, 0.75);">
			<thead>
				<tr>
					<th>Cedula</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Rol</th>
					<th>Correo</th>
					<th>Direccion</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < listaUsuario.size(); i++) {
				%>
				<tr>
					<td>
						<strong>
							<%
								out.println(listaUsuario.get(i).getUsuario());
							%>
						</strong>
					</td>
					<td>
						<%
							out.println(listaUsuario.get(i).getNombre());
						%>
					</td>
					<td>
						<%
							out.println(listaUsuario.get(i).getApellido());
						%>
					</td>
					<td>
						<%
							out.println(listaUsuario.get(i).getRol());
						%>
					</td>
					<td>
						<%
							out.println(listaUsuario.get(i).getCorreo());
						%>
					</td>
					<td>
						<%
							out.println(listaUsuario.get(i).getDireccion());
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