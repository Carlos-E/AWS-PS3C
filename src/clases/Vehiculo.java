package clases;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "vehiculos")
public class Vehiculo {

	private String placa, tipo, peso, pesoMax, espacio, espacioMax;
	private String estado, usuario, empresa, latitud, longitud;

	public Vehiculo() {
		super();
	}

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

	@DynamoDBIndexHashKey(globalSecondaryIndexName="usuario")
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