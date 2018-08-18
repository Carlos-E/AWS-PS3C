package com.envios;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Envio;
import clases.Trailer;
import clases.Vehiculo;

@WebServlet("/envios/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		DB DB = new DB();

		// Buscar el objeto en la base de datos e instanciarlo
		Envio envio = new DB().load(Envio.class, request.getParameter("cliente"), request.getParameter("fecha"));
		// Si no encontro nada, soltar mensaje de error y recargar pagina
		if (envio == null) {
			com.logica.Dibujar.mensaje(response.getWriter(), "Envio no encontrado", "/envios/modificar.jsp");
			return;
		}
		envio.setOrigen(request.getParameter("origen"));
		envio.setDestino(request.getParameter("destino"));
		envio.setOrigenLatLong(request.getParameter("origenLatLong"));
		envio.setDestinoLatLong(request.getParameter("destinoLatLong"));
		envio.setEmpresa(request.getParameter("empresa"));

		envio.setEspacio(Double.valueOf(request.getParameter("espacio")));
		envio.setPeso(Double.valueOf(request.getParameter("peso")));

		envio.setTipo(request.getParameter("tipo"));
		envio.setDescripcion(request.getParameter("descripcion"));

		String placaVehiculo = request.getParameter("camion");
		String patenteTrailer = request.getParameter("trailer");

		// Cambia el estado el camion anterior(si es que habia uno y no era "ninguno") dependiendo
		// si le quedan envios
		if (!envio.getCamion().equals("ninguno") && !envio.getCamion().equals(placaVehiculo)) {
			List<Envio> enviosVehiculoAnterior = DB.getEnviosVehiculo(envio.getCamion());

			if (enviosVehiculoAnterior.size() <= 1) {
				Vehiculo vehiculo = DB.load(Vehiculo.class, envio.getCamion());
				vehiculo.setEstado("no asignado");
				DB.save(vehiculo);
			}

		}
		// Set por defecto
		envio.setCamion("ninguno");
		
		if (!envio.getTrailer().equals("ninguno") && !envio.getTrailer().equals(patenteTrailer)) {
			List<Envio> enviosTrailerAnterior = DB.getEnviosTrailer(envio.getTrailer());

			if (enviosTrailerAnterior.size() <= 1) {
				Trailer trailer = DB.load(Trailer.class, envio.getTrailer());
				trailer.setEstado("no asignado");
				DB.save(trailer);
			}

		}
		// Set por defecto
		envio.setTrailer("ninguno");
		// No asignado hasta que se demuestre lo contrario
		envio.setEstado("no asignado");

		if (placaVehiculo != null && !placaVehiculo.equals("ninguno")) {
			System.out.println("Se selecciono un Camion");

			Vehiculo vehiculo = DB.load(Vehiculo.class, placaVehiculo.toLowerCase());
			vehiculo.setEstado("asignado");
			DB.save(vehiculo);

			envio.setCamion(vehiculo.getPlaca());
			envio.setEstado("asignado");

		} else if (patenteTrailer != null && !patenteTrailer.equals("ninguno")) {
			System.out.println("Se selecciono un Trailer");

			Trailer trailer = DB.load(Trailer.class, patenteTrailer.toLowerCase());
			trailer.setEstado("asignado");
			DB.save(trailer);

			envio.setCamion(trailer.getCamion());
			envio.setTrailer(trailer.getPatente());
			envio.setEstado("asignado");
		}

		DB.save(envio);
		com.logica.Dibujar.mensaje(response.getWriter(), "Envio actualizado correctamente", "/envios/modificar.jsp");

	}
}
