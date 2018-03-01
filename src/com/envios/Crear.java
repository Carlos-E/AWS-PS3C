package com.envios;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

import clases.*;

/**
 * Servlet implementation class envio
 */
@WebServlet("/envios/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.envio envio = new clases.envio();
	clases.usuario cliente = new clases.usuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Crear() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Calendar calendar = Calendar.getInstance();	
		DecimalFormat mFormat= new DecimalFormat("00");
		PrintWriter out = response.getWriter();
		String nextURL = request.getContextPath() + "/envios/crear.jsp";
		HttpSession session = request.getSession();		
		String destinoLatLong = request.getParameter("latitud_Destino").toLowerCase()+","+request.getParameter("longitud_Destino").toLowerCase();
		String origenLatLong = request.getParameter("latitud_Origen").toLowerCase()+","+request.getParameter("longitud_Origen").toLowerCase();
		String estado = "iniciado";
		String camion = "ninguno";
		String trailer = "ninguno";
		String empresa = "ninguno";
		String tipo = request.getParameter("tipo").toLowerCase();
		String persona;
		String tiempoCarga = "0";
		String tiempoDescargaUsuario = "0";
		String fecha = 	calendar.get(Calendar.YEAR)+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH)+1))+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))+" "+mFormat.format(calendar.get(Calendar.HOUR_OF_DAY))+":"+ mFormat.format(calendar.get(Calendar.MINUTE))+":"+ mFormat.format(calendar.get(Calendar.SECOND));
		String espacio = request.getParameter("espacio").toLowerCase();
		String destino = request.getParameter("destino").toLowerCase();
		String origen = request.getParameter("origen").toLowerCase();
		envio.setCamion(camion);
		envio.setTrailer(trailer);
		envio.setEmpresa(empresa);
		try {
			String busqueda = ControladorBD.buscaCamion(destino, origen, espacio);
			trailer trailer1 = (trailer) ControladorBD.getItem("trailers", "patente", busqueda.substring(1, busqueda.length()));
			camion camion1 = (camion) ControladorBD.getItem("camiones", "placa", busqueda.substring(1, busqueda.length()));
			if (busqueda == "nada") {
				response.setContentType("text/html");
				com.logica.Dibujar.mensaje(out, "No Hay Rutas", nextURL);
			}else if(busqueda == "sinEspacio"){
				response.setContentType("text/html");
				com.logica.Dibujar.mensaje(out, "No hay Espacio en la ruta asignada", nextURL);			
			}else {
				if (busqueda.charAt(0) == '0') {
					envio.setTrailer(trailer1.getPatente());
					envio.setEmpresa(trailer1.getEmpresa());
					envio.setCamion(trailer1.getCamion());
				} else if (busqueda.charAt(0) == '1') {
					envio.setCamion(camion1.getPlaca());
					envio.setEmpresa(camion1.getEmpresa());
				}
				if (request.getParameter("cliente") == null) {
					persona = session.getAttribute("username").toString();
				} else {
					persona = request.getParameter("cliente").toLowerCase();
				}
				envio.setFecha(fecha);
				envio.setDestino(destino);
				envio.setOrigen(origen);
				envio.setDestinoLatLong(destinoLatLong);
				envio.setOrigenLatLong(origenLatLong);
				envio.setEstado(estado);
				envio.setEspacio(espacio);
				envio.setTipo(tipo);
				envio.setTiempoCarga(tiempoCarga);
				envio.setTiempoDescargaUsuario(tiempoDescargaUsuario);
				envio.setUsuario(persona);		
				envio.setChequeoCarga(false);
				envio.setChequeoDescarga(false);
				trailer trailer2 = (trailer) ControladorBD.getItem("trailers", "patente", envio.getTrailer());
				camion camion2 = (camion) ControladorBD.getItem("camiones", "placa", envio.getCamion());
				if (envio.getCamion() == null && envio.getTrailer() != null) {
					envio.setEmpresa(trailer2.getEmpresa());
					envio.setCamion(trailer2.getCamion());
				} else if (envio.getCamion() != null && envio.getTrailer() == null) {
					envio.setEmpresa(camion2.getEmpresa());
				}
				ControladorBD.registrarItem("envios", envio);
				response.setContentType("text/html");
				com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
			}
		} catch (Exception e) {
			System.out.println("paso algo malo en la busqueda dentro del servlet"+ e.toString());
			response.setContentType("text/html");
			com.logica.Dibujar.mensaje(out, "Ocurrio un error Codigo 0x0001", nextURL);
			return;
		}		
	}

}
