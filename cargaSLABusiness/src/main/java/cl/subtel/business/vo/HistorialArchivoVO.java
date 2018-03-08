package cl.subtel.business.vo;

import java.util.Date;

public class HistorialArchivoVO {

	private int id;
	private String nombre;
	private Date fecha;
	private String estado;
	private String usuario;
	
	
	public HistorialArchivoVO() {
		super();
	}

	public HistorialArchivoVO(int id, String nombre, Date fecha, String estado, String usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.estado = estado;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
