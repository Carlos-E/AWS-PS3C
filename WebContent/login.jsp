<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="clases.DB"%>

<%
System.out.println("New Login");
System.out.println("AccessKeyId: " + new DB().getAccessKeyId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Login PS3C">
<meta name="author" content="Carlos E. Perez, Luis Puche">
<meta name="version" content="1.0.0">
<link rel="shortcut icon" type="image/png" href="/img/icon.ico" />

<title>Inicio de sesi&oacute;n</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- Icons -->
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<!-- Custom styles for this template -->
<link href="/css/login.css?v=1.0.0" rel="stylesheet">
</head>

<body>

	<div class="background-image"></div>

	<form id="myForm" class="form-signin" method="post" action="/login" role="login">

		<h1 class="text-center h1 mt-3 mb-3 font-weight-normal">
			<em class="fa fa-truck fa-sm"></em>
			PS3C
		</h1>

		<h1 class="text-center h3 mt-3 mb-3 font-weight-normal">Iniciar sesi&oacute;n</h1>

		<div class="form-label-group">
			<input type="email" id="inputEmail" class="form-control" name="username" placeholder="Usuario" required autofocus>
			<label class="unselectable" for="inputEmail">Correo</label>
		</div>

		<div class="form-label-group">
			<input type="password" id="inputPassword" class="form-control" name="password" required>
			<label class="unselectable" for="inputPassword">Contrase&ntildea</label>
		</div>

		<div class="checkbox mt-3 mb-3">
			<label id="remember">
				<input  type="checkbox" value="remember-me">
				Recuerdame
			</label>
		</div>

		<button id="submit" type="submit" class="btn btn-lg btn-primary btn-block">
			<span class="glyphicon glyphicon-log-in"></span>
			Ingresar
		</button>

		<div class="text-center mt-3 mb-3">
			<a href="signin.jsp" hidden>Registrarse</a>
		</div>

		<p class="text-center mt-5 mb-3 text-muted">&copy; 2017-2018</p>
	</form>
	
	<!-- Modal -->
	<div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="ModalTitle"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="ModalBody"></div>
			<div class="modal-footer">
 				<button type="button" class="btn btn-primary btn-md float-right" id="ModalButton"></button>
 				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
			</div>
		</div>
	</div>
	</div>

	<!-- Scripts -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<!-- Scripts -->
	
	<script>
	if (typeof Android != 'undefined') {
		var BestIsOn = false;
		Android.toggleBestUpdates(BestIsOn);
		document.getElementById('remember').setAttribute('hidden','true');
	}
	</script>
	
	<script>
	$(document).ready(function() {

		$('#myForm').submit(function(e) {
			
			e.preventDefault();

			$('#ModalButton').hide();

			switch (Math.floor((Math.random() * 5) + 1)) {
			case 1:
				$('#submit').html('<i class="fas fa-spinner fa-spin fa-lg"></i>');
				break;
			case 2:
				$('#submit').html('<i class="fas fa-circle-notch fa-spin fa-lg"></i>');
				break;
			case 3:
				$('#submit').html('<i class="fas fa-sync fa-spin fa-lg"></i>');
				break;
			case 4:
				$('#submit').html('<i class="fas fa-cog fa-spin fa-lg"></i>');
				break;
			case 5:
				$('#submit').html('<i class="fas fa-spinner fa-pulse fa-lg"></i>');
				break;
			}
		
			let url = $(this).attr('action');
			let data = $(this).serializeArray();
			
			//console.log('URL: '+ JSON.stringify(url,null,2));
			//console.log('Data: '+ JSON.stringify(data,null,2));
			
			$.ajax({
				url : url,
				data : data,
				type : "POST",
				dataType : "json"
			}).done(function(data, statusText, xhr) {
				
				$('#ModalTitle').html('Operaci&oacute;n exitosa');
				$('#ModalBody').html('Operaci&oacute;n completada');
				
			}).fail(function(xhr, statusText) {

				$('#ModalTitle').html('C&oacute;digo de Estado: ' + xhr.status);
				$('#ModalBody').html('Usuario o contraseña incorrectos');
				
			}).always(function(xhr, statusText,a,b,c) {
				
				if(a!=''){
					xhr = a;
				}
				
				console.log(JSON.stringify(xhr, null, 2));
				console.log(JSON.stringify(statusText, null, 2));
				console.log(JSON.stringify(a, null, 2));
				console.log(JSON.stringify(b, null, 2));
				console.log(JSON.stringify(c, null, 2));

				if (typeof xhr.responseJSON != 'undefined') {
					if (typeof xhr.responseJSON.title != 'undefined') {
						$('#ModalTitle').html(xhr.responseJSON.title);
					}
					if (typeof xhr.responseJSON.message != 'undefined') {
						$('#ModalBody').html(xhr.responseJSON.message);
					}
					if(typeof xhr.responseJSON.sendRedirect != 'undefined'){
						window.location.replace(xhr.responseJSON.sendRedirect);
					}
				}
				
				$("#Modal").modal();
				$('#submit').html('Ingresar');

			});

		});
	});
</script>

</body>
</html>
