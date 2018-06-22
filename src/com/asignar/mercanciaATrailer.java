package com.asignar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Vehiculo;
import clases.Envio;
import clases.Trailer;

/**
 * Servlet implementation class mercanciaATrailer
 */
@WebServlet("/mercanciaATrailer")
public class mercanciaATrailer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mercanciaATrailer() {
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
		response.setContentType("text/html");
		
		String patente =  request.getParameter("patente").toLowerCase();
		String id = request.getParameter("mercancia").toLowerCase();
		String[] frag = id.split(" : ");
		String frag1 = frag[0], frag2 = frag[1];
		Trailer trailer = (Trailer) ControladorBD.getItem("trailers", "patente", patente);
		Envio envio = (Envio) ControladorBD.getItem("envios", "usuario", frag1, "fecha", frag2);
		double suma = Double.parseDouble(trailer.getEspacio()) - Double.parseDouble(envio.getEspacio());
		if(suma < 0){
			String nextURL = request.getContextPath() + "/asignar/mercancia-trailer.jsp";
			com.logica.Dibujar.mensaje(response.getWriter(), "Error el la asignacion, revise los volumenes", nextURL);
		}
		ControladorBD.actualizarValor("trailers", "patente", patente, "espacio", String.valueOf(suma));
		ControladorBD.actualizarValor("trailers", "patente", patente, "estado", "asignado");
		ControladorBD.actualizarValor("envios", "usuario", frag1, "fecha", frag2, "trailer", patente);
		ControladorBD.actualizarValor("envios", "usuario", frag1, "fecha", frag2, "estado", "asignado");
		String nextURL = request.getContextPath() + "/asignar/mercancia-trailer.jsp";
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", nextURL);
		//response.sendRedirect("index.jsp");
		}catch(Exception e){
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar asignar una Mercancia a un Trailer", request.getContextPath() + "./index.jsp");
		}
	}

}
