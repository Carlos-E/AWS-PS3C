package clases;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class Usuario {

	private BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJSINT4F7K5BSGDRA",
			"512NOFNfUl4hAZMyFEHpt7ygdmksBVzmfXr6xLsR");

	private DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
			.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build());

	private String usuario, rol, nombre, apellido, clave, correo, direccion, telefono;

	public Usuario() {
		super();
	}

	// METODOS PARA MANIPULAR LA BD
	public Usuario load(String patente) {
		System.out.println("Loading object");
		return mapper.load(Usuario.class, patente);
	}

	public List<Usuario> scan() {
		System.out.println("Scaning table");
		return mapper.scan(Usuario.class, new DynamoDBScanExpression());
	}

	public void save() {
		System.out.println("Saving object");
		mapper.save(this);
	}

	public void delete() {
		System.out.println("Deleting object");
		mapper.delete(this);
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
		return "Usuario [awsCreds=" + awsCreds + ", mapper=" + mapper + ", usuario=" + usuario + ", rol=" + rol
				+ ", nombre=" + nombre + ", apellido=" + apellido + ", clave=" + clave + ", correo=" + correo
				+ ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}

}
