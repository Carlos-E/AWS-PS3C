package com.modificar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import com.logica.ControladorBD;

import clases.usuario;

/**
 * Servlet implementation class buscar
 */
@WebServlet("/buscar")
public class buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public buscar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		HttpSession session = request.getSession();
		String nit = "nada", placa = "nada", patente = "nada", usuario = "nada", usuarioEnvio = "nada";
		String destinoTrailer = "nada", mercanciaTrailer = "nada", trailerCamion = "nada";
		String chequeo = "nada";
		String busqueda = "nada";
		try {
			destinoTrailer = request.getParameter("destino-trailer");
			destinoTrailer.replaceAll(" ", "");
			if (!destinoTrailer.equals("nada")) {
				session.setAttribute("busca", "destino-trailer");
				session.setAttribute("obj", destinoTrailer);
				response.sendRedirect("asignar/destino-trailer.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es camion");
		}
		try {
			mercanciaTrailer = request.getParameter("mercancia-trailer");
			mercanciaTrailer.replaceAll(" ", "");
			if (!mercanciaTrailer.equals("nada")) {
				session.setAttribute("busca", "mercancia-trailer");
				session.setAttribute("obj", mercanciaTrailer);
				response.sendRedirect("asignar/destino-trailer.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es camion");
		}
		try {
			trailerCamion = request.getParameter("trailer-camion");
			trailerCamion.replaceAll(" ", "");
			if (!trailerCamion.equals("nada")) {
				session.setAttribute("busca", "trailer-camion");
				session.setAttribute("obj", trailerCamion);
				response.sendRedirect("modificarDatos/camion.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es camion");
		}
		try {
			placa = request.getParameter("placa");
			placa.replaceAll(" ", "");
			if (!placa.equals("nada")) {
				session.setAttribute("busca", "camion");
				session.setAttribute("obj", placa);
				response.sendRedirect("modificarDatos/camion.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es camion");
		}
		try {
			patente = request.getParameter("patente");
			patente.replaceAll(" ", "");
			if (!patente.equals("nada")) {
				session.setAttribute("busca", "trailer");
				session.setAttribute("obj", patente);
				response.sendRedirect("modificarDatos/trailer.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es trailer");
		}
		try {
			usuario = request.getParameter("usuario");
			usuario.replaceAll(" ", "");
			if (!usuario.equals("nada")) {
				usuario user = new usuario();
				user = (clases.usuario) ControladorBD.getItem("usuarios", "usuario", usuario);
				switch (user.getRol()){
				case "cliente":
					session.setAttribute("busca", "cliente");
					session.setAttribute("obj", usuario);
					response.sendRedirect("modificarDatos/usuario.jsp");
					break;
				case "empleado":
					session.setAttribute("busca", "empleado");
					session.setAttribute("obj", usuario);
					response.sendRedirect("modificarDatos/usuario.jsp");
					break;
				case "conductor":
					session.setAttribute("busca", "empleado");
					session.setAttribute("obj", usuario);
					response.sendRedirect("modificarDatos/usuario.jsp");
				}
			}
		} catch (Exception e) {
			System.out.println("no es usuario");
		}
		try {
			nit = request.getParameter("nit");
			nit.replaceAll(" ", "");
			if (!nit.equals("nada")) {
				session.setAttribute("busca", "empresa");
				session.setAttribute("obj", nit);
				response.sendRedirect("modificarDatos/empresa.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es empresa");
		}
		try {
			usuarioEnvio = request.getParameter("envio");
			if (!usuarioEnvio.equals("nada")) {
				String[] frag = usuarioEnvio.split(" : ");
				String frag1 = frag[0], frag2 = frag[1];
				session.setAttribute("busca", "mercancia");
				session.setAttribute("obj1", frag1);
				session.setAttribute("obj2", frag2);
				session.setAttribute("obj", usuarioEnvio);
				response.sendRedirect("modificarDatos/mercancia.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es envio");
		}
		try {
			usuarioEnvio = request.getParameter("usuarioEnvio");
			if (!usuarioEnvio.equals("nada")) {
				session.setAttribute("busca", "fecha");
				session.setAttribute("obj", usuarioEnvio);
				response.sendRedirect("envios/estadoDelEnvio.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es envioFecha");
		}
		try {
			usuarioEnvio = request.getParameter("fecha");
			if (!usuarioEnvio.equals("nada")) {	
				String[] frag = usuarioEnvio.split(" : ");
				String frag1 = frag[0], frag2 = frag[1];
				session.setAttribute("busca", "envio");
				session.setAttribute("obj1", frag1);
				session.setAttribute("obj2", frag2);
				session.setAttribute("obj", usuarioEnvio);
				response.sendRedirect("envios/estadoDelEnvio.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es envioFecha");
		}
		try {
			chequeo = request.getParameter("chequeoDescarga");
			if (!chequeo.equals("nada")) {
				session.setAttribute("busca", "chequeo");
				session.setAttribute("obj", chequeo);
				response.sendRedirect("chequeo/chequearDescarga.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es chequeo");
		}
		try {
			chequeo = request.getParameter("chequeoCarga");
			if (!chequeo.equals("nada")) {
				session.setAttribute("busca", "chequeo");
				session.setAttribute("obj", chequeo);
				response.sendRedirect("chequeo/chequearCarga.jsp");
			}
		} catch (Exception e) {
			System.out.println("no es chequeo");
		}
		
		/* 
		busqueda = request.getParameter("busqueda");
		switch(busqueda){
			case "nada":
				System.out.println("no es chequeo");
				response.sendRedirect("index.jsp");
				break;
			case "estadoDelEnvio":
				usuarioEnvio = request.getParameter("envio");
				String[] frag = usuarioEnvio.split(" : ");
				String fUsuario = frag[0], fFecha = frag[1];
				System.out.println(fUsuario + " y "+fFecha);
				session.setAttribute("busca", "envio");
				//session.setAttribute("obj", fUsuario);
				response.sendRedirect("envios/estadoDelEnvio.jsp");
			
		}
		if (!placa.equals(null)) {
			session.setAttribute("busca", "camion");
			session.setAttribute("obj", placa);
			response.sendRedirect("modificarDatos/camion.jsp");
		} else if (!patente.equals("nada")) {
			session.setAttribute("busca", "trailer");
			session.setAttribute("obj", patente);
			response.sendRedirect("modificarDatos/trailer.jsp");
		} else if (!nit.equals("nada")) {
			session.setAttribute("busca", "empresa");
			session.setAttribute("obj", nit);
			response.sendRedirect("modificarDatos/empresa.jsp");
		} else if (!id.equals("nada")) {
			session.setAttribute("busca", "mercancia");
			session.setAttribute("obj", id);
			response.sendRedirect("modificarDatos/mercancia.jsp");
		} else if (!usuario.equals("nada")) {
			usuario user = new usuario();
			user = (clases.usuario) logica.getItem("usuarios", "usuario", usuario);
			if (user.getRol() == "cliente") {
				session.setAttribute("busca", "cliente");
				session.setAttribute("obj", usuario);
				response.sendRedirect("modificarDatos/clinete.jsp");
			} else {
				session.setAttribute("busca", "empleado");
				session.setAttribute("obj", usuario);
				response.sendRedirect("modificarDatos/empleado.jsp");
			}
		}*/
	}
}
