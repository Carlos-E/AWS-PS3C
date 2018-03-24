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
@WebServlet("/getEnvios")
public class GetEnvios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public GetEnvios() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String conductor = request.getSession().getAttribute("username").toString();
		
		conductor = "condu2";
		
		String placaCamion = ControladorBD.checkPlaca(conductor);
		
		ArrayList<Envio> enviosCamion = ControladorBD.getShipments("camion", placaCamion);
		
		System.out.print(enviosCamion);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		response.setContentType("application/json");
		response.getWriter().print(ow.writeValueAsString(enviosCamion));
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

}
