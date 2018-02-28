package com.agregar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.usuario;

/**
 * Servlet implementation class empleado
 */
@WebServlet("/empleado")
public class agregarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	usuario usuario = new usuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregarEmpleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.sendError(400, "Acceso incorrecto");

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
		usuario.setApellido(request.getParameter("apellidos").toLowerCase());
		usuario.setTelefono(request.getParameter("telefono").toLowerCase());
		usuario.setCorreo(request.getParameter("correo").toLowerCase());
		usuario.setDireccion(request.getParameter("direccion").toLowerCase());
		usuario.setRol(request.getParameter("rol").toLowerCase());
		ControladorBD.registrarItem("usuarios", usuario);
		// cambiar ese response por un mensaje de exito
		// response.sendRedirect("index.jsp");
		PrintWriter out = response.getWriter();
		String nextURL = request.getContextPath() + "/agregar/empleado.jsp";
		com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
	}

}
