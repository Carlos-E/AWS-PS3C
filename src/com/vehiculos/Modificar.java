package com.vehiculos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String placa = request.getParameter("placa").toString();
		vehiculo = (clases.Vehiculo) ControladorBD.getItem("vehiculos", "placa", placa);
		String peso = request.getParameter("peso").toLowerCase();
		String espacio = request.getParameter("espacio").toLowerCase();
		String pesoMax = request.getParameter("pesoMax").toLowerCase();
		String espacioMax = request.getParameter("espacioMax").toLowerCase();
		String conductor = null;
		
		if (request.getParameter("conductor").equals("null")) {
			conductor = request.getParameter("conductorAsignado").toLowerCase();
		} else {
			conductor = request.getParameter("conductor").toLowerCase();
		}
		
		String empresa = request.getParameter("empresa").toLowerCase();

		boolean cambio = false;
		if (!vehiculo.getPeso().equals(peso)) {
			vehiculo.setPeso(peso);
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "peso", peso);
			cambio = true;
		}
		if (!vehiculo.getEspacio().equals(espacio)) {
			vehiculo.setEspacio(espacio);
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "espacio", espacio);
			cambio = true;
		}
		if (!vehiculo.getPeso().equals(pesoMax)) {
			vehiculo.setPeso(pesoMax);
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "pesoMax", pesoMax);
			cambio = true;
		}
		if (!vehiculo.getEspacio().equals(espacioMax)) {
			vehiculo.setEspacio(espacioMax);
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "espacioMax", espacioMax);
			cambio = true;
		}
		if (!vehiculo.getUsuario().equals(conductor)) {
			vehiculo.setUsuario(conductor);
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "usuario", conductor);
			cambio = true;
		}
		if (!vehiculo.getEmpresa().equals(empresa)) {
			vehiculo.setUsuario(empresa);
			ControladorBD.actualizarValor("vehiculos", "placa", placa, "empresa", empresa);
			cambio = true;
		}
		if (cambio) {
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
					request.getContextPath() + "/vehiculos/modificar.jsp");
		} else {
			System.out.println("no se cambio nada");
			response.setContentType("text/html");

			com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio",
					request.getContextPath() + "/vehiculos/modificar.jsp");

		}
	}

}
