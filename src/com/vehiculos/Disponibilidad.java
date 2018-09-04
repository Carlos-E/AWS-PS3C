package com.vehiculos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/disponibilidadDeVehiculos")
public class Disponibilidad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Disponibilidad() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");

		DB DB = new DB();

		double espacioEnvio = Double.valueOf(request.getParameter("espacioEnvio"));
		double pesoEnvio = Double.valueOf(request.getParameter("pesoEnvio"));

		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(DB.scan(Vehiculo.class, new DynamoDBScanExpression()));

		Iterator<Vehiculo> iteratorVehiculos = vehiculos.iterator();

		while (iteratorVehiculos.hasNext()) {
			Vehiculo vehiculo = iteratorVehiculos.next();

			if (vehiculo.getTipo().equals("remolque")) {
				iteratorVehiculos.remove();
				continue;
			}

			double pesoOcupado = DB.getPesoVehiculo(vehiculo.getPlaca());
			double espacioOcupado = DB.getEspacioVehiculo(vehiculo.getPlaca());

			double pesoDisponible = 0;

			if (vehiculo.getPesoMax() > pesoOcupado) {
				pesoDisponible = vehiculo.getPesoMax() - pesoOcupado;
			}

			double espacioDisponible = 0;
			if (vehiculo.getEspacioMax() > espacioOcupado) {
				espacioDisponible = vehiculo.getEspacioMax() - espacioOcupado;
			}

			if (espacioDisponible < espacioEnvio || pesoDisponible < pesoEnvio) {
				iteratorVehiculos.remove();
			}

		}
		
		response.getWriter().print(new ObjectMapper().writeValueAsString(vehiculos));
		response.getWriter().close();
	}

}
