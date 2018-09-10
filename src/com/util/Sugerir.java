package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Trailer;
import clases.Vehiculo;

@WebServlet("/sugerir")
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
		Double criterio = 0.0;
		Double cEspacioEnvio = 0.0;
		Double cPesoEnvio = 0.0;
		if(pesoEnvio<espacioEnvio) {
			criteria = "espacio";
			criterio = espacioEnvio/pesoEnvio;
			cEspacioEnvio = criterio;
			cPesoEnvio = 1.0;			
		}else if(pesoEnvio>espacioEnvio) {
			criteria = "peso";
			criterio = pesoEnvio/espacioEnvio;
			cEspacioEnvio = 1.0;
			cPesoEnvio = criterio;	
		}else {
			criteria = "unoAuno";
			criterio = 0.0;
		}
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(DB.scan(Vehiculo.class, new DynamoDBScanExpression()));
		List<Trailer> trailers = new ArrayList<Trailer>(DB.scan(Trailer.class, new DynamoDBScanExpression()));
		Iterator<Trailer> iteratorTrailers;
		Iterator<Vehiculo> iteratorVehiculos;
		iteratorTrailers = trailers.iterator();
		iteratorVehiculos = vehiculos.iterator();
		ArrayList<Map<String, Object>> test = new ArrayList<Map<String, Object>>();	
		ArrayList<Map<String, Object>> seleccionados = new ArrayList<Map<String, Object>>();
		while (iteratorTrailers.hasNext()) {
			Trailer trailer = iteratorTrailers.next();			
			trailer.setPesoMax(trailer.getPesoMax() - DB.getPesoTrailer(trailer.getPatente()));
			trailer.setEspacioMax(trailer.getEspacioMax() - DB.getEspacioTrailer(trailer.getPatente()));
		}
		while (iteratorVehiculos.hasNext()) {
			Vehiculo vehiculo = iteratorVehiculos.next();
			Double pesoVehiculo = DB.getPesoVehiculo(vehiculo.getPlaca());
			Double espacioVehiculo = DB.getEspacioVehiculo(vehiculo.getPlaca());
			if (vehiculo.getTipo().equals("remolque")) {
				iteratorVehiculos.remove();
				continue;
			}						
			vehiculo.setPesoMax(vehiculo.getPesoMax() - pesoVehiculo);
			vehiculo.setEspacioMax(vehiculo.getEspacioMax() - espacioVehiculo);
			if(vehiculo.getEspacioMax() == 0.0 || vehiculo.getPesoMax() == 0.0) {
				iteratorVehiculos.remove();
				continue;
			}
		}	
		//organizacion de la las listad dependiendo el criterio
		switch (criteria) {
		case "peso":
			vehiculos.sort(Comparator.comparing(Vehiculo::getPesoMax));
			Collections.reverse(vehiculos);
			trailers.sort(Comparator.comparing(Trailer::getPesoMax));
			Collections.reverse(trailers);
			break;
		case "espacio":
			vehiculos.sort(Comparator.comparing(Vehiculo::getEspacioMax));	
			Collections.reverse(vehiculos);
			trailers.sort(Comparator.comparing(Trailer::getEspacioMax));	
			Collections.reverse(trailers);		
			break;
		case "unoAuno":
			vehiculos.sort(Comparator.comparing(Vehiculo::getPesoMax));
			Collections.reverse(vehiculos);
			trailers.sort(Comparator.comparing(Trailer::getPesoMax));
			Collections.reverse(trailers);
			break;
		}
		//llenado de la lista Test
		for(int i=0;i<vehiculos.size();i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", vehiculos.get(i).getPlaca());
			map.put("peso", vehiculos.get(i).getPesoMax());
			map.put("espacio", vehiculos.get(i).getEspacioMax());
			test.add(map);
		}
		for(int i=0;i<trailers.size();i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", trailers.get(i).getPatente());
			map.put("peso", trailers.get(i).getPesoMax());
			map.put("espacio", trailers.get(i).getEspacioMax());
			test.add(map);
		}
		//seleccion de vehiculos y/o trailers
		switch (criteria) {
		case "peso":
			for(int i=0;i<test.size();i++) {
				if() {
					
				}else {
					System.out.println("ya se agoto");
				}
			}
			break;
		case "espacio":
					
			break;
		case "unoAuno":
			
			break;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		for(int i=0;i<test.size();i++) {
			System.out.println(test.get(i).toString());
			if(pesoEnvio!=0.0||espacioEnvio!=0.0) {
				System.out.println("antes de resta: peso= "+pesoEnvio +" espacio= "+espacioEnvio);
				pesoEnvio = pesoEnvio - Double.parseDouble(test.get(i).get("peso").toString());
				espacioEnvio = espacioEnvio - Double.parseDouble(test.get(i).get("espacio").toString());
				System.out.println("despues de resta: peso= "+pesoEnvio +" espacio= "+espacioEnvio);
				if(pesoEnvio<0.0) {
					pesoEnvio = 0.0;
				}
				if(espacioEnvio<0.0) {
					espacioEnvio=0.0;
				}
				if(pesoEnvio>0.0 || espacioEnvio>0.0) {					
					seleccionados.add(test.get(i));
				}else {
					System.out.println("El envio fue repartido entre los vehiculos y/o trailers");
					break;
				}
			}
			if(i==test.size()-1&&(pesoEnvio>0.0 || espacioEnvio>0.0)) {
				seleccionados = new ArrayList<Map<String, Object>>();	
				System.out.println("No fue posible encontrar vehiculos o trailers disponibles");
				break;
			}
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
						if(i==vehiculos.size()-1 && (pesoEnvio>0 || espacioEnvio>0)) {
							vehiculosSeleccionados = new ArrayList<Vehiculo>();						
						}
					}	
				}else {
					System.out.println("saliendo del ciclo de busqueda por peso");
					break;
				}
			}
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
			vehiculos.sort(Comparator.comparing(Vehiculo::getEspacioMax));	
			Collections.reverse(vehiculos);
			System.out.println("lista reorganizada, entrando a for para encontrar lista de vehiculos por espacio");			
			for(int i=0;i<vehiculos.size();i++) {
				System.out.println("cliclo #"+i+" en vehiculos de: " +vehiculos.size());	
				if(!pesoEnvio.equals(0) || !espacioEnvio.equals(0)) {
					System.out.println("cliclo #"+i+" entrando al if");
					System.out.println("cliclo #"+i+" valores peso y envio: "+pesoEnvio+" : "+espacioEnvio);
					pesoEnvio = pesoEnvio - vehiculos.get(i).getPesoMax();
					espacioEnvio = espacioEnvio - vehiculos.get(i).getEspacioMax();
					System.out.println("cliclo #"+i+" valores despues: "+pesoEnvio+" : "+espacioEnvio);
					if(pesoEnvio<0||espacioEnvio<0) {
						System.out.println("saliendo del ciclo de busqueda por espacio espao o peso igual a 0");
						break;
					}else {
						vehiculosSeleccionados.add(vehiculos.get(i));
						System.out.println("se agrega un nuevo vehiculo a la lisa indice: "+i);
						if(i==vehiculos.size()-1 && (pesoEnvio>0 || espacioEnvio>0)) {
							vehiculosSeleccionados = new ArrayList<Vehiculo>();						
						}
					}	
				}else {
					System.out.println("saliendo del ciclo de busqueda por espacio");
					break;
				}
			}
			trailers.sort(Comparator.comparing(Trailer::getEspacioMax));	
			Collections.reverse(trailers);
			System.out.println("lista reorganizada, entrando a for para encontrar lista de trailers por espacio");			
			for(int i=0;i<trailers.size();i++) {
				System.out.println("cliclo #"+i+" en trailers de: "+trailers.size());
				if(!pesoEnvio.equals(0) || !espacioEnvio.equals(0)) {
					System.out.println("cliclo #"+i+" entrando al if");
					System.out.println("cliclo #"+i+" valores peso y envio: "+pesoEnvio+" : "+espacioEnvio);
					pesoEnvio = pesoEnvio - trailers.get(i).getPesoMax();
					espacioEnvio = espacioEnvio - trailers.get(i).getEspacioMax();
					System.out.println("cliclo #"+i+" valores despues: "+pesoEnvio+" : "+espacioEnvio);
					if(pesoEnvio<0||espacioEnvio<0) {
						System.out.println("saliendo del ciclo de busqueda por espacio espao o peso igual a 0");
						break;
					}else {
						trailersSeleccionados.add(trailers.get(i));
						System.out.println("se agrega un nuevo trailer a la lisa indice: "+i);
						if(i==trailers.size()-1 && (pesoEnvio>0 || espacioEnvio>0)) {
							trailersSeleccionados = new ArrayList<Trailer>();						
						}
					}	
				}else {
					System.out.println("saliendo del ciclo de busqueda por espacio");
					break;
				}
			}
			break;
		}	*/
		response.getWriter().print(new ObjectMapper().writeValueAsString(seleccionados));
		response.getWriter().close();		
		this.doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//this.send();
	}
}
