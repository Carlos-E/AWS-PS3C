package com.envios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

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
		envio.setEspacio(request.getParameter("espacio"));
		envio.setPeso(request.getParameter("peso"));
		envio.setTipo(request.getParameter("tipo"));
		envio.setDescripcion(request.getParameter("descripcion"));
		envio.setEstado(request.getParameter("estado"));

		String placaVehiculo = request.getParameter("camion");
		String patenteTrailer = request.getParameter("trailer");

		// Set por defecto
		envio.setCamion("ninguno");

		if (placaVehiculo != null) {

			Vehiculo vehiculo = DB.load(Vehiculo.class, placaVehiculo.toLowerCase());

			double espacioTmpV = Double.valueOf(vehiculo.getEspacio())
					- Double.valueOf(request.getParameter("espacio"));
			double pesoTmpV = Double.valueOf(vehiculo.getPeso()) - Double.valueOf(request.getParameter("peso"));
			vehiculo.setEspacio(String.valueOf(espacioTmpV));
			vehiculo.setPeso(String.valueOf(pesoTmpV));

			DB.save(vehiculo);

			envio.setCamion(vehiculo.getPlaca());
			envio.setEstado("asignado");
		}

		// Set por defecto
		envio.setTrailer("ninguno");

		if (patenteTrailer != null) {

			Trailer trailer = DB.load(Trailer.class, patenteTrailer.toLowerCase());

			double espacioTmpT = Double.valueOf(trailer.getEspacio()) - Double.valueOf(request.getParameter("espacio"));
			double pesoTmpT = Double.valueOf(trailer.getPeso()) - Double.valueOf(request.getParameter("peso"));
			trailer.setEspacio(String.valueOf(espacioTmpT));
			trailer.setPeso(String.valueOf(pesoTmpT));

			DB.save(trailer);

			envio.setCamion(trailer.getCamion());
			envio.setTrailer(trailer.getPatente());
			envio.setEstado("asignado");
		}

		DB.save(envio);

		com.logica.Dibujar.mensaje(response.getWriter(), "Envio actualizado correctamente", "/envios/modificar.jsp");

	}
}
