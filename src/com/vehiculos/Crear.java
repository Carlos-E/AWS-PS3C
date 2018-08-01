package com.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

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

		String peso = request.getParameter("peso").toLowerCase();
		String espacio = request.getParameter("espacio").toLowerCase();
		String conductor = request.getParameter("conductor").toLowerCase();
		if (request.getParameter("tipo").toLowerCase().equals("remolque")) {
			peso = "ninguno";
			espacio = "ninguno";
		}
		if(conductor.equals("ninguno")) {
			vehiculo.setEstado("sin conductor");
		}else {
			vehiculo.setEstado("disponible");
		}
		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());
		vehiculo.setPeso("0");
		vehiculo.setPesoMax(peso);
		vehiculo.setTipo(request.getParameter("tipo").toLowerCase());
		vehiculo.setEspacioMax(espacio);
		vehiculo.setEspacio("0");
		
		vehiculo.setUsuario(request.getParameter("conductor").toLowerCase());
		vehiculo.setEmpresa(request.getParameter("empresa"));
		//CODIGO NUEVO 12 JUL 2018
		vehiculo.setLatitud("0.0");
		vehiculo.setLongitud("0.0");
		//CODIGO NUEVO 12 JUL 2018
		
		ControladorBD.registrarItem("vehiculos", vehiculo);
		
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/vehiculos/crear.jsp");

	}
}
