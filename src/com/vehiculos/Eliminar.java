package com.vehiculos;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/vehiculos/eliminar")
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

		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());

		vehiculo = DB.load(vehiculo);

		if (vehiculo != null) {

			if (DB.getEnviosPendientesVehiculo(vehiculo.getPlaca()).size() != 0) {
				// Dibujar.mensaje(response.getWriter(), "Este vehículo contiene
				// envíos sin entregar",
				// "/vehiculos/eliminar.jsp");
				response.setStatus(200);
				response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("title", "Operaci&oacute;n fallida");
						put("message", "Este vehículo contiene envíos sin entregar");
					}
				}));
				return;
			}

			if (vehiculo.getTipo().equals("remolque")) {
				if (DB.getTrailerRemolque(vehiculo.getPlaca()) != null) {
					response.setStatus(200);
					response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
						private static final long serialVersionUID = 1L;
						{
							put("title", "Operaci&oacute;n fallida");
							put("message", "Este vehículo tiene un tráiler asignado");
						}
					}));
					return;
				}
			}

			DB.delete(vehiculo);

		}

		response.setStatus(201);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Vehículo eliminado");
			}
		}));
		return;
	}

}
