package com.vehiculos;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

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
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		double espacioEnvio = Double.valueOf(request.getParameter("espacioEnvio"));
		double pesoEnvio = Double.valueOf(request.getParameter("pesoEnvio"));

		List<Vehiculo> vehiculos = DB.scan(Vehiculo.class, new DynamoDBScanExpression());

		Iterator<Vehiculo> iteratorVehiculos = vehiculos.iterator();

		while (iteratorVehiculos.hasNext()) {
			Vehiculo vehiculo = iteratorVehiculos.next();

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


	}

}
