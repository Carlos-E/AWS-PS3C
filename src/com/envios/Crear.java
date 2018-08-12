package com.envios;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.*;

@WebServlet("/envios/crear")
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
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
				
		// GENERAR ENVIO
		Envio envio = new Envio();
		
		if (request.getSession().getAttribute("rol").equals("cliente")) {
			envio.setUsuario(request.getSession().getAttribute("username").toString().toLowerCase());
		} else {
			envio.setUsuario(request.getParameter("cliente").toLowerCase());
		}
		
		Calendar calendar = Calendar.getInstance();
		DecimalFormat mFormat = new DecimalFormat("00");

		String fecha = calendar.get(Calendar.YEAR) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH) + 1)) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH))) + " "
				+ mFormat.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ mFormat.format(calendar.get(Calendar.MINUTE)) + ":" + mFormat.format(calendar.get(Calendar.SECOND));
		
		envio.setFecha(fecha);

		envio.setPeso(request.getParameter("peso").toLowerCase());
		envio.setEspacio(request.getParameter("espacio").toLowerCase());
		envio.setEmpresa(request.getParameter("empresa").toLowerCase());
		envio.setDestino(request.getParameter("destino"));
		envio.setOrigen(request.getParameter("origen"));
		envio.setDestinoLatLong(request.getParameter("destinoLatLong").toLowerCase());
		envio.setOrigenLatLong(request.getParameter("origenLatLong").toLowerCase());
		envio.setEspacio(request.getParameter("espacio").toLowerCase());
		envio.setTipo(request.getParameter("tipo").toLowerCase());
		envio.setDescripcion(request.getParameter("descripcion").toLowerCase());
		
		
		envio.setChequeoCarga(false);
		envio.setChequeoDescarga(false);
		envio.setEstado("no asignado");
		envio.setCamion("ninguno");
		envio.setTrailer("ninguno");
		
		//Guardar Envio en base de datos
		new DB().save(envio);
		// GENERAR ENVIO

		
		// GENERAR REPORTE
		Reporte reporte = new Reporte();
		
		reporte.setUsuario(envio.getUsuario());
		reporte.setHora(fecha);
		reporte.setNota("Hay un nuevo envio del cliente: " + envio.getUsuario() + " Con Fecha: " + fecha);
		reporte.setVisto(false);
		
		//Guardar Reporte en base de datos
		new DB().save(reporte);
		// GENERAR REPORTE
		
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa, Reporte generado.",
				request.getContextPath() + "/envios/crear.jsp");

	}

}
