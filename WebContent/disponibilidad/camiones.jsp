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
<title>Camiones Disponibles</title>
<% session.setAttribute("pagina", "Disponibilidad de Camiones"); %>
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
	<style> th,td{color: white;}</style>
		<div class="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="clases.*"%>
			<%@ page import="java.lang.reflect.Field"%>

			<%
				ArrayList<camion> listaCamion = ControladorBD.escanearTabla("camiones");
			%>

			<table class="table table-bordered" style="background-color: rgba(0, 0, 0, 0.75);">
				<thead>
					<tr>
						<th>Placa</th>
						<th>Conductor</th>
						<th>Tipo</th>
						<th>Capacidad</th>
						<th>Espacio</th>
						<th>Estado</th>
						<th>Empresa</th>

					</tr>
				</thead>
				<tbody>
					<%
						for (int i = 0; i < listaCamion.size(); i++) {
					%>
					<tr>
						<td><strong> <%
 	out.println(listaCamion.get(i).getPlaca());
 %>
						</strong></td>
						<td>
							<%
								out.println(listaCamion.get(i).getUsuario());
							%>
						</td>
						<td>
							<%
								out.println(listaCamion.get(i).getTipo());
							%>
						</td>
						<td>
							<%
								out.println(listaCamion.get(i).getCapacidad());
							%>
						</td>
						<td>
							<%
								out.println(listaCamion.get(i).getEspacio());
							%>
						</td>
						<td>
							<%
								out.println(listaCamion.get(i).getEstado());
							%>
						</td>

						<td>
							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="true">
									<%
										out.println(listaCamion.get(i).getEmpresa());
									%>
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a>Correo: <%
										out.println(listaCamion.get(i).getEmpresa());
									%></a></li>
									<li><a>Telefono: <%
										out.println(listaCamion.get(i).getEmpresa());
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