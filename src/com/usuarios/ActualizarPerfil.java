package com.usuarios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.logica.ControladorBD;

@WebServlet("/usuarios/perfil")
public class ActualizarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActualizarPerfil() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username") == null){
			response.sendRedirect("/error.jsp");
		}
		
		response.setContentType("application/json");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		response.getWriter().print(ow.writeValueAsString(ControladorBD.getItem("usuarios", "usuario", request.getSession().getAttribute("username").toString())));
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
