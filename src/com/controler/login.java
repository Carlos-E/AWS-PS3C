package com.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clases.*;
import com.logica.ControladorBD;
import com.logica.Dibujar;

@WebServlet("/login")
public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //request.getRequestDispatcher("/login.jsp").forward(request, response);
		response.sendRedirect("/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String uname = request.getParameter("username");
		String pass = request.getParameter("password");

		if (ControladorBD.validarLogin(uname, pass)) {

			HttpSession session = request.getSession();
			session.setAttribute("username", uname);

			Usuario usuario = new DB().load(Usuario.class, uname);

			switch (usuario.getRol()) {
			case "null":
				response.sendError(400, "Sin roll");
				break;
			case "cliente":
				session.setAttribute("rol", "cliente");
				response.sendRedirect("index.jsp");
				break;
			case "empleado":
				session.setAttribute("rol", "empleado");
				response.sendRedirect("index.jsp");
				break;
			case "admin":
				session.setAttribute("rol", "admin");
				response.sendRedirect("index.jsp");
				break;
			case "conductor":
				session.setAttribute("rol", "conductor");

				String placa = new DB().checkPlaca(uname);

				if (placa != null) {
					session.setAttribute("placa", placa);
					response.sendRedirect("/movil/index.jsp");
				} else {
					Dibujar.mensaje(response.getWriter(), "El conductor no tiene ningun vehículo asignado",
							"/login.jsp");
				}

				break;
			}

		} else {
			Dibujar.mensaje(response.getWriter(), "Usuario o contraseña incorrecto", "/login.jsp");
		}
	}
}
