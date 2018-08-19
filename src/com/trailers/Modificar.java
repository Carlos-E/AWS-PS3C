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

		response.setContentType("text/html");

		DB DB = new DB();

		Trailer trailer = new Trailer();

		trailer.setPatente(request.getParameter("patente").toLowerCase());

		trailer = DB.load(trailer);

		trailer.setTipo(request.getParameter("tipo").toLowerCase());
		trailer.setEmpresa(request.getParameter("empresa").toLowerCase());

		trailer.setEspacioMax(Double.valueOf(request.getParameter("espacioMax")));
		if (trailer.getEspacioMax() < DB.getEspacioTrailer(trailer.getPatente())) {
			Dibujar.mensaje(response.getWriter(),
					"El espacio no puede ser menor a la cantidad consumida por los envios asignados",
					request.getRequestURI() + ".jsp");
			return;
		}

		trailer.setPesoMax(Double.valueOf(request.getParameter("pesoMax")));
		if (trailer.getPesoMax() < DB.getPesoTrailer(trailer.getPatente())) {
			Dibujar.mensaje(response.getWriter(),
					"El peso no puede ser menor a la cantidad consumida por los envios asignados",
					request.getRequestURI() + ".jsp");
			return;
		}

		String camion = null;

		if (request.getParameter("remolque").equals("null")) {
			camion = request.getParameter("remolqueAsignado").toLowerCase();
		} else {
			camion = request.getParameter("remolque").toLowerCase();
		}

		trailer.setCamion(camion);

		DB.save(trailer);

		Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getRequestURI() + ".jsp");

	}

}
