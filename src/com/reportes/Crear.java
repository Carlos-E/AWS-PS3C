package com.reportes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

import clases.Reporte;

@WebServlet("/reportes/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Crear() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/404.jsp");;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Reporte reporte = new Reporte();
		
		Calendar calendar = Calendar.getInstance();		
		DecimalFormat mFormat= new DecimalFormat("00");
        
		reporte.setHora(calendar.get(Calendar.YEAR)+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH)+1))+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))+" "+mFormat.format(calendar.get(Calendar.HOUR_OF_DAY))+":"+ mFormat.format(calendar.get(Calendar.MINUTE))+":"+ mFormat.format(calendar.get(Calendar.SECOND)));
		reporte.setNota(request.getParameter("nota").toLowerCase());
		reporte.setUsuario(request.getSession().getAttribute("username").toString());
		reporte.setVisto(false);
		
		ControladorBD.registrarItem("reportes", reporte);
		
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getContextPath() + request.getContextPath() + "/movil/transportador/reportes.jsp");
	}

}
