package com.usuarios;

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
import clases.Usuario;

@WebServlet("/usuarios/conductoresDisponibles")
public class ConductoresDisponibles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConductoresDisponibles() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		List<Usuario> usuarios = new ArrayList<Usuario>(DB.scan(Usuario.class, new DynamoDBScanExpression()));

		Iterator<Usuario> iterator = usuarios.iterator();
		while (iterator.hasNext()) {
			Usuario usuario = iterator.next();
			if (!usuario.getRol().equals("conductor")||DB.estaOcupado(usuario.getUsuario(), "null")) {
				iterator.remove();
			}
		}

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(usuarios));
		response.getWriter().close();

	}

}
