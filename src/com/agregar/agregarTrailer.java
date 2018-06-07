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
		// https endpoint en AWS API Gateway, El futuro es ahora, oiste viejo?
		// String requestUrl =
		// "https://29mvhbvn3d.execute-api.us-east-1.amazonaws.com/dev/usuarios";
		// operacion en este caso create pq no le cambie el idioma
		// String operacion = "create";
		// String operaciones = "creates";
		// Datos para el usuario
		// String nombretabla = "usuarios";// No cambiar
		String patente = request.getParameter("patente").toLowerCase();
		String tipo = request.getParameter("tipo").toLowerCase();
		// los tipos de trailer definiran si es remolque, refrigerado o camion
		String capacidad = request.getParameter("capacidad").toLowerCase();
		// la capacidad son los metros cuadrados que se utilizan para transporte
		String espacio = capacidad;
		// el espacio es el espacio disponible despues de un embarque, el cual
		// puede aprobecharse
		String estado = "disponible";
		// los estados seran "disponible","ocupado","en carga","en descarga","en
		// raparacion"
		String nit = request.getParameter("empresa").toLowerCase();
		// la empresa a la cual pertenese el camion
		String camion = request.getParameter("camion").toLowerCase();
		;
		// es el camion que se le asigna al trailer
		String origen = request.getParameter("origen").toLowerCase();
		String destino = request.getParameter("destino").toLowerCase();
		Empresa empresa = (clases.Empresa) ControladorBD.getItem("empresas", "nit", nit);
		Camion placaCamion = (clases.Camion) ControladorBD.getItem("camiones", "placa", camion);
		trailer.setPatente(patente);
		trailer.setCapacidad(capacidad);
		trailer.setTipo(tipo);
		trailer.setEspacio(espacio);
		trailer.setEstado(estado);
		trailer.setEmpresa(empresa.getNit());
		trailer.setCamion(placaCamion.getPlaca());
		trailer.setOrigen(origen);
		trailer.setDestino(destino);
		ControladorBD.registrarItem("trailers", trailer);

		
		
		response.setContentType("text/html");
		String nextURL = request.getContextPath() + "/agregar/trailer.jsp";
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", nextURL);
		// response.sendRedirect("index.jsp");
	}

}
