package com.controler;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clases.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logica.ControladorBD;

@WebServlet("/login")
public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");

		if (ControladorBD.validarLogin(uname, pass)) {

			HttpSession session = request.getSession();
			// Vida de la sesion
			session.setMaxInactiveInterval(60 * 60 * 4);

			session.setAttribute("username", uname);
			
			Usuario usuario = new Usuario();
			
			usuario.setUsuario(uname);

			usuario = new DB().load(usuario);

			switch (usuario.getRol()) {
			case "null":
				response.sendError(400, "Sin roll");
				break;
			case "cliente":
				session.setAttribute("rol", "cliente");
				response.setStatus(200);
				response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("sendRedirect", "/index.jsp");
					}
				}));				
				break;
			case "empleado":
				session.setAttribute("rol", "empleado");
				response.setStatus(200);
				response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("sendRedirect", "/index.jsp");
					}
				}));				
				break;
			case "admin":
				session.setAttribute("rol", "admin");
				response.setStatus(200);
				response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("sendRedirect", "/index.jsp");
					}
				}));
				break;
			case "conductor":
				session.setAttribute("rol", "conductor");

				String placa = new DB().checkPlaca(uname);

				if (placa != null) {
					session.setAttribute("placa", placa);
					response.sendRedirect("/movil/index.jsp");
				} else {
					
					response.setStatus(200);
					response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
						private static final long serialVersionUID = 1L;
						{
							put("title", "Operaci&oacute;n fallida");
							put("message", "El conductor no tiene ningun vehículo asignado");
						}
					}));
					return;
				}

				break;
			}

		} else {
			response.setStatus(401);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Usuario o contraseña incorrectos");
				}
			}));
			return;
		}
	}
}
