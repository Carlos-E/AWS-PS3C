package clases;

public class envio {

	String  usuario, fecha, destino, origen, destinoLatLong, origenLatLong, estado, espacio, tipo, tiempoCarga, tiempoDescargaUsuario, camion, trailer, empresa;
	boolean chequeoCarga, chequeoDescarga;
	
	public envio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public envio(String usuario, String fecha, String destino, String origen, String destinoLatLong,
			String origenLatLong, String estado, String espacio, String tipo, String tiempoCarga,
			String tiempoDescargaUsuario, String camion, String trailer, String empresa, boolean chequeoCarga,
			boolean chequeoDescarga) {
		super();
		this.usuario = usuario;
		this.fecha = fecha;
		this.destino = destino;
		this.origen = origen;
		this.destinoLatLong = destinoLatLong;
		this.origenLatLong = origenLatLong;
		this.estado = estado;
		this.espacio = espacio;
		this.tipo = tipo;
		this.tiempoCarga = tiempoCarga;
		this.tiempoDescargaUsuario = tiempoDescargaUsuario;
		this.camion = camion;
		this.trailer = trailer;
		this.empresa = empresa;
		this.chequeoCarga = chequeoCarga;
		this.chequeoDescarga = chequeoDescarga;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

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

	public String getTiempoCarga() {
		return tiempoCarga;
	}

	public void setTiempoCarga(String tiempoCarga) {
		this.tiempoCarga = tiempoCarga;
	}

	public String getTiempoDescargaUsuario() {
		return tiempoDescargaUsuario;
	}

	public void setTiempoDescargaUsuario(String tiempoDescargaUsuario) {
		this.tiempoDescargaUsuario = tiempoDescargaUsuario;
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

	public boolean isChequeoCarga() {
		return chequeoCarga;
	}

	public void setChequeoCarga(boolean chequeoCarga) {
		this.chequeoCarga = chequeoCarga;
	}

	public boolean isChequeoDescarga() {
		return chequeoDescarga;
	}

	public void setChequeoDescarga(boolean chequeoDescarga) {
		this.chequeoDescarga = chequeoDescarga;
	}

	@Override
	public String toString() {
		return "envio [usuario=" + usuario + ", fecha=" + fecha + ", destino=" + destino + ", origen=" + origen
				+ ", destinoLatLong=" + destinoLatLong + ", origenLatLong=" + origenLatLong + ", estado=" + estado
				+ ", espacio=" + espacio + ", tipo=" + tipo + ", tiempoCarga=" + tiempoCarga
				+ ", tiempoDescargaUsuario=" + tiempoDescargaUsuario + ", camion=" + camion + ", trailer=" + trailer
				+ ", empresa=" + empresa + ", chequeoCarga=" + chequeoCarga + ", chequeoDescarga=" + chequeoDescarga
				+ "]";
	}
	
}