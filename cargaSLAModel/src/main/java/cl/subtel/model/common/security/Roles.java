package cl.subtel.model.common.security;

import java.util.HashSet;
import java.util.Set;

public class Roles implements java.io.Serializable {

	private static final long serialVersionUID = 1059650347832311587L;
	private int rolId;
	private String nombreRol;
	private String descripcion;
	private Set usuarioses = new HashSet(0);
	private Set usuarioses_1 = new HashSet(0);

	public Roles() {
	}

	public Roles(int rolId) {
		this.rolId = rolId;
	}

	public Roles(int rolId, String nombreRol, String descripcion, Set usuarioses, Set usuarioses_1) {
		this.rolId = rolId;
		this.nombreRol = nombreRol;
		this.descripcion = descripcion;
		this.usuarioses = usuarioses;
		this.usuarioses_1 = usuarioses_1;
	}

	public int getRolId() {
		return this.rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set usuarioses) {
		this.usuarioses = usuarioses;
	}

	public Set getUsuarioses_1() {
		return this.usuarioses_1;
	}

	public void setUsuarioses_1(Set usuarioses_1) {
		this.usuarioses_1 = usuarioses_1;
	}

}
