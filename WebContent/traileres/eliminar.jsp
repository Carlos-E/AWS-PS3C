<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Eliminar trailer");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/head.jsp" />
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
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
			<!-- INICIO CONTAINER -->

			<div class="card-block" id="buscar-form">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form class="form" name="form" method="post">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Seleccione el trailer</label>
						<div class="col-md-9">
							<select class="custom-select form-control" id="select">
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<button id="buscar" type="button" class="btn btn-primary btn-md float-right">Buscar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>

			<div class="card-block" id="form" hidden="true">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form id="myForm" class="form" action="/trailer/eliminar" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Patente</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="patente" placeholder="patente" id="patente" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Estado</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="estado" placeholder="estado" id="estado" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Empresa</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="empresa" placeholder="empresa" id="empresa" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Tipo</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tipo" placeholder="tipo" id="tipo" readonly>
						</div>
					</div>
					<div class="form-group row">
						
						<label class="col-md-2 col-form-label text-capitalize">Remolque</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="remolque" placeholder="remolque" id="remolque" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Peso M&aacute;ximo(Kg)</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="pesoMax" id="pesoMax" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Espacio M&aacute;ximo(m<sup>3</sup>)</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="espacioMax" id="espacioMax" readonly>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn float-left"><i id="reset"></i></button>
						<button id="submit" type="submit" class="btn btn-primary btn-md float-right">Confirmar</button>
						<button id="atras" type="button" data-target="#" class="btn btn-danger btn-md float-right">Atras</button>
					</div>
				</form>
			</div>
			<!-- /FIN CONTAINER -->
		</div>
	</div>
	</section> </main>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Confirmaci&oacute;n</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Desea cancelar?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
					<form name="form" action="/cancelar" method="post">
						<button type="submit" class="btn btn-danger btn-md float-right">Cancelar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!-- /FIN -->
	<script>
	var lista;
	var table = 'trailers';
	var scanFunction;
	
	$(document).ready(function() {
			
	scanFunction = (table) => {
					
		$.ajax({
						url : "/scanTable",
						data : {
							tabla : table
						},
						type : "POST",
						dataType : "json",
					}).done(
							function(response) {

								lista = response;
								
								$('#select').find('option').remove();
								
								$(response).each(function() {
									$('#select').append($("<option>").attr('value',this.patente).text(this.patente));
								});
								
							}).fail(function(xhr, status, errorThrown) {
						alert("Algo ha salido mal");
						console.log('Failed Request To Servlet /scanTable')
					});
	}
	scanFunction(table);


					$('#buscar').click(function() {
						let selectedIndex = $('#select').prop('selectedIndex');
						console.log(lista[selectedIndex]);
						let objeto = lista[selectedIndex];
						$('#patente').val(objeto.patente);
						$('#tipo').val(objeto.tipo);
						$('#estado').val(objeto.estado);
						$('#pesoMax').val(objeto.pesoMax);
						$('#espacioMax').val(objeto.espacioMax);
						$('#remolque').val(objeto.camion);
						$('#empresa').val(objeto.empresa);
						$('#buscar-form').hide();
						$('#form').removeAttr('hidden');
						$('#form').show();
					});
					
					$('#atras').click(function() {
						$('#buscar-form').show();
						$('#form').hide();
					});
					
				});
	</script>
</body>

</html>