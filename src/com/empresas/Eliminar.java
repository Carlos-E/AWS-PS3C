package com.empresas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Empresa;


@WebServlet("/empresas/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();
       
    public Eliminar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		Empresa empresa = new Empresa();
		empresa.setNit(request.getParameter("nit").toLowerCase());
		
		new DB().delete(empresa);
		
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/empresas/eliminar.jsp");
	}

}
