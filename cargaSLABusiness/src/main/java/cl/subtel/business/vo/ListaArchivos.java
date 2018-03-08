package cl.subtel.business.vo;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

public class ListaArchivos implements Serializable {

	private static final long serialVersionUID = -7421823399752825528L;
	@NotNull
	private Integer tipo; // 1: Validación 2:Carga
	@NotNull
	private List<Integer> archivoIds;
	
	
	public ListaArchivos() {
		super();
	}
	public ListaArchivos(Integer tipo, List<Integer> archivosIds) {
		super();
		this.tipo = tipo;
		this.archivoIds = archivosIds;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public List<Integer> getArchivoIds() {
		return archivoIds;
	}
	public void setArchivosIds(List<Integer> archivoIds) {
		this.archivoIds = archivoIds;
	}
	
}
