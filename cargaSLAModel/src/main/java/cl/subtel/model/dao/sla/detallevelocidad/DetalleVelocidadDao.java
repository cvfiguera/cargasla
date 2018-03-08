package cl.subtel.model.dao.sla.detallevelocidad;

import java.util.List;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.DetalleVelocidad;

public interface DetalleVelocidadDao {

	public List<DetalleVelocidad> getDetalleVelocidad(Integer archivoId);

	public Boolean setDetalleVelocidad(Archivos archivo, 
			List<DetalleVelocidad> detalleVelocidad, Boolean inicio);
	
	public Boolean deleteDetalleVelocidad(Integer archivoId);

}
