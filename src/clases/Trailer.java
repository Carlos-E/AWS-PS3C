package clases;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class Trailer {

	private BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	private DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build());

	private String patente, tipo, peso, espacio, estado, camion, empresa;

	public Trailer() {
		super();
	}

	// METODOS PARA MANIPULAR LA BD
	public Trailer load(String patente) {
		System.out.println("Loading object");
		return mapper.load(Trailer.class, patente);
	}

	public List<Trailer> scan() {
		System.out.println("Scaning table");
		return mapper.scan(Trailer.class, new DynamoDBScanExpression());
	}

	public void save() {
		System.out.println("Saving object");
		mapper.save(this);
	}

	public void delete() {
		System.out.println("Deleting object");
		mapper.delete(this);
	}
	// METODOS PARA MANIPULAR LA BD

	@DynamoDBHashKey
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	 
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	 
	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	 
	public String getEspacio() {
		return espacio;
	}

	public void setEspacio(String espacio) {
		this.espacio = espacio;
	}

	 
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	 
	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	 
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Trailer [patente=" + patente + ", tipo=" + tipo + ", peso=" + peso + ", espacio=" + espacio
				+ ", estado=" + estado + ", camion=" + camion + ", empresa=" + empresa + "]";
	}

}
