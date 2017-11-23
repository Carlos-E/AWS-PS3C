<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estado Envio</title>
<% session.setAttribute("pagina", "Estado de Envíos"); %>
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
		<div id="container">
			<%@ page import="com.logica.*"%>
			<%@ page import="clases.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="java.util.HashSet"%>
			<%@ page import="java.util.LinkedHashSet"%>
			<%@ page import="java.util.Set"%>
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
			<form id="form" name="form" action="/buscar" method="post"
				class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-2" for="fechas"> Fechas
					</label>
					<div class="col-sm-9">
						<select class="form-control" id="subject" name="fecha"
							tabindex="4">
							<%
								for (int i = 0; i < fechas.size(); i++) {
							%>
							<option
								value="<%out.print(session.getAttribute("obj") + " : " + fechas.get(i));%>">
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
				<!--          
                         <input name="placa" id="placa" type="text" placeholder="Placa del camion">
                         -->
				<div class="col-sm-2"></div>
				<button type="submit" name="submit" class="btn btn-primary">Buscar</button>
				<!-- 
                          <input name="submit" id="buscar" type="submit" value="Buscar">                 
                          -->
			</form>
			<div class="col-sm-2"></div>
			<form id="form" name="form" action="/cancelar" method="post">
				<button name="submit" id="cancelar" type="submit"
					class="btn btn-danger">Cancelar</button>
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
			<form id="form" name="form" action="/buscar" method="post"
				class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-2" for="clientes">
						Clientes </label>
					<div class="col-sm-9">
						<select class="form-control" id="subject" name="usuarioEnvio"
							tabindex="4">
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
				<div class="col-sm-2"></div>
				<button type="submit" name="submit" class="btn btn-primary">Buscar</button>
			</form>
			<div class="col-sm-2"></div>
			<form id="form" name="form" action="/cancelar" method="post">
				<button name="submit" id="cancelar" type="submit"
					class="btn btn-danger">Cancelar</button>
			</form>

			<%
				}
				} else {
					envio envio = new envio();
					envio = (envio) ControladorBD.getItem("envios", "usuario", session.getAttribute("obj1").toString(),
							"fecha", session.getAttribute("obj2").toString());
					//falta hacer la funcion get envio bien
			%>

			<form id="form" name="form" action="/cancelar" method="post"
				class="form-horizontal">

				<div class="form-group">
					<label class="control-label col-sm-2" for="destino">Estado:</label>
					<div class="col-sm-9">
						<input class="form-control" name="estado" id="estado" type="text"
							placeholder="Id del envio" disabled="disabled"
							value="<%out.println(envio.getEstado());%>">
					</div>
				</div>
				<div class="col-sm-2"></div>

				<button name="submit" id="modificar" type="submit" value="Aceptar"
					class="btn btn-primary">Aceptar</button>
					<br><br><br><br><br>
			</form>

			<%
				}
			%>
			<br><br><br><br><br><br><br><br>
		</div>
		<div class="container-fluid">
	<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>