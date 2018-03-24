<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Generar Reportes");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<jsp:include page="/movil/head.jsp" />
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
</head>
<body>

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/movil/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main  class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto small"> <!--  HEADER --> <jsp:include page="/movil/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<!-- INICIO CONTAINER -->

			<div class="card-block">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>

				<form class="form" action="#" method="post">

					<div class="col-sm-12 ">
						<table id="tabla" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
							<thead>

							</thead>
							<tfoot>

							</tfoot>
							<tbody>

							</tbody>
						</table>
					</div>
					<br>
					<br>
					<div class="form-group">
						<label class="col-12 control-label no-padding" for="message">Escriba su Reporte</label>
						<div class="col-12 no-padding">
							<textarea class="form-control" id="message" name="message" placeholder="Escriba aqui su reporte..." rows="5"></textarea>
						</div>
					</div>

					<div class="modal-footer">
						<button type="submit" name="submit" class="btn btn-primary btn-md float-right">Reportar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>

			<!-- /FIN CONTAINER -->
		</div>
	</div>
	</section> </main>

	<div class="container-fluid">
		<jsp:include page="/movil/footer.jsp" />
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
				console.log(response);
				
				let dataSet = [];
				
				response.forEach(element => {
					dataSet.push([
						element.hora,
						element.usuario,
						element.nota
				]);
				});
				
				console.log(dataSet);
					
				$('#tabla').DataTable( {
			        data: dataSet,
			        columns: [
			            { title: "hora" },
			            { title: "usuario" },
			            { title: "nota" }
			        ]
			    } );
				
		        $("#spinner").fadeOut("slow");
			
			}).fail(function(xhr, status, errorThrown) {
				alert("Algo ha salido mal");
				console.log('Failed Request To Servlet /scanTable')
			}).always(function(xhr, status) {
			});		
			
		});
	</script>
</body>
</html>