package com.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.ControladorBD;

import clases.Reporte;

@WebServlet("/getNumReports")
public class GetNumReports extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetNumReports() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Reporte> reportes = ControladorBD.escanearTabla("reportes");
		
		int num = 0;
		
		for (int i = 0; i < reportes.size(); i++) {
			if(!reportes.get(i).isVisto()){
				num++;
			}
		}
		
		response.setContentType("application/json");
		response.getWriter().print("{\"num\":"+num+"}");
		response.getWriter().close();

	}

}
