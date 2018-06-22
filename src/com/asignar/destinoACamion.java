package com.asignar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Vehiculo;
import clases.Trailer;

/**
 * Servlet implementation class destinoACamion
 */
@WebServlet("/destinoACamion")
public class destinoACamion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public destinoACamion() {
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
		String patente =  request.getParameter("trailer").toLowerCase();
		String destino = request.getParameter("destino").toLowerCase();
		ControladorBD.actualizarValor("trailers", "patente", patente, "destino", destino);
		
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/asignar/destino-trailer.jsp");
		//response.sendRedirect("index.jsp");
	}catch(Exception e){
		com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar asignar un Destino a un Camion", request.getContextPath() + "./index.jsp");
	}
	}

}
