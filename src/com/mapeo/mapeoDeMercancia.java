package com.mapeo;

import java.io.IOException;
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

@WebServlet("/mapeoDeMercancia")
public class mapeoDeMercancia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public mapeoDeMercancia() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Vehiculo> vehiculos = new DB().scan(Vehiculo.class,
				new DynamoDBScanExpression().withProjectionExpression("placa, latitud, longitud"));

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(vehiculos));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

}
