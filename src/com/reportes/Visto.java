package com.reportes;

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
import clases.Reporte;

@WebServlet("/reportes/visto")
public class Visto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Visto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Reporte> reportes = new DB().scan(Reporte.class, new DynamoDBScanExpression());

		String visto = "jeje saludos";

		for (int i = 0; i < reportes.size(); i++) {
			try {
				if (request.getParameter(reportes.get(i).getHora()) == null) {
					visto = "false";
				} else {
					visto = "true";
				}
				ControladorBD.actualizarValor("reportes", "usuario", reportes.get(i).getUsuario(), "hora",
						reportes.get(i).getHora(), "visto", visto);
			} catch (Exception e) {
				System.out.println("no encontro una fecha, algo anda mal");
			}
		}

		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
				request.getContextPath() + "/envios/reportes.jsp");

	}

}
