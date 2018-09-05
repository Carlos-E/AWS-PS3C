<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("rol") == null) {
		response.sendRedirect("/movil/login.jsp");
	}
	session.setAttribute("origin", request.getRequestURI());
%>
<header class="page-header row justify-center">
	<div class="col-md-12 col-lg-6 titulo"></div>
	<div class="dropdown user-dropdown col-md-12 col-lg-6 text-center text-md-right">
		<a class="btn btn-stripped dropdown-toggle" href="/index.html#" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<img src="/img/user.svg" alt="profile photo" class="circle float-left profile-photo" width="50" height="auto">
			<div class="username mt-1">
				<h6 class="mb-1" id="username">
					<%
						out.print(session.getAttribute("username").toString());
					%>
				</h6>
				<h7 id="role">
					<%
						out.print(session.getAttribute("rol").toString());
					%>
				</h7>
			</div>
		</a>
		<div class="dropdown-menu dropdown-menu-right" style="margin-right: 1.5rem;" aria-labelledby="dropdownMenuLink">
			<a class="dropdown-item" href="/movil/transportador/perfil.jsp">
				<em class="fa fa-user-circle mr-1"></em>
				Actualizar perfil
			</a>

			<a class="dropdown-item" href="/logout">
				<em class="fa fa-power-off mr-1"></em>
				Cerrar sesi&oacute;n
			</a>
		</div>
	</div>
	<div class="clear"></div>
</header>