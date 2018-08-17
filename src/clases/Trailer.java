package clases;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "trailers")
public class Trailer {

	private String patente, tipo, estado, camion, empresa;
	private double pesoMax, espacioMax;

	public Trailer() {
		super();
	}

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

	@Override
	public String toString() {
		return "Trailer [patente=" + patente + ", tipo=" + tipo + ", estado=" + estado + ", camion=" + camion
				+ ", empresa=" + empresa + ", pesoMax=" + pesoMax + ", espacioMax=" + espacioMax + "]";
	}

}
