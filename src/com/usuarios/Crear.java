package com.usuarios;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logica.ControladorBD;

import clases.Usuario;

@WebServlet("/usuarios/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Crear() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		Usuario usuario = new Usuario();

		usuario.setUsuario(request.getParameter("correo").toLowerCase());
		usuario.setClave(request.getParameter("clave1").toLowerCase());
		usuario.setNombre(request.getParameter("nombre").toLowerCase());
		usuario.setApellido(request.getParameter("apellido").toLowerCase());
		usuario.setTelefono(request.getParameter("telefono").toLowerCase());
		usuario.setCorreo(request.getParameter("correo").toLowerCase());
		usuario.setDireccion(request.getParameter("direccion").toLowerCase());
		usuario.setRol(request.getParameter("rol").toLowerCase());

		ControladorBD.registrarItem("usuarios", usuario);

		response.setStatus(200);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Usuario creado");
			}
		}));
		return;

	}

}
