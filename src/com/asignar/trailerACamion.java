package com.asignar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

/**
 * Servlet implementation class trailerACamion
 */
@WebServlet("/trailerACamion")
public class trailerACamion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public trailerACamion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
		String patente = request.getParameter("trailer").toLowerCase();
		String placa = request.getParameter("camion").toLowerCase();
		System.out.println(placa.length());
		ControladorBD.actualizarValor("camiones", "placa", placa, "estado", "asignado");
		ControladorBD.actualizarValor("trailers", "patente", patente, "camion", placa);

		response.setContentType("text/html");
		String nextURL = request.getContextPath() + "/asignar/trailer-camion.jsp";
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", nextURL);
		//response.sendRedirect("index.jsp");
		}catch(Exception e){
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar asignar un Trailer a un Camion", request.getContextPath() + "./index.jsp");
		}
	}

}
