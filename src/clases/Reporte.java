package clases;

public class Reporte {
	
	String hora, nota, usuario;
	
	public Reporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reporte(String hora, String nota, String usuario) {
		super();
		this.hora = hora;
		this.nota = nota;
		this.usuario = usuario;
	}

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "reporte [hora=" + hora + ", nota=" + nota + ", usuario=" + usuario + "]";
	}

	

}
