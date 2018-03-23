package com.agregar;

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
 * Servlet implementation class empresa
 */
@WebServlet("/empresa")
public class agregarEmpresa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Empresa empresa = new clases.Empresa();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregarEmpresa() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.sendError(400, "Acceso incorrecto");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		empresa.setNit(request.getParameter("nit").toLowerCase());
		empresa.setRut(request.getParameter("rut").toLowerCase());
		empresa.setNombre(request.getParameter("nombre").toLowerCase());
		empresa.setDireccion(request.getParameter("direccion").toLowerCase());
		empresa.setTelefono(request.getParameter("telefono").toLowerCase());
		empresa.setCorreo(request.getParameter("correo").toLowerCase());
		ControladorBD.registrarItem("empresas", empresa);

		// cambiar ese response por un mensaje de exito
		// response.sendRedirect("index.jsp");
		PrintWriter out = response.getWriter();
		String nextURL = request.getContextPath() + "/agregar/empresa.jsp";
		com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
	}

}
