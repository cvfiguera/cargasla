package cl.subtel.model.dao.sla.resumenvelocidad;

import java.util.List;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.PromedioMensualVel;
import cl.subtel.model.common.sla.ResumenVelocidad;

public interface ResumenVelocidadDao {

	public List<ResumenVelocidad> getResumenVelocidad(Integer archivoId);

	public Boolean setResumenVelocidad(Archivos archivo, List<ResumenVelocidad> resumen,
			List<PromedioMensualVel> promedios, Boolean inicio);
	
	public Boolean deleteResumenVelocidad(Integer archivoId);

}
