package com.vehiculos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Empresa;
import clases.Vehiculo;

@WebServlet("/vehiculos/listar")
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Listar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		List<Vehiculo> vehiculos = DB.scan(Vehiculo.class, new DynamoDBScanExpression());
		List<Empresa> empresas = DB.scan(Empresa.class, new DynamoDBScanExpression());

		List<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < vehiculos.size(); i++) {

			for (int j = 0; j < empresas.size(); j++) {
				if (vehiculos.get(i).getEmpresa().equals(empresas.get(j).getNit())) {
					vehiculos.get(i).setEmpresa(empresas.get(j).getNombre());
					break;
				} // if
			} // for

			vehiculos.get(i).setEstado(DB.getEstadoVehiculo(vehiculos.get(i).getPlaca(),true));

			@SuppressWarnings("unchecked")
			HashMap<String, Object> result = new ObjectMapper().convertValue(vehiculos.get(i), HashMap.class);

			if (vehiculos.get(i).getTipo().equals("camion")) {

				double pesoOcupado = DB.getPesoVehiculo(vehiculos.get(i).getPlaca());
				double espacioOcupado = DB.getEspacioVehiculo(vehiculos.get(i).getPlaca());

				double pesoDisponible = vehiculos.get(i).getPesoMax() - pesoOcupado;
				double espacioDisponible = vehiculos.get(i).getEspacioMax() - espacioOcupado;

				result.put("pesoMax", result.get("pesoMax") + " / " + pesoOcupado + " / " + pesoDisponible);
				result.put("espacioMax", result.get("espacioMax") + " / " + espacioOcupado + " / " + espacioDisponible);
			} else if (vehiculos.get(i).getTipo().equals("remolque")) {
				result.put("pesoMax", "NA");
				result.put("espacioMax", "NA");
			}

			results.add(result);

		} // for

		response.setContentType("application/json");
		// response.getWriter().print(new
		// ObjectMapper().writeValueAsString(vehiculos));
		response.getWriter().print(new ObjectMapper().writeValueAsString(results));
		response.getWriter().close();

	}

}
