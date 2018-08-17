package com.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/vehiculos/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());

		vehiculo = DB.load(vehiculo);

		vehiculo.setPesoMax(Double.valueOf(request.getParameter("pesoMax")));
		vehiculo.setEspacioMax(Double.valueOf(request.getParameter("espacioMax")));
		vehiculo.setEmpresa(request.getParameter("empresa").toLowerCase());

		if (request.getParameter("conductor").equals("null")) {
			vehiculo.setUsuario(request.getParameter("conductorAsignado").toLowerCase());
		} else {
			vehiculo.setUsuario(request.getParameter("conductor").toLowerCase());
		}

		if (vehiculo.getUsuario().equals("ninguno")) {
			vehiculo.setEstado("sin conductor");
		} else {
			vehiculo.setEstado("no asignado");
		}

		DB.save(vehiculo);

		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", "/vehiculos/modificar.jsp");

	}

}
