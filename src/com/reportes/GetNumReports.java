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

@WebServlet("/getNumReports")
public class GetNumReports extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetNumReports() {
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

		try {

			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val", new AttributeValue().withBOOL(false));

			List<Reporte> reportesSinVer = DB.scan(Reporte.class, new DynamoDBScanExpression()
					.withFilterExpression("visto = :val").withExpressionAttributeValues(eav));

			response.setContentType("application/json");
			response.getWriter().print("{\"num\":" + reportesSinVer.size() + "}");
			response.getWriter().close();

		} catch (Exception e) {

			response.setStatus(200);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Error al cargar el numero de Reportes");
				}
			}));
			return;
		}

	}

}
