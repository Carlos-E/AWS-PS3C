package com.modificar;

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
@WebServlet("/trueBuscar")
public class trueBuscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public trueBuscar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object objeto = new Object();

		switch (request.getParameter("modificar")) {
		case "envios":
			objeto = (Envio) com.logica.ControladorBD.getItem(request.getParameter("tabla"), "usuario",
					request.getParameter("usuario"), "fecha", request.getParameter("fecha"));
			break;
		case "usuarios":
			;
			break;
		case "empresas":
			;
			break;
		case "camiones":
			;
			break;
		case "traileres":
			;
			break;
		}

//		if (request.getParameter("modificar").equals("envios")) {
//			objeto = (envio) com.logica.ControladorBD.getItem(request.getParameter("tabla"), "usuario",
//					request.getParameter("usuario"), "fecha", request.getParameter("fecha"));
//		}

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(objeto);

		response.setContentType("application/json");
		response.getWriter().print(json);
		response.getWriter().close();

	}

}
