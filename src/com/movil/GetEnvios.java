package com.movil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.logica.ControladorBD;

import clases.*;

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
		String placaCamion = ControladorBD.checkPlaca(conductor);

		Camion camion = (Camion) ControladorBD.getItem("camiones", "placa", placaCamion);
		System.out.println(camion);

		ArrayList<Envio> envios = null;

		if (camion.getTipo().equals("camion")) {
			envios = ControladorBD.getShipments("camion", placaCamion);
		} else {

			@SuppressWarnings("unchecked")
			ArrayList<Trailer> listaTrailers = ControladorBD.escanearTabla("trailers");

			for (int i = 0; i < listaTrailers.size(); i++) {
				if (listaTrailers.get(i).getCamion().equals(camion.getPlaca())) {
					envios = ControladorBD.getShipments("trailer", listaTrailers.get(i).getPatente());
				}
			}
		}

		System.out.print(envios);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		response.setContentType("application/json");
		response.getWriter().print(ow.writeValueAsString(envios));
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

}
