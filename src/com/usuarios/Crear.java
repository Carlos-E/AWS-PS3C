package com.usuarios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try {

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

			response.setContentType("text/html");
			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", "/usuarios/crear.jsp");
		} catch (Exception e) {
			System.out.println(e);
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar crear el Usuario",
					"/usuarios/crear.jsp");
		}

	}

}
