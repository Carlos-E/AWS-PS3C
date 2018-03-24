package clases;

import org.json.JSONException;
import org.json.JSONObject;

public class Ubicacion {

	String placa;
	String hora;
	String latitud, longitud;
	
	public Ubicacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("placa", getPlaca());
            jsonObject.put("hora", getHora());
            jsonObject.put("latitud", getLatitud());
            jsonObject.put("longitud", getLongitud());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "null";
        }

    }

	@Override
	public String toString() {
		return "ubicacion [placa=" + placa + ", hora=" + hora + ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}

}
