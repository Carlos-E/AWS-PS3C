<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
session.setAttribute("pagina", "Asignar Trailer a Camión");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Asignar Trailer a Camión</title>

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
		<br> <br>
		<div class="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="clases.*"%>
			<%
				ArrayList<trailer> listaTrailer = ControladorBD.escanearTabla("trailers");
			%>
			<%
				ArrayList<camion> list = ControladorBD.escanearTabla("camiones");
				ArrayList<camion> listaCamion = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getTipo().equals("remolque")) {
						listaCamion.add(list.get(i));
					}
				}
			%>
			<div id="lacosa">
				<form id="form" name="form" class="form" action="/trailerACamion"
					method="post">
					<table class="table">
						<tr border="0">
							<td border="0">
								<div id="contact-form">
									<span>Trailer: </span> <select class="form-control"
										id="subject" name="trailer" tabindex="4">
										<%
											for (int i = 0; i < listaTrailer.size(); i++) {
										%>
										<option
											value="<%out.print(listaTrailer.get(i).getPatente());%>">
											<%
												out.print(listaTrailer.get(i).getPatente());
											%>
										</option>
										<%
											}
										%>
									</select>

								</div>
							</td>
							<td>
								<div id="contact-form2">
									<span>Cami&oacute;n: </span> <select class="form-control" id="subject"
										name="camion" tabindex="4">
										<%
											for (int i = 0; i < listaCamion.size(); i++) {
										%>
										<option value="<%out.print(listaCamion.get(i).getPlaca());%>">
											<%
												out.print(listaCamion.get(i).getPlaca());
											%>
										</option>
										<%
											}
										%>
									</select>
								</div>
							</td>
						</tr>
					</table>
					<!--  <input class="submit" id="subject" type="submit" name="Asignar">
                      -->
					<button id="subject" type="submit" name="asignar" value="Asignar"
						class="btn btn-primary">Asignar</button>
						<button formaction="/cancelar" name="submit" id="cancelar" type="submit"
						class="btn btn-danger">Cancelar</button>
				</form>
			</div>
		</div>
		<br> <br>
		<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>