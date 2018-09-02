<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="java.util.ArrayList"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Eliminar Envíos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title><%out.print(session.getAttribute("pagina").toString());%></title>
<%
	session.setAttribute("pagina", "Eliminar Envíos");
%>
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
			<!-- INICIO CONTAINER -->

			<div class="card-block" id="buscar-form">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form class="form" name="form" method="post">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Seleccione el env&iacute;o</label>
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
				<form id="myForm" class="form" action="/envios/eliminar" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Cliente</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="cliente" placeholder="" id="cliente" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Fecha</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="fecha" placeholder="" id="fecha" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Origen</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="origen" placeholder="origen" id="origen" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Destino</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="destino" placeholder="destino" id="destino" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Tipo</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tipo" placeholder="tipo" id="tipo" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Espacio</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="espacio" placeholder="espacio" id="espacio" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Peso</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="peso" placeholder="peso" id="peso" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Empresa</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="empresa" placeholder="empresa" id="empresa" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Estado</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="estado" placeholder="estado" id="estado" readonly>
						</div>
					</div>
					<input type="text" id="longitud_Destino" name="longitud_Destino" style="display: none">
					<input type="text" id="latitud_Destino" name="latitud_Destino" style="display: none">
					<input type="text" id="latitud_Origen" name="latitud_Origen" style="display: none">
					<input type="text" id="longitud_Origen" name="longitud_Origen" style="display: none">
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

	<!-- INICIO FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!--  /FIN FOOTER CON SCRIPTS -->
	<!-- /FIN -->
	
	<script>
		$(document).ready(function() {
			
			var lista;
			
			$.ajax({
				url : "/scanTable",
				data : {
					tabla : 'envios'
				},
				type : "POST",
				dataType : "json",
			}).done(function(response) {
				console.log(response);
				
				lista = response;
				    	
				 $(response).each(function() {
				 	$('#select').append($("<option>").attr('value',this.usuario+' : '+this.fecha).text(this.usuario+' : '+this.fecha));
				 	});
				
			}).fail(function(xhr, status, errorThrown) {
				alert("Algo ha salido mal");
				console.log('Failed Request To Servlet /scanTable')
			}).always(function(xhr, status) {
			});			

			$('#buscar').click(function() {
				
				let selectedIndex = $('#select').prop('selectedIndex');
				
				console.log(lista[selectedIndex]);
				
				let objeto = lista[selectedIndex];

				$('#cliente').val(objeto.usuario);
				$('#fecha').val(objeto.fecha);

				$('#origen').val(objeto.origen);
				$('#destino').val(objeto.destino);
				$('#tipo').val(objeto.tipo);
				$('#espacio').val(objeto.espacio);
				$('#peso').val(objeto.peso);
				$('#estado').val(objeto.estado);
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