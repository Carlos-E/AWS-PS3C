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
<title>Trailers Disponibles</title>
<% session.setAttribute("pagina", "Disponibilidad de Traileres"); %>
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
		</div><br><br>
	<style> td{color: white;}</style>
		<div class="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="java.util.List"%>
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="clases.*"%>
			<%
				ArrayList<trailer> listaTrailer = ControladorBD.escanearTabla("trailers");
				System.out.print(listaTrailer.size());
			%>
			<table class="table table-bordered" style="background-color: rgba(0, 0, 0, 0.75);">
				<thead>
					<tr>
						<th>Patente</th>
						<th>Conductor</th>
						<th>Placa de Remolque</th>
						<th>Capacidad</th>
						<th>Espacio</th>
						<th>Estado</th>
						<th>Empresa</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (int i = 0; i < listaTrailer.size(); i++) {
							camion camion = (camion) ControladorBD.getItem("camiones", "placa", listaTrailer.get(i).getCamion());
							empresa empresa = (empresa) ControladorBD.getItem("empresas", "nit", listaTrailer.get(i).getEmpresa());
					%>
					<tr>
						<td><strong> <%
 	out.println(listaTrailer.get(i).getPatente());
 %>
						</strong></td>
						<td>
							<%
								out.println(camion.getUsuario());
							%>
						</td>
						<td>
							<%
								out.println(listaTrailer.get(i).getCamion());
							%>
						</td>
						<td>
							<%
								out.println(listaTrailer.get(i).getCapacidad());
							%>
						</td>
						<td>
							<%
								out.println(listaTrailer.get(i).getEspacio());
							%>
						</td>
						<td>
							<%
								out.println(listaTrailer.get(i).getEstado());
							%>
						</td>

						<td>

							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="true">
									<%
										out.println(empresa.getNombre());
									%>
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a>Correo: <%
										out.println(empresa.getCorreo());
									%></a></li>
									<li><a>Telefono: <%
										out.println(empresa.getTelefono());
									%></a></li>
								</ul>
							</div>

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