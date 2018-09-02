package com.reportes;

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
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getNumReports")
public class GetNumReports extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

	public GetNumReports() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();

			expressionAttributeValues.put(":val", new AttributeValue().withBOOL(false));

			ScanRequest scanRequest = new ScanRequest().withTableName("reportes").withFilterExpression("visto = :val")
					.withExpressionAttributeValues(expressionAttributeValues);

			ScanResult result = client.scan(scanRequest);

			response.setContentType("application/json");
			response.getWriter().print("{\"num\":" + result.getItems().size() + "}");
			response.getWriter().close();

		} catch (Exception e) {
			// com.logica.Dibujar.mensaje(response.getWriter(), "Error al cargar
			// el numero de Reportes");
			response.setStatus(500);
			response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("title", "Operaci&oacute;n fallida");
					put("message", "Error al cargar el numero de Reportes");
				}
			}));
			return;
		}

	}

}
