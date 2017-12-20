<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Trailer</title>
<%
	session.setAttribute("pagina", "Eliminar trailer");
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
			ArrayList<trailer> listaTrailer = ControladorBD.escanearTabla("trailers");
		%>
		<%
			if (session.getAttribute("busca") != "trailer") {
		%>
		<form id="form" name="form" action="/buscar" method="post" class="form-horizontal">
		
			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->

					<div class="form-group">

						<div class="col-sm-2">

							<label class="control-label" for="camiones"> Traileres: </label>
						</div>
						<div class="col-sm-10">
							<select class="form-control" id="subject" name="patenteE" tabindex="4">
								<%
									for (int i = 0; i < listaTrailer.size(); i++) {
								%>
								<option value="<%out.print(listaTrailer.get(i).getPatente());%>">
									<%
										out.print(listaTrailer.get(i).getPatente());
									%>
								</option>
								<%
									}
								%>
							</select>
						</div>
					</div>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde -->
					<button type="submit" name="submit" class="btn btn-primary">Buscar</button>

				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo -->
				</div>
				<div class="col-sm-8"></div>
			</div>

		</form>
		<%
			} else {
				trailer trailer = (trailer) com.logica.ControladorBD.getItem("trailers", "patente",
						session.getAttribute("obj").toString());
		%>
		<form id="form" name="form" class="form-horizontal" action="../eliminarTrailer" method="post">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<%
						//Nombre de los campos del form	
							String[] inputs = { "capacidad", "espacio", "estado", "tipo", "camion" };
							String[] values = { trailer.getCapacidad(), trailer.getEspacio(), trailer.getEstado(),
									trailer.getTipo(), trailer.getCamion() };
							com.logica.Dibujar.inputs(out, inputs, values);
					%>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde -->
					<button name="submit" id="submit" type="submit" class="btn btn-primary">Eliminar</button>

				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo -->
					<button name="submit" id="submit" type="submit" class="btn btn-danger" formaction="/cancelar">Cancelar</button>

				</div>
				<div class="col-sm-8"></div>
			</div>

		</form>
		<%
			}
		%>
	</div>

	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>

</body>

</html>