package com.reportes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

import clases.Reporte;
import clases.Usuario;

/**
 * Servlet implementation class reportarProblema
 */
@WebServlet("/reportarProblema")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Crear() {
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
		Reporte reporte = new Reporte();
		HttpSession session = request.getSession();
		String nota = request.getParameter("nota").toLowerCase();
		String empleado = session.getAttribute("username").toString();
		Calendar calendar = Calendar.getInstance();		
		DecimalFormat mFormat= new DecimalFormat("00");
        reporte.setHora(calendar.get(Calendar.YEAR)+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH)+1))+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))+" "+mFormat.format(calendar.get(Calendar.HOUR_OF_DAY))+":"+ mFormat.format(calendar.get(Calendar.MINUTE))+":"+ mFormat.format(calendar.get(Calendar.SECOND)));
		reporte.setNota(nota);
		reporte.setUsuario(empleado);
		ControladorBD.registrarItem("reportes", reporte);
		PrintWriter out = response.getWriter();
		String nextURL = request.getContextPath() + "index.jsp";
		com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
		//response.sendRedirect("index.jsp");
	}

}
