package com.envios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

@WebServlet("/envios/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Envio envio = new clases.Envio();
	clases.Camion camion = new clases.Camion();
	clases.Empresa empresa = new clases.Empresa();
	clases.Usuario usuario = new clases.Usuario();

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{		
			request.setCharacterEncoding("UTF-8");
			String usuario = request.getParameter("cliente").toString();
			String fecha = request.getParameter("fecha").toString();
			envio = (clases.Envio) ControladorBD.getItem("envios", "usuario", usuario, "fecha", fecha);
			String destino = request.getParameter("destino").toLowerCase();
			String origen = request.getParameter("origen").toLowerCase();
			String espacio = request.getParameter("espacio").toLowerCase();
			String tipo = request.getParameter("tipo").toLowerCase();
			String estado = request.getParameter("estado").toLowerCase();
			String empresa = request.getParameter("empresa").toLowerCase();
			String descripcion = request.getParameter("descripcion").toLowerCase();
			String camion = request.getParameter("camion").toLowerCase();
			//String tiempoCarga = request.getParameter("tiempoCarga").toLowerCase();
			//String tiempoDescargaUsuario = request.getParameter("tiempoDescarga").toLowerCase();		
			String destinoLatLong = request.getParameter("latitud_Destino").toLowerCase()+","+request.getParameter("longitud_Destino").toLowerCase();
			String origenLatLong = request.getParameter("latitud_Origen").toLowerCase()+","+request.getParameter("longitud_Origen").toLowerCase();	
			boolean cambio = false;	
			if (!envio.getDestino().equals(destino)) {
				envio.setDestino(destino);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "destino", destino);
				cambio = true;
			}
			if (!envio.getOrigen().equals(origen)) {
				envio.setOrigen(origen);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "origen", origen);
				cambio = true;
			}
			if (!envio.getEspacio().equals(espacio)) {
				envio.setEspacio(espacio);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "espacio", espacio);
				cambio = true;
			}
			if (!envio.getTipo().equals(tipo)) {
				envio.setTipo(tipo);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "tipo", tipo);
				cambio = true;
			}
			if (!envio.getEstado().equals(estado)) {
				envio.setEstado(estado);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "estado", estado);
				cambio = true;
			}
			if (!envio.getUsuario().equals(usuario)) {
				envio.setUsuario(usuario);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "usuario", usuario);
				cambio = true;
			}
			if (!envio.getEmpresa().equals(empresa)) {
				envio.setEmpresa(empresa);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "empresa", empresa);
				cambio = true;
			}
			if (!envio.getDescripcion().equals(descripcion)) {
				envio.setDescripcion(descripcion);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "descripcion", descripcion);
				cambio = true;
			}
			if (!envio.getCamion().equals(camion)) {
				envio.setCamion(camion);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "camion", camion);
				cambio = true;
			}
			if (!envio.getDestinoLatLong().equals(destinoLatLong)) {
				envio.setDestinoLatLong(destinoLatLong);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "destinoLatLong", destinoLatLong);
				cambio = true;
			}
			if (!envio.getOrigenLatLong().equals(origenLatLong)) {
				envio.setOrigenLatLong(origenLatLong);
				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "origenLatLong", origenLatLong);
				cambio = true;
			}
//			if (!envio.getCamion().equals(tiempoCarga)) {
//				envio.setCamion(tiempoCarga);
//				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "tiempoCarga", tiempoCarga);
//				cambio = true;
//			}
//			if (!envio.getCamion().equals(tiempoDescargaUsuario)) {
//				envio.setCamion(tiempoDescargaUsuario);
//				ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "tiempoDescargaUsuario",
//						tiempoDescargaUsuario);
//				cambio = true;
//			}
			if (cambio) {
				System.out.println("algo se cambio");
				response.setContentType("text/html");

				com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", request.getRequestURL() + ".jsp");
			} else {
				System.out.println("no se cambio nada");
				response.setContentType("text/html");
				com.logica.Dibujar.mensaje(response.getWriter(), "No se cambio nada", request.getRequestURL() + ".jsp");
			}
		}catch(Exception e){
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar modificar el Envio", request.getContextPath() + "/envios/crear.jsp");
		}
	}
}
