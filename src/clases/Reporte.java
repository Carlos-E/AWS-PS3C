package clases;

public class Reporte {
	
	String hora, nota, usuario;
	String visto;
	
	public Reporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reporte(String hora, String nota, String usuario, String visto) {
		super();
		this.hora = hora;
		this.nota = nota;
		this.usuario = usuario;
		this.visto = visto;
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

	public String isVisto() {
		return visto;
	}

	public void setVisto(String visto) {
		this.visto = visto;
	}

	@Override
	public String toString() {
		return "Reporte [hora=" + hora + ", nota=" + nota + ", usuario=" + usuario + ", visto=" + visto + "]";
	}
	
	
	
}
