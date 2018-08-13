package com.envios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import clases.DB;
import clases.Envio;
import clases.Trailer;
import clases.Vehiculo;

@WebServlet("/envios/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/error.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		DB DB = new DB();

		// Buscar el objeto en la base de datos e instanciarlo
		Envio envio = new DB().load(Envio.class,request.getParameter("cliente"), request.getParameter("fecha"));
		// Si no encontro nada, soltar mensaje de error y recargar pagina
		if (envio == null) {
			com.logica.Dibujar.mensaje(response.getWriter(), "Envio no encontrado", "/envios/modificar.jsp");
			return;
		}
		// System.out.println("Objeto encontrado en tabla Envios: " + envio);
		envio.setOrigen(request.getParameter("origen"));
		envio.setDestino(request.getParameter("destino"));
		envio.setOrigenLatLong(request.getParameter("origenLatLong"));
		envio.setDestinoLatLong(request.getParameter("destinoLatLong"));
		envio.setEmpresa(request.getParameter("empresa"));
		envio.setEspacio(request.getParameter("espacio"));
		envio.setPeso(request.getParameter("peso"));
		envio.setTipo(request.getParameter("tipo"));
		envio.setDescripcion(request.getParameter("descripcion"));
		envio.setEstado(request.getParameter("estado"));
		List<Envio> envios = new DB().scan(Envio.class, new DynamoDBScanExpression());
        List<Vehiculo> vehiculos = new DB().scan(Vehiculo.class, new DynamoDBScanExpression());
        List<Trailer> trailers = new DB().scan(Trailer.class, new DynamoDBScanExpression());
        String vehiculo = request.getParameter("camion").toLowerCase();
        String trailer = request.getParameter("trailer").toLowerCase();
		
		if (vehiculo.equals("ninguno")) {
			envio.setCamion("ninguno");
		} else {
			Vehiculo vehiculoEncontrado = DB.load(Vehiculo.class,vehiculo);
			double espacioTmpV = Double.valueOf(vehiculoEncontrado.getEspacio())-Double.valueOf( request.getParameter("espacio"));
			double pesoTmpV = Double.valueOf(vehiculoEncontrado.getPeso())-Double.valueOf(request.getParameter("peso"));
			vehiculoEncontrado.setEspacio(String.valueOf(espacioTmpV));
			vehiculoEncontrado.setPeso(String.valueOf(pesoTmpV));
			DB.save(vehiculoEncontrado);
			envio.setCamion(vehiculo);
			envio.setEstado("asignado");
		}
		if (trailer.equals("ninguno")) {
			envio.setTrailer("ninguno");
		} else {
			Trailer trailerEncontrado = DB.load(Trailer.class,trailer);
			double espacioTmpT = Double.valueOf(trailerEncontrado.getEspacio())-Double.valueOf(request.getParameter("espacio"));
			double pesoTmpT = Double.valueOf(trailerEncontrado.getPeso())-Double.valueOf(request.getParameter("peso"));
			trailerEncontrado.setEspacio(String.valueOf(espacioTmpT));
			trailerEncontrado.setPeso(String.valueOf(pesoTmpT));
			DB.save(trailerEncontrado);
			envio.setTrailer(trailer);
			envio.setEstado("asignado");
		}

		new DB().save(envio);

		com.logica.Dibujar.mensaje(response.getWriter(), "Envio actualizado correctamente", "/envios/modificar.jsp");

	}
}
