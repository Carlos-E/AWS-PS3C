package clases;

public class ubicacion {

	String placa;
	double nlatitud, nlongitud;
	double ulatitud, ulongitud;
	
	public ubicacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getNlatitud() {
		return nlatitud;
	}

	public void setNlatitud(double nlatitud) {
		this.nlatitud = nlatitud;
	}

	public double getNlongitud() {
		return nlongitud;
	}

	public void setNlongitud(double nlongitud) {
		this.nlongitud = nlongitud;
	}

	public double getUlatitud() {
		return ulatitud;
	}

	public void setUlatitud(double ulatitud) {
		this.ulatitud = ulatitud;
	}

	public double getUlongitud() {
		return ulongitud;
	}

	public void setUlongitud(double ulongitud) {
		this.ulongitud = ulongitud;
	}

	@Override
	public String toString() {
		return "ubicacion [placa=" + placa + ", nlatitud=" + nlatitud + ", nlongitud=" + nlongitud + ", ulatitud="
				+ ulatitud + ", ulongitud=" + ulongitud + "]";
	}
	

}
