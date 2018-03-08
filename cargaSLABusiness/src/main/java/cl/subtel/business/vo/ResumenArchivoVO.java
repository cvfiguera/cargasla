package cl.subtel.business.vo;

public class ResumenArchivoVO implements java.io.Serializable {
	
	private static final long serialVersionUID = -3861057097506651031L;
	private String archivo;
	private Integer estadoId;
	private String estado;
	
	public ResumenArchivoVO() {
		super();
	}

	public ResumenArchivoVO(String archivo, Integer estadoId, String estado) {
		super();
		this.archivo = archivo;
		this.estadoId = estadoId;
		this.estado = estado;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
