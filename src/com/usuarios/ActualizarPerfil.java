package com.usuarios;

import java.io.IOException;
import java.util.HashMap;

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

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {
			if (request.getSession().getAttribute("username") == null) {
				response.sendRedirect("/error.jsp");
			}

			response.setContentType("application/json");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			response.getWriter().print(ow.writeValueAsString(ControladorBD.getItem("usuarios", "usuario",
					request.getSession().getAttribute("username").toString())));
			response.getWriter().close();
		} catch (Exception e) {
			// com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un
			// error al intentar actualizar un Usuario",
			//		request.getContextPath() + "./index.jsp");
			response.setStatus(500);
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
