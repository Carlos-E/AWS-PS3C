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

	private final static String AccessKeyID = "AKIAJSINT4F7K5BSGDRA";
	private final static String SecretKey = "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";
	
	private static Regions region = Regions.US_EAST_1;

	private static BasicAWSCredentials basicCreds = new BasicAWSCredentials(AccessKeyID, SecretKey);
	private static AWSStaticCredentialsProvider staticCreds = new AWSStaticCredentialsProvider(basicCreds);

	public DB() {
		super(AmazonDynamoDBClientBuilder.standard().withRegion(region).withCredentials(staticCreds).build());
	}
	
	public double getTrailerSpace(String patente){
		
		double space=0;

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(patente));

		List<Envio> EnviosEnTrailer = new DB().query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("trailer").withConsistentRead(false)
						.withKeyConditionExpression("trailer = :v1").withExpressionAttributeValues(eav));		
		
		for(int i=0;i<EnviosEnTrailer.size();i++){
			space = space + EnviosEnTrailer.get(i).getEspacio();
		}
		
		return space;
	}
	
	public double getVehicleSpace(String placa){
		
		double space=0;

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(placa));

		List<Envio> EnviosEnVehiculo = new DB().query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("camion").withConsistentRead(false)
						.withKeyConditionExpression("camion = :v1").withExpressionAttributeValues(eav));		
		
		for(int i=0;i<EnviosEnVehiculo.size();i++){
			space = space + EnviosEnVehiculo.get(i).getEspacio();
		}
		
		return space;
	}
	
	public List<Envio> getEnviosTrailer(String patente) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(patente));

		return new DB().query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("trailer").withConsistentRead(false)
						.withKeyConditionExpression("trailer = :v1").withExpressionAttributeValues(eav));
	}

	public List<Envio> getEnviosVehiculo(String placa) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(placa));

		return new DB().query(Envio.class,
				new DynamoDBQueryExpression<Envio>().withIndexName("camion").withConsistentRead(false)
						.withKeyConditionExpression("camion = :v1").withExpressionAttributeValues(eav));

	}

	
	public boolean estaOcupado(String nombre, String vehiculo){
		
		boolean resultado = false;
		
		List<Vehiculo> vehiculos = new DB().scan(Vehiculo.class, new DynamoDBScanExpression());
		List<Trailer> trailers = new DB().scan(Trailer.class, new DynamoDBScanExpression());
		
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
	
}
