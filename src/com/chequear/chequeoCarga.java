package com.chequear;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Envio;


/**
 * Servlet implementation class chequeoDescarga
 */
@WebServlet("/chequeoCarga")
public class chequeoCarga extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chequeoCarga() {
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
		ArrayList<Envio> envios = ControladorBD.escanearTabla("envios");
		String chequeoCarga = "jeje saludos";
		for(int i=0;i<envios.size();i++){		
			System.out.println(envios.get(i).getFecha());
			try{
				if(request.getParameter(envios.get(i).getFecha())==null){
					chequeoCarga="false";
				}else {
					chequeoCarga="true";
				}
				ControladorBD.actualizarValor("envios", "usuario", envios.get(i).getUsuario(), "fecha", envios.get(i).getFecha(), "chequeoCarga", chequeoCarga);
			}catch (Exception e) {
				System.out.println("no encontro una fecha, algo anda mal");				
			}
		}
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "chequeo/chequeoDeCarga.jsp");
		}catch(Exception e){
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar chequear los envios cargados", request.getContextPath() + "./index.jsp");
		}
	}
}
