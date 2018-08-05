package com.reportes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.DB;
import clases.Reporte;

@WebServlet("/reportes/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Crear() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		//Instancio el calendario y hago la hora
		Calendar calendar = Calendar.getInstance();
		DecimalFormat mFormat = new DecimalFormat("00");
		String hora = calendar.get(Calendar.YEAR) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH) + 1)) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH))) + " "
				+ mFormat.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ mFormat.format(calendar.get(Calendar.MINUTE)) + ":" + mFormat.format(calendar.get(Calendar.SECOND));

		Reporte reporte = new Reporte();
		reporte.setUsuario(request.getSession().getAttribute("username").toString());
		reporte.setHora(hora);
		reporte.setNota(request.getParameter("nota").toLowerCase());
		reporte.setVisto(false);
		
		//Guardar en la base de datos
		new DB().save(reporte);
		
		com.logica.Dibujar.mensaje(response.getWriter(), "Reporte Creado",
				request.getContextPath() + "/movil/transportador/reportes.jsp");

	}

}
