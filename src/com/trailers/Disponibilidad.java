package com.trailers;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/disponibilidadDeTrailers")
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

		DB DB = new DB();

		double espacioEnvio = Double.valueOf(request.getParameter("espacioEnvio"));
		double pesoEnvio = Double.valueOf(request.getParameter("pesoEnvio"));

		List<Trailer> trailers = new ArrayList<Trailer>(DB.scan(Trailer.class, new DynamoDBScanExpression()));
		ArrayList<Map<String, Object>> test = new ArrayList<Map<String, Object>>();
		
		Iterator<Trailer> iteratorTrailers = trailers.iterator();

		System.out.println("Lista Original");
		while (iteratorTrailers.hasNext()) {
			
			Trailer trailer = iteratorTrailers.next();
			
			System.out.println(trailer);

			double pesoOcupado = DB.getPesoTrailer(trailer.getPatente());
			double espacioOcupado = DB.getEspacioTrailer(trailer.getPatente());

			System.out.println("Peso maximo del trailer: " + trailer.getPesoMax());
			System.out.println("Peso ocupado en el trailer: " + pesoOcupado);

			double pesoDisponible = 0;
			if (trailer.getPesoMax() > pesoOcupado) {
				pesoDisponible = trailer.getPesoMax() - pesoOcupado;
			}

			double espacioDisponible = 0;
			if (trailer.getEspacioMax() > espacioOcupado) {
				espacioDisponible = trailer.getEspacioMax() - espacioOcupado;
			}

			if (espacioDisponible < espacioEnvio || pesoDisponible < pesoEnvio) {
				System.out.println("Trailer sin espacio, descartando");
				iteratorTrailers.remove();
			}

		}
		System.out.println("Lista Original");
		/*
		 * System.out.println("Lista resultante"); iteratorTrailers =
		 * trailers.iterator(); while (iteratorTrailers.hasNext()) {
		 * System.out.println(iteratorTrailers.next()); }
		 
		 com.vehiculo leer ---leee esta monda!!
*/      Map<String, Object> map = new HashMap<String, Object>();
		
		for(int j = 0; j <trailers.size();j++) {
			map = new HashMap<String, Object>();
			map.put("patente", trailers.get(j).getPatente());
			if(trailers.get(j).getCamion() != "ninguno") {
				Vehiculo vehiculo = DB.load(Vehiculo.class, trailers.get(j).getCamion());
				map.put("latitud", vehiculo.getLatitud());
				map.put("longitud", vehiculo.getLongitud());
			}else {
				map.put("latitud", "NA");
				map.put("longitud", "NA");
			}
			test.add(map);
			System.out.println(map);
			
		}
		System.out.println(test);
		
		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(test));
		response.getWriter().close();
	}

}
