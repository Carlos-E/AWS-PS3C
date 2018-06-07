package com.usuarios;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Usuario;

/**
 * Servlet implementation class empleado
 */
@WebServlet("/usuarios/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Usuario usuario = new Usuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Crear() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.sendRedirect("/error.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getRequestURL() + ".jsp");
		// response.sendRedirect("index.jsp");

	}

}
