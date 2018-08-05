package clases;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "reportes")
public class Reporte {

	private BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	private DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build());


	private String hora;
	private String nota;
	private String usuario;

	private boolean visto;

	public Reporte() {
		super();
	}

	public Reporte load(String usuario, String fecha) {
		return mapper.load(Reporte.class, usuario, fecha);
	}

	public void save() {
		mapper.save(this);
	}

	public void delete() {
		mapper.delete(this);
	}
	
	public List<Reporte> scan() {
		return mapper.scan(Reporte.class, new DynamoDBScanExpression());
	}

	public List<Reporte> scan(DynamoDBScanExpression dynamoDBScanExpression) {
		return mapper.scan(Reporte.class, dynamoDBScanExpression);
	}

	@DynamoDBHashKey
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@DynamoDBRangeKey
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	public boolean isVisto() {
		return visto;
	}
	
	public void setVisto(boolean visto) {
		this.visto = visto;
	}

}
