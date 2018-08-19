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

		vehiculo.setEmpresa(request.getParameter("empresa").toLowerCase());
		
		vehiculo.setEspacioMax(Double.valueOf(request.getParameter("espacioMax")));
		if (vehiculo.getEspacioMax() <= DB.getEspacioVehiculo(vehiculo.getPlaca())) {
			Dibujar.mensaje(response.getWriter(), "El espacio no puede ser menor a la cantidad consumida por los envios asignados", "/vehiculos/modificar.jsp");
			return;
		}

		vehiculo.setPesoMax(Double.valueOf(request.getParameter("pesoMax")));
		if (vehiculo.getPesoMax() <= DB.getPesoVehiculo(vehiculo.getPlaca())) {
			Dibujar.mensaje(response.getWriter(), "El peso no puede ser menor a la cantidad consumida por los envios asignados", "/vehiculos/modificar.jsp");
			return;
		}

		if (request.getParameter("conductor").equals("null")) {
			vehiculo.setUsuario(request.getParameter("conductorAsignado").toLowerCase());
		} else {
			vehiculo.setUsuario(request.getParameter("conductor").toLowerCase());
		}

		DB.save(vehiculo);

		Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", "/vehiculos/modificar.jsp");

	}

}
