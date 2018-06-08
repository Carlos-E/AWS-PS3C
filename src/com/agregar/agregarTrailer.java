package com.agregar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Camion;
import clases.Empresa;

/**
 * Servlet implementation class trailer
 */
@WebServlet("/trailer")
public class agregarTrailer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Trailer trailer = new clases.Trailer();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregarTrailer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		Empresa empresa = (clases.Empresa) ControladorBD.getItem("empresas", "nit", request.getParameter("empresa").toLowerCase());
		Camion placaCamion = (clases.Camion) ControladorBD.getItem("camiones", "placa", request.getParameter("camion").toLowerCase());
		trailer.setPatente(request.getParameter("patente").toLowerCase());
		trailer.setPeso(request.getParameter("peso").toLowerCase());
		trailer.setTipo(request.getParameter("tipo").toLowerCase());
		trailer.setEspacio(request.getParameter("espacio").toLowerCase());
		trailer.setEstado("disponible");
		trailer.setEmpresa(empresa.getNit());
		trailer.setCamion(placaCamion.getPlaca());
		trailer.setOrigen(request.getParameter("origen").toLowerCase());
		trailer.setDestino(request.getParameter("destino").toLowerCase());
		ControladorBD.registrarItem("trailers", trailer);	
		response.setContentType("text/html");
		String nextURL = request.getContextPath() + "/agregar/trailer.jsp";
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", nextURL);
	}

}
