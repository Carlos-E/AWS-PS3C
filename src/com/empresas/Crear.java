package com.empresas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Empresa;

@WebServlet("/empresas/crear")
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
		
		response.setContentType("text/html");
		
		Empresa empresa = new clases.Empresa();

		empresa.setNit(request.getParameter("nit").toLowerCase());
		empresa.setRut(request.getParameter("rut").toLowerCase());
		empresa.setNombre(request.getParameter("nombre").toLowerCase());
		empresa.setDireccion(request.getParameter("direccion").toLowerCase());
		empresa.setTelefono(request.getParameter("telefono").toLowerCase());
		empresa.setCorreo(request.getParameter("correo").toLowerCase());
		
		new DB().save(empresa);

		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa","/empresas/crear.jsp");

	}

}
