package com.reportes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Reporte;

@WebServlet("/getReportesDeUsuario")
public class GetReportesUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetReportesUsuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");

		DB DB = new DB();

		String usuario = request.getSession().getAttribute("username").toString();
		
		System.out.println("getReportesDeUsuario");
		System.out.println("Username: " + request.getSession().getAttribute("username").toString());

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(usuario));

		List<Reporte> reportesDeUsuario = DB.scan(Reporte.class,
				new DynamoDBScanExpression()
						.withFilterExpression("usuario = :v1").withExpressionAttributeValues(eav));
				
		response.getWriter().print(new ObjectMapper().writer().writeValueAsString(reportesDeUsuario));

	}

}
