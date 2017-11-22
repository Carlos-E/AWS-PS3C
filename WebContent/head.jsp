<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/img/icon.ico" />

<link rel="stylesheet" href="/css/style.css">

<!-- CSS del Bootstrap original -->
<link rel="stylesheet" href="/css/bootstrap.min.css">

<!-- CSS modificado del Bootstrap para la navbar  -->
<link rel="stylesheet" href="/css/custom.bootstrap.css">

<!-- Bootstrap Dropdown Hover CSS -->
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/bootstrap-dropdownhover.min.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<!-- Bootstrap Dropdown Hover JS -->
<script src="js/bootstrap-dropdownhover.min.js"></script>

