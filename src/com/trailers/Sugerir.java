package com.trailers;

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
import clases.Trailer;

@WebServlet("/trailers/sugerir")
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

		List<Trailer> trailers = new ArrayList<Trailer>(DB.scan(Trailer.class, new DynamoDBScanExpression()));
		List<Trailer> trailersSeleccionados = new ArrayList<Trailer>();
		Iterator<Trailer> iteratorTrailers;
		String validacion="false";
		iteratorTrailers = trailers.iterator();
		while (iteratorTrailers.hasNext()) {
			Trailer trailer = iteratorTrailers.next();

			if (trailer.getTipo().equals("remolque")) {
				iteratorTrailers.remove();
				continue;
			}
			
			trailer.setPesoMax(trailer.getPesoMax() - DB.getPesoTrailer(trailer.getPatente()));
			trailer.setEspacioMax(trailer.getEspacioMax() - DB.getEspacioTrailer(trailer.getPatente()));
		}
		
		switch (criteria) {
		case "peso":
			trailers.sort(Comparator.comparing(Trailer::getPesoMax));
			Collections.reverse(trailers);
			System.out.println("lista reorganizada, entrando a for para encontrar lista de trailers por peso");			
			for(int i=0;i<trailers.size();i++) {
				if(!pesoEnvio.equals(0)||!espacioEnvio.equals(0)) {
					pesoEnvio = pesoEnvio - trailers.get(i).getPesoMax();
					espacioEnvio = espacioEnvio - trailers.get(i).getEspacioMax();
					if(pesoEnvio<0||espacioEnvio<0) {
						System.out.println("saliendo del ciclo de busqueda por peso");
						break;
					}else {
						trailersSeleccionados.add(trailers.get(i));
						System.out.println("se agrega un nuevo trailer a la lisa indice: "+i);
						if(i==trailers.size()-1 && (pesoEnvio>0 || espacioEnvio>0)) {
							trailersSeleccionados = new ArrayList<Trailer>();						
						}
					}	
				}else {
					System.out.println("saliendo del ciclo de busqueda por peso");
					break;
				}
			}
			break;
		case "espacio":
			trailers.sort(Comparator.comparing(Trailer::getEspacioMax));	
			Collections.reverse(trailers);
			System.out.println("lista reorganizada, entrando a for para encontrar lista de trailers por espacio");			
			for(int i=0;i<trailers.size();i++) {
				if(!pesoEnvio.equals(0) || !espacioEnvio.equals(0)) {
					pesoEnvio = pesoEnvio - trailers.get(i).getPesoMax();
					espacioEnvio = espacioEnvio - trailers.get(i).getEspacioMax();
					if(pesoEnvio<0||espacioEnvio<0) {
						System.out.println("saliendo del ciclo de busqueda por espacio");
						break;
					}else {
						trailersSeleccionados.add(trailers.get(i));
						System.out.println("se agrega un nuevo trailer a la lisa indice: "+i);
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
		
		
		response.getWriter().print(new ObjectMapper().writeValueAsString(trailersSeleccionados));
		response.getWriter().close();
	
		
		this.doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//this.send();
	}
}
