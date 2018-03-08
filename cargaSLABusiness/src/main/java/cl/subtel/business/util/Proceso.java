package cl.subtel.business.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cl.subtel.business.vo.EstatusSLA;
import cl.subtel.model.common.control.Archivo;
import cl.subtel.model.common.control.Estados;
import cl.subtel.model.common.control.Procesos;
import cl.subtel.model.common.control.ProcesosDetalle;
import cl.subtel.model.common.control.TiposProceso;

public class Proceso implements java.io.Serializable {
	
	private static final long serialVersionUID = 2816694958193215064L;
	private Procesos proceso;
	private List<ProcesosDetalle> pDetalle;
	private List<EstatusSLA> status;
	
	public Proceso() {
		super();
		this.proceso = new Procesos();
		this.pDetalle = new ArrayList<ProcesosDetalle>();
		this.status = new ArrayList<EstatusSLA>();
	}
	
	public Procesos getProceso() {
		return this.proceso;
	}
	
	public List<ProcesosDetalle> getProcesoDetalle() {
		return this.pDetalle;
	}

	public List<EstatusSLA> getStatus() {
		return this.status;
	}

	public List<EstatusSLA> addStatus(Archivo archivo, Estados estado, Double valor, Boolean procesado, String descripcion) {
		
		for (int i=0; i<this.status.size(); i++) {
			EstatusSLA estatusSLA = this.status.get(i);
			
			if (estatusSLA.getId() == archivo.getId().getArchivoId()) {
				estatusSLA.setEstado(descripcion);
				estatusSLA.setValor(valor);
				estatusSLA.setProcesado(procesado);
				this.status.set(i, estatusSLA);
			}
		}
		this.pDetalle.add(new ProcesosDetalle(archivo, estado, proceso));
		
		return this.status;
	}
	
	public void iniciarProceso(List<Archivo> archivos, Integer usuario, Date fecha, TiposProceso tipo, Estados estado) {
		
		this.proceso.setTiposProceso(tipo);
		this.proceso.setUsuario(usuario);
		this.proceso.setFecha(fecha);
		this.proceso.setEstatus(GlobalVar.ACTIVO);
		
		for(int i = 0, n=archivos.size(); i < n ; i++) {
			Archivo archivo = archivos.get(i); 
			this.pDetalle.add(new ProcesosDetalle(archivo, estado, proceso));
			this.status.add(new EstatusSLA(archivo.getId().getArchivoId(), archivo.getArchivosTipos().getArchivoTipoId(),
					archivo.getArchivosTipos().getTipo(),
					archivo.getId().getNombreArchivo(), archivo.getVersion(), archivo.getFechaFtp(),
					0.00, estado.getEstado(), false));
		}
	}
	
	public void finalizarProceso() {
		this.proceso.setEstatus(GlobalVar.INACTIVO);
	}
	
	public Boolean estaBloqueado(List<Procesos> procesosActivos, Integer usuarioId) {
		
		if (procesosActivos.size()< GlobalVar.MAX_PROCESOS_CARGA) {
			
			for (int i=0; i<procesosActivos.size(); i++) {
				Procesos procesoUsuario = procesosActivos.get(i);
				if (procesoUsuario.getUsuario() == usuarioId) return true;
			}
			
		} else return true;
		
		return false;
	}
}
