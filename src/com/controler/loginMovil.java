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

/**
 * Servlet implementation class login
 */
@WebServlet("/loginMovil")
public class loginMovil extends HttpServlet {

	clases.usuario usuario;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginMovil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        request.getRequestDispatcher("/movil/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		if (ControladorBD.validarLogin(uname, pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			usuario = (clases.usuario) ControladorBD.getItem("usuarios", "usuario", uname);
			switch (usuario.getRol()) {
			case "null":
				response.sendError(400, "Sin roll");
				break;
			case "cliente":
				session.setAttribute("rol", "cliente");
				response.sendRedirect("/movil/index.jsp");
				break;
			case "empleado":
				session.setAttribute("rol", "empleado");
				response.sendRedirect("/movil/index.jsp");
				break;
			case "admin":
				session.setAttribute("rol", "admin");
				response.sendRedirect("/movil/index.jsp");
				break;
			}

		} else {
			
			PrintWriter out = response.getWriter();
			String nextURL = request.getContextPath() + "/movil/index.jsp";
			response.setContentType("text/html");//
			com.logica.Dibujar.mensaje(out, "Usuario o contraseña incorrecto", nextURL);
		}
	}
}
