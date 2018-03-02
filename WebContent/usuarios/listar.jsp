<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		//response.sendError(400, "Acceso incorrecto"); //cambiar
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Listar Usuarios");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%out.print(session.getAttribute("pagina").toString());%></title>
<jsp:include page="/head.jsp" />
</head>
<body>
	<!-- INICIO -->
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!-- INICIO NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>
	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<!-- INICIO CONTAINER -->
			
			<div class="card-block" id="buscar-form">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<style>
th, td {
	color: white;
}
</style>
<%
			ArrayList<usuario> listaUsuario = ControladorBD.escanearTabla("usuarios");
		%>
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
		<!-- /FIN CONTAINER -->
	</div>
	</div>
	</section> </main>
	<!-- Modal -->
	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!-- /FIN -->
</body>
</html>