<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/login.jsp");
	}
%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%
	usuario usuario = new usuario();
	usuario = (usuario) com.logica.ControladorBD.getItem("usuarios", "usuario",
			session.getAttribute("username").toString());
%>
<nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2">
	<h1 class="site-title">
		<a id="nav-brand-link" href="/index.jsp">
			<em class="fa fa-truck fa-2x"></em>
			PS3C
		</a>
	</h1>
	<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">
		<em class="fa fa-bars"></em>
	</a>
	<ul class="nav nav-pills flex-column sidebar-nav">
		<li class="nav-item">
			<a class="nav-link active" href="/index.jsp">
				<em class="fa fa-tachometer-alt"></em>
				Inicio
				<span class="sr-only">(current)</span>
			</a>
		</li>
		<li class="parent nav-item">
			<a class="nav-link" data-toggle="collapse" href="#sub-item-1">
				<em class="fa fa-paper-plane">&nbsp;</em>
				Env&iacute;os
				<span data-toggle="collapse" class="icon float-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-1">	
				<li class="nav-item">
					<a class="nav-link" href="/envios/crear.jsp">
						Crear
						<em class="fa fa-plus">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/envios/modificar.jsp">
						Modificar
						<em class="fa fa-edit">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/envios/eliminar.jsp">
						Eliminar
						<em class="fa fa-trash">&nbsp;</em>
					</a>
				</li>
			</ul>
		</li>
		<li class="parent nav-item">
			<a class="nav-link" data-toggle="collapse" href="#sub-item-2">
				<em class="fa fa-users">&nbsp;</em>
				Usuarios
				<span data-toggle="collapse" class="icon float-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-2">
				<li class="nav-item">
					<a class="nav-link" href="/usuarios/crear.jsp">
						Crear
						<em class="fa fa-plus">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/usuarios/modificar.jsp">
						Modificar
						<em class="fa fa-edit">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/usuarios/eliminar.jsp">
						Eliminar
						<em class="fa fa-trash">&nbsp;</em>
					</a>
				</li>
			</ul>
		</li>
		<li class="parent nav-item">
			<a class="nav-link" data-toggle="collapse" href="#sub-item-3">
				<em class="fa fa-building">&nbsp;</em>
				Empresas
				<span data-toggle="collapse" class="icon float-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-3">
				<li class="nav-item">
					<a class="nav-link" href="/empresas/crear.jsp">
						Crear
						<em class="fa fa-plus">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/empresas/modificar.jsp">
						Modificar
						<em class="fa fa-edit">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/empresas/eliminar.jsp">
						Eliminar
						<em class="fa fa-trash">&nbsp;</em>
					</a>
				</li>
			</ul>
		</li>
		<li class="parent nav-item">
			<a class="nav-link" data-toggle="collapse" href="#sub-item-4">
				<em class="fa fa-truck">&nbsp;</em>
				Camiones
				<span data-toggle="collapse"  class="icon float-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-4">
				<li class="nav-item">
					<a class="nav-link" href="/camiones/crear.jsp">
						Crear
						<em class="fa fa-plus">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/camiones/modificar.jsp">
						Modificar
						<em class="fa fa-edit">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/camiones/eliminar.jsp">
						Eliminar
						<em class="fa fa-trash">&nbsp;</em>
					</a>
				</li>
			</ul>
		</li>
		<li class="parent nav-item">
			<a class="nav-link" data-toggle="collapse" href="#sub-item-5">
				<em class="fa fa-truck">&nbsp;</em>
				Traileres
				<span data-toggle="collapse"  class="icon float-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-5">
				<li class="nav-item">
					<a class="nav-link" href="/traileres/crear.jsp">
						Crear
						<em class="fa fa-plus">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/traileres/modificar.jsp">
						Modificar
						<em class="fa fa-edit">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/traileres/eliminar.jsp">
						Eliminar
						<em class="fa fa-trash">&nbsp;</em>
					</a>
				</li>
			</ul>
		</li>
		<li class="parent nav-item">
			<a class="nav-link" data-toggle="collapse" href="#sub-item-6">
				<em class="fa fa-list">&nbsp;</em>
				Consultar
				<span data-toggle="collapse"  class="icon float-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-6">
				<li class="nav-item">
					<a class="nav-link" href="/envios/listar.jsp">
						Env&iacute;os
						<em class="fa fa-paper-plane">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/usuarios/listar.jsp">
						Usuarios
						<em class="fa fa-users">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/empresas/listar.jsp">
						Empresas
						<em class="fa fa-building">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/camiones/listar.jsp">
						Camiones
						<em class="fa fa-truck">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/traileres/listar.jsp">
						Traileres
						<em class="fa fa-truck">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/envios/reportes.jsp">
						Reportes
						<em class="fa fa-newspaper">&nbsp;</em>
					</a>
				</li>
			</ul>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/chequeo/chequeoDeCarga.jsp">
				<em class="fa fa-check-square"></em>
				Chequeo Carga
			</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/chequeo/chequeoDeDescarga.jsp">
				<em class="fa fa-check-square"></em>
				Chequeo Descarga
			</a>
		</li>
		
		<li class="nav-item">
			<a class="nav-link" href="/chequeo/mapa.jsp">
				<em class="fa fa-globe"></em>
				Mapa
			</a>
		</li>
		
	</ul>
	<a href="/logout" class="nav-link"><em class="fa fa-power-off"></em> Cerrar Sesi&oacute;n</a>
</nav>
