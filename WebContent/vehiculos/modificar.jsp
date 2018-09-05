<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%@ page import="com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Modificar veh&iacute;culo");
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
	<%
	 	DB DB = new DB();
		List<Usuario> listaUsuarios = DB.scan(Usuario.class, new DynamoDBScanExpression());
		List<Vehiculo> listavehiculos = DB.scan(Vehiculo.class, new DynamoDBScanExpression());
		List<Usuario> listaConductor = new ArrayList<Usuario>();
		List<Empresa> listaEmpresas = DB.scan(Empresa.class, new DynamoDBScanExpression());
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getRol().equals("conductor")) {
				if (!DB.estaOcupado(listaUsuarios.get(i).getUsuario(), "null")) {
					listaConductor.add(listaUsuarios.get(i));
				}
			}
		}
	%>
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
						<label class="col-md-3 col-form-label">Seleccione el veh&iacute;culo</label>
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
				<form id="myForm" class="form" action="/vehiculos/modificar" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Placa</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="placa" placeholder="placa" id="placa" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Estado</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="estado" id="estado" readonly>
						</div>
					</div>
					

					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Conductor asignado</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="conductorAsignado" id="conductorAsignado" readonly>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Conductores disponibles</label>
						<div class="col-md-4">
							<select class="form-control" name="conductor" id="conductor" required>
								
							</select>
						</div>
					</div>
					
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Empresa</label>
						<div class="col-md-4">
							<select class="form-control" name="empresa" id="empresa" required>
								<option value="" selected>Seleccionar...</option>
								<%
									for (int i = 0; i < listaEmpresas.size(); i++) {
								%>
								<option value="<%out.print(listaEmpresas.get(i).getNit());%>">
									<%
										out.print(listaEmpresas.get(i).getNombre());
									%>
								</option>
								<%
									}
								%>
							</select>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Tipo</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="tipo" id="tipo" readonly>
						</div>
					</div>
					<div id="peso-espacio">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Peso m&aacute;ximo(Kg)</label>
						<div class="col-md-4">
							<input class="form-control" pattern="^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$" name="pesoMax" placeholder="en kilogramos" id="pesoMax" required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Espacio m&aacute;ximo(m<sup>3</sup>)</label>
						<div class="col-md-4"> 
							<input class="form-control" pattern="^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$" name="espacioMax" placeholder="en metros c&uacute;bicos" id="espacioMax" required>
						</div>
					</div>
					</div>

					<div class="modal-footer">
						<button type="reset" class="btn btn-secondary float-left"><i class="fas fa-eraser fa-lg"></i></button>
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
	var table = 'vehiculos';
	
	var scanTable;
	var fillSelect;
	var fillInputs;
	
	$(document).ready(function() {

		scanTable = (table, callback) => {
					
					$.ajax({
						url : "/vehiculos/modificar/listar",
						data : {
							tabla : table
						},
						type : "POST",
						dataType : "json",
					}).done(function(response) {
						console.log(response);
								
						lista = response;
								
						callback(response);
								
					}).fail(function(xhr, status, errorThrown) {
						alert("Algo ha salido mal");
						console.log('Failed Request To Servlet /scanTable')
					});
					
		}
					
		fillSelect = (list) => {
			
			$(list).each(function() {
				$('#select').append($("<option>").attr('value',this.placa).text(this.placa));
			});
			
			if(getParameterByName('select') != null ){
				$('#select').val(getParameterByName('select'));
				fillInputs();
			}
		}

		fillInputs = () => {
			
			let selectedIndex = $('#select').prop('selectedIndex');
			console.log(lista[selectedIndex]);
			let objeto = lista[selectedIndex];
			
			$('#placa').val(objeto.placa);
			$('#estado').val(objeto.estado);
			$('#tipo').val(objeto.tipo);
						
			if(objeto.tipo=='remolque'){
				$('#pesoMax').prop('disabled',true);
				$('#espacioMax').prop('disabled',true);
				$('#peso-espacio').hide();
			}else{
				$('#pesoMax').prop('disabled',false);
				$('#espacioMax').prop('disabled',false);
				$('#peso-espacio').show();
			}
						
			$('#pesoMax').val(objeto.pesoMax);
			$('#espacioMax').val(objeto.espacioMax);
						
			$('#empresa').val(objeto.empresa);
			$('#conductorAsignado').val(objeto.usuario);
						
			$('#buscar-form').hide();
			$('#form').removeAttr('hidden');
			$('#form').show();
						
			$.getJSON( "/usuarios/conductoresDisponibles", function(data,textStatus,jqXHR) {
				$('#conductor').find('option').remove()
		          
				$('#conductor').append($("<option>").attr('value','').text('Seleccionar...'))
				.append($("<option>").attr('value','ninguno').text('ninguno'));
				
				$(data).each(function() {
					$('#conductor').append($("<option>").attr('value',this.usuario).text(this.nombre+' '+this.apellido));
				});
			});
		}
	
		$('#buscar').click(fillInputs);

		$('#atras').click(function() {
			$('#buscar-form').show();
			$('#form').hide();
		});
						
		scanTable('envios',function(list){
			fillSelect(list);			
		});
					
	});
	</script>
</body>
</html>