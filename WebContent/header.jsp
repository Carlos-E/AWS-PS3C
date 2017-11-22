<div class="page-header">
	<h1>
		<%
			out.print(session.getAttribute("pagina").toString());
		%>
		<small>
			<%
				if (session.getAttribute("rol") == null) {
					//response.sendError(400, "Acceso incorrecto"); //cambiar
				} else {
					out.print(session.getAttribute("username").toString());
				}
			%>
		</small>
	</h1>
</div>