package com.reportes;

import java.io.IOException;
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
import clases.Reporte;

@WebServlet("/reportes/visto")
public class Visto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Visto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		List<Reporte> reportes = new DB().scan(Reporte.class, new DynamoDBScanExpression());

		for (int i = 0; i < reportes.size(); i++) {
			try {
				if (request.getParameter(reportes.get(i).getHora()) == null) {
					reportes.get(i).setVisto(false);
				} else {
					reportes.get(i).setVisto(true);
				}
								
				new DB().save(reportes.get(i));
				
			} catch (Exception e) {
				System.out.println("no encontro una fecha, algo anda mal");
			}
		}

		response.setStatus(200);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Reportes actualizados");
			}
		}));
		return;

	}

}
