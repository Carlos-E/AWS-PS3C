package clases;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

@DynamoDBTable(tableName = "envios")
public class Envio {

	private BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	private DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build());

	private String usuario, fecha, destino, origen, destinoLatLong, origenLatLong;
	private String estado, peso, espacio, tipo, camion, trailer, empresa, descripcion;
	private boolean chequeoCarga, chequeoDescarga;

	public Envio() {
		super();
	}

	// METODOS PARA MANIPULAR LA BD
	public Envio load(String usuario, String fecha) {
		System.out.println("Loading object");
		return mapper.load(Envio.class, usuario, fecha);
	}

	public void save() {
		System.out.println("Saving object");
		mapper.save(this);
	}

	public void delete() {
		System.out.println("Deleting object");
		mapper.delete(this);
	}

	public List<Envio> scan() {
		System.out.println("Scaning table");
		return mapper.scan(Envio.class, new DynamoDBScanExpression());
	}

	public List<Envio> scan(DynamoDBScanExpression dynamoDBScanExpression) {
		System.out.println("Scaning table");
		return mapper.scan(Envio.class, dynamoDBScanExpression);
	}
	// METODOS PARA MANIPULAR LA BD

	@DynamoDBHashKey
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@DynamoDBRangeKey
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestinoLatLong() {
		return destinoLatLong;
	}

	public void setDestinoLatLong(String destinoLatLong) {
		this.destinoLatLong = destinoLatLong;
	}

	public String getOrigenLatLong() {
		return origenLatLong;
	}

	public void setOrigenLatLong(String origenLatLong) {
		this.origenLatLong = origenLatLong;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	public boolean isChequeoCarga() {
		return chequeoCarga;
	}

	public void setChequeoCarga(boolean chequeoCarga) {
		this.chequeoCarga = chequeoCarga;
	}

	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	public boolean isChequeoDescarga() {
		return chequeoDescarga;
	}

	public void setChequeoDescarga(boolean chequeoDescarga) {
		this.chequeoDescarga = chequeoDescarga;
	}

	@Override
	public String toString() {
		return "Envio [usuario=" + usuario + ", fecha=" + fecha + ", destino=" + destino + ", origen=" + origen
				+ ", destinoLatLong=" + destinoLatLong + ", origenLatLong=" + origenLatLong + ", estado=" + estado
				+ ", peso=" + peso + ", espacio=" + espacio + ", tipo=" + tipo + ", camion=" + camion + ", trailer="
				+ trailer + ", empresa=" + empresa + ", descripcion=" + descripcion + ", chequeoCarga=" + chequeoCarga
				+ ", chequeoDescarga=" + chequeoDescarga + "]";
	}

}