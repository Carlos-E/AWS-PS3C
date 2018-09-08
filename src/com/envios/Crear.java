package com.envios;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		DB DB = new DB();

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

		envio = DB.load(envio);

		if (envio != null) {
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "El env&iacute; ya existe");
				}
			}));
			return;
		} else {
			envio = new Envio();
		}

		if (request.getSession().getAttribute("rol").equals("cliente")) {
			envio.setUsuario(request.getSession().getAttribute("username").toString().toLowerCase());
		} else {
			envio.setUsuario(request.getParameter("cliente").toLowerCase());
		}

		envio.setFecha(fecha);

		envio.setPeso(Double.valueOf(request.getParameter("peso").toLowerCase()));
		envio.setEspacio(Double.valueOf(request.getParameter("espacio").toLowerCase()));

		envio.setEmpresa(request.getParameter("empresa").toLowerCase());
		envio.setDestino(request.getParameter("destino"));
		envio.setOrigen(request.getParameter("origen"));
		envio.setDestinoLatLong(request.getParameter("destinoLatLong").toLowerCase());
		envio.setOrigenLatLong(request.getParameter("origenLatLong").toLowerCase());
		envio.setTipo(request.getParameter("tipo").toLowerCase());
		envio.setDescripcion(request.getParameter("descripcion").toLowerCase());

		envio.setChequeoCarga(false);
		envio.setChequeoDescarga(false);
		envio.setEstado("no asignado");
		envio.setCamion("ninguno");
		envio.setTrailer("ninguno");

		// Guardar Envio en base de datos
		DB.save(envio);
		// GENERAR ENVIO

		// GENERAR REPORTE
		Reporte reporte = new Reporte();

		reporte.setUsuario(envio.getUsuario());
		reporte.setHora(fecha);
		String usuarioTag = "<a href=\"/usuarios/listar.jsp?search=" + envio.getUsuario()+ "\">"+envio.getUsuario()+"</a>,";
		String envioTag = "<a href=\"/envios/modificar.jsp?select=" + envio.getUsuario() + " : " + envio.getFecha()
				+ "\"> Modif&iacutequelo aqu&iacute.</a>";
		reporte.setNota("Nuevo env&iacute;o del cliente: " + usuarioTag + envioTag);
		reporte.setVisto(false);

		DB.save(reporte);
		// GENERAR REPORTE

		// ENVIAR CORREO
		new Email(DB.load(Usuario.class, envio.getUsuario()).getCorreo(), "PS3C - Envío Creado",
				"Su envío ha sido creado y pronto sera asignado.", envio);
		// ENVIAR CORREO

		response.setStatus(201);
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacute;n exitosa");
				put("message", "Envío creado");
			}
		}));
		return;

	}

}
