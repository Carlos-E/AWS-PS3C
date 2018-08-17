package com.envios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Envio;
import clases.Trailer;
import clases.Vehiculo;

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

		envio.setUsuario(request.getParameter("usuario").toLowerCase());
		envio.setFecha(request.getParameter("fecha").toLowerCase());

		envio = DB.load(envio);

		DB.delete(envio);

		if (envio.getTrailer().equals("ninguno") && !envio.getCamion().equals("ninguno")) {

			if (DB.getEnviosVehiculo(envio.getCamion()) == null) {
				System.out.println(
						"El camion " + envio.getCamion() + " no tiene mas envios, cambiando estado a \"no asignado\"");

				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setPlaca(envio.getCamion());
				vehiculo = DB.load(vehiculo);
				vehiculo.setEstado("no asignado");
				DB.save(vehiculo);
			}

		} else if (!envio.getTrailer().equals("ninguno")) {
			
			if (DB.getEnviosTrailer(envio.getTrailer()) == null) {
				System.out.println(
						"El trailer " + envio.getTrailer() + " no tiene mas envios, cambiando estado a \"no asignado\"");

				Trailer trailer = new Trailer();
				trailer.setPatente(envio.getTrailer());
				trailer = DB.load(trailer);
				trailer.setEstado("no asignado");
				DB.save(trailer);
			}

		}

		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getRequestURL() + ".jsp");
	}
}
