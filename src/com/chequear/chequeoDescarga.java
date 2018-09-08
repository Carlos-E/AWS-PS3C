package com.chequear;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Email;
import clases.Envio;
import clases.Usuario;

@WebServlet("/chequeoDescarga")
public class chequeoDescarga extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public chequeoDescarga() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		try {

			DB DB = new DB();

			List<Envio> envios = DB.scan(Envio.class, new DynamoDBScanExpression());

			for (int i = 0; i < envios.size(); i++) {

				System.out.println(envios.get(i).getFecha());
				try {

					if (request.getParameter(envios.get(i).getFecha()) == null) {
						
						if(envios.get(i).isChequeoDescarga()){
							new Email(DB.load(Usuario.class, envios.get(i).getUsuario()).getCorreo(),
									"PS3C - Envío Revertido", "Hemos revertido el estado de su envío.", envios.get(i));
						}
						
						envios.get(i).setChequeoDescarga(false);
						envios.get(i).setEstado("en tránsito");

					} else {
						
						if(!envios.get(i).isChequeoDescarga()){
							new Email(DB.load(Usuario.class, envios.get(i).getUsuario()).getCorreo(),
									"PS3C - Envío Entregado", "Hemos entregado su envío.", envios.get(i));
						}
						
						envios.get(i).setChequeoDescarga(true);
						envios.get(i).setChequeoCarga(true);
						envios.get(i).setEstado("entregado");
						
					}

					System.out.println("Guardando envio");
					DB.save(envios.get(i));

				} catch (Exception e) {
					System.out.println("no encontro una fecha, algo anda mal");
				}

			}

			// com.logica.Dibujar.mensaje(response.getWriter(), "Operacion
			// Exitosa",
			// request.getContextPath() + "/chequeo/entregado.jsp");

			response.setStatus(200);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n exitosa");
					put("message", "Env&iacute;os actualizados");
				}
			}));
			return;

		} catch (Exception e) {

			// com.logica.Dibujar.mensaje(response.getWriter(),
			// "Ocurrio un error al intentar chequear los envios cargados",
			// request.getContextPath() + "/chequeo/entregado.jsp");

			response.setStatus(200);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Ocurrio un error al intentar chequear los env&iacute;os cargados");
				}
			}));
			return;

		}
	}
}
