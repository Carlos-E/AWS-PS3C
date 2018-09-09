package com.movil;

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

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;

import clases.*;

@WebServlet("/getEnvios")
public class GetEnvios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEnvios() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Buscando envios");
		
		DB DB = new DB();

		String conductor = request.getSession().getAttribute("username").toString();

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(conductor));

		List<Vehiculo> vehiculosdelConductor = DB.query(Vehiculo.class,
				new DynamoDBQueryExpression<Vehiculo>().withIndexName("usuario").withConsistentRead(false)
						.withKeyConditionExpression("usuario = :v1").withExpressionAttributeValues(eav));

		// Se Toma la primer opcion porque se supone que solo puede tener un
		// conductor a la vez
		Vehiculo vehiculo = vehiculosdelConductor.get(0);

		List<Envio> envios = null;

		if (vehiculo.getTipo().equals("camion")) {
			envios = DB.getEnviosPendientesVehiculo(vehiculo.getPlaca());
		} else {

			List<Trailer> listaTrailers = new DB().scan(Trailer.class, new DynamoDBScanExpression());

			for (int i = 0; i < listaTrailers.size(); i++) {
				if (listaTrailers.get(i).getCamion().equals(vehiculo.getPlaca())) {
					//envios = ControladorBD.getShipments("trailer", listaTrailers.get(i).getPatente());
					envios = DB.getEnviosPendientesTrailer(listaTrailers.get(i).getPatente());
					break;
				}
			}
		}
		
		Iterator<Envio> iterator = envios.iterator();
		
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		while (iterator.hasNext()) {
			Envio envio = iterator.next();
			
			@SuppressWarnings("unchecked")
			Map<String, Object> result = new ObjectMapper().convertValue(envio, Map.class);
			
			result.put("cliente",DB.load(Usuario.class,envio.getUsuario()));
			results.add(result);
			
		}        

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writer().writeValueAsString(results));
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

}
