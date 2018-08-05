package clases;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "usuarios")
public class Usuario {

	private String usuario, rol, nombre, apellido, clave, correo, direccion, telefono;

	public Usuario() {
		super();
	}

	// METODOS PARA MANIPULAR LA BD
	public Usuario load(String usuario, String fecha) {
		return new DB().getMapper().load(Usuario.class, usuario, fecha);
	}

	public List<Usuario> scan() {
		return new DB().getMapper().scan(Usuario.class, new DynamoDBScanExpression());
	}

	public void save() {
		new DB().getMapper().save(this);
	}

	public void delete() {
		new DB().getMapper().delete(this);
	}
	// METODOS PARA MANIPULAR LA BD

	@DynamoDBHashKey
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
		return "Usuario [usuario=" + usuario + ", rol=" + rol + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", clave=" + clave + ", correo=" + correo + ", direccion=" + direccion + ", telefono=" + telefono
				+ "]";
	}

}
