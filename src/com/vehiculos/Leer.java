package com.vehiculos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Vehiculo;

@WebServlet("/vehicle/read")
public class Leer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Leer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DB DB = new DB();
		
		String placa = request.getParameter("plate");
		
		Vehiculo vehiculo = DB.load(Vehiculo.class, placa);
						
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("placa", vehiculo.getPlaca());
		map.put("usuario", vehiculo.getUsuario());
		map.put("estado", DB.getEstadoVehiculo(placa));
		map.put("numEnviosPendientes", DB.getEnviosPendientesVehiculo(placa).size());
		map.put("tipo", vehiculo.getTipo());
		
		if(vehiculo.getTipo().equals("remolque")){			
			map.put("trailer", DB.getTrailerRemolque(placa).getPatente());
		}

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writeValueAsString(map));
		response.getWriter().close();

	}

}
