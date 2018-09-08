package com.vehiculos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

@WebServlet("/vehiculos/sugerir")
public class Sugerir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sugerir() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		DB DB = new DB();

		// double espacioEnvio =
		// Double.valueOf(request.getParameter("espacioEnvio"));
		// double pesoEnvio = Double.valueOf(request.getParameter("pesoEnvio"));
		//String criteria = request.getParameter("criterio");
		
		
		String criteria = "espacio";
		//double espacioEnvio = 10;
		//double pesoEnvio = 10;

		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(DB.scan(Vehiculo.class, new DynamoDBScanExpression()));

		Iterator<Vehiculo> iteratorVehiculos;
		
		iteratorVehiculos = vehiculos.iterator();
		while (iteratorVehiculos.hasNext()) {
			Vehiculo vehiculo = iteratorVehiculos.next();

			if (vehiculo.getTipo().equals("remolque")) {
				iteratorVehiculos.remove();
				continue;
			}
			
			vehiculo.setPesoMax(vehiculo.getPesoMax() - DB.getPesoVehiculo(vehiculo.getPlaca()));
			vehiculo.setEspacioMax(vehiculo.getEspacioMax() - DB.getEspacioVehiculo(vehiculo.getPlaca()));
		}
		
		switch (criteria) {
		case "peso":
			vehiculos.sort(Comparator.comparing(Vehiculo::getPesoMax));
			break;
		case "espacio":
			vehiculos.sort(Comparator.comparing(Vehiculo::getEspacioMax));	
			break;
		}

		Collections.reverse(vehiculos);

		response.getWriter().print(new ObjectMapper().writeValueAsString(vehiculos));
		response.getWriter().close();
	
		
		this.doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//this.send();
	}
}
