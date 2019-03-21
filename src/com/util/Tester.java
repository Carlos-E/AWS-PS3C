package com.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import clases.*;

@WebServlet("/tester")
public class Tester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Tester() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		DB DB = new DB();
		
		Usuario usuario = new Usuario();
		usuario.setUsuario("root@ps3c.com");
		
		//Prueba de load
		usuario = DB.load(usuario);
		result.put("load",usuario.getUsuario());
		
		//Prueba de save
		usuario.setUsuario("prueba");
		DB.save(usuario);		
		result.put("save",usuario.getUsuario());
		
		//Prueba de scan
		//DB.scan(Usuario.class, new DynamoDBScanExpression());
		result.put("scan1",DB.scan(Usuario.class, new DynamoDBScanExpression()).size());
		
		//prueba de Email
		new Email("he-he@outlook.com","Prueba","Prueba Exitosa");
		result.put("email", "Enviado");

		//prueba delete
		DB.delete(usuario);
		result.put("delete", usuario.getUsuario());
		
		//Prueba de scan2 despues de borrar
		result.put("scan2",DB.scan(Usuario.class, new DynamoDBScanExpression()).size());
		
		response.getWriter().print(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writer().writeValueAsString(result));
		response.getWriter().close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
