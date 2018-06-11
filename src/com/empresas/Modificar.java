package com.empresas;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

/**
 * Servlet implementation class modificarEmpresa
 */
@WebServlet("/modificarEmpresa")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Empresa empresa = new clases.Empresa();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Modificar() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);
		HttpSession session = request.getSession();
		String nit = request.getParameter("nit").toString();
		empresa = (clases.Empresa) ControladorBD.getItem("empresas", "nit", nit);
		String nombre = request.getParameter("nombre").toLowerCase();
		String telefono = request.getParameter("telefono").toLowerCase();
		String direccion = request.getParameter("direccion").toLowerCase();
		String correo = request.getParameter("correo").toLowerCase();
		boolean cambio = false;
		if (!empresa.getNombre().equals(nombre)) {
			empresa.setNombre(nombre);
			ControladorBD.actualizarValor("empresas", "nit", nit, "nombre", nombre);
			cambio = true;
		}
		if (!empresa.getTelefono().equals(telefono)) {
			empresa.setNombre(telefono);
			ControladorBD.actualizarValor("empresas", "nit", nit, "telefono", telefono);
			cambio = true;
		}
		if (!empresa.getDireccion().equals(direccion)) {
			empresa.setNombre(direccion);
			ControladorBD.actualizarValor("empresas", "nit", nit, "direccion", direccion);
			cambio = true;
		}
		if (!empresa.getCorreo().equals(correo)) {
			empresa.setNombre(correo);
			ControladorBD.actualizarValor("empresas", "nit", nit, "correo", correo);
			cambio = true;
		}
		if (cambio) {
			session.setAttribute("busca", "ninguno");
			System.out.println("algo se cambio");
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getSession().getAttribute("origin").toString());
		} else {
			System.out.println("no se cambio nada");
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio", request.getSession().getAttribute("origin").toString());

		}
	}
}
