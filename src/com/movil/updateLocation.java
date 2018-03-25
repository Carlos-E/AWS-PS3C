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

		Ubicacion ubicacion = new Ubicacion();

        ubicacion.setLatitud(request.getParameter("lat"));
        ubicacion.setLongitud(request.getParameter("lng"));

        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();
        ubicacion.setHora(ts);

        ubicacion.setPlaca(ControladorBD.checkPlaca(request.getSession().getAttribute("username").toString()));
        
		boolean result = ControladorBD.putItem("ubicaciones", ubicacion.toJSON());

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		response.setContentType("application/json");
		response.getWriter().print(ow.writeValueAsString(result));
		response.getWriter().close();
	}

}
