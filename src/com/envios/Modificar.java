package com.envios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Envio;

@WebServlet("/envios/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
	

		// Buscar el objeto en la base de datos e instanciarlo
		Envio envio = new DB().load(Envio.class,request.getParameter("cliente"), request.getParameter("fecha"));

		// Si no encontro nada, soltar mensaje de error y recargar pagina
		if (envio == null) {
			com.logica.Dibujar.mensaje(response.getWriter(), "Envio no encontrado", "/envios/modificar.jsp");
			return;
		}

		// System.out.println("Objeto encontrado en tabla Envios: " + envio);

		envio.setOrigen(request.getParameter("origen"));
		envio.setDestino(request.getParameter("destino"));

		envio.setOrigenLatLong(request.getParameter("origenLatLong"));
		envio.setDestinoLatLong(request.getParameter("destinoLatLong"));

		envio.setEmpresa(request.getParameter("empresa"));
		envio.setEspacio(request.getParameter("espacio"));
		envio.setPeso(request.getParameter("peso"));
		envio.setTipo(request.getParameter("tipo"));
		//envio.setEstado(request.getParameter("estado"));
		envio.setDescripcion(request.getParameter("descripcion"));

		if (request.getParameter("camion") == null) {
			envio.setCamion("ninguno");
			envio.setEstado("no asignado");

		} else {
			envio.setCamion(request.getParameter("camion"));
			envio.setEstado("asignado");
		}

		if (request.getParameter("trailer") == null) {
			envio.setTrailer("ninguno");
			envio.setEstado("no asignado");

		} else {
			envio.setTrailer(request.getParameter("trailer"));
			envio.setEstado("asignado");
		}

		new DB().save(envio);

		com.logica.Dibujar.mensaje(response.getWriter(), "Envio actualizado correctamente", "/envios/modificar.jsp");

	}
}
