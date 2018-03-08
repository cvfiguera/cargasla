package cl.subtel.business.vo;

public class RespuestaSLA {
	
	private String respuesta;
	private String estadoFinal;
	
	public RespuestaSLA() {
		super();
	}

	public RespuestaSLA(String respuesta, String estadoFinal) {
		super();
		this.respuesta = respuesta;
		this.estadoFinal = estadoFinal;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(String estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
}
