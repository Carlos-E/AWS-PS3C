package com.eliminar;

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
 * Servlet implementation class eliminarUsuario
 */
@WebServlet("/trailer/eliminar")
public class eliminarTrailer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarTrailer() {
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
		String patente = session.getAttribute("obj").toString();
		System.out.println("hola" + patente);
		session.setAttribute("busca", "ninguno");
		System.out.println("algo se cambio");
		ControladorBD.borrarItem("trailers", "patente", patente);
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String nextURL = request.getContextPath() + "/index.jsp";
		com.logica.Dibujar.mensaje(out, "Operacion Exitosa", nextURL);
		// response.sendRedirect("index.jsp");

	}

}
