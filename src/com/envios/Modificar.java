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


		List<Envio> envios = new DB().scan(Envio.class, new DynamoDBScanExpression());
        List<Vehiculo> vehiculos = new DB().scan(Vehiculo.class, new DynamoDBScanExpression());
        List<Trailer> trailers = new DB().scan(Trailer.class, new DynamoDBScanExpression());
        String vehiculo = request.getParameter("camion").toLowerCase();
        String trailer = request.getParameter("trailer").toLowerCase();
        
		
        switch(request.getParameter("estado")) {
        case "":
        	envio.setEstado("no asignado");
            break;
        case "asignado":
        	envio.setEstado("asignado");
            break;
        case "en trancito":
        	envio.setEstado("en tránsito");
            break;
        case "entregado":
        	envio.setEstado("entregado");
            break;
        default:
        	envio.setEstado("no asignado");
        	}
		
		if (vehiculo.equals("ninguno")) {
			envio.setCamion("ninguno");
		} else {
			for(int i=0;i<vehiculos.size();i++) {
				if(vehiculos.get(i).getPlaca().equals(vehiculo)) {
					String espacioEnvV = request.getParameter("espacio");
					String pesoEnvV = request.getParameter("peso");
					String espacioV = vehiculos.get(i).getEspacio();
					String pesoV = vehiculos.get(i).getPeso();
					double espacioTmpV = Double.valueOf(espacioV)-Double.valueOf(espacioEnvV);
					double pesoTmpV = Double.valueOf(pesoV)-Double.valueOf(pesoEnvV);
					vehiculos.get(i).setEspacio(String.valueOf(espacioTmpV));
					vehiculos.get(i).setPeso(String.valueOf(pesoTmpV));
				}
			}
			envio.setCamion(vehiculo);
			envio.setEstado("asignado");
		}
		if (trailer.equals("ninguno")) {
			envio.setTrailer("ninguno");
		} else {
			for(int i=0;i<trailers.size();i++) {
				if(trailers.get(i).getPatente().equals(trailer)) {
					String espacioEnvT = request.getParameter("espacio");
					String pesoEnvT = request.getParameter("peso");
					String espacioT = vehiculos.get(i).getEspacio();
					String pesoT = vehiculos.get(i).getPeso();
					double espacioTmpT = Double.valueOf(espacioT)-Double.valueOf(espacioEnvT);
					double pesoTmpT = Double.valueOf(pesoT)-Double.valueOf(pesoEnvT);
					trailers.get(i).setEspacio(String.valueOf(espacioTmpT));
					trailers.get(i).setPeso(String.valueOf(pesoTmpT));
				}
			}
			envio.setTrailer(trailer);
			envio.setEstado("asignado");
		}
		
		if(trailer.equals("ninguno") && vehiculo.equals("ninguno")) {
			envio.setTrailer(request.getParameter("asignado"));
		}

		new DB().save(envio);

		com.logica.Dibujar.mensaje(response.getWriter(), "Envio actualizado correctamente", "/envios/modificar.jsp");

	}
}
