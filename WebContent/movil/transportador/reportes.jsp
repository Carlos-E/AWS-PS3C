<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Mis reportes");
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
<style>
.odd {
	word-break: break-all;
}

.even {
	word-break: break-all;
}
</style>
</head>
<body>

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/movil/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto small"> <!--  HEADER --> <jsp:include page="/movil/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<!-- INICIO CONTAINER -->

			<div class="card-block">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
					<i id="spinner" class="fa fa-circle-notch fa-spin" style="font-size: 30px"></i>
				</h3>

				<div class="col-sm-12 ">
					<table id="table" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%; font-size: 0.65rem;">
						<thead>

						</thead>
						<tfoot>

						</tfoot>
						<tbody>

						</tbody>
					</table>
				</div>

				<form id="myForm" action="/reportes/crear" method="post">
					<br>
					<div class="form-group">
						<label class="col-12 control-label no-padding" for="message">Escriba su reporte</label>
						<div class="col-12 no-padding">
							<textarea class="form-control" id="nota" name="nota" placeholder="Escriba aqu&iacute; su reporte..." rows="5"></textarea>
						</div>
					</div>

					<div class="modal-footer">
						<button type="reset" class="btn btn-secondary float-left">
							<i class="fas fa-eraser fa-lg"></i>
						</button>
						<button id="submit" type="submit" class="btn btn-primary btn-md float-right">Confirmar</button>
<!-- 						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
 -->					</div>
				</form>
			</div>

			<!-- /FIN CONTAINER -->
		</div>
	</div>
	</section> </main>

	<div class="container-fluid">
		<jsp:include page="/movil/footer.jsp" />
	</div>
	
	<script src="/js/form-modal.js"></script>
	
	<script>
		$(document).ready(function() {
									
			$.ajax({
				url : "/getReportesDeUsuario",
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
						//element.usuario,
						element.nota,
						element.visto ? "si" : "no"
				]);
				});
				
				console.log(dataSet);
					
				$('#table').DataTable( {
			        data: dataSet,
			        language: {
			            url: "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
			        },
			        columns: [
			            { title: "Hora" },
			            //{ title: "usuario" },
			            { title: "Nota" },
			            { title: "Visto" }
			        ],
			        searching: false,
			        info: false
			    } );
				
			
			}).fail(function(xhr, status, errorThrown) {
				if (typeof Android != 'undefined') {
						Android.showToast('Ha ocurrido un error');
				}
			}).always(function(){
		        $("#spinner").fadeOut('slow');
			});	
			
		});
		</script>

	

</body>
</html>