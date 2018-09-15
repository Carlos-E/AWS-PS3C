package com.vehiculos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases.*;

@WebServlet("/getEnviosVehiculo")
public class GetEnviosVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEnviosVehiculo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		String placa = request.getParameter("placa");

		System.out.println("Buscando envios de: " + placa);

		DB DB = new DB();

		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setPlaca(placa);

		vehiculo = DB.load(vehiculo);

		if (vehiculo == null) {
			response.getWriter().print(new ObjectMapper().writer().writeValueAsString(null));
			response.getWriter().close();
			return;
		}

		List<Envio> enviosPendientes = null;
		if (vehiculo.getTipo().equals("camion")) {
			enviosPendientes = DB.getEnviosPendientesVehiculo(placa);
		} else if (vehiculo.getTipo().equals("remolque")) {
			Trailer trailer = DB.getTrailerRemolque(placa);

			if (trailer != null) {
				enviosPendientes = DB.getEnviosPendientesTrailer(trailer.getPatente());
			} else {
				response.getWriter().print(new ObjectMapper().writer().writeValueAsString(null));
				response.getWriter().close();
				return;
			}

		}

		response.getWriter().print(new ObjectMapper().writer().writeValueAsString(enviosPendientes));
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

}
