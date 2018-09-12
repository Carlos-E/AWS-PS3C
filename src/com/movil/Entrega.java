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

@WebServlet("/chequeo/entrega")
public class Entrega extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Entrega() {
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
		
		System.out.println("Cambiando estado de envio");
		System.out.println(request.getParameter("client"));
		System.out.println(request.getParameter("date"));

		Envio envio = DB.load(Envio.class, request.getParameter("client"), request.getParameter("date"));
		
		if(envio==null){
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Env&iacute;o no existe");
				}
			}));
			return;
		}

		String valor = request.getParameter("value");

		if (valor.equals("true")) {
			
			envio.setChequeoDescarga(true);
			envio.setChequeoCarga(true);
			envio.setEstado("entregado");
			
			new Thread(() -> {
				new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío Entregado",
						"Hemos entregado su envío.", envio);
				}).start();		
			
		} else if (valor.equals("false")) {
			
			envio.setChequeoDescarga(false);
			envio.setEstado("en tránsito");
			
			new Thread(() -> {
				new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío Revertido",
						"Hemos revertido el estado de su envio.", envio);
				}).start();
			
		}
		
		DB.save(envio);

		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Env&iacute;o actualizado");
				put("estadoNuevo",envio.getEstado());
			}
		}));
		return;
	}

}
