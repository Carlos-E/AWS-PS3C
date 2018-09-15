<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("rol") == null) {
		//response.sendError(400, "Acceso incorrecto"); //cambiar
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Chequear env&iacute;os recogidos");
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

					</h3>
					<h6 class="text-muted mb-4"></h6>
					<form id="myForm" action="/chequeoCarga" method="post">
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
								<table id="table" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;font-size: 0.7rem;">
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
					<div class="modal-footer">
						<button id="modificar" type="submit" class="btn btn-primary btn-md float-right">Confirmar</button>
						<button id="atras" type="button" data-target="#" class="btn btn-danger btn-md float-right">Atras</button>
					</div>
					</form>
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
	
	var scanTable;
	var tableObj=null;

		$(document).ready(function() {
			
			scanTable = () => {
	
			$.ajax({
				url : "/envios/listar",
				data : {
					tabla : 'envios'
				},
				type : "POST",
				dataType : "json",
			}).done(function(response) {
				console.log(response);				
				let dataSet = [];				
				response.forEach(element => {
					var aux, chequeo = "unchecked";
					if (element.chequeoCarga==true) {
						chequeo = "checked";
					}
					
					let elementEstadoId = element.usuario.replace(/\@/g,'').replace(/\./g,'')+element.fecha.replace(/\s+/g,'').replace(/\-/g,'').replace(/\:/g,'');
					element.estado = '<span id="'+elementEstadoId+'">'+element.estado+'</span>';

					aux = '<input class="chequeo" value="'+element.usuario+';'+element.fecha+'" type="checkbox" '+chequeo+' >';
					element.fecha = '<a href="/envios/modificar.jsp?select='+element.usuario+' : '+element.fecha+'">'+element.fecha+'</a>';
					element.usuario = '<a class="linkNegro" href="/usuarios/listar.jsp?search='+element.usuario+'">'+element.usuario+'</a>';
					element.empresa = '<a class="linkNegro" href="/empresas/listar.jsp?search='+element.empresa+'">'+element.empresa+'</a>';
					
					dataSet.push([
						element.fecha,
						element.usuario,
						element.empresa,
						element.origen,
						element.estado,
						element.destino,						
						element.descripcion,
						aux
				]);
				});
				console.log(dataSet);
					
				if(tableObj!=null){
					tableObj.clear().rows.add(dataSet).draw();
					return;
				}
					
				tableObj = $('#table').DataTable( {
			        data: dataSet,
					language: {
						url: "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
					},
			        columns: [
			            { title: "Fecha" },
			            { title: "Usuario" },
			            { title: "Empresa" },
			            { title: "Origen" },
			            { title: "Estado" },
			            { title: "Destino" },
			            { title: "Descripci&oacute;n" },
			            { title: "Chequeo" }
			        ],initComplete: function(){
							
						$('input[type="checkbox"]').click(function() {
															
							var element = $(this);

							let idEstadoEnvio = element.val().replace(/\@/g,'').replace(/\./g,'').replace(/\;/g,'').replace(/\s+/g,'').replace(/\-/g,'').replace(/\:/g,'');
														
							let url = '/chequeo/recogida';
						
							$.ajax({
							    url: url,
							    data: {
							    	client: element.val().split(';')[0],
								      date: element.val().split(';')[1],
								      value: element.prop('checked')
							    },
							    type: 'POST',
							    dataType: 'json'
							}).done(function(data, statusText, xhr) {

								if (typeof xhr.responseJSON.estadoNuevo != 'undefined') {
									$('#'+idEstadoEnvio).html(xhr.responseJSON.estadoNuevo);
								}
								
								if (typeof xhr.responseJSON.fail != 'undefined') {
									$('#ModalTitle').html(xhr.responseJSON.title);
									$('#ModalBody').html(xhr.responseJSON.message);
									$('#ModalButton').hide();								
									$('#Modal').modal();	
									element.prop('checked',!element.prop('checked'));
								}
								
							}).fail(function(xhr, statusText) {

								$('#ModalTitle').html('C&oacute;digo de Estado: ' + xhr.status);
								$('#ModalBody').html('Oopss a ocurrido un error');
								$('#Modal').modal();
								
							});
						});
			        	
					}
			    } );
							
			}).fail(function(xhr, status, errorThrown) {
				alert("Algo ha salido mal");
				console.log('Failed Request To Servlet /scanTable')
			});
			
			}
			
			scanTable();

		});
	</script>
</body>
</html>