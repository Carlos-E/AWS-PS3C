package clases;

import org.json.JSONException;
import org.json.JSONObject;

public class Vehiculo {

	String placa, tipo, peso, espacio, pesoMax, espacioMax, estado, usuario, empresa, latitud, longitud;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String placa, String tipo, String peso, String espacio, String pesoMax, String espacioMax,
			String estado, String usuario, String empresa, String latitud, String longitud) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.peso = peso;
		this.espacio = espacio;
		this.pesoMax = pesoMax;
		this.espacioMax = espacioMax;
		this.estado = estado;
		this.usuario = usuario;
		this.empresa = empresa;
		this.latitud = latitud;
		this.longitud = longitud;
	}

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