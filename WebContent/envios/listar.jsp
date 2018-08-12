<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("rol") == null) {
		//response.sendError(400, "Acceso incorrecto"); //cambiar
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Listar Env&iacute;os");
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
	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER -->
	<section class="row">
		<div class="col-md-12 col-lg-12">

			<div class="card mb-4">

				<div class="card-block">
					<h3 class="card-title">
						Datos
						<i id="spinner" class="fa fa-circle-notch fa-spin" style="font-size: 30px"></i>
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
								<table id="tabla" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%; font-size: 0.6rem;">
									<thead>

									</thead>
									<tfoot>

									</tfoot>
									<tbody>

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
	</section>
	</main>
	<!-- Modal -->
	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!-- /FIN -->

	<script>
				$(document).ready(function () {

					$.ajax({
						url: "/envios/listar",
						data: {
							tabla: 'envios'
						},
						type: "POST",
						dataType: "json"
					}).done(function (response) {

							loadTable(response);
							
					}).fail(function (xhr, status, errorThrown) {
						alert("Error cargando informacion");
						console.log('Failed Request To Servlet /listarEnvios envios')
					});

				});
				
				
				function loadTable(envios){
					
					let dataSet = [];

					envios.forEach(envio => {

						dataSet.push([
							envio.fecha,
							envio.usuario,
							envio.empresa,
							envio.origen,
							envio.destino,
							envio.estado,
							envio.tipo,
							envio.espacio,
							envio.peso,
							envio.camion,
							envio.trailer,
							envio.descripcion,
						]);

					});

					$('#tabla').DataTable({
						data: dataSet,
						language: {
							url: "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
						},
						columns: [
							{ title: "Fecha" },
							{ title: "Usuario" },
							{ title: "Empresa" },
							{ title: "Origen" },
							{ title: "Destino" },
							{ title: "Estado" },
							{ title: "Tipo" },
							{ title: "Espacio" },
							{ title: "Peso" },
							{ title: "Cami&oacute;n/Remolque" },
							{ title: "Trailer" },
							{ title: "Descripci&oacute;n" }
						]
					});
					
					$("#spinner").fadeOut("slow");
					
				}
			</script>
</body>

</html>