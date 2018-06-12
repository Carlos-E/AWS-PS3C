package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.logica.ControladorBD;

@WebServlet("/scanTable")
public class scanTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// CODIGO DE PRUEBA
	BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

	DynamoDB dynamoDB = new DynamoDB(client);
	// CODIGO DE PRUEBA

	public scanTable() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/404.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		// response.setContentType("application/json");
		// response.getWriter().print(ow.writeValueAsString(ControladorBD.escanearTabla(request.getParameter("tabla"))));
		// response.getWriter().close();
		// CODIGO DE PRUEBA
		Table table = dynamoDB.getTable(request.getParameter("tabla"));
		ItemCollection<ScanOutcome> result = table.scan();
		ArrayList<String> Items = new ArrayList<String>();
		Iterator<Item> iterator = result.iterator();
		while (iterator.hasNext()) {
			Items.add(iterator.next().toJSON().toString());
		}
		response.setContentType("application/json");
		response.getWriter().print(Items);
		response.getWriter().close();
		// CODIGO DE PRUEBA
	}

}
