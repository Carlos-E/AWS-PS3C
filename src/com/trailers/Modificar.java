package com.trailers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

@WebServlet("/traileres/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Trailer trailer = new clases.Trailer();
       
    public Modificar() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String patente = request.getParameter("patente").toString();
		
		trailer = (clases.Trailer) ControladorBD.getItem("trailers", "patente", request.getParameter("patente").toString());

		String origen = request.getParameter("origen").toLowerCase();
		String destino = request.getParameter("destino").toLowerCase();
		String peso = request.getParameter("peso").toLowerCase();
		String espacio = request.getParameter("espacio").toLowerCase();
		String estado = request.getParameter("estado").toLowerCase();
		String camion = request.getParameter("camion").toLowerCase();
		String tipo = request.getParameter("tipo").toLowerCase();
		String empresa = request.getParameter("empresa").toLowerCase();
		boolean cambio = false;
		
		response.setContentType("text/html");

		if (!trailer.getPeso().equals(peso)) {
			trailer.setPeso(peso);
			ControladorBD.actualizarValor("trailers", "patente", patente, "peso", peso);
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
		if (!trailer.getEmpresa().equals(empresa)) {
			trailer.setTipo(empresa);
			ControladorBD.actualizarValor("trailers", "patente", patente, "empresa", empresa);
			cambio = true;
		}
		
		if (cambio) {
			System.out.println("algo se cambio");
			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getSession().getAttribute("origin").toString());
		} else {
			System.out.println("no se cambio nada");
			com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio", request.getSession().getAttribute("origin").toString());
		}
	}

}
