package com.modificar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logica.ControladorBD;

/**
 * Servlet implementation class cliente
 */
@WebServlet("/cliente")
public class modificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.usuario cliente = new clases.usuario();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarCliente() {
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
		String usuario = session.getAttribute("obj").toString();
		cliente = (clases.usuario) ControladorBD.getItem("usuarios", "usuario", usuario);
		String nombre = request.getParameter("nombre").toLowerCase();
		String apeliido = request.getParameter("apellido").toLowerCase();
		String claveOld = request.getParameter("claveOld").toLowerCase();
		String clave1 = request.getParameter("clave1").toLowerCase();
		String clave2 = request.getParameter("clave2").toLowerCase();
		String telefono = request.getParameter("telefono").toLowerCase();
		String direccion = request.getParameter("direccion").toLowerCase();
		String correo = request.getParameter("correo").toLowerCase();
		boolean cambio = false;
		if(!cliente.getClave().equals(claveOld)){
			System.out.println("clave vieja incorreccta");
		}else if(!clave1.equals(clave2)){
			System.out.println("clave nueva no coincide");
		}else{
			if(!cliente.getNombre().equals(nombre)){
				cliente.setNombre(nombre);
				ControladorBD.actualizarValor("usuarios", "usuario", usuario, "nombre", nombre);
				cambio = true;
			}
			if(!cliente.getApellido().equals(apeliido)){
				cliente.setNombre(apeliido);
				ControladorBD.actualizarValor("usuarios", "usuario", usuario, "apeliido", apeliido);
				cambio = true;
			}
			if(!cliente.getClave().equals(clave1)){
				cliente.setNombre(clave1);
				ControladorBD.actualizarValor("usuarios", "usuario", usuario, "clave", clave1);
				cambio = true;
			}
			if(!cliente.getTelefono().equals(telefono)){
				cliente.setNombre(telefono);
				ControladorBD.actualizarValor("usuarios", "usuario", usuario, "telefono", telefono);
				cambio = true;
			}
			if(!cliente.getDireccion().equals(direccion)){
				cliente.setNombre(direccion);
				ControladorBD.actualizarValor("usuarios", "usuario", usuario, "direccion", direccion);
				cambio = true;
			}
			if(!cliente.getCorreo().equals(correo)){
				cliente.setNombre(correo);
				ControladorBD.actualizarValor("usuarios", "usuario", usuario, "correo", correo);
				cambio = true;
			}
			if(cambio){
				session.setAttribute("busca", "ninguno");
				System.out.println("algo se cambio");
				response.sendRedirect("index.jsp");
			}else{
				System.out.println("no se cambio nada");
				com.logica.Dibujar.mensaje(response.getWriter(), "No ha habido cambio", request.getSession().getAttribute("origin").toString());
			}
		}
	}

}
