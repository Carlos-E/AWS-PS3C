package com.empresas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

@WebServlet("/empresas/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Empresa empresa = new clases.Empresa();

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
		empresa.setNit(request.getParameter("nit").toLowerCase());
		empresa.setRut(request.getParameter("rut").toLowerCase());
		empresa.setNombre(request.getParameter("nombre").toLowerCase());
		empresa.setDireccion(request.getParameter("direccion").toLowerCase());
		empresa.setTelefono(request.getParameter("telefono").toLowerCase());
		empresa.setCorreo(request.getParameter("correo").toLowerCase());
		
		ControladorBD.registrarItem("empresas", empresa);

		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
				request.getContextPath() + "/empresas/crear.jsp");
}catch(Exception e){
	com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar crear la Empresa", request.getContextPath() + "./index.jsp");
}
	}

}
