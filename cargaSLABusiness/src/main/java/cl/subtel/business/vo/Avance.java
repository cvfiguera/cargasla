package cl.subtel.business.vo;

public class Avance implements java.io.Serializable {
	
	private static final long serialVersionUID = -6549948937642853544L;
	private Integer archivoId;
	private Double valor;
	
	public Avance() {
		super();
	}

	public Avance(Integer archivoId, Double valor) {
		super();
		this.archivoId = archivoId;
		this.valor = valor;
	}

	public Integer getArchivoId() {
		return archivoId;
	}

	public void setArchivoId(Integer archivoId) {
		this.archivoId = archivoId;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
