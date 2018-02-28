package com.modificar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

/**
 * Servlet implementation class modificarUsuario
 */
@WebServlet("/trueBuscar")
public class trueBuscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.usuario usuario = new clases.usuario();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public trueBuscar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		String user = session.getAttribute("obj").toString();
		System.out.println("hola" +user);
		usuario = (clases.usuario) ControladorBD.getItem("usuarios", "usuario", user);
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
		if(claveOld.isEmpty() && clave1.isEmpty() && clave2.isEmpty()){
			if(!usuario.getRol().equals(rol)){
				ControladorBD.actualizarValor("usuarios", "usuario", user, "rol", rol);
				cambio = true;
			}
			if(!usuario.getNombre().equals(nombre)){
				ControladorBD.actualizarValor("usuarios", "usuario", user, "nombre", nombre);
				cambio = true;
			}
			if(!usuario.getApellido().equals(apellido)){
				ControladorBD.actualizarValor("usuarios", "usuario", user, "apellido", apellido);
				cambio = true;
			}
			if(!usuario.getTelefono().equals(telefono)){
				ControladorBD.actualizarValor("usuarios", "usuario", user, "telefono", telefono);
				cambio = true;
			}
			if(!usuario.getDireccion().equals(direccion)){
				ControladorBD.actualizarValor("usuarios", "usuario", user, "direccion", direccion);
				cambio = true;
			}
			if(!usuario.getCorreo().equals(correo)){
				ControladorBD.actualizarValor("usuarios", "usuario", user, "correo", correo);
				cambio = true;
			}
			if(cambio){
				session.setAttribute("busca", "ninguno");
				System.out.println("algo se cambio");
				PrintWriter out = response.getWriter();
				String nextURL = request.getContextPath() + "/modificar/usuario.jsp";
				com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
				//response.sendRedirect("index.jsp");
			}else{
				System.out.println("no se cambio nada");
			}
		}else if(!claveOld.isEmpty() || !clave1.isEmpty() || !clave2.isEmpty()){
			if(!usuario.getClave().equals(claveOld)){
				System.out.println("clave vieja incorreccta");
			}else if(!clave1.equals(clave2)){
				System.out.println("clave nueva no coincide");
			}else{
				if(!usuario.getRol().equals(rol)){
					ControladorBD.actualizarValor("usuarios", "usuario", user, "rol", rol);
					cambio = true;
				}
				if(!usuario.getNombre().equals(nombre)){
					ControladorBD.actualizarValor("usuarios", "usuario", user, "nombre", nombre);
					cambio = true;
				}
				if(!usuario.getApellido().equals(apellido)){
					ControladorBD.actualizarValor("usuarios", "usuario", user, "apellido", apellido);
					cambio = true;
				}
				if(!usuario.getClave().equals(clave1)){
					ControladorBD.actualizarValor("usuarios", "usuario", user, "clave", clave1);
					cambio = true;
				}
				if(!usuario.getTelefono().equals(telefono)){
					ControladorBD.actualizarValor("usuarios", "usuario", user, "telefono", telefono);
					cambio = true;
				}
				if(!usuario.getDireccion().equals(direccion)){
					ControladorBD.actualizarValor("usuarios", "usuario", user, "direccion", direccion);
					cambio = true;
				}
				if(!usuario.getCorreo().equals(correo)){
					ControladorBD.actualizarValor("usuarios", "usuario", user, "correo", correo);
					cambio = true;
				}
				if(cambio){
					session.setAttribute("busca", "ninguno");
					System.out.println("algo se cambio");
					PrintWriter out = response.getWriter();
					String nextURL = request.getContextPath() + "/modificar/usuario.jsp";
					com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
					//response.sendRedirect("index.jsp");
				}else{
					System.out.println("no se cambio nada");
				}
			}
		}
		
	}

}
