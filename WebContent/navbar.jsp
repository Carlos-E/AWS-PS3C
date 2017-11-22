<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">

		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-animations">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/index.jsp">
				<span class="glyphicon glyphicon-home"></span>
				Inicio
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-animations" data-hover="dropdown" data-animations="pulse fadeInUp pulse fadeInUp">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Env&iacuteos
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdownhover-bottom" role="menu" style="">
						<li>
							<a href="#">Listar</a>
						</li>
						<li>
							<a href="#">Crear</a>
						</li>
						<li class="dropdown">
							<a href="#">
								Actualizar
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Modificar</a>
								</li>
								<li>
									<a href="#">Remplazar</a>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">Borrar</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Usuarios
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdownhover-bottom" role="menu" style="">
						<li>
							<a href="#">Listar</a>
						</li>
						<li>
							<a href="/agregar/empleado.jsp">Crear</a>
						</li>
						<li class="dropdown">
							<a href="#">
								Actualizar
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Modificar</a>
								</li>
								<li>
									<a href="#">Remplazar</a>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">Borrar</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Camiones
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdownhover-bottom" role="menu" style="">
						<li>
							<a href="#">Listar</a>
						</li>
						<li>
							<a href="/agregar/camion.jsp">Crear</a>
						</li>
						<li class="dropdown">
							<a href="#">
								Actualizar
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Modificar</a>
								</li>
								<li>
									<a href="#">Asignar Ruta</a>
								</li>
								<li>
									<a href="#">Remplazar</a>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">Borrar</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Traileres
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdownhover-bottom" role="menu" style="">
						<li>
							<a href="#">Listar</a>
						</li>
						<li>
							<a href="#">Crear</a>
						</li>
						<li class="dropdown">
							<a href="#">
								Actualizar
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Modificar</a>
								</li>
								<li>
									<a href="#">Asignar Camion</a>
								</li>
								<li>
									<a href="#">Remplazar</a>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">Borrar</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Empresas
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdownhover-bottom" role="menu" style="">
						<li>
							<a href="#">Listar</a>
						</li>
						<li>
							<a href="#">Crear</a>
						</li>
						<li class="dropdown">
							<a href="#">
								Actualizar
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Modificar</a>
								</li>
								<li>
									<a href="#">Remplazar</a>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">Borrar</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Gestion
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdownhover-bottom" role="menu" style="">
						<li class="dropdown">
							<a href="#">
								Reportes
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Generar</a>
								</li>
								<li>
									<a href="#">Listar</a>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li class="dropdown">
							<a href="#">
								Chequeo de mercancia
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Chequear Carga</a>
								</li>
								<li>
									<a href="#">Chequear Descarga</a>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li class="dropdown">
							<a href="#">
								Asignaciones
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdownhover-right">
								<li>
									<a href="#">Asignar Camion</a>
								</li>
								<li>
									<a href="#">Asignar Trailer</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>

				<!-- 
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Dropdown 2
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdownhover-top" role="menu" style="bottom: 100%; top: auto;">
						<li>
							<a href="#">Action</a>
						</li>
						<li>
							<a href="#">Another action</a>
						</li>
						<li>
							<a href="#">Something else here</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">Separated link</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">One more separated link</a>
						</li>
					</ul>
				</li>
				 -->

				<!-- MAPA -->
				<li class="dropdown">
					<a href="/chequeo/mapeoDeMercancia.jsp" class="dropdown-toggle">
						<span class="glyphicon glyphicon-globe"></span>
					</a>
				</li>

			</ul>
			<form class="navbar-form navbar-right">
				<button type="submit" class="btn btn-danger">
					<span class="glyphicon glyphicon-log-out"></span>
					  Cerrar Sesi&oacuten
				</button>

			</form>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>