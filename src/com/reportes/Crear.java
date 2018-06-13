package com.reportes;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.logica.ControladorBD;

import clases.Reporte;

@WebServlet("/reportes/crear")
public class Crear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_1)
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	
	DynamoDB dynamoDB = new DynamoDB(client);
	Table table = dynamoDB.getTable("reportes");

	public Crear() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try {
		Calendar calendar = Calendar.getInstance();
		DecimalFormat mFormat = new DecimalFormat("00");
		String hora = calendar.get(Calendar.YEAR) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH) + 1)) + "-"
				+ mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH))) + " "
				+ mFormat.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ mFormat.format(calendar.get(Calendar.MINUTE)) + ":" + mFormat.format(calendar.get(Calendar.SECOND));

		// Reporte reporte = new Reporte();
		//
		// reporte.setHora(hora);
		// reporte.setNota(request.getParameter("nota").toLowerCase());
		// reporte.setUsuario(request.getSession().getAttribute("username").toString());
		// reporte.setVisto(false);

		// ControladorBD.registrarItem("reportes", reporte);

		// PRUEBA

		PutItemOutcome outcome = table.putItem(new Item()
				.withPrimaryKey("usuario", request.getSession().getAttribute("username").toString())
				.withString("hora", hora)
				.withString("nota", request.getParameter("nota").toLowerCase())
				.withString("visto", "false"));

		System.out.println(outcome);
		// PRUEBA
		response.setContentType("text/html");
		com.logica.Dibujar.mensaje(response.getWriter(), "Reporte Creado",
				request.getContextPath() + request.getContextPath() + "/movil/transportador/reportes.jsp");
}catch(Exception e){
	com.logica.Dibujar.mensaje(response.getWriter(), "Ocurrio un error al intentar crear el Reporte", request.getContextPath() + "./index.jsp");
}
	}

}
