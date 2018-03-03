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
	session.setAttribute("pagina", "Listar Env&iacute;os");
	ArrayList<envio> listaEnvios = ControladorBD.escanearTabla("envios");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
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
			<div class="card-block">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<h6 class="text-muted mb-4">Datos</h6>

				<div id="example_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
					<div class="row">
						<div class="col-sm-12 col-md-6">
							<div class="dataTables_length" id="example_length"></div>
						</div>
						<div class="col-sm-12 col-md-6">
							<div id="example_filter" class="dataTables_filter"></div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table id="tabla-usuarios" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
								<thead>
									<tr>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 71px;">Fecha</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 59px;">Cliente</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 34px;">Empresa</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 64px;">Origen</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 67px;">Destino</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 67px;">Tipo</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 67px;">Capacidad</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 67px;">Camion</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 67px;">Trailer</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th rowspan="1" colspan="1">Fecha</th>
										<th rowspan="1" colspan="1">Cliente</th>
										<th rowspan="1" colspan="1">Empresa</th>
										<th rowspan="1" colspan="1">Origen</th>
										<th rowspan="1" colspan="1">Destino</th>
										<th rowspan="1" colspan="1">Tipo</th>
										<th rowspan="1" colspan="1">Capacidad</th>
										<th rowspan="1" colspan="1">Camion</th>
										<th rowspan="1" colspan="1">Trailer</th>
									</tr>
								</tfoot>
								<tbody>
									<%
										for (int i = 0; i < listaEnvios.size(); i++) {
									%>
									<tr>
										<td>
											<strong>
												<%
													out.println(listaEnvios.get(i).getFecha());
												%>
											</strong>
										</td>
										<td>
											<%
												out.println(listaEnvios.get(i).getUsuario());
											%>
										</td>
										<td>
											<%
												out.println(listaEnvios.get(i).getEmpresa());
											%>
										</td>
										<td>
											<%
												out.println(listaEnvios.get(i).getOrigen());
											%>
										</td>
										<td>
											<%
												out.println(listaEnvios.get(i).getDestino());
											%>
										</td>
										<td>
											<%
												out.println(listaEnvios.get(i).getTipo());
											%>
										</td>
										<td>
											<%
												out.println(listaEnvios.get(i).getEspacio());
											%>
										</td>
										<td id="camion">
											<%
												out.println(listaEnvios.get(i).getCamion());
											%>
										</td>
										<td id="trailer">
											<%
												out.println(listaEnvios.get(i).getTrailer());
											%>
										</td>								
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 col-md-5">
							<div class="dataTables_info" id="example_info" role="status" aria-live="polite"></div>
						</div>
						<div class="col-sm-12 col-md-7">
							<div class="dataTables_paginate paging_simple_numbers" id="example_paginate"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /FIN CONTAINER -->

	</div>
	</section> </main>
	<!-- Modal -->
	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!-- /FIN -->

	<script>
		$(document).ready(function() {
			$('#tabla').DataTable();
		});		
	</script>
</body>
</html>