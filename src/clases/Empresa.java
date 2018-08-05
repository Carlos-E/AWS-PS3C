package clases;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "empresas")
public class Empresa {

	private String nombre, nit, rut, direccion, telefono, correo;

	public Empresa() {
		super();
	}

	// METODOS PARA MANIPULAR LA BD
	public Empresa load(String usuario, String fecha) {
		return new DB().getMapper().load(Empresa.class, usuario, fecha);
	}

	public List<Empresa> scan() {
		return new DB().getMapper().scan(Empresa.class, new DynamoDBScanExpression());
	}

	public void save() {
		new DB().getMapper().save(this);
	}

	public void delete() {
		new DB().getMapper().delete(this);
	}
	// METODOS PARA MANIPULAR LA BD

	@DynamoDBHashKey
	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
