package com.camiones;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

import clases.*;

@WebServlet("/camion")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Camion camion = new clases.Camion();

	public Crear() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.sendError(400, "Acceso incorrecto");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String peso = request.getParameter("peso").toLowerCase();
			String espacio = request.getParameter("espacio").toLowerCase();
			if (request.getParameter("tipo").toLowerCase().equals("remolque")) {
				peso = "ninguna";
				espacio = "ninguno";
			}
			Usuario usuarioConductor = (clases.Usuario) ControladorBD.getItem("usuarios", "usuario",
					request.getParameter("conductor").toLowerCase());
			Empresa empresa = (clases.Empresa) ControladorBD.getItem("empresas", "nit",
					request.getParameter("empresa").toLowerCase());
			camion.setPlaca(request.getParameter("placa").toLowerCase());
			camion.setPeso(peso);
			camion.setTipo(request.getParameter("tipo").toLowerCase());
			camion.setEspacio(espacio);
			camion.setEstado("disponible");
			camion.setUsuario(usuarioConductor.getUsuario());
			camion.setEmpresa(empresa.getNit());
			camion.setOrigen(request.getParameter("origen").toLowerCase());
			camion.setDestino(request.getParameter("destino").toLowerCase());
			camion.setUbicacion(request.getParameter("latitud_Origen").toLowerCase() + ","
					+ request.getParameter("longitud_Origen").toLowerCase());
			ControladorBD.registrarItem("camiones", camion);
			response.setContentType("text/html");
			String nextURL = request.getContextPath() + "/camiones/crear.jsp";
			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa", nextURL);
		} catch (Exception e) {
			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar crear el Camion o Remolque",
					request.getContextPath() + "./index.jsp");
		}
	}
}
