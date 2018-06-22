package clases;

public class Camion {

	String placa, tipo, peso, espacio, estado, ubicacion, usuario, empresa;
	
	public Camion() {
		super();
	}

	public Camion(String placa, String tipo, String peso, String espacio, String estado,
			String ubicacion, String usuario, String empresa) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.peso = peso;
		this.espacio = espacio;
		this.estado = estado;
		this.ubicacion = ubicacion;
		this.usuario = usuario;
		this.empresa = empresa;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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

	@Override
	public String toString() {
		return "Camion [placa=" + placa + ", tipo=" + tipo + ", peso=" + peso + ", espacio=" + espacio + ", estado="
				+ estado + ", ubicacion=" + ubicacion + ", usuario=" + usuario + ", empresa=" + empresa + "]";
	}

	
}