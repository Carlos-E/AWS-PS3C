package com.trailers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.ControladorBD;

@WebServlet("/trailer/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public Eliminar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ControladorBD.borrarItem("trailers", "patente", request.getParameter("patente").toString());
			response.setContentType("text/html");
			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
					request.getContextPath() + "/traileres/eliminar.jsp");
		} catch (Exception e) {
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar eliminar el Trailer",
					request.getContextPath() + "/traileres/eliminar.jsp");
		}
	}

}
