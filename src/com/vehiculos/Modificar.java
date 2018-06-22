package com.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

import clases.Vehiculo;

@WebServlet("/vehiculos/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Vehiculo vehiculo = new Vehiculo();

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			String placa = request.getParameter("placa").toString();
			vehiculo = (clases.Vehiculo) ControladorBD.getItem("vehiculos", "placa", placa);
			String peso = request.getParameter("peso").toLowerCase();
			String espacio = request.getParameter("espacio").toLowerCase();
			String estado = request.getParameter("estado").toLowerCase();
			String conductor = request.getParameter("conductor").toLowerCase();
			boolean cambio = false;
			if (!vehiculo.getPeso().equals(peso)) {
				vehiculo.setPeso(peso);
				ControladorBD.actualizarValor("vehiculos", "placa", placa, "capacidad", peso);
				cambio = true;
			}
			if (!vehiculo.getEspacio().equals(espacio)) {
				vehiculo.setEspacio(espacio);
				ControladorBD.actualizarValor("vehiculos", "placa", placa, "espacio", espacio);
				cambio = true;
			}
			if (!vehiculo.getEstado().equals(estado)) {
				vehiculo.setEstado(estado);
				ControladorBD.actualizarValor("vehiculos", "placa", placa, "estado", estado);
				cambio = true;
			}
			if (!vehiculo.getUsuario().equals(conductor)) {
				vehiculo.setUsuario(conductor);
				ControladorBD.actualizarValor("vehiculos", "placa", placa, "usuario", conductor);
				cambio = true;
			}
			if (cambio) {
				session.setAttribute("busca", "ninguno");
				response.setContentType("text/html");

				com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
						request.getContextPath() + "/vehiculos/modificar.jsp");
			} else {
				System.out.println("no se cambio nada");
				response.setContentType("text/html");

				com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio",
						request.getContextPath() + "/vehiculos/modificar.jsp");

			}
		} catch (Exception e) {
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar modificar el vehiculo",
					request.getContextPath() + "/vehiculos/modificar.jsp");
		}
	}

}
