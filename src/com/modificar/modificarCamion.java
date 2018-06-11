package com.modificar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

import clases.Camion;
import clases.Usuario;

/**
 * Servlet implementation class modificarCamion
 */
@WebServlet("/camiones/modificar")
public class modificarCamion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Camion camion = new Camion();
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarCamion() {
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
		HttpSession session = request.getSession();	
		String placa = request.getParameter("placa").toString();
		camion = (clases.Camion) ControladorBD.getItem("camiones", "placa", placa);
		String peso = request.getParameter("peso").toLowerCase();
		String espacio = request.getParameter("espacio").toLowerCase();
		String estado = request.getParameter("estado").toLowerCase();
		String conductor = request.getParameter("conductor").toLowerCase();
		boolean cambio = false;
		if (!camion.getPeso().equals(peso)) {
			camion.setPeso(peso);
			ControladorBD.actualizarValor("camiones", "placa", placa, "capacidad", peso);
			cambio = true;
		}
		if (!camion.getEspacio().equals(espacio)) {
			camion.setEspacio(espacio);
			ControladorBD.actualizarValor("camiones", "placa", placa, "espacio", espacio);
			cambio = true;
		}
		if (!camion.getEstado().equals(estado)) {
			camion.setEstado(estado);
			ControladorBD.actualizarValor("camiones", "placa", placa, "estado", estado);
			cambio = true;
		}
		if (!camion.getUsuario().equals(conductor)) {
			camion.setUsuario(conductor);
			ControladorBD.actualizarValor("camiones", "placa", placa, "usuario", conductor);
			cambio = true;
		}
		if (cambio) {
			session.setAttribute("busca", "ninguno");
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getSession().getAttribute("origin").toString());
		} else {
			System.out.println("no se cambio nada");
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio", request.getSession().getAttribute("origin").toString());

		}		
	}

}
