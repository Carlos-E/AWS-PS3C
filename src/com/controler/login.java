package com.controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import clases.*;
import com.logica.ControladorBD;

@WebServlet("/login")
public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	clases.Usuario usuario;

	public login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		if (ControladorBD.validarLogin(uname, pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			usuario = (clases.Usuario) ControladorBD.getItem("usuarios", "usuario", uname);
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
				session.setAttribute("placa", new DB().checkPlaca(uname));
				response.sendRedirect("/movil/index.jsp");
				break;
			}

		} else {

			PrintWriter out = response.getWriter();
			String nextURL = request.getContextPath() + "/login.jsp";
			response.setContentType("text/html");//
			com.logica.Dibujar.mensaje(out, "Usuario o contraseña incorrecto", nextURL);
		}
	}
}
