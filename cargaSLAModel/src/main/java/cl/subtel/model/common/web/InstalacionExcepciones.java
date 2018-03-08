package cl.subtel.model.common.web;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class InstalacionExcepciones implements java.io.Serializable {

	private static final long serialVersionUID = -558562146156491704L;
	private int instalacionExcepcionId;
	private Date fechaInicioFe;
	private Date fechaTerminoFe;
	private Date fechaCreacionFe;
	private Boolean esPago;
	private String comentario;
	private Set instalacioneses = new HashSet(0);

	public InstalacionExcepciones() {
	}

	public InstalacionExcepciones(int instalacionExcepcionId) {
		this.instalacionExcepcionId = instalacionExcepcionId;
	}

	public InstalacionExcepciones(int instalacionExcepcionId, Date fechaInicioFe, Date fechaTerminoFe,
			Date fechaCreacionFe, Boolean esPago, String comentario, Set instalacioneses) {
		this.instalacionExcepcionId = instalacionExcepcionId;
		this.fechaInicioFe = fechaInicioFe;
		this.fechaTerminoFe = fechaTerminoFe;
		this.fechaCreacionFe = fechaCreacionFe;
		this.esPago = esPago;
		this.comentario = comentario;
		this.instalacioneses = instalacioneses;
	}

	public int getInstalacionExcepcionId() {
		return this.instalacionExcepcionId;
	}

	public void setInstalacionExcepcionId(int instalacionExcepcionId) {
		this.instalacionExcepcionId = instalacionExcepcionId;
	}

	public Date getFechaInicioFe() {
		return this.fechaInicioFe;
	}

	public void setFechaInicioFe(Date fechaInicioFe) {
		this.fechaInicioFe = fechaInicioFe;
	}

	public Date getFechaTerminoFe() {
		return this.fechaTerminoFe;
	}

	public void setFechaTerminoFe(Date fechaTerminoFe) {
		this.fechaTerminoFe = fechaTerminoFe;
	}

	public Date getFechaCreacionFe() {
		return this.fechaCreacionFe;
	}

	public void setFechaCreacionFe(Date fechaCreacionFe) {
		this.fechaCreacionFe = fechaCreacionFe;
	}

	public Boolean getEsPago() {
		return this.esPago;
	}

	public void setEsPago(Boolean esPago) {
		this.esPago = esPago;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Set getInstalacioneses() {
		return this.instalacioneses;
	}

	public void setInstalacioneses(Set instalacioneses) {
		this.instalacioneses = instalacioneses;
	}

}
