<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
%>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- Icons -->
<!-- <link href="./css/font-awesome.css" rel="stylesheet"> -->
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<!-- Custom styles for this template -->
<link href="/css/style.css" rel="stylesheet">

<link rel="stylesheet" href="/css/theme-teal.css" id="theme-css">
<!-- End Theme Switcher -->
