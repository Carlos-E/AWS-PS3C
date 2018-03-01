<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.LinkedHashSet"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estado Envio</title>
<%
	session.setAttribute("pagina", "Estado de Envíos");
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
			ArrayList<envio> listaEnvio = ControladorBD.escanearTabla("envios");
			ArrayList<String> nombres = new ArrayList<String>();
			ArrayList<String> fechas = new ArrayList<String>();
		%>
		<%
			if (session.getAttribute("busca") != "envio") {
				if (session.getAttribute("busca") == "fecha") {
					for (int i = 0; i < listaEnvio.size(); i++) {
						if (listaEnvio.get(i).getUsuario().equals(session.getAttribute("obj"))) {
							fechas.add(listaEnvio.get(i).getFecha());
						}
					}
		%>
		<form id="form" name="form" action="/buscar" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<div class="form-group">
						<div class="col-sm-2">

							<label class="control-label" for="fechas"> Fechas </label>
						</div>
						<div class="col-sm-10">
							<select class="form-control" id="subject" name="fecha" tabindex="4">
								<%
									for (int i = 0; i < fechas.size(); i++) {
								%>
								<option value="<%out.print(session.getAttribute("obj") + " : " + fechas.get(i));%>">
									<%
										out.print(fechas.get(i));
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
					<button name="submit" id="submit" type="submit" class="btn btn-danger" formaction="/cancelar">Cancelar</button>

				</div>
				<div class="col-sm-8"></div>
			</div>

		</form>

		<%
			} else {
					for (int i = 0; i < listaEnvio.size(); i++) {
						nombres.add(listaEnvio.get(i).getUsuario());
					}
					Set<String> linkedHashSet = new LinkedHashSet<String>();
					linkedHashSet.addAll(nombres);
					nombres.clear();
					nombres.addAll(linkedHashSet);
		%>
		<form id="form" name="form" action="/buscar" method="post" class="form-horizontal">

			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->
					<div class="form-group">
						<div class="col-sm-2">

							<label class="control-label" for="clientes"> Clientes </label>
						</div>
						<div class="col-sm-10">
							<select class="form-control" id="subject" name="usuarioEnvio" tabindex="4">
								<%
									for (int i = 0; i < nombres.size(); i++) {
								%>
								<option value="<%out.print(nombres.get(i));%>">
									<%
										out.print(nombres.get(i));
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
					<button name="submit" id="submit" type="submit" class="btn btn-danger" formaction="/cancelar">Cancelar</button>

				</div>
				<div class="col-sm-8"></div>
			</div>

		</form>

		<%
			}
			} else {
				envio envio = new envio();
				envio = (envio) ControladorBD.getItem("envios", "usuario", session.getAttribute("obj1").toString(),
						"fecha", session.getAttribute("obj2").toString());
				//falta hacer la funcion get envio bien
		%>

		<form id="form" name="form" action="/cancelar" method="post" class="form-horizontal">


			<div class="row">

				<div class="col-sm-6">

					<!-- INPUTS -->

					<div class="form-group">
						<div class="col-sm-2">

							<label class="control-label" for="destino">Estado:</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control" name="estado" id="estado" type="text" placeholder="Id del envio" disabled="disabled" value="<%out.println(envio.getEstado());%>">
						</div>
					</div>

				</div>

				<div class="col-sm-6"></div>

			</div>

			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-1">
					<!-- Boton Verde -->
					<button name="submit" id="modificar" type="submit" value="Aceptar" class="btn btn-primary">Aceptar</button>

				</div>
				<div class="col-sm-1">
					<!-- Boton Rojo -->
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