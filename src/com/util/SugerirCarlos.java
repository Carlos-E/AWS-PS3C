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
import com.fasterxml.jackson.databind.SerializationFeature;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/vehiculos/sugerir2")
public class SugerirCarlos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SugerirCarlos() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		DB DB = new DB();

		double pesoEnvio = Double.valueOf(request.getParameter("pesoEnvio"));
		double espacioEnvio = Double.valueOf(request.getParameter("espacioEnvio"));

		System.out.println("pesoEnvio: " + pesoEnvio);
		System.out.println("espacioEnvio: " + espacioEnvio);

		String criteria = "espacio";
		double relacion = 0.0;
		
		criteria = "peso";
		relacion = espacioEnvio / pesoEnvio;

		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>(DB.scan(Vehiculo.class, new DynamoDBScanExpression()));
		Iterator<Vehiculo> iteratorVehiculos;

		iteratorVehiculos = vehiculos.iterator();
		while (iteratorVehiculos.hasNext()) {
			Vehiculo vehiculo = iteratorVehiculos.next();

			double pesoOcupadoVehiculo = DB.getPesoVehiculo(vehiculo.getPlaca());
			double espacioOcupadoVehiculo = DB.getEspacioVehiculo(vehiculo.getPlaca());

			if (vehiculo.getTipo().equals("remolque")) {
				iteratorVehiculos.remove();
				continue;
			}

			if (vehiculo.getPesoMax() == pesoOcupadoVehiculo) {
				iteratorVehiculos.remove();
				continue;
			}

			if (vehiculo.getEspacioMax() == espacioOcupadoVehiculo) {
				iteratorVehiculos.remove();
				continue;
			}

			if (vehiculo.getEspacioMax() == 0.0 || vehiculo.getPesoMax() == 0.0) {
				iteratorVehiculos.remove();
				continue;
			}
		}

		// organizacion de la las listad dependiendo el criterio
		switch (criteria) {
		case "peso":
			vehiculos.sort(Comparator.comparing(Vehiculo::getPesoMax));
			Collections.reverse(vehiculos);
			break;
		case "espacio":
			vehiculos.sort(Comparator.comparing(Vehiculo::getEspacioMax));
			Collections.reverse(vehiculos);
			break;
		default:
			vehiculos.sort(Comparator.comparing(Vehiculo::getPesoMax));
			Collections.reverse(vehiculos);
			break;
		}

		List<Map<String, Object>> seleccionados = new ArrayList<Map<String, Object>>();

		iteratorVehiculos = vehiculos.iterator();
		while (iteratorVehiculos.hasNext()) {
			Vehiculo vehiculo = iteratorVehiculos.next();

			double pesoOcupadoVehiculo = DB.getPesoVehiculo(vehiculo.getPlaca());
			double espacioOcupadoVehiculo = DB.getEspacioVehiculo(vehiculo.getPlaca());

			Map<String, Object> seleccionado = new HashMap<String, Object>();

			seleccionado.put("placa", vehiculo.getPlaca());
			seleccionado.put("pesoMax", vehiculo.getPesoMax());
			seleccionado.put("pesoOcupado", pesoOcupadoVehiculo);
			seleccionado.put("pesoDisponible", vehiculo.getPesoMax() - pesoOcupadoVehiculo);
			seleccionado.put("espacioMax", vehiculo.getEspacioMax());
			seleccionado.put("espacioOcupado", espacioOcupadoVehiculo);
			seleccionado.put("espacioDisponible", vehiculo.getEspacioMax() - espacioOcupadoVehiculo);

			seleccionados.add(seleccionado);
		}

		List<HashMap<String, Object>> distribuciones = new ArrayList<HashMap<String, Object>>();

		if (pesoEnvio < espacioEnvio) {

			System.out.println("pesoEnvio < espacioEnvio");

			criteria = "peso";
			relacion = espacioEnvio / pesoEnvio;

			System.out.println("Relacion: " + relacion);

			espacioEnvio = pesoEnvio * relacion;

			for (int i = 0; i < seleccionados.size(); i++) {

				HashMap<String, Object> distribucion = new HashMap<String, Object>();

				double espacioDisponible = (Double) seleccionados.get(i).get("pesoDisponible");
				double pesoDisponible = (Double) seleccionados.get(i).get("espacioDisponible");

				if (pesoDisponible >= pesoEnvio) {
					// soporta
					System.out.println(seleccionados.get(i).get("placa") + " soporta peso");

					if (espacioDisponible >= espacioEnvio) {
						// cabe y soporta todo el envio restante
						System.out.println(seleccionados.get(i).get("placa") + " y cabe");

						distribucion.put("placa", seleccionados.get(i).get("placa"));
						distribucion.put("asignarPeso", pesoEnvio);
						distribucion.put("asignarEspacio", espacioEnvio);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);

						pesoEnvio = pesoEnvio - pesoEnvio;
						espacioEnvio = espacioEnvio - espacioEnvio;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);
						break;
					} else {
						// cabe menos y soporta todo
						System.out.println(seleccionados.get(i).get("placa") + " y cabe menos");

						distribucion.put("placa", seleccionados.get(i).get("placa"));
						distribucion.put("asignarPeso", espacioDisponible / relacion);
						distribucion.put("asignarEspacio", espacioDisponible);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);

						pesoEnvio = pesoEnvio - espacioDisponible / relacion;
						espacioEnvio = espacioEnvio - espacioDisponible;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);

					}

				}else{

					// cabe y soporta todo el envio restante
					System.out.println(seleccionados.get(i).get("placa") + " sampale todo");

					distribucion.put("placa", seleccionados.get(i).get("placa"));
					distribucion.put("asignarPeso", pesoDisponible);
					distribucion.put("asignarEspacio", espacioDisponible);

					System.out.println("Peso antes de la operacion: " + pesoEnvio);
					System.out.println("Espacio antes de la operacion: " + espacioEnvio);

					pesoEnvio = pesoEnvio - pesoDisponible;
	
					espacioEnvio = espacioEnvio - espacioDisponible;

					System.out.println("Peso restante del envio: " + pesoEnvio);
					System.out.println("Espacio restante del envio: " + espacioEnvio);

					distribuciones.add(distribucion);

				}

			}

			System.out.println("Peso restante del envio: " + pesoEnvio);
			System.out.println("Espacio restante del envio: " + espacioEnvio);

			if (pesoEnvio > 0 || espacioEnvio > 0) {
				distribuciones = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> distribucion = new HashMap<String, Object>();

				distribucion.put("mensaje", "los vehiculos no alcanzan");
				distribucion.put("pesoEnvioRestante", pesoEnvio);
				distribucion.put("espacioEnvioRestante", espacioEnvio);

				distribuciones.add(distribucion);

			}
			
		}else if (pesoEnvio > espacioEnvio){

			System.out.println("pesoEnvio > espacioEnvio");

			criteria = "espacio";
			relacion =  pesoEnvio/espacioEnvio;

			System.out.println("Relacion: " + relacion);

			pesoEnvio =  espacioEnvio* relacion;

			for (int i = 0; i < seleccionados.size(); i++) {

				HashMap<String, Object> distribucion = new HashMap<String, Object>();

				double espacioDisponible = (Double) seleccionados.get(i).get("pesoDisponible");
				double pesoDisponible = (Double) seleccionados.get(i).get("espacioDisponible");

				if (espacioDisponible >= espacioEnvio) {
					// soporta
					System.out.println(seleccionados.get(i).get("placa") + " soporta peso");

					if (pesoDisponible >= pesoEnvio) {
						// cabe y soporta todo el envio restante
						System.out.println(seleccionados.get(i).get("placa") + " y cabe");

						distribucion.put("placa", seleccionados.get(i).get("placa"));
						distribucion.put("asignarPeso", pesoEnvio);
						distribucion.put("asignarEspacio", espacioEnvio);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);

						pesoEnvio = pesoEnvio - pesoEnvio;
		
						espacioEnvio = espacioEnvio - espacioEnvio;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);
						break;
					} else {
						// cabe menos y soporta todo
						System.out.println(seleccionados.get(i).get("placa") + " y cabe menos");

						distribucion.put("placa", seleccionados.get(i).get("placa"));
						
						distribucion.put("asignarPeso", pesoDisponible );
						distribucion.put("asignarEspacio", pesoDisponible / relacion);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);
						
						pesoEnvio = pesoEnvio - pesoDisponible;
						espacioEnvio = espacioEnvio - pesoDisponible/ relacion;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);

					}

				}else{

					// cabe y soporta todo el envio restante
					System.out.println(seleccionados.get(i).get("placa") + " sampale todo");

					distribucion.put("placa", seleccionados.get(i).get("placa"));
					distribucion.put("asignarPeso", pesoDisponible);
					distribucion.put("asignarEspacio", espacioDisponible);

					System.out.println("Peso antes de la operacion: " + pesoEnvio);
					System.out.println("Espacio antes de la operacion: " + espacioEnvio);

					pesoEnvio = pesoEnvio - pesoDisponible;
	
					espacioEnvio = espacioEnvio - espacioDisponible;

					System.out.println("Peso restante del envio: " + pesoEnvio);
					System.out.println("Espacio restante del envio: " + espacioEnvio);

					distribuciones.add(distribucion);

				}

			}

			System.out.println("Peso restante del envio: " + pesoEnvio);
			System.out.println("Espacio restante del envio: " + espacioEnvio);

			if (pesoEnvio > 0 || espacioEnvio > 0) {
				distribuciones = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> distribucion = new HashMap<String, Object>();

				distribucion.put("mensaje", "los vehiculos no alcanzan");
				distribucion.put("pesoEnvioRestante", pesoEnvio);
				distribucion.put("espacioEnvioRestante", espacioEnvio);

				distribuciones.add(distribucion);

			}
		}else if(pesoEnvio == espacioEnvio){
			
			System.out.println("pesoEnvio == espacioEnvio");

			criteria = "espacio";
			relacion =  1;

			System.out.println("Relacion: " + relacion);

			for (int i = 0; i < seleccionados.size(); i++) {

				HashMap<String, Object> distribucion = new HashMap<String, Object>();

				double espacioDisponible = (Double) seleccionados.get(i).get("pesoDisponible");
				double pesoDisponible = (Double) seleccionados.get(i).get("espacioDisponible");

				if (espacioDisponible >= espacioEnvio) {
					// soporta
					System.out.println(seleccionados.get(i).get("placa") + " soporta peso");

					if (pesoDisponible >= pesoEnvio) {
						// cabe y soporta todo el envio restante
						System.out.println(seleccionados.get(i).get("placa") + " y cabe");

						distribucion.put("placa", seleccionados.get(i).get("placa"));
						distribucion.put("asignarPeso", pesoEnvio);
						distribucion.put("asignarEspacio", espacioEnvio);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);

						pesoEnvio = pesoEnvio - pesoEnvio;
		
						espacioEnvio = espacioEnvio - espacioEnvio;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);
						break;
					} else {
						// cabe menos y soporta todo
						System.out.println(seleccionados.get(i).get("placa") + " y cabe menos");

						distribucion.put("placa", seleccionados.get(i).get("placa"));
						
						distribucion.put("asignarPeso", pesoDisponible );
						distribucion.put("asignarEspacio", pesoDisponible / relacion);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);
						
						pesoEnvio = pesoEnvio - pesoDisponible;
						espacioEnvio = espacioEnvio - pesoDisponible/ relacion;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);

					}

				}else{

					// cabe y soporta todo el envio restante
					System.out.println(seleccionados.get(i).get("placa") + " sampale todo");

					distribucion.put("placa", seleccionados.get(i).get("placa"));
					distribucion.put("asignarPeso", pesoDisponible);
					distribucion.put("asignarEspacio", espacioDisponible);

					System.out.println("Peso antes de la operacion: " + pesoEnvio);
					System.out.println("Espacio antes de la operacion: " + espacioEnvio);

					pesoEnvio = pesoEnvio - pesoDisponible;
	
					espacioEnvio = espacioEnvio - espacioDisponible;

					System.out.println("Peso restante del envio: " + pesoEnvio);
					System.out.println("Espacio restante del envio: " + espacioEnvio);

					distribuciones.add(distribucion);
				}

			}

			System.out.println("Peso restante del envio: " + pesoEnvio);
			System.out.println("Espacio restante del envio: " + espacioEnvio);

			if (pesoEnvio > 0 || espacioEnvio > 0) {
				distribuciones = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> distribucion = new HashMap<String, Object>();

				distribucion.put("mensaje", "los vehiculos no alcanzan");
				distribucion.put("pesoEnvioRestante", pesoEnvio);
				distribucion.put("espacioEnvioRestante", espacioEnvio);

				distribuciones.add(distribucion);

			}
			
		}

		response.getWriter().print(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writer()
				.writeValueAsString(distribuciones));
		response.getWriter().close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}
}
