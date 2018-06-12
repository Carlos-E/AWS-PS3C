package com.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.logica.ControladorBD;

@WebServlet("/scanTable")
public class scanTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//CODIGO DE PRUEBA
	BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	//CODIGO DE PRUEBA

	
	public scanTable() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		response.setContentType("application/json");
		//response.getWriter().print(ow.writeValueAsString(ControladorBD.escanearTabla(request.getParameter("tabla"))));
		
		//CODIGO DE PRUEBA
		
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();

		expressionAttributeValues.put(":val", new AttributeValue().withS("false"));

		ScanRequest scanRequest = new ScanRequest().withTableName("reportes").withFilterExpression("visto = :val")
				.withExpressionAttributeValues(expressionAttributeValues);

		ScanResult result = client.scan(scanRequest);
		
		//CODIGO DE PRUEBA

		response.getWriter().print(result.getItems());
		response.getWriter().close();

	}

}
