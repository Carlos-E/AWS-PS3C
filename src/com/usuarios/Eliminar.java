package com.usuarios;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logica.ControladorBD;

import clases.DB;

@WebServlet("/usuarios/eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clases.Usuario usuario = new clases.Usuario();
   
    public Eliminar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String usuario = request.getParameter("usuario").toString();
		
		if(new DB().checkPlaca(usuario)!=null){
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacuten fallida");
					put("message", "El usuario esta asignado a un veh&iacute;culo");
				}
			}));
			return;
		}
		
		ControladorBD.borrarItem("usuarios", "usuario", usuario);
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("title", "Operaci&oacuten exitosa");
				put("message", "Usuario eliminado");
			}
		}));
		return;
	}

}
