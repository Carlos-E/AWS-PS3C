package com.movil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/updateLocation")
public class updateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//NOT IMPLEMENTED

		response.setContentType("application/json");

		try {
			
			DB DB = new DB();

			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":v1", new AttributeValue().withS(request.getSession().getAttribute("username").toString()));

			List<Vehiculo> vehiculosdelConductor = DB.query(Vehiculo.class,
					new DynamoDBQueryExpression<Vehiculo>().withIndexName("usuario").withConsistentRead(false)
							.withKeyConditionExpression("usuario = :v1").withExpressionAttributeValues(eav));

			Vehiculo vehiculo = vehiculosdelConductor.get(0);
			
			vehiculo.setLatitud(request.getParameter("lat"));
			vehiculo.setLatitud(request.getParameter("lng"));
			
			DB.save(vehiculo);
			
			System.out.println("Ubicacion de "+vehiculo.getPlaca()+" actualizada");

			response.getWriter().print(true);

		} catch (Exception e) {
			response.getWriter().print(false);
		}

		response.getWriter().close();

	}

}
