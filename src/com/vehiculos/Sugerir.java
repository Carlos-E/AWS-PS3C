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
		Double pesoEnvio = Double.valueOf(request.getParameter("pesoEnvio"));
		Double espacioEnvio = Double.valueOf(request.getParameter("espacioEnvio"));
		String criteria = "espacio";
		//double espacioEnvio = 10;
		//double pesoEnvio = 10;

		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(DB.scan(Vehiculo.class, new DynamoDBScanExpression()));
		List<Vehiculo> vehiculosSeleccionados = new ArrayList<Vehiculo>();
		Iterator<Vehiculo> iteratorVehiculos;
		String validacion="false";
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
			Collections.reverse(vehiculos);
			System.out.println("lista reorganizada, entrando a for para encontrar lista de vehiculos por peso");			
			for(int i=0;i<vehiculos.size();i++) {
				if(!pesoEnvio.equals(0)||!espacioEnvio.equals(0)) {
					pesoEnvio = pesoEnvio - vehiculos.get(i).getPesoMax();
					espacioEnvio = espacioEnvio - vehiculos.get(i).getEspacioMax();
					if(pesoEnvio<0||espacioEnvio<0) {
						System.out.println("saliendo del ciclo de busqueda por peso");
						break;
					}else {
						vehiculosSeleccionados.add(vehiculos.get(i));
						System.out.println("se agrega un nuevo vehiculo a la lisa indice: "+i);
						/*if(i==vehiculos.size()-1 && (pesoEnvio>0 || espacioEnvio>0)) {
							vehiculosSeleccionados = new ArrayList<Vehiculo>();						
						}*/
					}	
				}else {
					System.out.println("saliendo del ciclo de busqueda por peso");
					break;
				}
			}
			break;
		case "espacio":
			vehiculos.sort(Comparator.comparing(Vehiculo::getEspacioMax));	
			Collections.reverse(vehiculos);
			System.out.println("lista reorganizada, entrando a for para encontrar lista de vehiculos por espacio");			
			for(int i=0;i<vehiculos.size();i++) {
				if(!pesoEnvio.equals(0) || !espacioEnvio.equals(0)) {
					pesoEnvio = pesoEnvio - vehiculos.get(i).getPesoMax();
					espacioEnvio = espacioEnvio - vehiculos.get(i).getEspacioMax();
					if(pesoEnvio<0||espacioEnvio<0) {
						System.out.println("saliendo del ciclo de busqueda por espacio");
						break;
					}else {
						vehiculosSeleccionados.add(vehiculos.get(i));
						System.out.println("se agrega un nuevo vehiculo a la lisa indice: "+i);
						/*if(i==vehiculos.size()-1 && (pesoEnvio>0 || espacioEnvio>0)) {
							vehiculosSeleccionados = new ArrayList<Vehiculo>();						
						}*/
					}	
				}else {
					System.out.println("saliendo del ciclo de busqueda por espacio");
					break;
				}
			}
			break;
		} 
		
		
		response.getWriter().print(new ObjectMapper().writeValueAsString(vehiculosSeleccionados));
		response.getWriter().close();
	
		
		this.doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//this.send();
	}
}
