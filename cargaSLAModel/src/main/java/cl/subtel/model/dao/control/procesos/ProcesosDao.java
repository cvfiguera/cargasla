package cl.subtel.model.dao.control.procesos;
import java.util.List;

import cl.subtel.model.common.control.ProcesoResumen;
import cl.subtel.model.common.control.Procesos;
import cl.subtel.model.common.control.ProcesosDetalle;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.model.common.control.TiposProceso;

public interface ProcesosDao {

	public Procesos getProceso(Integer procesoId);
	
	public void setProceso(Procesos proceso);
	
	public void updateProceso(Procesos proceso);
	
	public void setProcesoDetalle(List<ProcesosDetalle> procesoDetalle);
	
	public TiposProceso getTipoProceso(Integer tipoProcesoId);
	
	public List<Procesos> getProcesosActivos();
	
	public List<ProcesoResumen> getUltimosProcesos(Integer cant);
	
	public Procesos getProcesoActivo(Integer usuarioId, Integer tipoProcesoId, Integer estatus);
	
	public List<ProcesosDetalle> getProcesosDetalle(Integer procesoId);
	
	public List<ResumenArchivo> getMaxProcesosDetalle(Integer procesoId);
	
	public List<ProcesoResumen> getProcesoByFecha(String fechaIni, String fechaFin);
}
