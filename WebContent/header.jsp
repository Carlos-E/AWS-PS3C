<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/login.jsp");
	}
	session.setAttribute("origin", request.getRequestURI());
%>
<header class="page-header row justify-center">
	<div class="col-md-12 col-lg-8 titulo">
		<h1 class="float-left text-center text-md-left">
			<%
				out.print(session.getAttribute("pagina").toString());
			%>&nbsp;
			<a href="/envios/reportes.jsp" style="color: black;">
				<span class="fa-layers fa-fw">
					<i class="fas fa-envelope"></i>
					<span class="fa-layers-counter" style="background: Tomato" id="numMessages">0</span>
				</span>
			</a>
		</h1>

	</div>
	<div class="dropdown user-dropdown col-md-10 col-lg-4 text-center text-md-right">
		<a class="btn btn-stripped dropdown-toggle" href="/index.html#" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<img src="/img/user.svg" alt="profile photo" class="circle float-left profile-photo" width="50" height="auto">
			<div class="username mt-1">
				<h4 class="mb-1" id="username">
					<%
						out.print(session.getAttribute("username").toString());
					%>
				</h4>
				<h6 id="role">
					<%
						out.print(session.getAttribute("rol").toString());
					%>
				</h6>
			</div>
		</a>
		<div class="dropdown-menu dropdown-menu-right" style="margin-right: 1.5rem;" aria-labelledby="dropdownMenuLink">
			<a class="dropdown-item" href="/usuarios/perfil.jsp">
				<em class="fa fa-user-circle mr-1"></em>
				Actualizar Perfil
			</a>

			<a class="dropdown-item" href="/logout">
				<em class="fa fa-power-off mr-1"></em>
				Cerrar Sesi&oacute;n
			</a>
		</div>
	</div>
	<div class="clear"></div>

</header>