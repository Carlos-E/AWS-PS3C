<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
session.setAttribute("pagina", "Chequear Descarga de Mercancía");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Chequeo de Descarga de Mercancia</title>

<jsp:include page="/head.jsp" />

</head>
<body>

	<!-- Header  -->
	<!--  Container de la Barra de navegacion -->
	<jsp:include page="/navbar.jsp" />
	<!-- Contenido -->
	<div class="fondo"> <br> <br>
		<div class="container">
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="com.logica.*"%>
			<%@ page import="clases.*"%>
			<%
				ArrayList<camion> listaCamion = ControladorBD.escanearTabla("camiones");
				ArrayList<trailer> listaTrailers = ControladorBD.escanearTabla("trailers");
				ArrayList<camion> listaCamiones = new ArrayList<camion>();
				for (int i = 0; i < listaCamion.size(); i++) {
					if (!listaCamion.get(i).getTipo().equals("camion")) {
						listaCamiones.add(listaCamion.get(i));
					}
				}

				if (session.getAttribute("busca") != "chequeo") {
			%>
			<form id="form" name="form" action="/buscar" method="post"
				class="form-horizontal">
				<div id="contact-form2">
					<span>Mercancia: </span> <select class="form-control" id="subject"
						name="chequeoDescarga" tabindex="4">
						<%
							for (int i = 0; i < listaCamiones.size(); i++) {
						%>
						<option value="<%out.print(listaCamiones.get(i).getPlaca());%>">Camion
							Placa :
							<%
							out.print(listaCamiones.get(i).getPlaca());
						%>
						</option>
						<%
							}
								for (int i = 0; i < listaTrailers.size(); i++) {
						%>
						<option value="<%out.print(listaTrailers.get(i).getPatente());%>">Trailer
							Patente:
							<%
							out.print(listaTrailers.get(i).getPatente());
						%>
						</option>
						<%
							}
						%>
					</select>
				</div>
				<div class="col-sm-2"></div>

				<button type="submit" class="btn btn-primary">Buscar</button>

			</form>

			<%
				} else {
					String camtra = session.getAttribute("obj").toString();
					camion camion = new camion();
					trailer trailer = new trailer();
					ArrayList<envio> envio = ControladorBD.escanearTabla("envios");
					ArrayList<envio> envios = new ArrayList<envio>();
					try {
						for (int i = 0; i < envio.size(); i++) {
							if (envio.get(i).getCamion().equals(camtra)) {
								envios.add(envio.get(i));
							}
						}
					} catch (Exception e) {
						System.out.println("---no es camion");
					}
					try {
						for (int i = 0; i < envio.size(); i++) {
							if (envio.get(i).getTrailer().equals(camtra)) {
								envios.add(envio.get(i));
							}
						}
					} catch (Exception e) {
						System.out.println("---no es trailer");
					}
					if (!envios.isEmpty()) {
			%>
			<form id="form" name="form" action="/chequeoDescarga" method="post">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Fecha</th>
							<th>Cliente</th>
							<th>Tipo</th>
							<th>Estado</th>
							<th>Origen</th>
							<th>Destino</th>
							<th>Espacio</th>
							<th>Chequeo</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < envios.size(); i++) {
										String chequeo = "";
										if (envios.get(i).isChequeoDescarga()) {
											chequeo = "checked='checked'";
										}
						%>
						<tr>
							<td>
								<%
									out.println(envios.get(i).getFecha());
								%>
							</td>
							<td>
								<%
									out.println(envios.get(i).getUsuario());
								%>
							</td>
							<td>
								<%
									out.println(envios.get(i).getTipo());
								%>
							</td>
							<td>
								<%
									out.println(envios.get(i).getEstado());
								%>
							</td>
							<td>
								<%
									out.println(envios.get(i).getOrigen());
								%>
							</td>
							<td>
								<%
									out.println(envios.get(i).getDestino());
								%>
							</td>
							<td>
								<%
									out.println(envios.get(i).getEspacio());
								%>
							</td>
							<td><input name="<%out.print(envios.get(i).getFecha());%>"
								value="true" type="checkbox" <%out.print(chequeo);%>></td>
							<%
								}
							%>
						</tr>
					</tbody>
				</table>
				<div class="col-sm-2"></div>
				<button name="submit" id="submit" type="submit"
					class="btn btn-primary">Chequear</button>
			</form>
			<div class="col-sm-2"></div>
			<form id="form" name="form" action="/cancelar" method="post">
				<button name="submit" id="cancelar" type="submit"
					class="btn btn-danger">Cancelar</button>
			</form>
			<%
				} else {
			%>

			<form id="form" name="form" action="/chequeo/chequearDescarga.jsp"
				method="post" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-2" for="destino"> Este
						camion o trailer no contiene envios </label>
				</div>
				<div class="col-sm-2"></div>
				<button type="submit" class="btn btn-primary">Atras</button>
			</form>

			<%
				}
				}
				session.setAttribute("busca", " ");
			%>
		</div>
		<br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>
