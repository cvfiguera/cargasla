package cl.subtel.business.vo;

import java.util.Date;

public class EstatusSLA implements java.io.Serializable {

	private static final long serialVersionUID = 786130671802080991L;
	private Integer id;
	private Integer codigo;
	private String tipo;
	private String  nombre;
	private Integer version;
	private Date fecha;
	private Double valor;
	private String  estado;
	private Boolean procesado;
	
	public EstatusSLA() {
		super();
	}

	public EstatusSLA(Integer id, Integer codigo, String tipo, String nombre, Integer version, Date fecha, Double valor,
			String estado, Boolean procesado) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.version = version;
		this.fecha = fecha;
		this.valor = valor;
		this.estado = estado;
		this.procesado = procesado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getProcesado() {
		return procesado;
	}

	public void setProcesado(Boolean procesado) {
		this.procesado = procesado;
	}
}
