<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
	}
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="/img/icon.ico" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/style.css">
<!-- CSS del Bootstrap original -->
<link rel="stylesheet" href="/css/bootstrap.min.css">
<!-- CSS modificado del Bootstrap para la navbar  -->
<link rel="stylesheet" href="/css/custom.bootstrap.css">
<link rel=stylesheet href="/css/font-awesome.css">
<link rel=stylesheet href="/css/font-awesome.min.css">
<link rel=stylesheet href="/css/stolen.css">

<script src="/js/jquery/3.1.1/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

