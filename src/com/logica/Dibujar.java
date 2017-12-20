package com.logica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.jsp.JspWriter;

public class Dibujar {
	
	public static void input(JspWriter out, String input) {


			String name = input;
			// para que salga en mayuscula la primera letra
			String upname = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

			try {
				out.println("<div class=\"form-group\">" + "<label class=\"control-label col-sm-2\" for=\"" + name
						+ "\">" + upname + ":" + "</label>"

						+ "<div class=\"col-sm-10\">" + "<input class=\"form-control\" name=\"" + name + "\" id=\""
						+ name + "\" type=\"text\" placeholder=\"" + upname + "\">" + "</div>" + "</div>");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void inputs(JspWriter out, String[] inputs) {

		for (int i = 0; i < inputs.length; i++) {

			String name = inputs[i];
			// para que salga en mayuscula la primera letra
			String upname = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

			try {
				out.println("<div class=\"form-group\">" + "<label class=\"control-label col-sm-2\" for=\"" + name
						+ "\">" + upname + ":" + "</label>"

						+ "<div class=\"col-sm-10\">" + "<input class=\"form-control\" name=\"" + name + "\" id=\""
						+ name + "\" type=\"text\" placeholder=\"" + upname + "\">" + "</div>" + "</div>");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void inputs(JspWriter out, String[] inputs, String[] value) {

		for (int i = 0; i < inputs.length; i++) {

			String name = inputs[i];
			String valu = value[i];
			// para que salga en mayuscula la primera letra
			String upname = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

			try {
				out.println("<div class=\"form-group\">" + "<label class=\"control-label col-sm-2\" for=\"" + name
						+ "\">" + upname + ":" + "</label>"

						+ "<div class=\"col-sm-10\">" + "<input class=\"form-control\" name=\"" + name + "\" id=\""
						+ name + "\" type=\"text\" value=\"" + valu + "\">" + "</div>" + "</div>");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static void inputsHidden(JspWriter out, String[] inputs) {

		for (int i = 0; i < inputs.length; i++) {

			String name = inputs[i];
			// para que salga en mayuscula la primera letra
			String upname = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

			try {
				out.println("<div class=\"form-group\">" + "<label class=\"control-label col-sm-2\" for=\"" + name
						+ "\">" + upname + ":" + "</label>"

						+ "<div class=\"col-sm-10\">" + "<input class=\"form-control\" name=\"" + name + "\" id=\""
						+ name + "\" type=\"password\" placeholder=\"" + upname + "\">" + "</div>" + "</div>");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	public static void mensaje(PrintWriter out, String mensaje, String nextURL) {

		out.println("<script type=\"text/javascript\">");
		out.println("alert(\"" + mensaje + "\");");
		// Remplazo el
		// response.sendRedirect("/PS3C/envios/realizarNuevoEnvio.jsp");
		// este redirect de Javascript
		out.println("location='" + nextURL + "';");
		out.println("</script>");

	}
	
	
	public static void mensaje(PrintWriter out, String mensaje) {

		out.println("<script type=\"text/javascript\">");
		out.println("alert(\"" + mensaje + "\");");
		out.println("</script>");

	}

	
}
