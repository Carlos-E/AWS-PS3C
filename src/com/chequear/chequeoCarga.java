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
//import com.logica.ControladorBD;
import com.fasterxml.jackson.databind.ObjectMapper;

import clases.DB;
import clases.Email;
import clases.Envio;
import clases.Usuario;

@WebServlet("/chequeoCarga")
public class chequeoCarga extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public chequeoCarga() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {

			DB DB = new DB();

			List<Envio> envios = DB.scan(Envio.class, new DynamoDBScanExpression());

			for (int i = 0; i < envios.size(); i++) {

				try {

					if (request.getParameter(envios.get(i).getFecha()) == null) {

						if (envios.get(i).isChequeoCarga()) {
							new Email(DB.load(Usuario.class, envios.get(i).getUsuario()).getCorreo(),
									"PS3C - Envío Revertido", "Hemos revertido el estado de su envío.", envios.get(i));
						} else {
							continue;
						}

						envios.get(i).setChequeoCarga(false);
						envios.get(i).setChequeoDescarga(false);

						envios.get(i).setEstado("asignado");

						// si no tiene camion y no tiene trailer entonces poner
						// todo falso y no asignado
						if (envios.get(i).getCamion().equals("ninguno")
								&& envios.get(i).getTrailer().equals("ninguno")) {
							envios.get(i).setChequeoDescarga(false);
							envios.get(i).setChequeoCarga(false);
							envios.get(i).setEstado("no asignado");
						}

					} else {

						if (!envios.get(i).isChequeoCarga()) {
							new Email(DB.load(Usuario.class, envios.get(i).getUsuario()).getCorreo(),
									"PS3C - Envío En Tránsito",
									"Su envío ha sido recogido y esta en tránsito hacia su destino.", envios.get(i));
						} else {

							continue;
						}

						envios.get(i).setChequeoCarga(true);
						envios.get(i).setEstado("en tránsito");

						// si no tiene camion y no tiene trailer entonces poner
						// todo falso y no asignado
						if (envios.get(i).getCamion().equals("ninguno")
								&& envios.get(i).getTrailer().equals("ninguno")) {
							envios.get(i).setChequeoDescarga(false);
							envios.get(i).setChequeoCarga(false);
							envios.get(i).setEstado("no asignado");
						}

					}

					System.out.println("Guardando envio");
					DB.save(envios.get(i));

				} catch (Exception e) {
					System.out.println("no encontro una fecha, algo anda mal");
				}

			}

			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n exitosa");
					put("message", "Env&iacute;os actualizados");
				}
			}));
			return;

		} catch (Exception e) {

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
