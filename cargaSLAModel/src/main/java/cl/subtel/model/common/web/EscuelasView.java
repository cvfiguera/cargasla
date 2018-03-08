package cl.subtel.model.common.web;

import java.math.BigDecimal;
import java.util.Date;

public class EscuelasView implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer concursoId;
	private Integer rbdId;
	private String concurso;
	private Integer rbd;
	private String anexo;
	private String nombreEES;
	private String direccion;
	private Long matricula;
	private BigDecimal precioMensual;
	private BigDecimal velocidadBajada;
	private BigDecimal velocidadSubida;
	private Date fechaFas;
	private Integer tecnologiaId;
	private String tecnologia;
	private Integer operadoraId;
	private String operadora;
	private Integer estadoAdjudicadoId;
	private Integer subEstadoAdjudicadoId;
	private String subEstadoAdjudicado;
	private Integer estadoMineducId;
	private String estadoMineduc;
	private Integer estadoConectadoId;
	private String estadoConectado;
	private Integer comunaId;
	private String comuna;
	private Integer regionId;
	private String region;

	public EscuelasView(){
		
	}

	public EscuelasView(Integer concursoId, Integer rbdId, String concurso, Integer rbd, String anexo, String nombreEes, String direccion, Long matricula, BigDecimal precioMensual, BigDecimal velocidadBajada, BigDecimal velocidadSubida, Date fechaFas, Integer tecnologiaId, String tecnologia, Integer operadoraId, String operadora, Integer estadoAdjudicadoId, Integer subEstadoAdjudicadoId, String subEstadoAdjudicado, Integer estadoMineducId, String estadoMineduc, Integer estadoConectadoId, String estadoConectado, Integer comunaId, String comuna, Integer regionId, String region) {
      this.concursoId = concursoId;
      this.rbdId = rbdId;
      this.concurso = concurso;
      this.rbd = rbd;
      this.anexo = anexo;
      this.nombreEES = nombreEes;
      this.direccion = direccion;
      this.matricula = matricula;
      this.precioMensual = precioMensual;
      this.velocidadBajada = velocidadBajada;
      this.velocidadSubida = velocidadSubida;
      this.fechaFas = fechaFas;
      this.tecnologiaId = tecnologiaId;
      this.tecnologia = tecnologia;
      this.operadoraId = operadoraId;
      this.operadora = operadora;
      this.estadoAdjudicadoId = estadoAdjudicadoId;
      this.subEstadoAdjudicadoId = subEstadoAdjudicadoId;
      this.subEstadoAdjudicado = subEstadoAdjudicado;
      this.estadoMineducId = estadoMineducId;
      this.estadoMineduc = estadoMineduc;
      this.estadoConectadoId = estadoConectadoId;
      this.estadoConectado = estadoConectado;
      this.comunaId = comunaId;
      this.comuna = comuna;
      this.regionId = regionId;
      this.region = region;
   }

	public Integer getConcursoId() {
		return this.concursoId;
	}

	public void setConcursoId(Integer concursoId) {
		this.concursoId = concursoId;
	}

	public Integer getRbdId() {
		return this.rbdId;
	}

	public void setRbdId(Integer rbdId) {
		this.rbdId = rbdId;
	}

	public String getConcurso() {
		return this.concurso;
	}

	public void setConcurso(String concurso) {
		this.concurso = concurso;
	}

	public Integer getRbd() {
		return this.rbd;
	}

	public void setRbd(Integer rbd) {
		this.rbd = rbd;
	}

	public String getAnexo() {
		return this.anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getNombreEES() {
		return this.nombreEES;
	}

	public void setNombreEES(String nombreEes) {
		this.nombreEES = nombreEes;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public BigDecimal getPrecioMensual() {
		return this.precioMensual;
	}

	public void setPrecioMensual(BigDecimal precioMensual) {
		this.precioMensual = precioMensual;
	}

	public BigDecimal getVelocidadBajada() {
		return this.velocidadBajada;
	}

	public void setVelocidadBajada(BigDecimal velocidadBajada) {
		this.velocidadBajada = velocidadBajada;
	}

	public BigDecimal getVelocidadSubida() {
		return this.velocidadSubida;
	}

	public void setVelocidadSubida(BigDecimal velocidadSubida) {
		this.velocidadSubida = velocidadSubida;
	}

	public Date getFechaFas() {
		return this.fechaFas;
	}

	public void setFechaFas(Date fechaFas) {
		this.fechaFas = fechaFas;
	}

	public Integer getTecnologiaId() {
		return this.tecnologiaId;
	}

	public void setTecnologiaId(Integer tecnologiaId) {
		this.tecnologiaId = tecnologiaId;
	}

	public String getTecnologia() {
		return this.tecnologia;
	}

	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}

	public Integer getOperadoraId() {
		return this.operadoraId;
	}

	public void setOperadoraId(Integer operadoraId) {
		this.operadoraId = operadoraId;
	}

	public String getOperadora() {
		return this.operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public Integer getEstadoAdjudicadoId() {
		return this.estadoAdjudicadoId;
	}

	public void setEstadoAdjudicadoId(Integer estadoAdjudicadoId) {
		this.estadoAdjudicadoId = estadoAdjudicadoId;
	}

	public Integer getSubEstadoAdjudicadoId() {
		return this.subEstadoAdjudicadoId;
	}

	public void setSubEstadoAdjudicadoId(Integer subEstadoAdjudicadoId) {
		this.subEstadoAdjudicadoId = subEstadoAdjudicadoId;
	}

	public String getSubEstadoAdjudicado() {
		return this.subEstadoAdjudicado;
	}

	public void setSubEstadoAdjudicado(String subEstadoAdjudicado) {
		this.subEstadoAdjudicado = subEstadoAdjudicado;
	}

	public Integer getEstadoMineducId() {
		return this.estadoMineducId;
	}

	public void setEstadoMineducId(Integer estadoMineducId) {
		this.estadoMineducId = estadoMineducId;
	}

	public String getEstadoMineduc() {
		return this.estadoMineduc;
	}

	public void setEstadoMineduc(String estadoMineduc) {
		this.estadoMineduc = estadoMineduc;
	}

	public Integer getEstadoConectadoId() {
		return this.estadoConectadoId;
	}

	public void setEstadoConectadoId(Integer estadoConectadoId) {
		this.estadoConectadoId = estadoConectadoId;
	}

	public String getEstadoConectado() {
		return this.estadoConectado;
	}

	public void setEstadoConectado(String estadoConectado) {
		this.estadoConectado = estadoConectado;
	}

	public Integer getComunaId() {
		return this.comunaId;
	}

	public void setComunaId(Integer comunaId) {
		this.comunaId = comunaId;
	}

	public String getComuna() {
		return this.comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EscuelasView))
			return false;
		EscuelasView castOther = (EscuelasView) other;

		return ((this.getConcursoId() == castOther.getConcursoId()) || (this.getConcursoId() != null
				&& castOther.getConcursoId() != null && this.getConcursoId().equals(castOther.getConcursoId())))
				&& ((this.getRbdId() == castOther.getRbdId()) || (this.getRbdId() != null
						&& castOther.getRbdId() != null && this.getRbdId().equals(castOther.getRbdId())))
				&& ((this.getConcurso() == castOther.getConcurso()) || (this.getConcurso() != null
						&& castOther.getConcurso() != null && this.getConcurso().equals(castOther.getConcurso())))
				&& ((this.getRbd() == castOther.getRbd()) || (this.getRbd() != null && castOther.getRbd() != null
						&& this.getRbd().equals(castOther.getRbd())))
				&& ((this.getAnexo() == castOther.getAnexo()) || (this.getAnexo() != null
						&& castOther.getAnexo() != null && this.getAnexo().equals(castOther.getAnexo())))
				&& ((this.getNombreEES() == castOther.getNombreEES()) || (this.getNombreEES() != null
						&& castOther.getNombreEES() != null && this.getNombreEES().equals(castOther.getNombreEES())))
				&& ((this.getDireccion() == castOther.getDireccion()) || (this.getDireccion() != null
						&& castOther.getDireccion() != null && this.getDireccion().equals(castOther.getDireccion())))
				&& ((this.getMatricula() == castOther.getMatricula()) || (this.getMatricula() != null
						&& castOther.getMatricula() != null && this.getMatricula().equals(castOther.getMatricula())))
				&& ((this.getPrecioMensual() == castOther.getPrecioMensual())
						|| (this.getPrecioMensual() != null && castOther.getPrecioMensual() != null
								&& this.getPrecioMensual().equals(castOther.getPrecioMensual())))
				&& ((this.getVelocidadBajada() == castOther.getVelocidadBajada())
						|| (this.getVelocidadBajada() != null && castOther.getVelocidadBajada() != null
								&& this.getVelocidadBajada().equals(castOther.getVelocidadBajada())))
				&& ((this.getVelocidadSubida() == castOther.getVelocidadSubida())
						|| (this.getVelocidadSubida() != null && castOther.getVelocidadSubida() != null
								&& this.getVelocidadSubida().equals(castOther.getVelocidadSubida())))
				&& ((this.getFechaFas() == castOther.getFechaFas()) || (this.getFechaFas() != null
						&& castOther.getFechaFas() != null && this.getFechaFas().equals(castOther.getFechaFas())))
				&& ((this.getTecnologiaId() == castOther.getTecnologiaId())
						|| (this.getTecnologiaId() != null && castOther.getTecnologiaId() != null
								&& this.getTecnologiaId().equals(castOther.getTecnologiaId())))
				&& ((this.getTecnologia() == castOther.getTecnologia()) || (this.getTecnologia() != null
						&& castOther.getTecnologia() != null && this.getTecnologia().equals(castOther.getTecnologia())))
				&& ((this.getOperadoraId() == castOther.getOperadoraId())
						|| (this.getOperadoraId() != null && castOther.getOperadoraId() != null
								&& this.getOperadoraId().equals(castOther.getOperadoraId())))
				&& ((this.getOperadora() == castOther.getOperadora()) || (this.getOperadora() != null
						&& castOther.getOperadora() != null && this.getOperadora().equals(castOther.getOperadora())))
				&& ((this.getEstadoAdjudicadoId() == castOther.getEstadoAdjudicadoId())
						|| (this.getEstadoAdjudicadoId() != null && castOther.getEstadoAdjudicadoId() != null
								&& this.getEstadoAdjudicadoId().equals(castOther.getEstadoAdjudicadoId())))
				&& ((this.getSubEstadoAdjudicadoId() == castOther.getSubEstadoAdjudicadoId())
						|| (this.getSubEstadoAdjudicadoId() != null && castOther.getSubEstadoAdjudicadoId() != null
								&& this.getSubEstadoAdjudicadoId().equals(castOther.getSubEstadoAdjudicadoId())))
				&& ((this.getSubEstadoAdjudicado() == castOther.getSubEstadoAdjudicado())
						|| (this.getSubEstadoAdjudicado() != null && castOther.getSubEstadoAdjudicado() != null
								&& this.getSubEstadoAdjudicado().equals(castOther.getSubEstadoAdjudicado())))
				&& ((this.getEstadoMineducId() == castOther.getEstadoMineducId())
						|| (this.getEstadoMineducId() != null && castOther.getEstadoMineducId() != null
								&& this.getEstadoMineducId().equals(castOther.getEstadoMineducId())))
				&& ((this.getEstadoMineduc() == castOther.getEstadoMineduc())
						|| (this.getEstadoMineduc() != null && castOther.getEstadoMineduc() != null
								&& this.getEstadoMineduc().equals(castOther.getEstadoMineduc())))
				&& ((this.getEstadoConectadoId() == castOther.getEstadoConectadoId())
						|| (this.getEstadoConectadoId() != null && castOther.getEstadoConectadoId() != null
								&& this.getEstadoConectadoId().equals(castOther.getEstadoConectadoId())))
				&& ((this.getEstadoConectado() == castOther.getEstadoConectado())
						|| (this.getEstadoConectado() != null && castOther.getEstadoConectado() != null
								&& this.getEstadoConectado().equals(castOther.getEstadoConectado())))
				&& ((this.getComunaId() == castOther.getComunaId()) || (this.getComunaId() != null
						&& castOther.getComunaId() != null && this.getComunaId().equals(castOther.getComunaId())))
				&& ((this.getComuna() == castOther.getComuna()) || (this.getComuna() != null
						&& castOther.getComuna() != null && this.getComuna().equals(castOther.getComuna())))
				&& ((this.getRegionId() == castOther.getRegionId()) || (this.getRegionId() != null
						&& castOther.getRegionId() != null && this.getRegionId().equals(castOther.getRegionId())))
				&& ((this.getRegion() == castOther.getRegion()) || (this.getRegion() != null
						&& castOther.getRegion() != null && this.getRegion().equals(castOther.getRegion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getConcursoId() == null ? 0 : this.getConcursoId().hashCode());
		result = 37 * result + (getRbdId() == null ? 0 : this.getRbdId().hashCode());
		result = 37 * result + (getConcurso() == null ? 0 : this.getConcurso().hashCode());
		result = 37 * result + (getRbd() == null ? 0 : this.getRbd().hashCode());
		result = 37 * result + (getAnexo() == null ? 0 : this.getAnexo().hashCode());
		result = 37 * result + (getNombreEES() == null ? 0 : this.getNombreEES().hashCode());
		result = 37 * result + (getDireccion() == null ? 0 : this.getDireccion().hashCode());
		result = 37 * result + (getMatricula() == null ? 0 : this.getMatricula().hashCode());
		result = 37 * result + (getPrecioMensual() == null ? 0 : this.getPrecioMensual().hashCode());
		result = 37 * result + (getVelocidadBajada() == null ? 0 : this.getVelocidadBajada().hashCode());
		result = 37 * result + (getVelocidadSubida() == null ? 0 : this.getVelocidadSubida().hashCode());
		result = 37 * result + (getFechaFas() == null ? 0 : this.getFechaFas().hashCode());
		result = 37 * result + (getTecnologiaId() == null ? 0 : this.getTecnologiaId().hashCode());
		result = 37 * result + (getTecnologia() == null ? 0 : this.getTecnologia().hashCode());
		result = 37 * result + (getOperadoraId() == null ? 0 : this.getOperadoraId().hashCode());
		result = 37 * result + (getOperadora() == null ? 0 : this.getOperadora().hashCode());
		result = 37 * result + (getEstadoAdjudicadoId() == null ? 0 : this.getEstadoAdjudicadoId().hashCode());
		result = 37 * result + (getSubEstadoAdjudicadoId() == null ? 0 : this.getSubEstadoAdjudicadoId().hashCode());
		result = 37 * result + (getSubEstadoAdjudicado() == null ? 0 : this.getSubEstadoAdjudicado().hashCode());
		result = 37 * result + (getEstadoMineducId() == null ? 0 : this.getEstadoMineducId().hashCode());
		result = 37 * result + (getEstadoMineduc() == null ? 0 : this.getEstadoMineduc().hashCode());
		result = 37 * result + (getEstadoConectadoId() == null ? 0 : this.getEstadoConectadoId().hashCode());
		result = 37 * result + (getEstadoConectado() == null ? 0 : this.getEstadoConectado().hashCode());
		result = 37 * result + (getComunaId() == null ? 0 : this.getComunaId().hashCode());
		result = 37 * result + (getComuna() == null ? 0 : this.getComuna().hashCode());
		result = 37 * result + (getRegionId() == null ? 0 : this.getRegionId().hashCode());
		result = 37 * result + (getRegion() == null ? 0 : this.getRegion().hashCode());
		return result;
	}

}
