package clases;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "reportes")
public class Reporte {

	private String hora;
	private String nota;
	private String usuario;

	private boolean visto;

	public Reporte() {
		super();
	}

	// METODOS PARA MANIPULAR LA BD
	public Reporte load(String usuario, String fecha) {
		return new DB().getMapper().load(Reporte.class, usuario, fecha);
	}

	public List<Reporte> scan() {
		return new DB().getMapper().scan(Reporte.class, new DynamoDBScanExpression());
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

	@DynamoDBRangeKey
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

}
