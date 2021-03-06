package com.logica;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
//import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

import clases.*;

public class ControladorBD { 
	
	static final String requestUrl = "https://muktzanw2i.execute-api.us-east-1.amazonaws.com/prod/managerDB";
	static final String API_KEY = "VjvCwhk8Q23GnhuCxT2tO4XfKRvAhCeS85SkqWG9";
	
    public static StringBuffer postRequest(String payload) {
		
		StringBuffer jsonString=null;

		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			
			// Llave para la api
		/*
			if (System.getenv("API_KEY") == null) {
				if (System.getProperty("API_KEY") == null) {
					connection.setRequestProperty("x-api-key", API_KEY);
				} else {
					connection.setRequestProperty("x-api-key", System.getProperty("API_KEY"));
				}
			} else {
				connection.setRequestProperty("x-api-key", System.getenv("API_KEY"));
			}*/
			
			connection.setRequestProperty("x-api-key",API_KEY);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			
			System.out.println(payload);
			
			writer.write(payload);
			
			writer.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			jsonString = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e.getMessage());
		}
		
		System.out.println(jsonString);
		
		return jsonString;
	}
	

    public static boolean registrarItem(String nombretabla, Object objeto){
		
		//String nombretabla = "usuarios";// No cambiar
		String operacion = "create";
		
		
		JSONObject jsonObject = new JSONObject(objeto);

		String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombretabla+"\","
    					+ "\"payload\":{"
    						+ "\"Item\":" + jsonObject.toString()
    					+ "}"
    				+ "}";
		
		System.out.println(jsonObject);
		
		if(postRequest(payload)!=null){
			System.out.println("Success, Registrado");
			return true;

		}else{
			
			System.out.println("Fail");
			return false;
		}
		
	}
    
     
    public static Object getEnvio(String nombreTabla,  String valorLlavePrimaria, String valorLlaveSegundaria) {   
    	
    	Object objeto = new Object();
    	
		//String nombreTabla = "usuarios";// No cambiar
		String operacion = "query";
		/*String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombreTabla+"\","
    					+ "\"payload\":{"
    						+ "\"Key\":{"
    							+ "\""+llavePrimaria+"\":\""+valorLlavePrimaria+"\""
    						+ "}"
    					+ "}"
    				+ "}";	*/
		
		String payload = "{"
                + "\"operation\":\"" + operacion + "\","
                + "\"tableName\":\"" + nombreTabla + "\","
                + "\"payload\":{"
                + "\"IndexName\":" + "\"" + valorLlavePrimaria + "\","
                + "\"KeyConditionExpression\":" + "\"" + valorLlavePrimaria + " = :v1\", "
                + "\"ExpressionAttributeValues\": {"
                + "\":v1\": \"" + valorLlaveSegundaria + "\"}"
                + "}"
                + "}";
		
		String response = postRequest(payload).toString();
		
		JSONObject obj;
		
		try {
			obj = new JSONObject(response);
			ObjectMapper mapper = new ObjectMapper();

			if (nombreTabla.equals("usuarios")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Usuario.class);
			}

			if (nombreTabla.equals("empresas")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Empresa.class);
			}

			if (nombreTabla.equals("vehiculos")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Vehiculo.class);
			}

			if (nombreTabla.equals("trailers")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Trailer.class);
			}

			if (nombreTabla.equals("envios")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Envio.class);
			}
			
//			if (nombreTabla.equals("ubicaciones")) {
//				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Ubicacion.class);
//			}

			System.out.println(objeto);
			System.out.println("Success");

			return objeto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
			return objeto=null;
		}
	}
    
    
    public static Object getItem(String nombreTabla, String llaveprimaria, String valorllaveprimaria) {   
    	
    	Object objeto = new Object();
    	
		//String nombreTabla = "usuarios";// No cambiar
		String operacion = "read";
		String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombreTabla+"\","
    					+ "\"payload\":{"
    						+ "\"Key\":{"
    							+ "\""+llaveprimaria+"\":\""+valorllaveprimaria+"\""
    						+ "}"
    					+ "}"
    				+ "}";	
		
		String response = postRequest(payload).toString();
		
		JSONObject obj;
		
		try {
			obj = new JSONObject(response);
			ObjectMapper mapper = new ObjectMapper();

			if (nombreTabla.equals("usuarios")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Usuario.class);
			}

			if (nombreTabla.equals("empresas")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Empresa.class);
			}

			if (nombreTabla.equals("vehiculos")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Vehiculo.class);
			}

			if (nombreTabla.equals("trailers")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Trailer.class);
			}

			if (nombreTabla.equals("envios")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Envio.class);
			}
			
//			if (nombreTabla.equals("ubicaciones")) {
//				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Ubicacion.class);
//			}

			System.out.println(objeto);
			System.out.println("Success");

			return objeto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
			return objeto=null;
		}
	}
    
    
    public static Object getItem(String nombreTabla, String llaveprimaria, String valorllaveprimaria, String llavesegundaria, String valorllavesegundaria) {   
    	
    	Object objeto = new Object();
    	
		//String nombreTabla = "usuarios";// No cambiar
		String operacion = "read";
		String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombreTabla+"\","
    					+ "\"payload\":{"
    						+ "\"Key\":{"
    							+ "\""+llaveprimaria+"\":\""+valorllaveprimaria+"\","
    							+ "\""+llavesegundaria+"\":\""+valorllavesegundaria+"\""
    						+ "}"
    					+ "}"
    				+ "}";	
		
		String response = postRequest(payload).toString();
		
		JSONObject obj;
		
		try {
			obj = new JSONObject(response);
			ObjectMapper mapper = new ObjectMapper();

			if (nombreTabla.equals("usuarios")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Usuario.class);
			}

			if (nombreTabla.equals("empresas")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Empresa.class);
			}

			if (nombreTabla.equals("vehiculos")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Vehiculo.class);
			}

			if (nombreTabla.equals("trailers")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Trailer.class);
			}

			if (nombreTabla.equals("envios")) {
				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Envio.class);
			}
			
//			if (nombreTabla.equals("ubicaciones")) {
//				objeto = mapper.readValue(obj.getJSONObject("Item").toString(), Ubicacion.class);
//			}

			System.out.println(objeto);
			System.out.println("Success");

			return objeto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
			return objeto=null;
		}
	}
    
    
    public static boolean validarLogin(String usuario, String clave) {

		String nombreTabla = "usuarios";// No cambiar

		String operacion = "read";

		String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombreTabla+"\","
    					+ "\"payload\":{"
    						+ "\"Key\":{"
    							+ "\"usuario\":\""+usuario+"\""
    						+ "}"
    					+ "}"
    				+ "}";
		
		String response = postRequest(payload).toString();

		JSONObject obj;

		try {
			obj = new JSONObject(response);
			String usuarioEncontrado = obj.getJSONObject("Item").getString("usuario");
			String claveEncontrada = obj.getJSONObject("Item").getString("clave");

			System.out.println();
			System.out.println("   Enviado: " + usuario);
			System.out.println("   Enviado: " + clave);

			System.out.println();

			System.out.println("Encontrado: " + usuarioEncontrado);
			System.out.println("Encontrado: " + claveEncontrada);

			System.out.println();

			if (clave.equals(claveEncontrada)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("\nError: " + e.getMessage());
			System.out.println("Fail, usuario malo o error en la operacion");
			return false;
			//e.printStackTrace();			
			//throw new RuntimeException(e.getMessage());
		}
	}
	
    
	public static boolean borrarItem(String tabla, String nombrellaveprimaria, String valorllaveprimaria) {

		String nombretabla = tabla;// No cambiar
		String operacion = "delete";
		
		String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombretabla+"\","
    					+ "\"payload\":{"
    						+ "\"Key\":{"
    							+ "\""+nombrellaveprimaria+"\":\""+valorllaveprimaria+"\""
    						+ "},"
    						//+ "\"ReturnValues\":\"ALL_NEW\""
    						+ "\"ReturnValues\": \"ALL_OLD\""
    					+ "}"
    				+ "}";
		
		String response = postRequest(payload).toString();

		JSONObject obj;

		try {
			obj = new JSONObject(response);
			String valorllaveencontrada = obj.getJSONObject("Attributes").getString(nombrellaveprimaria);
			// String valorencontrado =
			// obj.getJSONObject("Attributes").getString(llave);
			String statuscode = obj.getJSONObject("ResponseMetadata").get("HTTPStatusCode").toString();

			System.out.println();

			System.out.println("Codigo de estatus: " + statuscode);

			System.out.println();

			System.out.println("   Enviado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveprimaria + "\"");

			System.out.println();

			System.out
					.println("   Borrado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveencontrada + "\"");

			System.out.println();

			// if(valorllave.equals(valorllaveencontrada) &&
			// valorvalor.equals(valorvalorencontrado)){
			if (valorllaveprimaria.equals(valorllaveencontrada)) {

				System.out.println("Success");
				return true;

			} else {

				System.out.println("Fail");
				return false;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("\nError: " + e.getMessage());

			System.out.println("Fail, error o no existe");
			// throw new RuntimeException(e.getMessage());
			return false;

		}

	}
	
	public static boolean borrarItem(String tabla, String nombrellaveprimaria, String valorllaveprimaria, String nombrellaveordenada, String valorllaveordenada) {

		String nombretabla = tabla;// No cambiar
		String operacion = "delete";
		
		String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombretabla+"\","
    					+ "\"payload\":{"
    						+ "\"Key\":{"
    							+ "\""+nombrellaveprimaria+"\":\""+valorllaveprimaria+"\","
    							+ "\""+nombrellaveordenada+"\":\""+valorllaveordenada+"\""
    						+ "},"
    						//+ "\"ReturnValues\":\"ALL_NEW\""
    						+ "\"ReturnValues\": \"ALL_OLD\""
    					+ "}"
    				+ "}";
		
		String response = postRequest(payload).toString();

		JSONObject obj;

		try {
			obj = new JSONObject(response);
			String valorllaveencontrada = obj.getJSONObject("Attributes").getString(nombrellaveprimaria);
			// String valorencontrado =
			// obj.getJSONObject("Attributes").getString(llave);
			String statuscode = obj.getJSONObject("ResponseMetadata").get("HTTPStatusCode").toString();

			System.out.println();

			System.out.println("Codigo de estatus: " + statuscode);

			System.out.println();

			System.out.println("   Enviado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveprimaria + "\"");

			System.out.println();

			System.out
					.println("   Borrado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveencontrada + "\"");

			System.out.println();

			// if(valorllave.equals(valorllaveencontrada) &&
			// valorvalor.equals(valorvalorencontrado)){
			if (valorllaveprimaria.equals(valorllaveencontrada)) {

				System.out.println("Success");
				return true;

			} else {

				System.out.println("Fail");
				return false;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("\nError: " + e.getMessage());

			System.out.println("Fail, error o no existe");
			// throw new RuntimeException(e.getMessage());
			return false;

		}

	}

	
	public static boolean actualizarValor(String tabla, String nombrellaveprimaria, String valorllaveprimaria,
			String llave, String string) {

		String nombretabla = tabla;// No cambiar
		String operacion = "update";
		
		String payload = "{"
				+ "\"operation\":\""+operacion+"\","
				+ "\"tableName\":\""+nombretabla+"\","
						+ "\"payload\":{"
							+ "\"Key\":{"
								+ "\""+nombrellaveprimaria+"\":\""+valorllaveprimaria+"\""
								+ "},"
								+ "\"UpdateExpression\": \"set "+llave+" = :valor\","
								+ "\"ExpressionAttributeValues\": {"
								+ "\":valor\":\""+string+"\""
							 + "},"
							//+ "\"ReturnValues\":\"ALL_NEW\""
							+ "\"ReturnValues\": \"UPDATED_NEW\""
						+ "}"
					+ "}";
	
		String response = postRequest(payload).toString();

		JSONObject obj;
		//Object objetoencontrado = new Object();
		String objetoencontrado = "??";

		try {
			
			obj = new JSONObject(response);

			String statuscode = obj.getJSONObject("ResponseMetadata").get("HTTPStatusCode").toString();
						
			try {
				objetoencontrado = obj.getJSONObject("Attributes").getString(llave).toString();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			System.out.println("\n"+"Codigo de estatus: " + statuscode+"\n");

			System.out.println("    Enviado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveprimaria + "\"");
			System.out.println("    Enviado: " + "\"" + llave + "\": " + "\"" + string + "\"");
			
			System.out.println("\n"+"     Actual: " + "\"" + llave + "\": " + "\"" + objetoencontrado + "\""+"\n");


			if (string.equals(objetoencontrado.toString())) {

				System.out.println("Success");
				return true;

			} else {

				System.out.println("Fail");
				return false;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("\nError: " + e.getMessage());

			System.out.println("Fail, usuario malo o error en la operacion");
			// throw new RuntimeException(e.getMessage());
			return false;

		}

	}
	
	
	public static boolean actualizarValor(String tabla, String nombrellaveprimaria, String valorllaveprimaria, 
			String nombrellavesegunaria, String valorllavesegundaria, String llave, String string) {

		String nombretabla = tabla;// No cambiar
		String operacion = "update";
		
		String payload = "{"
				+ "\"operation\":\""+operacion+"\","
				+ "\"tableName\":\""+nombretabla+"\","
						+ "\"payload\":{"
							+ "\"Key\":{"
								+ "\""+nombrellaveprimaria+"\":\""+valorllaveprimaria+"\","
								+ "\""+nombrellavesegunaria+"\":\""+valorllavesegundaria+"\""
								+ "},"
								+ "\"UpdateExpression\": \"set "+llave+" = :valor\","
								+ "\"ExpressionAttributeValues\": {"
								+ "\":valor\":\""+string+"\""
							 + "},"
							//+ "\"ReturnValues\":\"ALL_NEW\""
							+ "\"ReturnValues\": \"UPDATED_NEW\""
						+ "}"
					+ "}";
	
		String response = postRequest(payload).toString();

		JSONObject obj;
		//Object objetoencontrado = new Object();
		String objetoencontrado = "??";

		try {
			
			obj = new JSONObject(response);

			String statuscode = obj.getJSONObject("ResponseMetadata").get("HTTPStatusCode").toString();
						
			try {
				objetoencontrado = obj.getJSONObject("Attributes").getString(llave).toString();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			System.out.println("\n"+"Codigo de estatus: " + statuscode+"\n");

			System.out.println("    Enviado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveprimaria + "\"");
			System.out.println("    Enviado: " + "\"" + llave + "\": " + "\"" + string + "\"");
			
			System.out.println("\n"+"     Actual: " + "\"" + llave + "\": " + "\"" + objetoencontrado + "\""+"\n");


			if (string.equals(objetoencontrado.toString())) {

				System.out.println("Success");
				return true;

			} else {

				System.out.println("Fail");
				return false;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("\nError: " + e.getMessage());

			System.out.println("Fail, usuario malo o error en la operacion");
			// throw new RuntimeException(e.getMessage());
			return false;

		}

	}
	
	
	
	
	public static boolean actualizarValor(String tabla, String nombrellaveprimaria, String valorllaveprimaria,
			String llave, Object objeto) {

		String nombretabla = tabla;// No cambiar
		String operacion = "update";
		
		JSONObject jsonObject = new JSONObject(objeto);


		String payload = "{"
				+ "\"operation\":\""+operacion+"\","
				+ "\"tableName\":\""+nombretabla+"\","
						+ "\"payload\":{"
							+ "\"Key\":{"
								+ "\""+nombrellaveprimaria+"\":\""+valorllaveprimaria+"\""
							+ "},"
								+ "\"UpdateExpression\": \"set "+llave+" = :valor\","
								+ "\"ExpressionAttributeValues\": {"
								+ "\":valor\":"+jsonObject+""
							 + "},"
							//+ "\"ReturnValues\":\"ALL_NEW\""
							+ "\"ReturnValues\": \"UPDATED_NEW\""
						+ "}"
					+ "}";
	
		String response = postRequest(payload).toString();

		JSONObject obj;
		//Object objetoencontrado = new Object();
		String objetoencontrado = "??";

		try {
			
			obj = new JSONObject(response);

			String statuscode = obj.getJSONObject("ResponseMetadata").get("HTTPStatusCode").toString();
						
			try {
				objetoencontrado = obj.getJSONObject("Attributes").getJSONObject(llave).toString();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			System.out.println("\n"+"Codigo de estatus: " + statuscode+"\n");

			System.out.println("    Enviado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveprimaria + "\"");
			System.out.println("    Enviado: " + "\"" + llave + "\": " + "\"" + jsonObject + "\"");
			
			System.out.println("\n"+"     Actual: " + "\"" + llave + "\": " + "\"" + objetoencontrado + "\""+"\n");


			if (jsonObject.toString().equals(objetoencontrado.toString())) {

				System.out.println("Success");
				return true;

			} else {

				System.out.println("Fail");
				return false;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("\nError: " + e.getMessage());

			System.out.println("Fail, usuario malo o error en la operacion");
			// throw new RuntimeException(e.getMessage());
			return false;

		}

	}
/*
	
	public static boolean moficarItem(String tabla, String nombrellaveprimaria, String valorllaveprimaria, Object objeto) {

		String nombretabla = tabla;// No cambiar
		String operacion = "update";
		
		JSONObject jsonObject = new JSONObject(objeto);


		String payload = "{"
				+ "\"operation\":\""+operacion+"\","
				+ "\"tableName\":\""+nombretabla+"\","
						+ "\"payload\":{"
							+ "\"Key\":{"
								+ "\""+nombrellaveprimaria+"\":\""+valorllaveprimaria+"\""
							+ "},"
								+ "\"UpdateExpression\": \"set "+llave+" = :valor\","
								+ "\"ExpressionAttributeValues\": {"
								+ "\":valor\":"+jsonObject+""
							 + "},"
							//+ "\"ReturnValues\":\"ALL_NEW\""
							+ "\"ReturnValues\": \"UPDATED_NEW\""
						+ "}"
					+ "}";
	
		String response = managerDB(payload).toString();

		JSONObject obj;
		//Object objetoencontrado = new Object();
		String objetoencontrado = "??";

		try {
			
			obj = new JSONObject(response);

			String statuscode = obj.getJSONObject("ResponseMetadata").get("HTTPStatusCode").toString();
						
			try {
				objetoencontrado = obj.getJSONObject("Attributes").getJSONObject(llave).toString();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			System.out.println("\n"+"Codigo de estatus: " + statuscode+"\n");

			System.out.println("    Enviado: " + "\"" + nombrellaveprimaria + "\": " + "\"" + valorllaveprimaria + "\"");
			System.out.println("    Enviado: " + "\"" + jsonObject + "\"");
			
			System.out.println("\n"+"     Actual: " + "\"" + objetoencontrado + "\""+"\n");


			if (jsonObject.toString().equals(objetoencontrado.toString())) {

				System.out.println("Success");
				return true;

			} else {

				System.out.println("Fail");
				return false;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			System.out.println("\nError: " + e.getMessage());

			System.out.println("Fail, usuario malo o error en la operacion");
			// throw new RuntimeException(e.getMessage());
			return false;

		}

	}
	
	*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList escanearTabla(String nombreTabla) {

		ArrayList objetos = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject obj;
    	
		String operacion = "list";
		String payload="{"
    			+ "\"operation\":\""+operacion+"\","
    			+ "\"tableName\":\""+nombreTabla+"\","
    					+ "\"payload\":{"
    						
    							+ "\"ReturnConsumedCapacity\":\"TOTAL\""
    						
    					+ "}"
    				+ "}";	
		
		String response = postRequest(payload).toString();
		
		
		try {
			
			obj = new JSONObject(response);
			
			if(nombreTabla.equals("usuarios")){
				 
				for(int i=0 ; i< obj.getJSONArray("Items").length(); i++){   // iterate through jsonArray 
					
					objetos.add(mapper.readValue(obj.getJSONArray("Items").get(i).toString(), Usuario.class));

					System.out.println("Objeto " + i + ": " + objetos.get(i));
				}
				
			}
			
			if(nombreTabla.equals("empresas")){
				
				for(int i=0 ; i< obj.getJSONArray("Items").length(); i++){   // iterate through jsonArray 
					
					objetos.add(mapper.readValue(obj.getJSONArray("Items").get(i).toString(), Empresa.class));

					System.out.println("Objeto " + i + ": " + objetos.get(i));
				}
				
			}
			
			if(nombreTabla.equals("vehiculos")){
					for(int i=0 ; i< obj.getJSONArray("Items").length(); i++){   // iterate through jsonArray 
					
					objetos.add(mapper.readValue(obj.getJSONArray("Items").get(i).toString(), Vehiculo.class));

					System.out.println("Objeto " + i + ": " + objetos.get(i));
				}				
					
			}
			
			if(nombreTabla.equals("trailers")){
				for(int i=0 ; i< obj.getJSONArray("Items").length(); i++){   // iterate through jsonArray 
					
					objetos.add(mapper.readValue(obj.getJSONArray("Items").get(i).toString(), Trailer.class));

					System.out.println("Objeto " + i + ": " + objetos.get(i));
				}
			}
			
			if(nombreTabla.equals("envios")){
				for(int i=0 ; i< obj.getJSONArray("Items").length(); i++){   // iterate through jsonArray 
					
					objetos.add(mapper.readValue(obj.getJSONArray("Items").get(i).toString(), Envio.class));

					System.out.println("Objeto " + i + ": " + objetos.get(i));
				}				
				
			}
			
			if(nombreTabla.equals("reportes")){
				for(int i=0 ; i< obj.getJSONArray("Items").length(); i++){   // iterate through jsonArray 
					
					objetos.add(mapper.readValue(obj.getJSONArray("Items").get(i).toString(), Reporte.class));

					System.out.println("Objeto " + i + ": " + objetos.get(i));
				}				
				
			}
			
//			if(nombreTabla.equals("ubicaciones")){
//				for(int i=0 ; i< obj.getJSONArray("Items").length(); i++){   // iterate through jsonArray 
//					
//					objetos.add(mapper.readValue(obj.getJSONArray("Items").get(i).toString(), Ubicacion.class));
//
//					System.out.println("Objeto " + i + ": " + objetos.get(i));
//				}				
//				
//			}
				
			System.out.println("\nCantidad de Items encontrados: "+ obj.getInt("Count"));
			System.out.println("Capacidad de lectura consumida: "+obj.getJSONObject("ConsumedCapacity").getInt("CapacityUnits"));
			//System.out.println("Array de Objeto:\n"+objeto);
			System.out.println("\nSuccess");
			
			return objetos;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error: "+e.getMessage());
			return objetos;
		}
	}
	
	public static boolean estaOcupado(String nombre, String vehiculo){
		boolean resultado = false;
		ArrayList<Vehiculo> vehiculos = escanearTabla("vehiculos");
		ArrayList<Trailer> trailers = escanearTabla("trailers");
		if(vehiculo.equals("null")){
			for (int i = 0; i < vehiculos.size() ; i++){
				if(nombre.equals(vehiculos.get(i).getUsuario())){
					System.out.println("sale");
					return true;
				}
			}
		}else if (nombre.equals("null")){
			for (int i = 0; i < trailers.size() ; i++){
				if(vehiculo.equals(trailers.get(i).getCamion())){
					return true;
				}
			}
		}
		return resultado;
	}
	
	/*
	public static ArrayList<Envio> getShipments(String key, String value) {


		ArrayList<Envio> Envios = new ArrayList<>();

        JSONObject jsonObject = null;

        String operation = "query", tableName = "envios";

        String payload = "{"
                + "\"operation\":\"" + operation + "\","
                + "\"tableName\":\"" + tableName + "\","
                + "\"payload\":{"
                + "\"IndexName\":" + "\"" + key + "\","
                + "\"KeyConditionExpression\":" + "\"" + key + " = :v1\", "
                + "\"ExpressionAttributeValues\": {"
                + "\":v1\": \"" + value + "\"}"
                + "}"
                + "}";

        StringBuffer response = postRequest(payload);

        if (response != null) {

            try {

                jsonObject = new JSONObject(response.toString());

                if (jsonObject.getDouble("Count") == 0) {
                    return null;
                }

                // "I want to iterate though the objects in the array..."
                JSONArray jsonArray = jsonObject.getJSONArray("Items");

                for (int i = 0; i < jsonArray.length(); i++) {

                    Envio envio = new Envio();

                    //String id, fecha, destino, origen, estado, espacio, tipo, tiempoCarga, tiempoDescargausuario, usuario, camion, trailer, empresa;
                    //String destinoLatLong, origenLatLong;
                    //boolean chequeoCarga, chequeoDescarga;
                    //15 en total.

                    envio.setUsuario(jsonArray.getJSONObject(i).getString("usuario"));
                    envio.setCamion(jsonArray.getJSONObject(i).getString("camion"));
                    envio.setTrailer(jsonArray.getJSONObject(i).getString("trailer"));
                    envio.setEmpresa(jsonArray.getJSONObject(i).getString("empresa"));
                    envio.setFecha(jsonArray.getJSONObject(i).getString("fecha"));
                    envio.setDestino(jsonArray.getJSONObject(i).getString("destino"));
                    envio.setOrigen(jsonArray.getJSONObject(i).getString("origen"));
                    envio.setEstado(jsonArray.getJSONObject(i).getString("estado"));
                    envio.setEspacio(jsonArray.getJSONObject(i).getString("espacio"));
                    envio.setTipo(jsonArray.getJSONObject(i).getString("tipo"));
                    //envio.setTiempoCarga(jsonArray.getJSONObject(i).getString("tiempoCarga"));
                    //envio.setTiempoDescargausuario(jsonArray.getJSONObject(i).getString("tiempoDescargausuario"));
                    envio.setChequeoCarga(jsonArray.getJSONObject(i).getBoolean("chequeoCarga"));
                    envio.setChequeoDescarga(jsonArray.getJSONObject(i).getBoolean("chequeoDescarga"));

                    envio.setOrigenLatLong(jsonArray.getJSONObject(i).getString("origenLatLong"));
                    envio.setDestinoLatLong(jsonArray.getJSONObject(i).getString("destinoLatLong"));

                    Envios.add(envio);

                }

                return Envios;

            } catch (Exception e) {
                
                return null;
            }

        } else {
            return null;
        }
    }
	
	*/
	
	public static String checkPlaca(String usuario) {

        String key = "usuario", tableName = "vehiculos", operation = "query";

        String payload = "{"
                + "\"operation\":\"" + operation + "\","
                + "\"tableName\":\"" + tableName + "\","
                + "\"payload\":{"
                + "\"IndexName\":" + "\"" + key + "\","
                + "\"KeyConditionExpression\":" + "\"" + key + " = :v1\","
                + "\"ExpressionAttributeValues\": {"
                + "\":v1\": \"" + usuario + "\"}"
                + "}"
                + "}";

        StringBuffer response = postRequest(payload);

        if (response != null) {

        } else {
            return null;
        }

        JSONObject jsonObject;

        try {

            jsonObject = new JSONObject(response.toString());

            if (jsonObject.getDouble("Count") == 0) {
                return null;
            }

            // "I want to iterate though the objects in the array..."
            JSONArray jsonArray = jsonObject.getJSONArray("Items");
            
            String currentPlaca =null;

            for (int i = 0; i < jsonArray.length(); i++) {
                currentPlaca = jsonArray.getJSONObject(i).getString("placa");
            }

            return currentPlaca;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }
    }
	
	public static boolean updateShipment(String primaryKeyValue, Object sortKeyValue, String key, Object value) {

        JSONObject jsonObject = null;
        
        String operation = "update";
        String tableName = "envios";
        String primaryKey = "usuario";
        String sortKey = "fecha";


        String payload;

        payload = "{"
                + "\"operation\":\"" + operation + "\","
                + "\"tableName\":\"" + tableName + "\","
                + "\"payload\":{"
                + "\"Key\":{"
                + "\"" + primaryKey + "\":\"" + primaryKeyValue + "\","
                + "\"" + sortKey + "\":\"" + sortKeyValue + "\""
                + "},"
                + "\"UpdateExpression\": \"set " + key + " = :val\","
                + "\"ExpressionAttributeValues\": {"
                + "\":val\":\"" + value + "\""
                + "},"
                //+ "\"ReturnValues\":\"ALL_NEW\""
                + "\"ReturnValues\": \"UPDATED_NEW\""
                + "}"
                + "}";

        StringBuffer response = postRequest(payload);
        
        System.out.println("\nRespuesta: "+response);

        String objectFound;

        if (response != null) {

            try {

                jsonObject = new JSONObject(response.toString());

                String statuscode = jsonObject.getJSONObject("ResponseMetadata").get("HTTPStatusCode").toString();

                try {
                    objectFound = jsonObject.getJSONObject("Attributes").getString(key).toString();
                    System.out.println("\nObjecto encontrado: "+objectFound);

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

                if (value.equals(objectFound.toString())) {
                    return true;
                } else {
                    return false;
                }


            } catch (Exception e) {
                return false;
            }

        } else {
            return false;
        }
    }
	
	public static boolean putItem(String tableName, String objectJSON) {

        String operation = "create";

        String payload = "{"
                + "\"operation\":\"" + operation + "\","
                + "\"tableName\":\"" + tableName + "\","
                + "\"payload\":{"
                + "\"Item\":" + objectJSON
                + "}"
                + "}";

        if (postRequest(payload) != null) {
            return true;

        } else {
            return false;
        }
    }
	
}
