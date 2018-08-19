package com.trailers;

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
import clases.Empresa;
import clases.Trailer;

@WebServlet("/trailers/listar")
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

		List<Trailer> trailers = new DB().scan(Trailer.class, new DynamoDBScanExpression());
		List<Empresa> empresas = new DB().scan(Empresa.class, new DynamoDBScanExpression());

		for (int i = 0; i < trailers.size(); i++) {

			for (int j = 0; j < empresas.size(); j++) {
				if (trailers.get(i).getEmpresa().equals(empresas.get(j).getNit())) {
					trailers.get(i).setEmpresa(empresas.get(j).getNombre());
					break;
				} // if
			} // for

		} // for

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(trailers));
		response.getWriter().close();

	}

}
