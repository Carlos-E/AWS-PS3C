package com.movil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases.*;

@WebServlet("/chequeo/entrega")
public class Entrega extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Entrega() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB DB = new DB();

		Envio envio = DB.load(Envio.class, request.getParameter("client"), request.getParameter("date"));

		String valor = request.getParameter("value");

		if (valor.equals("true")) {
			envio.setChequeoDescarga(true);
			envio.setChequeoCarga(true);
			envio.setEstado("entregado");
			
			if (envio.getTrailer().equals("ninguno") && !envio.getCamion().equals("ninguno")) {

				if (DB.getEnviosVehiculo(envio.getCamion()) == null) {
					System.out.println(
							"El camion " + envio.getCamion() + " no tiene mas envios, cambiando estado a \"no asignado\"");

					Vehiculo vehiculo = new Vehiculo();
					vehiculo.setPlaca(envio.getCamion());
					vehiculo = DB.load(vehiculo);
					vehiculo.setEstado("no asignado");
					DB.save(vehiculo);
				}

			} else if (!envio.getTrailer().equals("ninguno")) {
				
				if (DB.getEnviosTrailer(envio.getTrailer()) == null) {
					System.out.println(
							"El trailer " + envio.getTrailer() + " no tiene mas envios, cambiando estado a \"no asignado\"");

					Trailer trailer = new Trailer();
					trailer.setPatente(envio.getTrailer());
					trailer = DB.load(trailer);
					trailer.setEstado("no asignado");
					DB.save(trailer);
				}

			}
			
		} else if (valor.equals("false")) {
			envio.setChequeoDescarga(false);
			envio.setEstado("en tránsito ");
		}
		
		DB.save(envio);
		
		try {
			new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío Entregado",
					"Hemos entregado su envío.", envio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Chequeo Entrega/Descarga: " + envio.getUsuario() + " "
				+ envio.getFecha() + " : " + envio.isChequeoDescarga());

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writer().writeValueAsString(true));
		response.getWriter().close();
	}

}
