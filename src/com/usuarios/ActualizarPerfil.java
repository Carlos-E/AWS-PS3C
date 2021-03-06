package com.usuarios;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Usuario;

@WebServlet("/usuarios/leer")
public class ActualizarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActualizarPerfil() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {

			if (request.getSession().getAttribute("username") == null) {
				response.sendRedirect("/error.jsp");
			}

			Usuario usuario = new DB().load(Usuario.class,request.getSession().getAttribute("username").toString());
			usuario.setClave(null);			
			
			response.getWriter().print(new ObjectMapper().writer().writeValueAsString(usuario));
			response.getWriter().close();

		} catch (Exception e) {

			response.setStatus(200);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Ocurrio un error al intentar actualizar el usuario");
				}
			}));
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
