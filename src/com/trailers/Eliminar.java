package com.trailers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		DB DB = new DB();

		Trailer trailer = new Trailer();

		trailer.setPatente(request.getParameter("patente").toLowerCase());

		if (DB.getEnviosPendientesTrailer(trailer.getPatente()).size() != 0) {
			// Dibujar.mensaje(response.getWriter(), "Este trailer contiene
			// envíos sin entregar",
			// "/traileres/eliminar.jsp");
			response.setStatus(400);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Este trailer contiene envíos sin entregar");
				}
			}));
			return;
		}

		DB.delete(trailer);

		response.setStatus(201);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Trailer eliminado");
			}
		}));
		return;
	}

}
