package com.empresas;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Empresa;

@WebServlet("/modificarEmpresa")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		DB DB = new DB();

		Empresa empresa = new Empresa();

		empresa.setNit(request.getParameter("nit"));

		empresa = DB.load(empresa);

		empresa.setRut(request.getParameter("rut").toLowerCase());
		empresa.setNombre(request.getParameter("nombre").toLowerCase());
		empresa.setTelefono(request.getParameter("telefono").toLowerCase());
		empresa.setDireccion(request.getParameter("nombre").toLowerCase());
		empresa.setCorreo(request.getParameter("nombre").toLowerCase());

		DB.save(empresa);

		response.setStatus(200);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Empresa actualizada");
			}
		}));
		return;
	}
}
