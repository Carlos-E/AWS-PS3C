package com.asignar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Camion;
import clases.Envio;
import clases.Trailer;

/**
 * Servlet implementation class mercanciaACamion
 */
@WebServlet("/mercanciaACamion")
public class mercanciaACamion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mercanciaACamion() {
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
		String placa =  request.getParameter("camion").toLowerCase();
		String id = request.getParameter("mercancia").toLowerCase();
		String[] frag = id.split(" : ");
		String frag1 = frag[0], frag2 = frag[1];
		Camion camion = (Camion) ControladorBD.getItem("camiones", "placa", placa);
		Envio envio = (Envio) ControladorBD.getItem("envios", "usuario", frag1, "fecha", frag2);
		double suma = Double.parseDouble(camion.getEspacio()) - Double.parseDouble(envio.getEspacio());
		System.out.println(Double.parseDouble(camion.getEspacio()) +" - "+ Double.parseDouble(envio.getEspacio()) +" = "+ suma);
		if(suma < 0){
			PrintWriter out = response.getWriter();
			String nextURL = request.getContextPath() + "/asignar/mercancia-camion.jsp";
			com.logica.Dibujar.mensaje(out, "Error el la asignacion, revise los volumenes", nextURL);
		}else{
		ControladorBD.actualizarValor("camiones", "placa", placa, "espacio", String.valueOf(suma));
		ControladorBD.actualizarValor("camiones", "placa", placa, "estado", "asignado");
		ControladorBD.actualizarValor("envios", "usuario", frag1, "fecha", frag2, "camion", placa);
		ControladorBD.actualizarValor("envios", "usuario", frag1, "fecha", frag2, "estado", "asignado");
		PrintWriter out = response.getWriter();
		String nextURL = request.getContextPath() + "/asignar/mercancia-camion.jsp";
		com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
		//response.sendRedirect("index.jsp");
		}
	}

}
