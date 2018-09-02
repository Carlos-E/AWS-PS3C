package com.trailers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logica.Dibujar;

import clases.*;

@WebServlet("/trailers/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Crear() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		Trailer trailer = new Trailer();

		trailer.setPatente(request.getParameter("patente").toLowerCase());
		trailer.setTipo(request.getParameter("tipo").toLowerCase());

		trailer.setPesoMax(Double.valueOf(request.getParameter("peso").toLowerCase()));
		trailer.setEspacioMax(Double.valueOf(request.getParameter("espacio").toLowerCase()));

		trailer.setEmpresa(request.getParameter("empresa").toLowerCase());

		if (request.getParameter("remolque").equals("ninguno")) {
			trailer.setCamion("ninguno");
		} else {
			trailer.setCamion(request.getParameter("remolque"));
		}

		new DB().save(trailer);

		// Response
		response.setStatus(200);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacuten exitosa");
				put("message", "Trailer creado");
			}
		}));
		return;
	}
}
