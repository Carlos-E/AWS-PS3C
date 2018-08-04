package clases;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

public class Envio {

	String  usuario, fecha, destino, origen, destinoLatLong, origenLatLong, estado, peso, espacio, tipo, camion, trailer, empresa, descripcion;
	boolean chequeoCarga, chequeoDescarga;
	
	public Envio() {
		super();
	}

	@DynamoDBHashKey
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@DynamoDBAttribute
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@DynamoDBAttribute
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@DynamoDBAttribute
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@DynamoDBAttribute
	public String getDestinoLatLong() {
		return destinoLatLong;
	}

	public void setDestinoLatLong(String destinoLatLong) {
		this.destinoLatLong = destinoLatLong;
	}

	@DynamoDBAttribute
	public String getOrigenLatLong() {
		return origenLatLong;
	}

	public void setOrigenLatLong(String origenLatLong) {
		this.origenLatLong = origenLatLong;
	}

	@DynamoDBAttribute
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@DynamoDBAttribute
	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	@DynamoDBAttribute
	public String getEspacio() {
		return espacio;
	}

	public void setEspacio(String espacio) {
		this.espacio = espacio;
	}

	@DynamoDBAttribute
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@DynamoDBAttribute
	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	@DynamoDBAttribute
	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	@DynamoDBAttribute
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@DynamoDBAttribute
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@DynamoDBAttribute
	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	public boolean isChequeoCarga() {
		return chequeoCarga;
	}

	
	public void setChequeoCarga(boolean chequeoCarga) {
		this.chequeoCarga = chequeoCarga;
	}

	@DynamoDBAttribute
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