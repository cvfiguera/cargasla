package cl.subtel.model.dao.sla.resumendisponibilidad;

import java.util.List;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.PromedioMensualDispo;
import cl.subtel.model.common.sla.ResumenDisponibilidad;

public interface ResumenDisponibilidadDao {
	
	public List<ResumenDisponibilidad> getResumenDisponibilidad(Integer archivoId);

	public Boolean setResumenDisponibilidad(Archivos archivo, List<ResumenDisponibilidad> resumen,
			List <PromedioMensualDispo> promedios, Boolean inicio);
	
	public Boolean deleteResumenDisponibilidad(Integer archivoId);

}
