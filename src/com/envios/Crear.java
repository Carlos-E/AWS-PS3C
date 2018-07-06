package com.envios;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logica.ControladorBD;

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

		Envio envio = new clases.Envio();
		request.setCharacterEncoding("UTF-8");
		Calendar calendar = Calendar.getInstance();
		DecimalFormat mFormat = new DecimalFormat("00");

		String destinoLatLong = request.getParameter("destinoLatLong").toLowerCase();
		String origenLatLong = request.getParameter("origenLatLong").toLowerCase();
		String asignado = request.getParameter("asignado").toLowerCase();
		System.out.println(asignado);
		String fecha = calendar.get(Calendar.YEAR) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH) + 1)) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH))) + " "
				+ mFormat.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ mFormat.format(calendar.get(Calendar.MINUTE)) + ":" + mFormat.format(calendar.get(Calendar.SECOND));
		envio.setPeso(request.getParameter("peso").toLowerCase());
		envio.setEspacio(request.getParameter("espacio").toLowerCase());
		envio.setCamion(asignado);
		envio.setTrailer("ninguno");
		envio.setEmpresa(request.getParameter("empresa").toLowerCase());
		envio.setFecha(fecha);
		envio.setDestino(request.getParameter("destino"));
		envio.setOrigen(request.getParameter("origen"));
		envio.setDestinoLatLong(destinoLatLong);
		envio.setOrigenLatLong(origenLatLong);
		envio.setEstado("asignado");
		envio.setEspacio(request.getParameter("espacio").toLowerCase());
		envio.setTipo(request.getParameter("tipo").toLowerCase());

		if (request.getSession().getAttribute("rol").equals("cliente")) {
			envio.setUsuario(request.getSession().getAttribute("username").toString().toLowerCase());
		} else {
			envio.setUsuario(request.getParameter("cliente").toLowerCase());
		}

		envio.setChequeoCarga(false);
		envio.setChequeoDescarga(false);
		envio.setDescripcion(request.getParameter("descripcion").toLowerCase());
		ControladorBD.registrarItem("envios", envio);

		// Generar Reporte
		Reporte reporte = new Reporte();
		reporte.setHora(fecha);
		reporte.setNota("Hay un nuevo envio del cliente: " + envio.getUsuario() + " Con Fecha: " + fecha);
		reporte.setUsuario(envio.getUsuario());
		reporte.setVisto("false");
		ControladorBD.registrarItem("reportes", reporte);
		// Generar Reporte
		response.setContentType("text/html");

		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa, Reporte generado.",
				request.getContextPath() + "/envios/crear.jsp");

	}

}
