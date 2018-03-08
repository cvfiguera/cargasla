package cl.subtel.model.dao.sla.resumenvelocidad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.PromedioMensualVel;
import cl.subtel.model.common.sla.ResumenVelocidad;
import cl.subtel.model.dao.HibernateSla;


@Repository
public class ResumenVelocidadDaoImpl extends HibernateSla implements ResumenVelocidadDao{

	protected Logger log = Logger.getLogger(getClass().getName());
	
	@Override
	public List<ResumenVelocidad> getResumenVelocidad(Integer archivoId) {
		String hql = "from ResumenVelocidad where archivos.archivoId = " + archivoId;
		return (List<ResumenVelocidad>) (List) this.getObjetHqlList(new ResumenVelocidad(), hql);
	}

	@Override
	public Boolean setResumenVelocidad(Archivos archivo, List<ResumenVelocidad> resumen,
			List<PromedioMensualVel> promedios, Boolean inicio) {
		
		try {
			
			if (inicio) {
				if (archivo.getArchivoId()==0) 
					addObject(archivo);
				else
					updateObject(archivo);
				
				String hql = "DELETE FROM ResumenVelocidad WHERE archivos.archivoId = " + archivo.getArchivoId();
				deleteObjectHqlUnique(hql);
				
			    hql = "DELETE FROM PromedioMensualVel WHERE archivos.archivoId = " + archivo.getArchivoId();
				deleteObjectHqlUnique(hql);			
			}
			this.addObjectList((List<Object>) (List) resumen);
			this.addObjectList((List<Object>) (List) promedios);
			
		} catch (Exception ex) {
			log.error("Error al borrar los datos del resumen de disponibilidad en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteResumenVelocidad(Integer archivoId) {
		try {
			String hql = "DELETE FROM ResumenVelocidad WHERE archivos.archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
			hql = "DELETE FROM PromedioMensualVel WHERE archivos.archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
			hql = "DELETE FROM Archivos WHERE archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
		} catch (Exception ex) {
			log.error("Error al borrar los datos del resumen de disponibilidad en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

}
