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
<div class="wow fadeIn" id="rt-top">
	<div class=rt-container>
		<div class="rt-grid-6 rt-alpha">
			<div class="rt-block logo-block">
				<br>
				<a style="font-size: x-large;">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</a>
			</div>
		</div>
		<div class="rt-block ">
			<div class=module-surround>
				<div class=module-content>
					<div class=custom>
						<ul class=top-address-bar>
							<!-- <li class=jd-phone>
								<i class="fa fa-mobile"></i>
								<%
									if (session.getAttribute("rol") == null) {
										//response.sendError(400, "Acceso incorrecto"); //cambiar
									} else {
										out.print(usuario.getTelefono().toString());
									}
								%>
							</li>
							<li class=jd-emailid>
								<i class="fa fa-envelope"></i>
								<%
									if (session.getAttribute("rol") == null) {
										//response.sendError(400, "Acceso incorrecto"); //cambiar
									} else {
										out.print(usuario.getCorreo().toString());
									}
								%>
							</li>  -->
							<li class=jd-address>
								<i class="fa fa-user" aria-hidden="true"></i>
								<p>
									<%
										if (session.getAttribute("rol") == null) {
											//response.sendError(400, "Acceso incorrecto"); //cambiar
										} else {
											out.print(session.getAttribute("username").toString());
										}
									%>
								</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class=clear></div>
	</div>
</div>
<div class="wow fadeIn" id=rt-header>
	<div class=rt-container>
		<div class="rt-grid-12 rt-alpha">
			<div class="rt-block menu-block">
				<div class="gf-menu-device-container responsive-type-panel"></div>
				<ul class="gf-menu l1 ">
					<li class="item435 active last">
						<a class=item href="/index.jsp"> Home </a>
					</li>
					<li class="item498 parent">
						<a class=item href=javascript:void(0);>
							Gesti&oacuten de Env&iacuteos
							<span class=border-fixer></span>
						</a>
						<div class="dropdown columns-1 " style="width: 180px;">
							<div class="column col1" style="width: 180px;">
								<ul class=l2>
									<li class=item648>
										<a class=item href="/envios/realizarNuevoEnvio.jsp"> Agregar </a>
									</li>
									<li class=item649>
										<a class=item href="/envios/estadoDelEnvio.jsp"> Contultar estado </a>
									</li>
									<li class=item650>
										<a class=item href="/modificarDatos/mercancia.jsp"> Modificar </a>
									</li>
									<li class=item650>
										<a class=item href="/asignar/mercancia-camion.jsp"> Asignar Camion</a>
									</li>
									<li class=item650>
										<a class=item href="/asignar/mercancia-trailer.jsp"> Asignar Trailer</a>
									</li>
								</ul>
							</div>
						</div>
					</li>
					<li class="item496 parent full-width-menu">
						<a class=item href=javascript:void(0);>
							Gesti&oacuten de Datos
							<span class=border-fixer></span>
						</a>
						<div class="dropdown columns-4 " style="width: 900px;">
							<div class="column col1" style="width: 200px;">
								<ul class=l2>
									<li class="item657 parent grouped">
										<a class="item icon" href=javascript:void(0);>
											<i>Usuarios</i>
											<span class=border-fixer></span>
										</a>
										<ol class="">
											<li class=item520>
												<a class=item href="/agregar/empleado.jsp"> Agregar </a>
											</li>
											<li class=item521>
												<a class=item href="/modificarDatos/usuario.jsp"> Modificar </a>
											</li>
										</ol>
									</li>
								</ul>
							</div>
							<div class="column col2" style="width: 200px;">
								<ul class=l2>
									<li class="item658 parent grouped">
										<a class="item icon" href=javascript:void(0);>
											<i>Empresa</i>
											<span class=border-fixer></span>
										</a>
										<ol>
											<li class=item520>
												<a class=item href="/agregar/empresa.jsp"> Agregar </a>
											</li>
											<li class=item521>
												<a class=item href="/modificarDatos/empresa.jsp"> Modificar </a>
											</li>
										</ol>
									</li>
								</ul>
							</div>
							<div class="column col2" style="width: 200px;">
								<ul class=l2>
									<li class="item658 parent grouped">
										<a class="item icon" href=javascript:void(0);>
											<i>Camiones</i>
											<span class=border-fixer></span>
										</a>
										<ol class="">
											<li class=item520>
												<a class=item href="/agregar/camion.jsp"> Agregar </a>
											</li>
											<li class=item521>
												<a class=item href="/modificarDatos/camion.jsp"> Modificar </a>
											</li>
											<li class=item521>
												<a class=item href="/asignar/destino-camion.jsp"> Asignar Ruta </a>
											</li>

										</ol>
									</li>
								</ul>
							</div>
							<div class="column col2" style="width: 200px;">
								<ul class=l2>
									<li class="item658 parent grouped">
										<a class="item icon" href=javascript:void(0);>
											<i>Traileres</i>
											<span class=border-fixer></span>
										</a>
										<ol class="">
											<li class=item520>
												<a class=item href="/agregar/trailer.jsp"> Agregar </a>
											</li>
											<li class=item521>
												<a class=item href="/modificarDatos/trailer.jsp"> Modificar </a>
											</li>
											<li class=item521>
												<a class=item href="/asignar/trailer-camion.jsp"> Asignar Camion</a>
											</li>
										</ol>
									</li>
								</ul>
							</div>
							<div class="column col4" style="width: 280px;"></div>
						</div>
					</li>
					<li class="item518 parent">
						<a class=item href=javascript:void(0);>
							Gesti&oacuten de Mercanc&iacutea
							<span class=border-fixer></span>
						</a>
						<div class="dropdown columns-1 " style="width: 200px;">
							<div class="column col1" style="width: 200px;">
								<ul class=l2>
									<li class=item520>
										<a class=item href="/chequeo/chequearCarga.jsp"> Chequear Carga </a>
									</li>
									<li class=item521>
										<a class=item href="/chequeo/chequearDescarga.jsp"> Chequear Descarga </a>
									</li>
									<li class=item521>
										<a class=item href="/chequeo/reportarProblema.jsp"> Reportar un Problema</a>
									</li>
								</ul>
							</div>
						</div>
					</li>
					<li class="item518 parent">
						<a class=item href=javascript:void(0);>
							Gesti&oacuten de Recursos
							<span class=border-fixer></span>
						</a>
						<div class="dropdown columns-1 " style="width: 250px;">
							<div class="column col1" style="width: 250px;">
								<ul class=l2>
									<li class=item520>
										<a class=item href="/disponibilidad/camiones.jsp"> Disponibilidad de Camiones </a>
									</li>
									<li class=item521>
										<a class=item href="/disponibilidad/trailers.jsp"> Disponibilidad de Traileres </a>
									</li>
									<li class=item521>
										<a class=item href="/envios/ultimosReportes.jsp"> Lista de Reportes</a>
									</li>
									<li class=item521>
										<a class=item href="/chequeo/mapeoDeMercancia.jsp"> Mapa</a>
									</li>
								</ul>
							</div>
						</div>
					</li>
					<li class="item435 active last">
						<a class=item href="/logout">
							Cerrar Sesi&oacuten
							<span class=border-fixer></span>
						</a>
					</li>
				</ul>
				<div class=clear></div>
			</div>
		</div>
		<div class=clear></div>
	</div>
</div>