<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Empresa</title>

<jsp:include page="/head.jsp" />
<%
	session.setAttribute("pagina", "Modificar Empresa");
%>
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
			ArrayList<empresa> listaempresa = ControladorBD.escanearTabla("empresas");
		%>
		<%
			if (session.getAttribute("busca") != "empresa") {
		%>
		<form id="form" name="form" action="/buscar" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<div class="form-group">

						<div class="col-sm-2">

							<label class="control-label" for="empresas"> Empresas: </label>

						</div>

						<div class="col-sm-10">

							<select class="form-control" id="subject" name="nit" tabindex="4">
								<%
									for (int i = 0; i < listaempresa.size(); i++) {
								%>
								<option value="<%out.print(listaempresa.get(i).getNit());%>">
									<%
										out.print(listaempresa.get(i).getNit());
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
				empresa empresa = (empresa) com.logica.ControladorBD.getItem("empresas", "nit",
						session.getAttribute("obj").toString());
		%>
		<form id="form" name="form" class="form-horizontal" action="../modificarEmpresa" method="post">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<%
						//Nombre de los campos del form	
							String[] inputs = { "nombre", "telefono", "direccion", "correo" };
							String[] values = { empresa.getNombre(), empresa.getTelefono(), empresa.getDireccion(),
									empresa.getCorreo() };
							com.logica.Dibujar.inputs(out, inputs, values);
					%>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde -->
					<button name="submit" id="submit" type="submit" class="btn btn-primary">Modificar</button>

				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo -->
					<button formaction="/cancelar" name="submit" id="cancelar" type="submit" class="btn btn-danger">Cancelar</button>

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