package com.empresas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Empresa;

@WebServlet("/modificarEmpresa")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Empresa empresa = new clases.Empresa();

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		DB DB = new DB();

		Empresa empresa = new Empresa();

		empresa.setNit(request.getParameter("nit"));

		empresa = DB.load(empresa);

		empresa.setNombre(request.getParameter("nombre").toLowerCase());
		empresa.setTelefono(request.getParameter("telefono").toLowerCase());
		empresa.setDireccion(request.getParameter("nombre").toLowerCase());
		empresa.setCorreo(request.getParameter("nombre").toLowerCase());

		DB.save(empresa);

		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", "/empresas/modificar.jsp");
	}
}
