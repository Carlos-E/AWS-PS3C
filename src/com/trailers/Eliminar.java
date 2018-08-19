package com.trailers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.Dibujar;

import clases.DB;
import clases.Trailer;

@WebServlet("/trailer/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Eliminar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		response.setContentType("text/html");

		Trailer trailer = new Trailer();

		trailer.setPatente(request.getParameter("patente").toLowerCase());

		if (DB.getEnviosPendientesTrailer(trailer.getPatente()).size() != 0) {
			Dibujar.mensaje(response.getWriter(), "Este trailer contiene envíos sin entregar",
					"/traileres/eliminar.jsp");
			return;
		}

		DB.delete(trailer);

		Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", "/traileres/eliminar.jsp");

	}

}
