package com.trailers;

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
import clases.Trailer;

@WebServlet("/traileres/sugerir/carlos")
public class Sugerir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sugerir() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		DB DB = new DB();

		double pesoEnvio = Double.valueOf(request.getParameter("pesoEnvio"));
		double espacioEnvio = Double.valueOf(request.getParameter("espacioEnvio"));
		double relacion = 0.0;

		System.out.println("pesoEnvio: " + pesoEnvio);
		System.out.println("espacioEnvio: " + espacioEnvio);

		List<Trailer> lista = new ArrayList<Trailer>(DB.scan(Trailer.class, new DynamoDBScanExpression()));
		Iterator<Trailer> iterator;

		iterator = lista.iterator();
		while (iterator.hasNext()) {
			Trailer objeto = iterator.next();

			double pesoOcupadoTrailer = DB.getPesoTrailer(objeto.getPatente());
			double espacioOcupadoTrailer = DB.getEspacioTrailer(objeto.getPatente());

			if (objeto.getTipo().equals("remolque")) {
				iterator.remove();
				continue;
			}

			if (objeto.getPesoMax() == pesoOcupadoTrailer) {
				iterator.remove();
				continue;
			}

			if (objeto.getEspacioMax() == espacioOcupadoTrailer) {
				iterator.remove();
				continue;
			}

			if (objeto.getEspacioMax() == 0.0 || objeto.getPesoMax() == 0.0) {
				iterator.remove();
				continue;
			}
		}

		// organizacion de la las listad dependiendo el criterio
		if (pesoEnvio < espacioEnvio) {
			lista.sort(Comparator.comparing(Trailer::getPesoMax));
			Collections.reverse(lista);
		} else {
			lista.sort(Comparator.comparing(Trailer::getEspacioMax));
			Collections.reverse(lista);
		}

		List<Map<String, Object>> seleccionados = new ArrayList<Map<String, Object>>();

		iterator = lista.iterator();
		while (iterator.hasNext()) {
			Trailer objeto = iterator.next();

			double pesoOcupadoTrailer = DB.getPesoTrailer(objeto.getPatente());
			double espacioOcupadoTrailer = DB.getEspacioTrailer(objeto.getPatente());

			Map<String, Object> seleccionado = new HashMap<String, Object>();

			seleccionado.put("placa", objeto.getPatente());
			seleccionado.put("pesoMax", objeto.getPesoMax());
			seleccionado.put("pesoOcupado", pesoOcupadoTrailer);
			seleccionado.put("pesoDisponible", objeto.getPesoMax() - pesoOcupadoTrailer);
			seleccionado.put("espacioMax", objeto.getEspacioMax());
			seleccionado.put("espacioOcupado", espacioOcupadoTrailer);
			seleccionado.put("espacioDisponible", objeto.getEspacioMax() - espacioOcupadoTrailer);

			seleccionados.add(seleccionado);
		}

		List<HashMap<String, Object>> distribuciones = new ArrayList<HashMap<String, Object>>();

		if (pesoEnvio < espacioEnvio) {

			System.out.println("pesoEnvio < espacioEnvio");

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

				} else {

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

				distribucion.put("mensaje", "los contenedores no alcanzan");
				distribucion.put("pesoEnvioRestante", pesoEnvio);
				distribucion.put("espacioEnvioRestante", espacioEnvio);

				distribuciones.add(distribucion);

			}

		} else if (pesoEnvio > espacioEnvio) {

			System.out.println("pesoEnvio > espacioEnvio");

			relacion = pesoEnvio / espacioEnvio;

			System.out.println("Relacion: " + relacion);

			pesoEnvio = espacioEnvio * relacion;

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

						distribucion.put("asignarPeso", pesoDisponible);
						distribucion.put("asignarEspacio", pesoDisponible / relacion);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);

						pesoEnvio = pesoEnvio - pesoDisponible;
						espacioEnvio = espacioEnvio - pesoDisponible / relacion;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);

					}

				} else {

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

				distribucion.put("mensaje", "los contenedores no alcanzan");
				distribucion.put("pesoEnvioRestante", pesoEnvio);
				distribucion.put("espacioEnvioRestante", espacioEnvio);

				distribuciones.add(distribucion);

			}
		} else if (pesoEnvio == espacioEnvio) {

			System.out.println("pesoEnvio == espacioEnvio");

			relacion = 1;

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

						distribucion.put("asignarPeso", pesoDisponible);
						distribucion.put("asignarEspacio", pesoDisponible / relacion);

						System.out.println("Peso antes de la operacion: " + pesoEnvio);
						System.out.println("Espacio antes de la operacion: " + espacioEnvio);

						pesoEnvio = pesoEnvio - pesoDisponible;
						espacioEnvio = espacioEnvio - pesoDisponible / relacion;

						System.out.println("Peso restante del envio: " + pesoEnvio);
						System.out.println("Espacio restante del envio: " + espacioEnvio);

						distribuciones.add(distribucion);

					}

				} else {

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

				distribucion.put("mensaje", "los contenedores no alcanzan");
				distribucion.put("pesoEnvioRestante", pesoEnvio);
				distribucion.put("espacioEnvioRestante", espacioEnvio);
				distribucion.put("fail", "true");

				distribuciones.add(distribucion);

			}

		}

		
		//Poner como lo implemento puche
		if (distribuciones.get(0).get("fail") == null) {
			for (int i = 0; i < distribuciones.size(); i++) {
				distribuciones.get(i).put("id", distribuciones.get(i).get("placa"));
				distribuciones.get(i).remove("placa");
				distribuciones.get(i).put("peso", distribuciones.get(i).get("asignarPeso"));
				distribuciones.get(i).remove("asignarPeso");
				distribuciones.get(i).put("espacio", distribuciones.get(i).get("asignarEspacio"));
				distribuciones.get(i).remove("asignarEspacio");
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
