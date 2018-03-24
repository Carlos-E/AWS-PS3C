package com.movil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.logica.ControladorBD;

import clases.*;

/**
 * Servlet implementation class trueBuscar
 */
@WebServlet("/updateShipment")
public class UpdateShipment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public UpdateShipment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean result = ControladorBD.updateShipment(request.getParameter("key"), request.getParameter("value"));

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		response.setContentType("application/json");
		response.getWriter().print(ow.writeValueAsString(result));
		response.getWriter().close();
	}

}
