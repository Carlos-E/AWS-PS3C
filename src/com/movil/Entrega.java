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
		} else if (valor.equals("false")) {
			envio.setChequeoDescarga(false);
		}

		DB.save(envio);

		response.setContentType("application/json");
		response.getWriter().print(new ObjectMapper().writer().writeValueAsString(true));
		response.getWriter().close();
	}

}
