package clases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

public class DB extends DynamoDBMapper {

	//VARIABLES
	private final static String AccessKeyID = "AKIAJSINT4F7K5BSGDRA";
	private final static String SecretKey = "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";
	//VARIABLES

	private static Regions region = Regions.US_EAST_1;

	private static BasicAWSCredentials basicCreds = new BasicAWSCredentials(AccessKeyID, SecretKey);
	private static AWSStaticCredentialsProvider staticCreds = new AWSStaticCredentialsProvider(basicCreds);

	public DB() {
		super(AmazonDynamoDBClientBuilder.standard().withRegion(region).withCredentials(staticCreds).build());
	}

	public double getEspacioTrailer(String patente) {

		double space = 0;

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(patente));
		eav.put(":v2", new AttributeValue().withS("entregado"));

		List<Envio> EnviosEnTrailer = this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("trailer").withConsistentRead(false)
						.withKeyConditionExpression("trailer = :v1").withFilterExpression("estado <> :v2").withExpressionAttributeValues(eav));

		for (int i = 0; i < EnviosEnTrailer.size(); i++) {
			space = space + EnviosEnTrailer.get(i).getEspacio();
		}

		return space;
	}

	public double getEspacioVehiculo(String placa) {

		double space = 0;

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(placa));
		eav.put(":v2", new AttributeValue().withS("entregado"));

		List<Envio> EnviosEnVehiculo = this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("camion").withConsistentRead(false)
						.withKeyConditionExpression("camion = :v1").withFilterExpression("estado <> :v2").withExpressionAttributeValues(eav));

		for (int i = 0; i < EnviosEnVehiculo.size(); i++) {
			space = space + EnviosEnVehiculo.get(i).getEspacio();
		}

		return space;
	}

	public double getPesoTrailer(String patente) {

		double peso = 0;

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(patente));
		eav.put(":v2", new AttributeValue().withS("entregado"));

		List<Envio> EnviosEnTrailer = this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("trailer").withConsistentRead(false)
						.withKeyConditionExpression("trailer = :v1").withFilterExpression("estado <> :v2").withExpressionAttributeValues(eav));

		for (int i = 0; i < EnviosEnTrailer.size(); i++) {
			peso = peso + EnviosEnTrailer.get(i).getPeso();
		}

		return peso;
	}

	public double getPesoVehiculo(String placa) {

		double peso = 0;

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(placa));
		eav.put(":v2", new AttributeValue().withS("entregado"));

		List<Envio> EnviosEnVehiculo = this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("camion").withConsistentRead(false)
						.withKeyConditionExpression("camion = :v1").withFilterExpression("estado <> :v2")
						.withExpressionAttributeValues(eav));

		for (int i = 0; i < EnviosEnVehiculo.size(); i++) {
			peso = peso + EnviosEnVehiculo.get(i).getPeso();
		}

		return peso;
	}

	public List<Envio> getEnviosTrailer(String patente) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(patente));

		return this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("trailer").withConsistentRead(false)
						.withKeyConditionExpression("trailer = :v1").withExpressionAttributeValues(eav));
	}

	public List<Envio> getEnviosVehiculo(String placa) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(placa));

		return this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("camion").withConsistentRead(false)
						.withKeyConditionExpression("camion = :v1").withExpressionAttributeValues(eav));

	}

	public List<Envio> getEnviosPendientesTrailer(String patente) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(patente));
		eav.put(":v2", new AttributeValue().withS("entregado"));

		return this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("trailer").withConsistentRead(false)
						.withKeyConditionExpression("trailer = :v1").withFilterExpression("estado <> :v2")
						.withExpressionAttributeValues(eav));

	}

	public List<Envio> getEnviosPendientesVehiculo(String placa) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(placa));
		eav.put(":v2", new AttributeValue().withS("entregado"));

		return this.query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("camion").withConsistentRead(false)
						.withKeyConditionExpression("camion = :v1").withFilterExpression("estado <> :v2")
						.withExpressionAttributeValues(eav));

	}

	public boolean estaOcupado(String nombre, String vehiculo) {

		boolean resultado = false;

		List<Vehiculo> vehiculos = this.scan(Vehiculo.class, new DynamoDBScanExpression());
		List<Trailer> trailers = this.scan(Trailer.class, new DynamoDBScanExpression());

		if (vehiculo.equals("null")) {
			for (int i = 0; i < vehiculos.size(); i++) {
				if (nombre.equals(vehiculos.get(i).getUsuario())) {
					System.out.println("sale");
					return true;
				}
			}
		} else if (nombre.equals("null")) {
			for (int i = 0; i < trailers.size(); i++) {
				if (vehiculo.equals(trailers.get(i).getCamion())) {
					return true;
				}
			}
		}
		return resultado;
	}

	public String checkPlaca(String conductor) {

		System.out.println("Chequeando vehiculo de conductor:" + conductor);

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(conductor));

		List<Vehiculo> Vehiculos = this.query(Vehiculo.class,
				new DynamoDBQueryExpression<Vehiculo>().withIndexName("usuario").withConsistentRead(false)
						.withKeyConditionExpression("usuario = :v1").withExpressionAttributeValues(eav));

		if (Vehiculos.size() == 0) {
			return null;
		}
		
		System.out.println("Vehiculo del conductor:" + Vehiculos.get(0).getPlaca());

		return Vehiculos.get(0).getPlaca();
	}

	public Trailer getTrailerRemolque(String placa) {

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(placa));

		List<Trailer> trailers = this.query(Trailer.class,
				new DynamoDBQueryExpression<Trailer>().withIndexName("camion").withConsistentRead(false)
						.withKeyConditionExpression("camion = :v1").withExpressionAttributeValues(eav));

		if (trailers.size() == 0) {
			return null;
		}

		return trailers.get(0);
	}

	public String getEstadoVehiculo(String placa) {

		String estado = "";

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);

		vehiculo = this.load(vehiculo);

		if (vehiculo != null) {

			if (vehiculo.getUsuario().equals("ninguno")) {
				estado = estado + "sin conductor";
			}

			if (this.getEnviosPendientesVehiculo(vehiculo.getPlaca()).size() > 0) {
				estado = estado + " con envíos";
			} else {
				estado = estado + " sin envíos";
			}

			if (vehiculo.getTipo().equals("remolque")) {
				Trailer trailer = this.getTrailerRemolque(vehiculo.getPlaca());
				if (trailer == null) {
					estado = estado + " sin tráiler";
				}else{
					estado = estado + " con tráiler <a href=\"/traileres/listar.jsp?search="+trailer.getPatente()+"\">"+trailer.getPatente()+"</a>";
				}

			}
		}

		return estado;
	}

	public String getEstadoTrailer(String patente) {

		String estado = "";

		Trailer trailer = new Trailer();
		trailer.setPatente(patente);

		trailer = this.load(trailer);

		if (trailer != null) {

			if (trailer.getCamion().equals("ninguno")) {
				estado = estado + "sin remolque";
			} else {
				estado = estado + " con remolque <a href=\"/vehiculos/listar.jsp?search="+trailer.getCamion()+"\">"+trailer.getCamion()+"</a>";
			}

			if (this.getEnviosPendientesTrailer(trailer.getPatente()).size() > 0) {
				estado = estado + " con envíos";
			} else {
				estado = estado + " sin envíos";
			}

		}

		return estado;
	}

}
