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

@WebServlet("/traileres/modificar")
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		DB DB = new DB();

		Trailer trailer = new Trailer();

		trailer.setPatente(request.getParameter("patente").toLowerCase());

		trailer = DB.load(trailer);

		trailer.setTipo(request.getParameter("tipo").toLowerCase());
		trailer.setEmpresa(request.getParameter("empresa").toLowerCase());

		trailer.setEspacioMax(Double.valueOf(request.getParameter("espacioMax")));
		if (trailer.getEspacioMax() < DB.getEspacioTrailer(trailer.getPatente())) {
			response.setStatus(200);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n exitosa");
					put("message", "El espacio no puede ser menor a la cantidad consumida por los envios asignados");
				}
			}));
			return;
		}

		trailer.setPesoMax(Double.valueOf(request.getParameter("pesoMax")));
		if (trailer.getPesoMax() < DB.getPesoTrailer(trailer.getPatente())) {
			response.setStatus(200);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n exitosa");
					put("message", "El peso no puede ser menor a la cantidad consumida por los envios asignados");
				}
			}));
			return;		}

		String camion = null;

		if (request.getParameter("remolque").equals("null")) {
			camion = request.getParameter("remolqueAsignado").toLowerCase();
		} else {
			camion = request.getParameter("remolque").toLowerCase();
		}

		trailer.setCamion(camion);

		DB.save(trailer);

		response.setStatus(201);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Trailer actualizado");
			}
		}));
		return;
	}

}
