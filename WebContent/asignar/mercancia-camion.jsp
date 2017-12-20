<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
session.setAttribute("pagina", "Asignar Mercancía a Camion");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Asignar Mercancia-Camion</title>

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
		<br>
		<br>
		<div class="container">

			<%@ page import="com.logica.*"%>
			<%@ page import="java.util.ArrayList"%>
			<%@ page import="clases.*"%>
			<% ArrayList<camion> listaCamiones = ControladorBD.escanearTabla("camiones");
                   ArrayList<camion> listaCamion = new ArrayList<camion>();
                   for(int i=0; i<listaCamiones.size();i++){
                	   if(listaCamiones.get(i).getTipo().equals("camion") ){
                		   listaCamion.add(listaCamiones.get(i));
                       }
                   }
                   %>
			<% ArrayList<envio> listaEnvio = ControladorBD.escanearTabla("envios");
                %>
			<form id="form" name="form" class="form" action="/mercanciaACamion"
				method="post">
				<table class="table table-bordered">
					<tr>
						<td>
							<div id="contact-form">
								<span>Camion: </span> <select class="form-control" id="subject"
									name="camion" tabindex="4">
									<%for(int i=0;i<listaCamion.size();i++){ %>
									<option value="<%out.print(listaCamion.get(i).getPlaca()); %>">
										<%out.print(listaCamion.get(i).getPlaca()); %>
									</option>
									<%}%>
								</select>
							</div>
						</td>
						<td>
							<div id="contact-form2">
								<span>Mercancia: </span> <select class="form-control"
									id="subject" name="mercancia" tabindex="4">
									<%for(int i=0;i<listaEnvio.size();i++){ %>
									<option
										value="<%out.print(listaEnvio.get(i).getUsuario()+" : "+listaEnvio.get(i).getFecha());%>">
										<%out.print(listaEnvio.get(i).getUsuario()+" : "+listaEnvio.get(i).getFecha());%>
									</option>
									<%} %>
								</select>
							</div>
						</td>
					</tr>
				</table>
				<!-- <input class="submit" id="subject" type="submit" name="Asignar">
                 -->
				<button name="submit" id="submit" type="submit"
					class="btn btn-primary">Asignar</button>
					<button formaction="/cancelar" name="submit" id="cancelar" type="submit"
						class="btn btn-danger">Cancelar</button>
			</form>
			<br>
			<br>
		</div>
		<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
</body>
</html>