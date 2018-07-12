package com.movil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.ControladorBD;

@WebServlet("/updateLocation")
public class updateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		try {

			String placa = ControladorBD.checkPlaca(request.getSession().getAttribute("username").toString());
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "latitud", request.getParameter("lat"));
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "longitud", request.getParameter("lng"));

			response.getWriter().print(true);

		} catch (Exception e) {
			response.getWriter().print(false);
		}

		response.getWriter().close();

	}

}
