package com.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.*;

@WebServlet("/vehiculos/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Vehiculo vehiculo = new clases.Vehiculo();

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
		
		if (request.getParameter("tipo").toLowerCase().equals("remolque")) {
			peso = "ninguna";
			espacio = "ninguno";
		}		
		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());
		vehiculo.setPeso(peso);
		vehiculo.setTipo(request.getParameter("tipo").toLowerCase());
		vehiculo.setEspacio(espacio);
		vehiculo.setEstado("disponible");
		vehiculo.setUsuario(request.getParameter("conductor").toLowerCase());
		vehiculo.setEmpresa(request.getParameter("empresa"));
		
		ControladorBD.registrarItem("vehiculos", vehiculo);
		
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/vehiculos/crear.jsp");

	}
}
