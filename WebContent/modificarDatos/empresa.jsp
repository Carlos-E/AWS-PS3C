<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Empresa</title>

<jsp:include page="/head.jsp" />
<% session.setAttribute("pagina", "Modificar Empresa"); %>
</head>
<body>

	<!-- Header  -->
	<!--  Container de la Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />
	<div class="fondo">
		<br> <br>
		<div id="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="clases.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%
				ArrayList<empresa> listaempresa = ControladorBD.escanearTabla("empresas");
			%>
			<%
				if (session.getAttribute("busca") != "empresa") {
			%>
			<form id="form" name="form" action="/buscar" method="post"
				class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-2" for="camiones">
						Camiones </label>
					<div class="col-sm-9">
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
				<div class="col-sm-2"></div>

				<button type="submit" name="submit" class="btn btn-primary">Buscar</button>
			</form>
			<%
				} else {
					empresa empresa = (empresa) com.logica.ControladorBD.getItem("empresas", "nit",
							session.getAttribute("obj").toString());
			%>
			<form id="form" name="form" class="form" action="../modificarEmpresa"
				method="post">
				<div class="form-horizontal">
					<%
						//Nombre de los campos del form	
							String[] inputs = { "nombre", "telefono", "direccion", "correo" };
							String[] values = { empresa.getNombre(), empresa.getTelefono(), empresa.getDireccion(),
									empresa.getCorreo() };
							com.logica.Dibujar.inputs(out, inputs, values);
					%>
				</div>
				<div class="col-sm-2"></div>
				<div class="form-vertical">
					<button name="submit" id="submit" type="submit"
						class="btn btn-primary">Modificar</button>
				</div>
			</form>
			<div class="col-sm-2"></div>
			<form id="form" name="form" action="/cancelar" method="post">
				<div class="form-vertical">
					<button name="submit" id="cancelar" type="submit"
						class="btn btn-danger">Cancelar</button>
				</div>
			</form>
			<%
				}
			%>
		</div>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>