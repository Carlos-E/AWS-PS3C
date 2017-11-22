<div>
	<h2 style="color:rgb(157, 157, 157);">
		<%
			out.print(session.getAttribute("pagina").toString());
		%>
		<!--  <small>
			<%
				if (session.getAttribute("rol") == null) {
					//response.sendError(400, "Acceso incorrecto"); //cambiar
				} else {
					out.print(session.getAttribute("username").toString());
				}
			%>
		</small>
		 -->
	</h2>
</div>