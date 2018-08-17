package com.trailers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.ControladorBD;

import clases.*;

@WebServlet("/trailers/crear")
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

		Vehiculo vehiculo = (Vehiculo) ControladorBD.getItem("vehiculos", "placa",
				request.getParameter("remolque").toLowerCase());

		Trailer trailer = new clases.Trailer();

		trailer.setPatente(request.getParameter("patente").toLowerCase());
		trailer.setPesoMax(Double.valueOf(request.getParameter("peso").toLowerCase()));
		trailer.setTipo(request.getParameter("tipo").toLowerCase());
		trailer.setEspacioMax(Double.valueOf(request.getParameter("espacio").toLowerCase()));
		trailer.setEstado("disponible");
		trailer.setEmpresa(request.getParameter("empresa").toLowerCase());
		
		if (request.getParameter("remolque").equals("ninguno")) {
			trailer.setCamion("ninguno");
		} else {
			trailer.setCamion(vehiculo.getPlaca());
		}

		ControladorBD.registrarItem("trailers", trailer);

		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
				request.getContextPath() + "/traileres/crear.jsp");

	}
}
