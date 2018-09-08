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
		
		DB DB = new DB();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		Empresa empresa = new Empresa();

		empresa.setNit(request.getParameter("nit").toLowerCase());
		
		empresa = DB.load(empresa);
		
		if(empresa!=null){
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "El empresa ya existe");
				}
			}));
			return;
		}else{
			empresa = new Empresa();
		}
		
		empresa.setNit(request.getParameter("nit").toLowerCase());
		empresa.setRut(request.getParameter("rut").toLowerCase());
		empresa.setNombre(request.getParameter("nombre").toLowerCase());
		empresa.setDireccion(request.getParameter("direccion").toLowerCase());
		empresa.setTelefono(request.getParameter("telefono").toLowerCase());
		empresa.setCorreo(request.getParameter("correo").toLowerCase());
		
		DB.save(empresa);

		response.setStatus(201);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Empresa creada");
			}
		}));
		return;
	}

}
