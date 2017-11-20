package com.modificar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

/**
 * Servlet implementation class modificarMercancia
 */
@WebServlet("/modificarMercancia")
public class modificarMercancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.envio envio = new clases.envio();
	clases.camion camion = new clases.camion();
	clases.empresa empresa = new clases.empresa();
	clases.usuario usuario = new clases.usuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public modificarMercancia() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		HttpSession session = request.getSession();
		String usuario = session.getAttribute("obj1").toString();
		String fecha = session.getAttribute("obj2").toString();
		envio = (clases.envio) ControladorBD.getItem("envios", "usuario",usuario,"fecha", fecha);
		String destino = request.getParameter("destino").toLowerCase();
		String origen = request.getParameter("origen").toLowerCase();
		String espacio = request.getParameter("espacio").toLowerCase();
		String tipo = request.getParameter("tipo").toLowerCase();
		String estado = request.getParameter("estado").toLowerCase();
		String empresa = request.getParameter("empresa").toLowerCase();
		String tiempoCarga = request.getParameter("tiempoCarga").toLowerCase();
		String tiempoDescargaUsuario = request.getParameter("tiempoDescarga").toLowerCase();
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
		if (!envio.getCamion().equals(tiempoCarga)) {
			envio.setCamion(tiempoCarga);
			ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "tiempoCarga", tiempoCarga);
			cambio = true;
		}
		if (!envio.getCamion().equals(tiempoDescargaUsuario)) {
			envio.setCamion(tiempoDescargaUsuario);
			ControladorBD.actualizarValor("envios", "usuario", usuario, "fecha", fecha, "tiempoDescargaUsuario", tiempoDescargaUsuario);
			cambio = true;
		}
		if (cambio) {
			session.setAttribute("busca", "ninguno");
			System.out.println("algo se cambio");
			PrintWriter out = response.getWriter();
			String nextURL = request.getContextPath() + "/modificarDatos/mercancia.jsp";
			com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
			//response.sendRedirect("index.jsp");
		} else {
			System.out.println("no se cambio nada");
		}
	}
}
