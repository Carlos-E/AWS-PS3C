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
	session.setAttribute("pagina", "Listar Usuarios");
	ArrayList<usuario> listaUsuario = ControladorBD.escanearTabla("usuarios");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
<jsp:include page="/head.jsp" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.16/af-2.2.2/b-1.5.1/b-colvis-1.5.1/b-flash-1.5.1/b-html5-1.5.1/b-print-1.5.1/cr-1.4.1/fc-3.2.4/fh-3.1.3/kt-2.3.2/r-2.2.1/rg-1.0.2/rr-1.2.3/sc-1.4.4/sl-1.2.5/datatables.min.css" />
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
					Datos
				</h3>
				<h6 class="text-muted mb-4"></h6>

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
							<table id="tabla" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
								<thead>
									<tr>
										<th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 67px;">Cedula</th>

										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 71px;">Nombre</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 59px;">Apellido</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 34px;">Rol</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 64px;">Correo</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 67px;">Direcci&oacute;n</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th rowspan="1" colspan="1">Cedula</th>
										<th rowspan="1" colspan="1">Nombre</th>
										<th rowspan="1" colspan="1">Apellido</th>
										<th rowspan="1" colspan="1">Rol</th>
										<th rowspan="1" colspan="1">Correo</th>
										<th rowspan="1" colspan="1">Direcci&oacute;n</th>
									</tr>
								</tfoot>
								<tbody>
									<%
										for (int i = 0; i < listaUsuario.size(); i++) {
									%>
									<tr>
										<td>
											<strong>
												<%
													out.println(listaUsuario.get(i).getUsuario());
												%>
											</strong>
										</td>
										<td>
											<%
												out.println(listaUsuario.get(i).getNombre());
											%>
										</td>
										<td>
											<%
												out.println(listaUsuario.get(i).getApellido());
											%>
										</td>
										<td>
											<%
												out.println(listaUsuario.get(i).getRol());
											%>
										</td>
										<td>
											<%
												out.println(listaUsuario.get(i).getCorreo());
											%>
										</td>
										<td>
											<%
												out.println(listaUsuario.get(i).getDireccion());
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

	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.16/af-2.2.2/b-1.5.1/b-colvis-1.5.1/b-flash-1.5.1/b-html5-1.5.1/b-print-1.5.1/cr-1.4.1/fc-3.2.4/fh-3.1.3/kt-2.3.2/r-2.2.1/rg-1.0.2/rr-1.2.3/sc-1.4.4/sl-1.2.5/datatables.min.js"></script>

	<script>
		$(document).ready(function() {
			$('#tabla').DataTable();
		});
	</script>
</body>
</html>