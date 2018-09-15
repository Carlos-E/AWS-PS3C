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

@WebServlet("/vehiculos/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Modificar() {
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

		DB DB = new DB();

		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());

		vehiculo = DB.load(vehiculo);

		vehiculo.setEmpresa(request.getParameter("empresa").toLowerCase());

		if (vehiculo.getTipo().equals("camion")) {

			vehiculo.setEspacioMax(Double.valueOf(request.getParameter("espacioMax")));
			if (vehiculo.getEspacioMax() < DB.getEspacioVehiculo(vehiculo.getPlaca())) {
				// Dibujar.mensaje(response.getWriter(), "El espacio no puede
				// ser
				// menor a la cantidad consumida por los envios asignados",
				// "/vehiculos/modificar.jsp");
				response.setStatus(200);
				response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("title", "Operaci&oacute;n fallida");
						put("message",
								"El espacio no puede ser menor a la cantidad consumida por los envios asignados");
					}
				}));
				return;
			}

			vehiculo.setPesoMax(Double.valueOf(request.getParameter("pesoMax")));
			if (vehiculo.getPesoMax() < DB.getPesoVehiculo(vehiculo.getPlaca())) {
				// Dibujar.mensaje(response.getWriter(), "El peso no puede ser
				// menor
				// a la cantidad consumida por los envios asignados",
				// "/vehiculos/modificar.jsp");
				response.setStatus(200);
				response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("title", "Operaci&oacute;n fallida");
						put("message", "El peso no puede ser menor a la cantidad consumida por los envios asignados");
					}
				}));
				return;
			}

		}

		if (request.getParameter("conductor").equals("")) {
			System.out.println("CONDUCTOR: "+request.getParameter("conductorAsignado"));
			vehiculo.setUsuario(request.getParameter("conductorAsignado").toLowerCase());
		} else {
			System.out.println("CONDUCTOR: "+request.getParameter("conductor"));
			vehiculo.setUsuario(request.getParameter("conductor").toLowerCase());
		}

		DB.save(vehiculo);

		response.setStatus(200);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Vehículo actualizado");
			}
		}));
		return;
	}

}
