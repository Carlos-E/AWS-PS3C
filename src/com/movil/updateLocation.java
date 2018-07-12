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
@WebServlet("/updateLocation")
public class updateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public updateLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");

		try {

			String placa = ControladorBD.checkPlaca(request.getSession().getAttribute("username").toString());
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "latitud", request.getParameter("lat"));
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "longitud", request.getParameter("lng"));

			response.getWriter().print(true);
			response.getWriter().close();
			
		} catch (Exception e) {
			response.getWriter().print(false);
			response.getWriter().close();
		}

	}

}
