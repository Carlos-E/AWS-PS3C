package com.movil;

import java.io.IOException;

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

		DB DB = new DB();

		Envio envio = DB.load(Envio.class, request.getParameter("client"), request.getParameter("date"));

		String valor = request.getParameter("value");

		if (valor.equals("true")) {
			
			envio.setChequeoCarga(true);
			envio.setEstado("en tránsito");
			
			new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío En Tránsito",
					"Su envío ha sido recogido y esta en tránsito hacia su destino.", envio);
			
		} else if (valor.equals("false")) {
			
			envio.setChequeoCarga(false);
			envio.setChequeoDescarga(false);
			envio.setEstado("asignado");
			
			new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío Revertido",
					"Hemos revertido el estado de su envio.", envio);
		}

		DB.save(envio);
		
		
		
		System.out.println("Chequeo Recogida/Carga: " + envio.getUsuario() + " "
				+ envio.getFecha() + " : " + envio.isChequeoCarga());

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writer().writeValueAsString(true));
		response.getWriter().close();
	}

}
