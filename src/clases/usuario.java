package clases;

public class usuario {
	
	String usuario, rol, nombre, apellido, clave, correo, direccion, telefono;

	public usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public usuario(String usuario, String rol, String nombre, String apellido, String clave, String correo,
			String direccion, String telefono) {
		super();
		this.usuario = usuario;
		this.rol = rol;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	@Override
	public String toString() {
		return "usuario [usuario=" + usuario + ", rol=" + rol + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", clave=" + clave + ", correo=" + correo + ", direccion=" + direccion + ", telefono=" + telefono
				+ "]";
	}

	
}
