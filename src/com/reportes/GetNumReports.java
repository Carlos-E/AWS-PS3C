package com.reportes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.ControladorBD;

import clases.Reporte;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

@WebServlet("/getNumReports")
public class GetNumReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

	public GetNumReports() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ArrayList<Reporte> reportes =
		// ControladorBD.escanearTabla("reportes");
		//
		// int num = 0;
		//
		// for (int i = 0; i < reportes.size(); i++) {
		// if(!reportes.get(i).isVisto()){
		// num++;
		// }
		// }
		//
		// response.setContentType("application/json");
		// response.getWriter().print("{\"num\":"+num+"}");
		// response.getWriter().close();

		
		//PRUEBA
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();

		expressionAttributeValues.put(":val", new AttributeValue().withS("false"));

		ScanRequest scanRequest = new ScanRequest().withTableName("reportes").withFilterExpression("visto = :val")
				.withExpressionAttributeValues(expressionAttributeValues);

		ScanResult result = client.scan(scanRequest);
		
		//System.out.println(result.getItems()+"\n");
		//PRUEBA

		response.setContentType("application/json");
		response.getWriter().print("{\"num\":" + result.getItems().size() + "}");
		response.getWriter().close();

	}

}
