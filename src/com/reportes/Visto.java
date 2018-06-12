package com.reportes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Reporte;

/**
 * Servlet implementation class Visto
 */
@WebServlet("/reportes/visto")
public class Visto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Visto() {
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
		ArrayList<Reporte> reportes = ControladorBD.escanearTabla("reportes");
		String visto = "jeje saludos";
		for(int i=0;i<reportes.size();i++){		
			try{
				visto = request.getParameter(reportes.get(i).getHora()).toLowerCase();
				if(visto.equals("true")){
					ControladorBD.actualizarValor("reportes", "usuario", reportes.get(i).getUsuario(), "hora", reportes.get(i).getHora(), "visto", visto);
				}else if(visto.equals("false")) {
					
				}
			}catch (Exception e) {
				System.out.println("no encontro una fecha, algo anda mal");				
			}
		}
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + "envios/reportes.jsp");
	}

}
