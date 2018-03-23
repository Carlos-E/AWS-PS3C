<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/movil/login.jsp");
	}
%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%
	Usuario usuario = new Usuario();
	usuario = (Usuario) com.logica.ControladorBD.getItem("usuarios", "usuario",
			session.getAttribute("username").toString());
%>
<nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2">
	<h1 class="site-title">
		<a id="nav-brand-link" href="/movil/index.jsp">
			<em class="fa fa-truck fa-2x"></em>
			PS3C
		</a>
	</h1>
	<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">
		<em class="fa fa-bars"></em>
	</a>
	<ul class="nav nav-pills flex-column sidebar-nav">
		<li class="nav-item">
			<a class="nav-link active" href="/movil/index.jsp">
				<em class="fa fa-tachometer-alt"></em>
				Inicio
				<span class="sr-only">(current)</span>
			</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/movil/transportador/envios.jsp">
				Env&iacute;os
				<em class="fa fa-paper-plane">&nbsp;</em>
			</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/movil/transportador/reportes.jsp">
				Reportes
				<em class="fa fa-newspaper">&nbsp;</em>
			</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/movil/transportador/mapa.jsp">
				Mapa
				<em class="fa fa-map">&nbsp;</em>
			</a>
		</li>

	</ul>
	<a href="/logout" class="nav-link">
		<em class="fa fa-power-off"></em>
		Cerrar Sesi&oacute;n
	</a>
</nav>
