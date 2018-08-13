package com.envios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.DB;
import clases.Envio;
import clases.Trailer;
import clases.Vehiculo;

@WebServlet("/envios/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Eliminar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String env = "lo que sea que venga aqui para poder llamar el envio";
		DB DB = new DB();
		Envio envio = DB.load(Envio.class, env);
		if(envio.getTrailer().equals("ninguno")) {
			Vehiculo vehiculo = DB.load(Vehiculo.class, envio.getCamion());
			double espacio = Double.valueOf(vehiculo.getEspacio()) - Double.valueOf(envio.getEspacio());
			double peso = Double.valueOf(vehiculo.getPeso()) - Double.valueOf(envio.getPeso());
			vehiculo.setEspacio(String.valueOf(espacio));
			vehiculo.setEspacio(String.valueOf(peso));
			DB.save(vehiculo);
		}else {
			Trailer trailer = DB.load(Trailer.class, envio.getCamion());
			double espacio = Double.valueOf(trailer.getEspacio()) - Double.valueOf(envio.getEspacio());
			double peso = Double.valueOf(trailer.getPeso()) - Double.valueOf(envio.getPeso());
			trailer.setEspacio(String.valueOf(espacio));
			trailer.setEspacio(String.valueOf(peso));
			DB.save(trailer);
		}
		ControladorBD.borrarItem("envios", "usuario", request.getParameter("usuario"), "fecha",
				request.getParameter("fecha"));
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getRequestURL() + ".jsp");
	}
}
