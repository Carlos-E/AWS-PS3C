package clases;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "vehiculos")
public class Vehiculo {

	private String placa;
	private String tipo, estado, usuario, empresa, latitud, longitud;
	private double pesoMax,espacioMax;

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

	public double getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(double pesoMax) {
		this.pesoMax = pesoMax;
	}

	public double getEspacioMax() {
		return espacioMax;
	}

	public void setEspacioMax(double espacioMax) {
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
		return "Vehiculo [placa=" + placa + ", tipo=" + tipo + ", estado=" + estado + ", usuario=" + usuario
				+ ", empresa=" + empresa + ", latitud=" + latitud + ", longitud=" + longitud + ", pesoMax=" + pesoMax
				+ ", espacioMax=" + espacioMax + "]";
	}

}