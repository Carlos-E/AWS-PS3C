package com.trailers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.ControladorBD;

import clases.Camion;
import clases.Empresa;

@WebServlet("/trailers/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Trailer trailer = new clases.Trailer();

	public Crear() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/traileres/crear.jsp");
	}

}
