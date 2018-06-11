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
<<<<<<< HEAD:src/com/trailers/Crear.java

=======
		// TODO Auto-generated method stub
		// doGet(request, response);
		System.out.println("eeeeeeeeeeeeeeeeeeeeeee");
>>>>>>> 243bf0ced42abc43c2a55d83f350ca14d0072389:src/com/trailers/agregarTrailer.java
		Empresa empresa = (clases.Empresa) ControladorBD.getItem("empresas", "nit", request.getParameter("empresa").toLowerCase());
		System.out.println("eeeeeeeeeeeeeeeeeeeeeee!!");
		Camion camion = (clases.Camion) ControladorBD.getItem("camiones", "placa", request.getParameter("camion").toLowerCase());
		System.out.println("epaaaaaaaaaaa colombia");
		trailer.setPatente(request.getParameter("patente").toLowerCase());
		trailer.setPeso(request.getParameter("peso").toLowerCase());
		trailer.setTipo(request.getParameter("tipo").toLowerCase());
		trailer.setEspacio(request.getParameter("espacio").toLowerCase());
		trailer.setEstado("disponible");
		trailer.setEmpresa(empresa.getNit());
<<<<<<< HEAD:src/com/trailers/Crear.java
		trailer.setCamion(placaCamion.getPlaca());
		trailer.setOrigen(request.getParameter("origen").toLowerCase());
		trailer.setDestino(request.getParameter("destino").toLowerCase());
		
		ControladorBD.registrarItem("trailers", trailer);	
=======
		trailer.setCamion(camion.getPlaca());
		trailer.setOrigen(camion.getOrigen());
		trailer.setDestino(camion.getDestino());
		System.out.println("veamos si salgo por ahi");
		ControladorBD.registrarItem("trailers", trailer);
		System.out.println("nop esto no es");
>>>>>>> 243bf0ced42abc43c2a55d83f350ca14d0072389:src/com/trailers/agregarTrailer.java
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "/traileres/crear.jsp");
	}
}
