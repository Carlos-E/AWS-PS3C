package com.vehiculos;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/vehiculos/remolquesDisponibles")
public class RemolquesDisponibles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemolquesDisponibles() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		List<Vehiculo> list = new ArrayList<Vehiculo>(DB.scan(Vehiculo.class, new DynamoDBScanExpression()));

		Iterator<Vehiculo> iterator = list.iterator();
		while (iterator.hasNext()) {
			Vehiculo object = iterator.next();
			if (!object.getTipo().equals("remolque")||DB.estaOcupado("null", object.getPlaca())) {
				iterator.remove();
			}
		}

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(list));
		response.getWriter().close();

	}

}
