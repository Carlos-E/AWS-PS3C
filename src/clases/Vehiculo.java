package clases;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class Vehiculo {

	private BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	private DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build());

	private String placa, tipo, peso, espacio, pesoMax, espacioMax, estado, usuario, empresa, latitud, longitud;

	public Vehiculo() {
		super();
	}

	// METODOS PARA MANIPULAR LA BD
	public Vehiculo load(String patente) {
		System.out.println("Loading object");
		return mapper.load(Vehiculo.class, patente);
	}

	public List<Vehiculo> scan() {
		System.out.println("Scaning table");
		return mapper.scan(Vehiculo.class, new DynamoDBScanExpression());
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
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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

	
	public String getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(String pesoMax) {
		this.pesoMax = pesoMax;
	}

	
	public String getEspacioMax() {
		return espacioMax;
	}

	public void setEspacioMax(String espacioMax) {
		this.espacioMax = espacioMax;
	}

	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	
	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	
	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "Vehiculo [placa=" + placa + ", tipo=" + tipo + ", peso=" + peso + ", espacio=" + espacio + ", pesoMax="
				+ pesoMax + ", espacioMax=" + espacioMax + ", estado=" + estado + ", usuario=" + usuario + ", empresa="
				+ empresa + ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}

}