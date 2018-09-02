package com.vehiculos;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		double peso = 0;
		double espacio = 0;

		if (request.getParameter("tipo").toLowerCase().equals("camion")) {
			peso = Double.valueOf(request.getParameter("peso"));
			espacio = Double.valueOf(request.getParameter("espacio"));
		}

		vehiculo.setPlaca(request.getParameter("placa").toLowerCase());
		vehiculo.setTipo(request.getParameter("tipo").toLowerCase());

		vehiculo.setEspacioMax(espacio);
		vehiculo.setPesoMax(peso);

		vehiculo.setUsuario(request.getParameter("conductor").toLowerCase());
		vehiculo.setEmpresa(request.getParameter("empresa"));

		vehiculo.setLatitud("10.403242");
		vehiculo.setLongitud("-75.505819");

		new DB().save(vehiculo);

		response.setStatus(201);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Veh&iacute;culo creado");
			}
		}));
		return;
	}
}
