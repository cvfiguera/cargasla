package cl.subtel.business.vo;

public class BitacoraVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 9011737400585002448L;
	private String nombre;
	private Integer version;
	private Integer operadora;
	private String mes;
	private String year;
	
	public BitacoraVO() {
		super();
	}

	public BitacoraVO(String nombre, Integer version, Integer operadora, String mes, String year) {
		super();
		this.nombre = nombre;
		this.version = version;
		this.operadora = operadora;
		this.mes = mes;
		this.year = year;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getOperadora() {
		return operadora;
	}

	public void setOperadora(Integer operadora) {
		this.operadora = operadora;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
