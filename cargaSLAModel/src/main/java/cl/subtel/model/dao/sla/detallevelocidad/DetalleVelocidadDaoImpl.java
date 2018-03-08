package cl.subtel.model.dao.sla.detallevelocidad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.DetalleVelocidad;
import cl.subtel.model.dao.HibernateSla;
import cl.subtel.model.dao.HibernateWeb;

@Repository
public class DetalleVelocidadDaoImpl extends HibernateSla implements DetalleVelocidadDao{

	protected Logger log = Logger.getLogger(getClass().getName());
	
	
	@Override
	public List<DetalleVelocidad> getDetalleVelocidad(Integer archivoId) {
		String hql = "FROM DetalleVelocidad WHERE archivos.archivoId = " + archivoId;
		return (List<DetalleVelocidad>) (List) this.getObjetHqlList(new DetalleVelocidad(), hql);
	}

	@Override
	public Boolean setDetalleVelocidad(Archivos archivo, 
			List<DetalleVelocidad> detalleVelocidad, Boolean inicio) {
		try {
			
			if (archivo.getArchivoId()==0) 
				addObject(archivo);
			else
				updateObject(archivo);
			
			String hql = "DELETE FROM DetalleVelocidad WHERE archivos.archivoId = " + archivo.getArchivoId();
			deleteObjectHqlUnique(hql);
			
			this.addObjectList((List<Object>) (List) detalleVelocidad);
			
		} catch (Exception ex) {
			log.error("Error al borrar los datos del resumen de disponibilidad en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;

	}

	@Override
	public Boolean deleteDetalleVelocidad(Integer archivoId) {
		try {
			String hql = "DELETE FROM DetalleVelocidad WHERE archivos.archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
			hql = "DELETE FROM Archivos WHERE archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
		} catch (Exception ex) {
			log.error("Error al borrar los datos del detalle de velocidad: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

}
