<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	int i = 0;
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Seguimiento de mercancía");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>

<jsp:include page="/head.jsp" />
<title>Mapa</title>

<style type="text/css">
html, body {
	/* 	height: 100%;
 */
	margin: 0;
	padding: 0;
}

#map {
	height: 70vh;
	width: 80vw;
}
</style>

</head>

<body>

	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!--  NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>

	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <section class="row">
	<div class="col-md-12 col-lg-12">
		<!-- Container del mapa -->
		<div class="embed-responsive embed-responsive-16by9">
			<div id="map" class="embed-responsive-item" style="border: 1px solid black"></div>
		</div>
	</div>

	<!-- 
		<br>
		<div class="container">
			<button id="botonlocalizar" onclick="localizar()" class="btn btn-primary">Localizarte</button>
		</div>
	</div> --> </section> </main>


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="myModalTitle"></h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="myModalBody"></div>
				<div class="modal-footer">
<!-- 					<button type="button" class="btn btn-primary btn-md float-right" id="ruta">Ver ruta</button>
 -->					<button type="button" class="btn btn-primary btn-md float-right" id="myModalButton">Ver env&iacute;os</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>

	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />

	<!-- JavaScript del mapa -->
	
	<script src="/js/map.js"></script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsQwNmnSYTDtkrlXKeKnfP0x8TNwVJ2uI&language=es&callback=initMap"></script>
	
	
	<script>
	$(document).ready(function(){

		$('#myModalButton').click(function(){
			console.log('hi');
			window.open('/envios/listar.jsp?search='+$('#myModalTitle').html(), '_blank');
		});
		
		/* $('#ruta').click(function(){
			console.log('Calculando ruta');
			calculateAndDisplayRoute(directionsService, directionsDisplay);
		}); */
	
	});
	
	
		$.getJSON('/mapeoDeMercancia', function(data) {
			ponerMarcadores(data);
		});
		setInterval(function() {
			$.getJSON('/mapeoDeMercancia', function(data) {
				ponerMarcadores(data);
			});
		}, 5000);
		
	</script>

</body>
</html>