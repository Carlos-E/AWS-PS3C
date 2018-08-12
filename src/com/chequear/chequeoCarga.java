package com.chequear;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.logica.ControladorBD;

import clases.DB;
import clases.Envio;

@WebServlet("/chequeoCarga")
public class chequeoCarga extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public chequeoCarga() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Envio> envios = new DB().scan(Envio.class, new DynamoDBScanExpression());
			String chequeoCarga = "jeje saludos";
			
			for (int i = 0; i < envios.size(); i++) {
				
				System.out.println(envios.get(i).getFecha());
				try {
					
					if (request.getParameter(envios.get(i).getFecha()) == null) {
						chequeoCarga = "false";
					} else {
						chequeoCarga = "true";
					}
					ControladorBD.actualizarValor("envios", "usuario", envios.get(i).getUsuario(), "fecha",
							envios.get(i).getFecha(), "chequeoCarga", chequeoCarga);
				
				} catch (Exception e) {
					System.out.println("no encontro una fecha, algo anda mal");
				}
				
			}

			response.setContentType("text/html");
			com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
					request.getContextPath() + "chequeo/chequeoDeCarga.jsp");

		} catch (Exception e) {
			com.logica.Dibujar.mensaje(response.getWriter(),
					"Ocurrio un error al intentar chequear los envios cargados",
					request.getContextPath() + "./index.jsp");
		}
	}
}
