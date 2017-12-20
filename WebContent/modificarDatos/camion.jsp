<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
%>
<%
	ArrayList<camion> listaCamion = ControladorBD.escanearTabla("camiones");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Camion</title>
<%
	session.setAttribute("pagina", "Modificar Camion");
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
			if (session.getAttribute("busca") != "camion") {
		%>
		<form id="form" name="form" action="/buscar" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<div class="form-group">

						<label class="control-label col-sm-2" for="camiones"> Camiones </label>

						<select class="form-control col-sm-10" id="subject" name="placa" tabindex="4">
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

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">

				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<button type="submit" name="submit" class="btn btn-primary">Buscar</button>
				</div>
				<div class="col-sm-1">
					<button formaction="/cancelar" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>
				</div>
				<div class="col-sm-8"></div>

			</div>

		</form>
		<%
			} else {
				camion camion = new camion();
				camion = (camion) com.logica.ControladorBD.getItem("camiones", "placa",
						session.getAttribute("obj").toString());
		%>
		<form id="form" name="form" class="form" action="/modificarCamion" method="post">

			<div class="row">

				<div class="col-sm-6">

					<div class="form-group">

						<%
							if (camion.getTipo().equals("camion")) {
									//Nombre de los campos del form
									String[] inputs = { "capacidad", "espacio" };
									String[] values = { camion.getCapacidad(), camion.getEspacio() };
									com.logica.Dibujar.inputs(out, inputs, values);
								}
								//Nombre de los campos del form
								String[] inputs = { "estado", "conductor" };
								String[] values = { camion.getEstado(), camion.getUsuario() };
								com.logica.Dibujar.inputs(out, inputs, values);
						%>

					</div>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<button name="submit" id="submit" type="submit" class="btn btn-primary">Modificar</button>
				</div>
				<div class="col-sm-1">
					<button formaction="/cancelar" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>
				</div>
				<div class="col-sm-8"></div>
			</div>
		</form>
		<%
			}
		%>
	</div>
	
	<br>

	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>

</body>
</html>