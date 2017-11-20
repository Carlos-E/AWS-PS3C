package clases;

public class empresa {
	
	String nombre, nit, rut, direccion, telefono, correo;

	public empresa(String nombre, String nit, String rut, String direccion, String telefono, String correo) {
		super();
		this.nombre = nombre;
		this.nit = nit;
		this.rut = rut;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}

	public empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "empresa [nombre=" + nombre + ", nit=" + nit + ", rut=" + rut + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", correo=" + correo + "]";
	}
}
