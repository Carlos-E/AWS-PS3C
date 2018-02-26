<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%
	if (session.getAttribute("rol") == null) {
		response.sendError(400, "Acceso incorrecto"); //cambiar
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
		<a href="/index.jsp">
			<em class="fa fa-truck"></em>
			PS3C
		</a>

		<a class="ml-5" href="/logout">
			<em class="fa fa-power-off mr-1"></em>
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
				<span data-toggle="collapse" href="" class="icon pull-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-1">
				<li class="nav-item">
					<a class="nav-link" href="envios/listar.jsp">
						Listar
						<em class="fa fa-list-alt">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="envios/crear.jsp">
						Crear
						<em class="fa fa-plus-square">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="envios/modificar.jsp">
						Modificar
						<em class="fa fa-edit">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="envios/eliminar.jsp">
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
				<span data-toggle="collapse" href="" class="icon pull-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-2">
				<li class="nav-item">
					<a class="nav-link" href="/usuarios/listar.jsp">
						Listar
						<em class="fa fa-list-alt">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/usuarios/crear.jsp">
						Crear
						<em class="fa fa-plus-square">&nbsp;</em>
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
				<span data-toggle="collapse" href="" class="icon pull-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-3">
				<li class="nav-item">
					<a class="nav-link" href="/empresas/listar.jsp">
						Listar
						<em class="fa fa-list-alt">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/empresas/crear.jsp">
						Crear
						<em class="fa fa-plus-square">&nbsp;</em>
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
				<span data-toggle="collapse" href="" class="icon pull-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-4">
				<li class="nav-item">
					<a class="nav-link" href="/camiones/listar.jsp">
						Listar
						<em class="fa fa-list-alt">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/camiones/crear.jsp">
						Crear
						<em class="fa fa-plus-square">&nbsp;</em>
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
				<span data-toggle="collapse" href="" class="icon pull-right">
					<i class="fa fa-caret-down"></i>
				</span>
			</a>
			<ul class="children collapse" id="sub-item-5">
				<li class="nav-item">
					<a class="nav-link" href="/traileres/listar.jsp">
						Listar
						<em class="fa fa-list-alt">&nbsp;</em>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/traileres/crear.jsp">
						Crear
						<em class="fa fa-plus-square">&nbsp;</em>
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
				<em class="fa fa-check-circle">&nbsp;</em>
				Chequeo
				<span data-toggle="collapse" href="" class="icon pull-right">
					<i class="fa fa-caret-down"></i>
				</span>

			</a>
			<ul class="children collapse" id="sub-item-6">
				<li class="nav-item">
					<a class="nav-link" href="/asignar/carga.jsp"> Carga </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/asignar/descarga.jsp"> Descarga </a>
				</li>
			</ul>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/chequeo/mapeoDeMercancia.jsp">
				<em class="fa fa-globe"></em>
				Mapa
			</a>
		</li>
	</ul>

</nav>
