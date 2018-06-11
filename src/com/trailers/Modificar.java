package com.trailers;

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

/**
 * Servlet implementation class modificarTrailer
 */
@WebServlet("/traileres/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Trailer trailer = new clases.Trailer();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modificar() {
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
		String patente = session.getAttribute("obj").toString();
		trailer = (clases.Trailer) ControladorBD.getItem("trailers", "trailer", patente);
		String peso = request.getParameter("peso").toLowerCase();
		String espacio = request.getParameter("espacio").toLowerCase();
		String estado = request.getParameter("estado").toLowerCase();
		String camion = request.getParameter("camion").toLowerCase();
		String tipo = request.getParameter("tipo").toLowerCase();
		boolean cambio = false;
		if (!trailer.getPeso().equals(peso)) {
			trailer.setPeso(peso);
			ControladorBD.actualizarValor("trailers", "patente", patente, "capacidad", peso);
			cambio = true;
		}
		if (!trailer.getEspacio().equals(espacio)) {
			trailer.setEspacio(espacio);
			ControladorBD.actualizarValor("trailers", "patente", patente, "espacio", espacio);
			cambio = true;
		}
		if (!trailer.getEstado().equals(estado)) {
			trailer.setEstado(estado);
			ControladorBD.actualizarValor("trailers", "patente", patente, "estado", estado);
			cambio = true;
		}
		if (!trailer.getCamion().equals(camion)) {
			trailer.setCamion(camion);
			ControladorBD.actualizarValor("trailers", "patente", patente, "camion", camion);
			cambio = true;
		}
		if (!trailer.getTipo().equals(tipo)) {
			trailer.setTipo(tipo);
			ControladorBD.actualizarValor("trailers", "patente", patente, "tipo", tipo);
			cambio = true;
		}
		if (cambio) {
			session.setAttribute("busca", "ninguno");
			System.out.println("algo se cambio");
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getSession().getAttribute("origin").toString());
		} else {
			System.out.println("no se cambio nada");
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio", request.getSession().getAttribute("origin").toString());
		}
	}

}
