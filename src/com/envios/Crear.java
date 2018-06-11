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
//		request.setCharacterEncoding("UTF-8");
//		
//		Calendar calendar = Calendar.getInstance();	
//		DecimalFormat mFormat= new DecimalFormat("00");
//		PrintWriter out = response.getWriter();
//		String nextURL = request.getContextPath() + "/envios/crear.jsp";
//		HttpSession session = request.getSession();		
//		String destinoLatLong = request.getParameter("latitud_Destino").toLowerCase()+","+request.getParameter("longitud_Destino").toLowerCase();
//		String origenLatLong = request.getParameter("latitud_Origen").toLowerCase()+","+request.getParameter("longitud_Origen").toLowerCase();
//		String estado = "no asignado";
//		String camion = "ninguno";
//		String trailer = "ninguno";
//		String empresa = request.getParameter("empresa").toLowerCase();
//		String tipo = request.getParameter("tipo").toLowerCase();
//		String persona;
//		String fecha = 	calendar.get(Calendar.YEAR)+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH)+1))+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))+" "+mFormat.format(calendar.get(Calendar.HOUR_OF_DAY))+":"+ mFormat.format(calendar.get(Calendar.MINUTE))+":"+ mFormat.format(calendar.get(Calendar.SECOND));
//		String peso = request.getParameter("peso").toLowerCase();
//		String espacio = request.getParameter("espacio").toLowerCase();
//		String destino = request.getParameter("destino").toLowerCase();
//		String origen = request.getParameter("origen").toLowerCase();
//		envio.setPeso(peso);
//		envio.setEspacio(espacio);
//		envio.setCamion(camion);
//		envio.setTrailer(trailer);
//		envio.setEmpresa(empresa);
//		try {
//			String busqueda = ControladorBD.buscaCamion(destino, origen, espacio);
//			Trailer trailer1 = (Trailer) ControladorBD.getItem("trailers", "patente", busqueda.substring(1, busqueda.length()));
//			Camion camion1 = (Camion) ControladorBD.getItem("camiones", "placa", busqueda.substring(1, busqueda.length()));
//			if (busqueda == "nada") {
//				response.setContentType("text/html");
//				com.logica.Dibujar.mensaje(out, "No Hay Rutas", nextURL);
//			}else if(busqueda == "sinEspacio"){
//				response.setContentType("text/html");
//				com.logica.Dibujar.mensaje(out, "No hay Espacio en la ruta asignada", nextURL);			
//			}else {
//				if (busqueda.charAt(0) == '0') {
//					envio.setTrailer(trailer1.getPatente());
//					envio.setEmpresa(trailer1.getEmpresa());
//					envio.setCamion(trailer1.getCamion());
//					double resultado = Double.parseDouble(trailer1.getEspacio())-Double.parseDouble(envio.getEspacio());
//					ControladorBD.actualizarValor("trailers", "patente", envio.getCamion(), "espacio", String.valueOf(resultado));
//				} else if (busqueda.charAt(0) == '1') {
//					envio.setCamion(camion1.getPlaca());
//					envio.setEmpresa(camion1.getEmpresa());
//					double resultado = Double.parseDouble(camion1.getEspacio())-Double.parseDouble(envio.getEspacio());
//					ControladorBD.actualizarValor("camiones", "placa", envio.getCamion(), "espacio", String.valueOf(resultado));
//				}
//				if (request.getParameter("cliente") == null) {
//					persona = session.getAttribute("username").toString();
//				} else {
//					persona = request.getParameter("cliente").toLowerCase();
//				}
//				envio.setFecha(fecha);
//				envio.setDestino(destino);
//				envio.setOrigen(origen);
//				envio.setDestinoLatLong(destinoLatLong);
//				envio.setOrigenLatLong(origenLatLong);
//				envio.setEstado(estado);
//				envio.setEspacio(espacio);
//				envio.setTipo(tipo);
//				envio.setUsuario(persona);		
//				envio.setChequeoCarga(false);
//				envio.setChequeoDescarga(false);
//				Trailer trailer2 = (Trailer) ControladorBD.getItem("trailers", "patente", envio.getTrailer());
//				Camion camion2 = (Camion) ControladorBD.getItem("camiones", "placa", envio.getCamion());
//				if (envio.getCamion() == null && envio.getTrailer() != null) {
//					envio.setEmpresa(trailer2.getEmpresa());
//					envio.setCamion(trailer2.getCamion());
//				} else if (envio.getCamion() != null && envio.getTrailer() == null) {
//					envio.setEmpresa(camion2.getEmpresa());
//				}			
//				ControladorBD.registrarItem("envios", envio);
//				response.setContentType("text/html");
//				com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
//			}
//		} catch (Exception e) {
//			System.out.println("ENVIOS paso algo malo en la busqueda dentro del servlet"+ e.toString());
//			response.setContentType("text/html");
//			com.logica.Dibujar.mensaje(out, "Ocurrio un error Codigo 0x0001", nextURL);
//			return;
//		}
		
		Envio envio = new clases.Envio();
		
		request.setCharacterEncoding("UTF-8");
		
		Calendar calendar = Calendar.getInstance();	
		DecimalFormat mFormat= new DecimalFormat("00");
		
		String destinoLatLong = request.getParameter("latitud_Destino").toLowerCase()+","+request.getParameter("longitud_Destino").toLowerCase();
		String origenLatLong = request.getParameter("latitud_Origen").toLowerCase()+","+request.getParameter("longitud_Origen").toLowerCase();
		String fecha = 	calendar.get(Calendar.YEAR)+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH)+1))+"-"+mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))+" "+mFormat.format(calendar.get(Calendar.HOUR_OF_DAY))+":"+ mFormat.format(calendar.get(Calendar.MINUTE))+":"+ mFormat.format(calendar.get(Calendar.SECOND));
		
		envio.setPeso(request.getParameter("peso").toLowerCase());
		envio.setEspacio(request.getParameter("espacio").toLowerCase());
		envio.setCamion("ninguno");
		envio.setTrailer("ninguno");
		envio.setEmpresa(request.getParameter("empresa").toLowerCase());
		envio.setFecha(fecha);
		envio.setDestino(request.getParameter("destino").toLowerCase());
		envio.setOrigen(request.getParameter("origen").toLowerCase());
		envio.setDestinoLatLong(destinoLatLong);
		envio.setOrigenLatLong(origenLatLong);
		envio.setEstado("no asignado");
		envio.setEspacio(request.getParameter("espacio").toLowerCase());
		envio.setTipo(request.getParameter("tipo").toLowerCase());
		envio.setUsuario(request.getSession().getAttribute("username").toString());		
		envio.setChequeoCarga(false);
		envio.setChequeoDescarga(false);
		
		ControladorBD.registrarItem("envios", envio);
		//Generar Reporte
		Reporte reporte = new Reporte();
		reporte.setHora(fecha);
		reporte.setNota("Hay un nuevo envio del cliente: "+envio.getUsuario()+"");
		reporte.setUsuario(envio.getUsuario());
		reporte.setVisto(false);
		ControladorBD.registrarItem("reportes", reporte);
		//Generar Reporte

		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa, Reporte generado.", request.getContextPath() + "/envios/crear.jsp");

	}

}
