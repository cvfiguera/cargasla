package cl.subtel.model.dao.sla.detalledisponibilidad;

import java.util.List;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.DetalleDisponibilidad;

public interface DetalleDisponibilidadDao {

	public List<DetalleDisponibilidad> getDetalleDisponibilidad(Integer archivoId);
	
	public Boolean setDetalleDisponibilidad(Archivos archivo, 
			List<DetalleDisponibilidad> detalleDisp, Boolean inicio);

	public Boolean deleteDetalleDisponibilidad(Integer archivoId);
	
}
