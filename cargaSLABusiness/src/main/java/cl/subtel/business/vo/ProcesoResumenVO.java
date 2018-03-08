package cl.subtel.business.vo;

import java.util.Date;

public class ProcesoResumenVO {
	
	private Integer id;
	private String proceso;
	private String operadora;
	private String mes;
	private String ano;
	private String usuario;
	private Date fecha;
	private Integer estado;
	
	public ProcesoResumenVO() {
		super();
	}

	public ProcesoResumenVO(Integer id, String proceso, String operadora, String mes, String ano, String usuario,
			Date fecha, Integer estado) {
		super();
		this.id = id;
		this.proceso = proceso;
		this.operadora = operadora;
		this.mes = mes;
		this.ano = ano;
		this.usuario = usuario;
		this.fecha = fecha;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
