package com.vehiculos;

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
import clases.Usuario;
import clases.Vehiculo;

@WebServlet("/vehiculos/modificar/listar")
public class ModificarListar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModificarListar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		List<Vehiculo> vehiculos = DB.scan(Vehiculo.class, new DynamoDBScanExpression());

		for (int i = 0; i < vehiculos.size(); i++) {
			vehiculos.get(i).setEstado(DB.getEstadoVehiculo(vehiculos.get(i).getPlaca(),false));
			if (!vehiculos.get(i).getUsuario().equals("ninguno")) {
				Usuario usuario = DB.load(Usuario.class, vehiculos.get(i).getUsuario());
				if (usuario != null) {
					vehiculos.get(i).setUsuario(usuario.getUsuario());
				}
			}
		}

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(vehiculos));
		response.getWriter().close();

	}

}
