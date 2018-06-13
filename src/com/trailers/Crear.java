package com.trailers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.ControladorBD;

import clases.*;

@WebServlet("/trailers/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Crear() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try {
		Empresa empresa = (Empresa) ControladorBD.getItem("empresas", "nit", request.getParameter("empresa").toLowerCase());
		Camion camion = (Camion) ControladorBD.getItem("camiones", "placa", request.getParameter("camion").toLowerCase());
		
		Trailer trailer = new clases.Trailer();
		
		trailer.setPatente(request.getParameter("patente").toLowerCase());
		trailer.setPeso(request.getParameter("peso").toLowerCase());
		trailer.setTipo(request.getParameter("tipo").toLowerCase());
		trailer.setEspacio(request.getParameter("espacio").toLowerCase());
		trailer.setEstado("disponible");
		trailer.setEmpresa(empresa.getNit());
		trailer.setCamion(camion.getPlaca());
		trailer.setOrigen(camion.getOrigen());
		trailer.setDestino(camion.getDestino());
		
		ControladorBD.registrarItem("trailers", trailer);

		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/traileres/crear.jsp");
}catch(Exception e){
	com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar crear el Trailer", request.getContextPath() + "./index.jsp");
}
	}
}
