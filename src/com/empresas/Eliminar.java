package com.empresas;

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
import clases.Empresa;
import clases.Envio;
import clases.Trailer;
import clases.Vehiculo;

@WebServlet("/empresas/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public Eliminar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		Empresa empresa = new Empresa();
		empresa.setNit(request.getParameter("nit").toLowerCase());

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(empresa.getNit()));
		eav.put(":v2", new AttributeValue().withS("entregado"));
		
		List<Envio> envios = DB.scan(Envio.class,
				new DynamoDBScanExpression().withFilterExpression("empresa = :v1 AND estado <> :v2").withExpressionAttributeValues(eav));

		eav.remove(":v2");
		
		if (envios.size() > 0) {
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "La empresa tiene env&iacute;os sin entregar");
				}
			}));
			return;
		}

		List<Vehiculo> vehiculos = DB.scan(Vehiculo.class,
				new DynamoDBScanExpression().withFilterExpression("empresa = :v1").withExpressionAttributeValues(eav));

		if (vehiculos.size() > 0) {
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "La empresa tiene veh&iacute;culos");
				}
			}));
			return;
		}

		List<Trailer> trailers = DB.scan(Trailer.class,
				new DynamoDBScanExpression().withFilterExpression("empresa = :v1").withExpressionAttributeValues(eav));

		if (trailers.size() > 0) {
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "La empresa tiene tráileres");
				}
			}));
			return;
		}

		new DB().delete(empresa);

		response.setStatus(200);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Empresa eliminada");
			}
		}));
		return;
	}

}
