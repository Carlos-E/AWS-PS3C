package com.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/vehiculos/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Vehiculo vehiculo = new Vehiculo();

	public Crear() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendError(400, "Acceso incorrecto");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		

		double peso = Double.valueOf(request.getParameter("peso"));
		double espacio = Double.valueOf(request.getParameter("espacio"));
		
		String conductor = request.getParameter("conductor").toLowerCase();
		
		if (request.getParameter("tipo").toLowerCase().equals("remolque")) {
			peso = 0;
			espacio = 0;
		}
		
		if(conductor.equals("ninguno")) {
			vehiculo.setEstado("sin conductor");
		}else {
			vehiculo.setEstado("disponible");
		}
		
		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());
		vehiculo.setTipo(request.getParameter("tipo").toLowerCase());

		vehiculo.setEspacioMax(espacio);
		vehiculo.setPesoMax(peso);
		
		vehiculo.setUsuario(request.getParameter("conductor").toLowerCase());
		vehiculo.setEmpresa(request.getParameter("empresa"));

		vehiculo.setLatitud("0.0");
		vehiculo.setLongitud("0.0");

		new DB().save(vehiculo);
		
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/vehiculos/crear.jsp");

	}
}
