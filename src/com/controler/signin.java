package com.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.*;

/**
 * Servlet implementation class signin
 */
@WebServlet("/signin")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		clases.Usuario persona = new clases.Usuario();
		
		persona.setUsuario(request.getParameter("usuario").toLowerCase());
		persona.setClave(request.getParameter("clave1").toLowerCase());
		persona.setNombre(request.getParameter("nombres").toLowerCase());
		persona.setApellido(request.getParameter("apellidos").toLowerCase());
		persona.setTelefono(request.getParameter("telefono").toLowerCase());
		persona.setCorreo(request.getParameter("correo").toLowerCase());
		persona.setDireccion(request.getParameter("direccion").toLowerCase());
		persona.setRol("cliente");
		ControladorBD.registrarItem("usuarios",persona);
		response.sendRedirect("login.jsp");

		 
	}

}
