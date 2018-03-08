package cl.subtel.business.vo;

import java.util.Date;
import java.util.List;

public class ProcesoVO {
	
	private Integer procesoId;
	private String tipo;
	private Integer usuario;
	private Date fecha;
	private Integer operadora;
	private Integer mes;
	private Integer anio;
	private List<ArchivoVO> archivos;
	
	public ProcesoVO() {
		super();
	}

	public ProcesoVO(Integer procesoId, String tipo, Integer usuario, Date fecha, Integer operadora, Integer mes,
			Integer anio, List<ArchivoVO> archivos) {
		super();
		this.procesoId = procesoId;
		this.tipo = tipo;
		this.usuario = usuario;
		this.fecha = fecha;
		this.operadora = operadora;
		this.mes = mes;
		this.anio = anio;
		this.archivos = archivos;
	}

	public Integer getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(Integer procesoId) {
		this.procesoId = procesoId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getOperadora() {
		return operadora;
	}

	public void setOperadora(Integer operadora) {
		this.operadora = operadora;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public List<ArchivoVO> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<ArchivoVO> archivos) {
		this.archivos = archivos;
	}
}
