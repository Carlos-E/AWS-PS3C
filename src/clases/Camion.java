package clases;

public class Camion {

	String placa, tipo, capacidad, espacio, estado, destino, ubicacion, origen, usuario, empresa;
	
	public Camion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Camion(String placa, String tipo, String capacidad, String espacio, String estado, String destino,
			String ubicacion, String origen, String conductor, String empresa) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.espacio = espacio;
		this.estado = estado;
		this.destino = destino;
		this.ubicacion = ubicacion;
		this.origen = origen;
		this.usuario = conductor;
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

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
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

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String conductor) {
		this.usuario = conductor;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "camion [placa=" + placa + ", tipo=" + tipo + ", capacidad=" + capacidad + ", espacio=" + espacio
				+ ", estado=" + estado + ", destino=" + destino + ", ubicacion=" + ubicacion + ", origen=" + origen
				+ ", usuario=" + usuario + ", empresa=" + empresa + "]";
	}
	
	
}