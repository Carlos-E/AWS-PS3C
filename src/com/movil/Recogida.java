package com.movil;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases.*;

@WebServlet("/chequeo/recogida")
public class Recogida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public Recogida() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		DB DB = new DB();

		Envio envio = DB.load(Envio.class, request.getParameter("client"), request.getParameter("date"));

		String valor = request.getParameter("value");

		if (valor.equals("true")) {

			envio.setChequeoCarga(true);
			envio.setEstado("en tránsito");

			new Thread(() -> {
				new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío En Tránsito",
						"Su envío ha sido recogido y esta en tránsito hacia su destino.", envio);
			}).start();

		} else if (valor.equals("false")) {

			envio.setChequeoCarga(false);
			envio.setChequeoDescarga(false);
			envio.setEstado("asignado");

			new Thread(() -> {
				new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío Revertido",
						"Hemos revertido el estado de su envio.", envio);
			}).start();

		}

		// si no tiene camion y no tiene trailer entonces poner
		// todo falso y no asignado
		if (envio.getCamion().equals("ninguno") && envio.getTrailer().equals("ninguno")) {
			envio.setChequeoDescarga(false);
			envio.setChequeoCarga(false);
			envio.setEstado("no asignado");
		}

		DB.save(envio);

		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Env&iacute;o actualizado");
				put("estadoNuevo", envio.getEstado());
			}
		}));
		return;
	}

}
