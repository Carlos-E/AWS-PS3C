package com.modificar;

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
public class modificarEmpresa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.empresa empresa = new clases.empresa();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public modificarEmpresa() {
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
		String nit = session.getAttribute("obj").toString();
		empresa = (clases.empresa) ControladorBD.getItem("empresas", "empresa", nit);
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
			PrintWriter out = response.getWriter();
			String nextURL = request.getContextPath() + "/modificar/empresa.jsp";
			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getSession().getAttribute("origin").toString());
		} else {
			System.out.println("no se cambio nada");
			com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio", request.getSession().getAttribute("origin").toString());

		}
	}
}
