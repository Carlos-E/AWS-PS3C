package com.agregar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

import clases.*;

/**
 * Servlet implementation class camion
 */
@WebServlet("/camion")
public class agregarCamion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Camion camion = new clases.Camion();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregarCamion() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.sendError(400, "Acceso incorrecto");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		String placa = request.getParameter("placa").toLowerCase();
		String tipo = request.getParameter("tipo").toLowerCase();
		// los tipos de camion definiran si es remolque, refrigerado o camion
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
		String conductor = request.getParameter("conductor").toLowerCase();
		;
		// es el conductor que se le asigna al camion
		String destino = request.getParameter("destino").toLowerCase();
		String ubicacion = request.getParameter("latitud_Origen").toLowerCase() + ","
				+ request.getParameter("longitud_Origen").toLowerCase();
		String origen = request.getParameter("origen").toLowerCase();
		// destino y origen del camion respectivamente
		if (tipo.equals("remolque")) {
			capacidad = "ninguna";
			espacio = "ninguno";
		}
		Usuario usuarioConductor = (clases.Usuario) ControladorBD.getItem("usuarios", "usuario", conductor);
		Empresa empresa = (clases.Empresa) ControladorBD.getItem("empresas", "nit", nit);
		camion.setPlaca(placa);
		camion.setCapacidad(capacidad);
		camion.setTipo(tipo);
		camion.setEspacio(espacio);
		camion.setEstado(estado);
		camion.setUsuario(usuarioConductor.getUsuario());
		camion.setEmpresa(empresa.getNit());
		camion.setOrigen(origen);
		camion.setDestino(destino);
		camion.setUbicacion(ubicacion);
		System.out.println(camion);
		ControladorBD.registrarItem("camiones", camion);
		// response.sendRedirect("index.jsp");

		
		response.setContentType("text/html");

		String nextURL = request.getContextPath() + "/camiones/crear.jsp";
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", nextURL);

	}

}
