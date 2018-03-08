package cl.subtel.model.dao.sla.detalledisponibilidad;


import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.DetalleDisponibilidad;
import cl.subtel.model.dao.HibernateSla;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleDisponibilidadDaoImpl extends HibernateSla implements DetalleDisponibilidadDao {

	protected Logger log = Logger.getLogger(getClass().getName());

	@Override
	public List<DetalleDisponibilidad> getDetalleDisponibilidad(Integer archivoId) {
		String hql = "FROM DetalleDisponibilidad WHERE archivos.archivoId = " + archivoId;
		return (List<DetalleDisponibilidad>) (List) this.getObjetHqlList(new DetalleDisponibilidad(), hql);
	}

	@Override
	public Boolean setDetalleDisponibilidad(Archivos archivo, 
			List<DetalleDisponibilidad> detalleDisp, Boolean inicio) {
		try {
			if (inicio) {
				if (archivo.getArchivoId()==0) 
					addObject(archivo);
				else
					updateObject(archivo);
				
				String hql = "DELETE FROM DetalleDisponibilidad WHERE archivos.archivoId = " + archivo.getArchivoId();
				deleteObjectHqlUnique(hql);
			}
			
			this.addObjectList((List<Object>) (List) detalleDisp);
			
		} catch (Exception ex) {
			log.error("Error al borrar los datos del detalle de disponibilidad: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteDetalleDisponibilidad(Integer archivoId) {
		try {
			String hql = "DELETE FROM DetalleDisponibilidad WHERE archivos.archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
			hql = "DELETE FROM Archivos WHERE archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
		} catch (Exception ex) {
			log.error("Error al borrar los datos del detalle de disponibilidad: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

}
