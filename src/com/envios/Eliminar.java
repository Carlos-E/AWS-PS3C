package com.envios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Email;
import clases.Envio;
import clases.Usuario;

@WebServlet("/envios/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Eliminar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		DB DB = new DB();

		Envio envio = new Envio();

		envio.setUsuario(request.getParameter("cliente").toLowerCase());
		envio.setFecha(request.getParameter("fecha").toLowerCase());

		envio = DB.load(envio);

		DB.delete(envio);

		new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío Eliminado",
				"Su envío ha sido elimindo del sistema PS3C.", envio);

		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getRequestURL() + ".jsp");
	}
}
