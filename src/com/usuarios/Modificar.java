package com.usuarios;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logica.ControladorBD;

@WebServlet("/usuarios/modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();

	public Modificar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {
			HttpSession session = request.getSession();
			usuario = (clases.Usuario) ControladorBD.getItem("usuarios", "usuario", request.getParameter("usuario"));
			String rol = request.getParameter("rol").toLowerCase().replaceAll(" ", "");
			String nombre = request.getParameter("nombre").toLowerCase().replaceAll(" ", "");
			String apellido = request.getParameter("apellido").toLowerCase().replaceAll(" ", "");
			String claveOld = request.getParameter("claveOld").toLowerCase().replaceAll(" ", "");
			String clave1 = request.getParameter("clave").toLowerCase().replaceAll(" ", "");
			String clave2 = request.getParameter("repita clave").toLowerCase().replaceAll(" ", "");
			String telefono = request.getParameter("telefono").toLowerCase().replaceAll(" ", "");
			String direccion = request.getParameter("direccion").toLowerCase().replaceAll(" ", "");
			String correo = request.getParameter("correo").toLowerCase().replaceAll(" ", "");
			boolean cambio = false;
			if (claveOld.isEmpty() && clave1.isEmpty() && clave2.isEmpty()) {
				if (!usuario.getRol().equals(rol)) {
					ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "rol", rol);
					cambio = true;
				}
				if (!usuario.getNombre().equals(nombre)) {
					ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "nombre",
							nombre);
					cambio = true;
				}
				if (!usuario.getApellido().equals(apellido)) {
					ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "apellido",
							apellido);
					cambio = true;
				}
				if (!usuario.getTelefono().equals(telefono)) {
					ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "telefono",
							telefono);
					cambio = true;
				}
				if (!usuario.getDireccion().equals(direccion)) {
					ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "direccion",
							direccion);
					cambio = true;
				}
				if (!usuario.getCorreo().equals(correo)) {
					ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "correo",
							correo);
					cambio = true;
				}
				if (cambio) {
					session.setAttribute("busca", "ninguno");
					System.out.println("algo se cambio");
					
					//com.logica.Dibujar.mensaje(out, "Operacion exitosa", nextURL);
					
					response.setStatus(200);
					response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
						private static final long serialVersionUID = 1L;
						{
							put("title", "Operaci&oacute;n exitosa");
							put("message", "Usuario actualizado");
						}
					}));
					return;
					
					// response.sendRedirect("index.jsp");
				} else {
					System.out.println("no se cambio nada");
				}
			} else if (!claveOld.isEmpty() || !clave1.isEmpty() || !clave2.isEmpty()) {
				if (!usuario.getClave().equals(claveOld)) {
					System.out.println("clave antigua incorrecta");
					
					//com.logica.Dibujar.mensaje(response.getWriter(), "clave antigua incorrecta");
					
					response.setStatus(400);
					response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
						private static final long serialVersionUID = 1L;
						{
							put("title", "Error del usuario");
							put("message", "Contraseña incorrecta");
						}
					}));
					return;

				} else if (!clave1.equals(clave2)) {
					System.out.println("clave nueva no coincide");
					//com.logica.Dibujar.mensaje(response.getWriter(), "clave nueva no coincide");
					
					response.setStatus(400);
					response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
						private static final long serialVersionUID = 1L;
						{
							put("title", "Error del usuario");
							put("message", "Las contraseña no coinciden");
						}
					}));
					return;
					
				} else {
					if (!usuario.getRol().equals(rol)) {
						ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "rol",
								rol);
						cambio = true;
					}
					if (!usuario.getNombre().equals(nombre)) {
						ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "nombre",
								nombre);
						cambio = true;
					}
					if (!usuario.getApellido().equals(apellido)) {
						ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"),
								"apellido", apellido);
						cambio = true;
					}
					if (!usuario.getClave().equals(clave1)) {
						ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "clave",
								clave1);
						cambio = true;
					}
					if (!usuario.getTelefono().equals(telefono)) {
						ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"),
								"telefono", telefono);
						cambio = true;
					}
					if (!usuario.getDireccion().equals(direccion)) {
						ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"),
								"direccion", direccion);
						cambio = true;
					}
					if (!usuario.getCorreo().equals(correo)) {
						ControladorBD.actualizarValor("usuarios", "usuario", request.getParameter("usuario"), "correo",
								correo);
						cambio = true;
					}
					if (cambio) {
						session.setAttribute("busca", "ninguno");
						System.out.println("algo se cambio");

//						com.logica.Dibujar.mensaje(response.getWriter(), "Operacion Exitosa",
//								request.getSession().getAttribute("origin").toString());
						
						response.setStatus(200);
						response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
							private static final long serialVersionUID = 1L;
							{
								put("title", "Operaci&oacute;n exitosa");
								put("message", "Usuario actualizado");
							}
						}));
						return;

					} else {
						System.out.println("no se cambio nada");

//						com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio",
//								request.getSession().getAttribute("origin").toString());
						
						response.setStatus(200);
						response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
							private static final long serialVersionUID = 1L;
							{
								put("title", "Operaci&oacute;n exitosa");
								put("message", "No ha habido cambio");
							}
						}));
						return;
					}
				}
			}
		} catch (Exception e) {
//			com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar modificar el Usuario",
//					request.getContextPath() + "./index.jsp");
			response.setStatus(500);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Ocurrio un error al intentar modificar el Usuario");
				}
			}));
			return;
		}
	}

}
