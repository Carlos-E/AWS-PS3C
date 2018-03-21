<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	int i = 0;
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
	session.setAttribute("pagina", "Seguimiento de Mercancía");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>

<jsp:include page="/head.jsp" />
<title>Mapa</title>

<style type="text/css">
html, body {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
	padding-top: 1rem;
}

body {
	overflow-x: hidden;
}

#map {
	margin: auto;
	padding: 0;
}

#embeded {
	height: 80vh;
	margin: auto !important;
	padding: 0;
	padding-top: 1rem;
	margin: auto !important;
}
</style>

</head>

<body>

	<div class="row pt-1">

		<div class="col-md-12 col-lg-12">
			<div class="container">
				<iframe id="embeded" width="100%" height="80%" frameborder="0" style="border: 0" src="https://www.google.com/maps/embed/v1/directions?origin=new%20york&destination=los%20angeles&key=AIzaSyCBKeFEezcbwUAD_pfyNBEXOn4xWuugg6M" allowfullscreen></iframe>
			</div>
		</div>

	</div>

	<div class="row pt-1">

		<div class="col-md-12 col-lg-12">
			<div class="container">
				<p id="demo"></p>
			</div>
		</div>

	</div>

	<!-- SCRIPTS -->
	<jsp:include page="/scripts.jsp" />

	<!-- JavaScript del mapa -->

	<script src="">
		var place = 'q=urbanizacion%20britania';

		$('#embeded').attr(
				'src',
				'https://www.google.com/maps/embed/v1/place?' + place
						+ '&key=AIzaSyCBKeFEezcbwUAD_pfyNBEXOn4xWuugg6M');
	</script>


	<script>
		var x = document.getElementById("demo");

		var options = {
			enableHighAccuracy : true,
			timeout : 5000,
			maximumAge : 0
		};

		function getLocation() {
			console.log('getlocation');

			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(success, error,
						options);

			} else {
				x.innerHTML = "Geolocation is not supported by this browser.";
			}
		}

		function success(position) {
			console.log('showPosition');
			x.innerHTML = "Latitude: " + position.coords.latitude
					+ "<br>Longitude: " + position.coords.longitude;
		}

		function error(err) {
			console.warn('ERROR(' + err.code + '): ' + err.message);
		};

		window.onload = function() {
			setTimeout(function() {
				//getLocation();
			}, 3000);
		};
	</script>

	<script>
		var id, target, option;

		function success(pos) {
			console.log('changed pos')
			var crd = pos.coords;

			if (target.latitude === crd.latitude
					&& target.longitude === crd.longitude) {
				console.log('Congratulation, you reach the target');
				navigator.geolocation.clearWatch(id);
			}
		};

		function error(err) {
			console.warn('ERROR(' + err.code + '): ' + err.message);
		};

		target = {
			latitude : 0,
			longitude : 0,
		}

		options = {
			enableHighAccuracy : false,
			timeout : 5000,
			maximumAge : 0
		};

		id = navigator.geolocation.watchPosition(success, error, options);
	</script>

</body>
</html>