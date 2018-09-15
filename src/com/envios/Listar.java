package com.envios;

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

@WebServlet("/envios/listar")
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Listar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		List<Envio> envios;

		if (request.getParameter("filterBy") != null&&request.getParameter("filter")!=null&&request.getParameter("operator")!=null) {
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":v1", new AttributeValue().withS(request.getParameter("filter")));

			envios = DB.scan(Envio.class, new DynamoDBScanExpression().withFilterExpression(request.getParameter("filterBy")+request.getParameter("operator")+":v1")
					.withExpressionAttributeValues(eav));

		} else {
			envios = DB.scan(Envio.class, new DynamoDBScanExpression());
		}

		List<Empresa> empresas = DB.scan(Empresa.class, new DynamoDBScanExpression());
		List<Trailer> trailers = DB.scan(Trailer.class, new DynamoDBScanExpression());

		for (int i = 0; i < envios.size(); i++) {

			for (int j = 0; j < empresas.size(); j++) {
				if (envios.get(i).getEmpresa().equals(empresas.get(j).getNit())) {
					envios.get(i).setEmpresa(empresas.get(j).getNombre());
					break;
				} // if
			} // for

			if (!envios.get(i).getTrailer().equals("ninguno")) {

				for (int j = 0; j < trailers.size(); j++) {

					if (envios.get(i).getTrailer().equals(trailers.get(j).getPatente())
							&& !trailers.get(j).getCamion().equals("ninguno")) {
						envios.get(i).setCamion(trailers.get(j).getCamion());
						break;
					} // if
				} // for
			} // if
		} // for

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(envios));
		response.getWriter().close();

	}

}
