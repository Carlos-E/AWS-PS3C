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
	clases.usuario usuario = new clases.usuario();

	public GetEnvios() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
camion camion = (camion)ControladorBD.getItem("camiones", "usuario", request.getSession().getAttribute("username").toString());
		
		String placaCamion = camion.getPlaca();
		ArrayList<envio> enviosCamion = null; 
		
		ArrayList<envio> envios = ControladorBD.escanearTabla("envios");
		
		for (int i = 0; i < envios.size(); i++) {
			
			if(envios.get(i).getCamion().equals(placaCamion)){
				enviosCamion.add(envios.get(i));
			}
		}
		
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
