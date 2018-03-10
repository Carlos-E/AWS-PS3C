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

import clases.envio;

/**
 * Servlet implementation class chequeoDescarga
 */
@WebServlet("/chequeoDescarga")
public class chequeoDescarga extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chequeoDescarga() {
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
		ArrayList<envio> envios = ControladorBD.escanearTabla("envios");
		String chequeoDescarga = "jeje saludos";
		System.out.println(chequeoDescarga);
		for(int i=0;i<envios.size();i++){			
			try{
				chequeoDescarga = request.getParameter(envios.get(i).getFecha()).toLowerCase();
				if(chequeoDescarga.equals("true")){
					ControladorBD.actualizarValor("envios", "usuario", envios.get(i).getUsuario(), "fecha", envios.get(i).getFecha(), "chequeoDescarga", chequeoDescarga);
				}
			}catch (Exception e) {
				System.out.println("no encontro una fecha, algo anda mal");				
			}
		}
		PrintWriter out = response.getWriter();
		String nextURL = request.getContextPath() + "chequeo/chequeoDeDescarga.jsp";
		com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
	}

}
