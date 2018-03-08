package cl.subtel.business.vo;

import java.math.BigInteger;

public class ResumenOperadoraVO implements java.io.Serializable {
	
	private static final long serialVersionUID = -8883883783644496365L;
	private Integer operadoraId;
	private String operadora;
	private BigInteger validados;
	private BigInteger cargados;
	private BigInteger facturados;
	
	public ResumenOperadoraVO() {
		super();
	}

	public ResumenOperadoraVO(Integer operadoraId, String operadora, BigInteger validados, BigInteger cargados,
			BigInteger facturados) {
		super();
		this.operadoraId = operadoraId;
		this.operadora = operadora;
		this.validados = validados;
		this.cargados = cargados;
		this.facturados = facturados;
	}

	public Integer getOperadoraId() {
		return operadoraId;
	}

	public void setOperadoraId(Integer operadoraId) {
		this.operadoraId = operadoraId;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public BigInteger getValidados() {
		return validados;
	}

	public void setValidados(BigInteger validados) {
		this.validados = validados;
	}

	public BigInteger getCargados() {
		return cargados;
	}

	public void setCargados(BigInteger cargados) {
		this.cargados = cargados;
	}

	public BigInteger getFacturados() {
		return facturados;
	}

	public void setFacturados(BigInteger facturados) {
		this.facturados = facturados;
	}
}
