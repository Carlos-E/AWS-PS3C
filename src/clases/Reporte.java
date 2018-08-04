package clases;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
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

	public Reporte(String usuario, String hora, String nota, boolean visto) {
		super();
		this.hora = hora;
		this.nota = nota;
		this.usuario = usuario;
		this.visto = visto;
	}

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

	@DynamoDBAttribute
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	@DynamoDBAttribute
	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

}
