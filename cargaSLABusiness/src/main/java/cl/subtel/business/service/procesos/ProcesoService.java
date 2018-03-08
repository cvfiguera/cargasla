package cl.subtel.business.service.procesos;

import java.util.List;

import cl.subtel.business.vo.HistorialArchivoVO;
import cl.subtel.business.vo.ProcesoResumenVO;
import cl.subtel.business.vo.ProcesoVO;
import cl.subtel.model.common.control.ResumenArchivo;

public interface ProcesoService {
	
	public List<ProcesoResumenVO> getProcesos(String fechaIni, String fechaFin);
	
	public List<ResumenArchivo> getProcesoDetalle(Integer procesoId);
	
	public ProcesoVO getProcesoActivo(Integer usuarioId);
	
	public List<HistorialArchivoVO> getHistorialArchivo(Integer archivoId);

}
