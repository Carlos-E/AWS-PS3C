package clases;

public class Ubicacion {

	String placa;
	String hora;
	double latitud, longitud;
	
	public Ubicacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "ubicacion [placa=" + placa + ", hora=" + hora + ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}

}
