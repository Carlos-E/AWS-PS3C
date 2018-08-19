package com.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.Dibujar;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/vehiculos/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());

		if (DB.getEnviosPendientesVehiculo(vehiculo.getPlaca()).size() != 0) {
			Dibujar.mensaje(response.getWriter(), "Este vehículo contiene envíos sin entregar",
					"/vehiculos/eliminar.jsp");
			return;
		}

		DB.delete(vehiculo);

		response.setContentType("text/html");
		Dibujar.mensaje(response.getWriter(), "Operación Exitosa", "/vehiculos/eliminar.jsp");
	}

}
