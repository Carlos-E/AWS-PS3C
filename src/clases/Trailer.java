package clases;

public class Trailer {
	
	String patente, tipo, peso, espacio, estado, camion, empresa;

	public Trailer() {
		super();
	}

	public Trailer(String patente, String tipo, String peso, String espacio, String estado, String camion, String empresa) {
		super();
		this.patente = patente;
		this.tipo = tipo;
		this.peso = peso;
		this.espacio = espacio;
		this.estado = estado;
		this.camion = camion;
		this.empresa = empresa;
	}

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
