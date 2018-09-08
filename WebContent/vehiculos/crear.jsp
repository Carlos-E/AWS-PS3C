<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%@ page import="com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Crear vehículo");
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
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>
	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <%
 	DB DB = new DB();
	List<Usuario> listaUsuarios = DB.scan(Usuario.class, new DynamoDBScanExpression());
 	List<Vehiculo> listaCamiones = DB.scan(Vehiculo.class, new DynamoDBScanExpression());
 	List<Usuario> listaConductor = new ArrayList<Usuario>();
 	List<Empresa> listaEmpresas = DB.scan(Empresa.class, new DynamoDBScanExpression());
 	for (int i = 0; i < listaUsuarios.size(); i++) {
 		if (listaUsuarios.get(i).getRol().equals("conductor")) {
 			if (!DB.estaOcupado(listaUsuarios.get(i).getUsuario(), "null")) {
 				listaConductor.add(listaUsuarios.get(i));
 			}
 		}
 	}
 %> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<div class="card-block">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>
				<form id="myForm" class="form" action="/vehiculos/crear" method="post">
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Placa</label>
						<div class="col-md-4">
							<input class="form-control" type="text" name="placa" placeholder="placa" id="placa" required>
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
						<label class="col-md-2 col-form-label text-capitalize">Conductor</label>
						<div class="col-md-4">
							<select class="form-control" name="conductor" id="conductor" required>
								<option value="" selected>Seleccionar...</option>
								<option value="ninguno" >ninguno</option>
								<%
									for (int i = 0; i < listaConductor.size(); i++) {
								%>
								<option value="<%out.print(listaConductor.get(i).getUsuario());%>">
									<%
										out.print(listaConductor.get(i).getUsuario());
									%>
								</option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-2 col-form-label text-capitalize">Cami&oacute;n</label>
						<div class="col-md-4">
							<input type="radio" name="tipo" onclick="mostrar()" value="camion">
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Remolque</label>
						<div class="col-md-4">
							<input type="radio" name="tipo" onclick="ocultar()" value="remolque" checked="checked">
						</div>
					</div>
					<div id="oculto" class="form-group row" style="display: none;">
						<label class="col-md-2 col-form-label text-capitalize">Peso m&aacute;ximo(Kg)</label>
						<div class="col-md-4">
							<input class="form-control" pattern="^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$" name="peso" placeholder="en kilogramos" id="peso" value="0" disabled required>
						</div>
						<label class="col-md-2 col-form-label text-capitalize">Espacio m&aacute;ximo(m<sup>3</sup>)</label>
						<div class="col-md-4">
							<input class="form-control" pattern="^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$" name="espacio" placeholder="en metros c&uacute;bicos" id="espacio" value="0" disabled required>
						</div>
					</div>
					
					<div class="modal-footer">
						<button type="reset" class="btn btn-secondary float-left"><i class="fas fa-eraser fa-lg"></i></button>
						<button id="submit" type="submit" class="btn btn-primary btn-md float-right">Confirmar</button>
						<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-danger btn-md float-right">Cancelar</button>
					</div>
				</form>
			</div>

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
	<script type="text/javascript">
		function mostrar() {
			document.getElementById('oculto').style.display = '';
			$('#peso').prop('disabled',false);
			$('#espacio').prop('disabled',false);
		}

		function ocultar() {
			document.getElementById('oculto').style.display = 'none';
			$('#peso').prop('disabled',true);
			$('#espacio').prop('disabled',true);
		}
	</script>
</body>
</html>