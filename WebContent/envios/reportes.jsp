<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Consultar reportes");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<jsp:include page="/head.jsp" />
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
</head>
<body>
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
			<!-- INICIO CONTAINER -->
			<form id="myForm" action="/reportes/visto" method="post">
				<div class="card-block">
					<h3 class="card-title">
						<%
							out.print(session.getAttribute("pagina").toString());
						%>
					</h3>

					<div class="col-sm-12">
						<table id="tabla" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%; font-size: 0.65rem;">
							<thead>

							</thead>
							<tfoot>

							</tfoot>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submit" type="submit" class="btn btn-primary btn-md float-right">Confirmar</button>
					<button id="atras" type="button" data-target="#" class="btn btn-danger btn-md float-right">Atras</button>
				</div>
			</form>
			<!-- /FIN CONTAINER -->
		</div>
	</div>
	</section> </main>

	<div class="container-fluid">
		<jsp:include page="/footer.jsp" />
	</div>
	<script>

		$(document).ready(function() {
						
			$.ajax({
				url : "/scanTable",
				data : {
					tabla : 'reportes'
				},
				type : "POST",
				dataType : "json",
			}).done(function(response) {
				
				let dataSet = [];
				
				response.forEach(element => {
					var aux, visto = "unchecked";
					if (element.visto=="true") {
						visto = "checked='checked'";
					}
					aux = '<input id="'+element.hora+'" name="'+element.hora+'" value="true" type="checkbox" '+visto+' >';
					dataSet.push([
						element.hora,
						element.usuario,
						element.nota,
						aux
				]);
				});
				
					
				$('#tabla').DataTable( {
			        data: dataSet,
			        language: {
			            url: "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
			        },
			        columns: [
			            { title: "Hora" },
			            { title: "Usuario" },
			            { title: "Nota" },
			            { title: "Visto" }
			        ]
			    } );
				
			
			}).fail(function(xhr, status, errorThrown) {
				alert("Algo ha salido mal");
				console.log('Failed Request To Servlet /scanTable')
			}).always(function(xhr, status) {
			});		
			
		});
		
	</script>
</body>
</html>