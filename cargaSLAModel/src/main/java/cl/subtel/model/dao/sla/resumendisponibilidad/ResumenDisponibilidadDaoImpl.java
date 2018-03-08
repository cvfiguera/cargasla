package cl.subtel.model.dao.sla.resumendisponibilidad;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.PromedioMensualDispo;
import cl.subtel.model.common.sla.ResumenDisponibilidad;
import cl.subtel.model.dao.HibernateSla;

@Repository
public class ResumenDisponibilidadDaoImpl extends HibernateSla implements ResumenDisponibilidadDao{
	
	protected Logger log = Logger.getLogger(getClass().getName());

	@Override
	public List<ResumenDisponibilidad> getResumenDisponibilidad(Integer archivoId) {
		String hql = "FROM ResumenDisponibilidad WHERE archivos.archivoId = " + archivoId;
		return (List<ResumenDisponibilidad>) (List) this.getObjetHqlList(new ResumenDisponibilidad(), hql);
	}

	@Override
	public Boolean setResumenDisponibilidad(Archivos archivo, List<ResumenDisponibilidad> resumen,
			List<PromedioMensualDispo> promedios, Boolean inicio) {
		
		try {
			
			if (inicio) {
				if (archivo.getArchivoId()==0) 
					addObject(archivo);
				else
					updateObject(archivo);
				
				String hql = "DELETE FROM ResumenDisponibilidad WHERE archivos.archivoId = " + archivo.getArchivoId();					
				deleteObjectHqlUnique(hql);
				
				hql = "DELETE FROM PromedioMensualDispo WHERE archivos.archivoId = " + archivo.getArchivoId();
				deleteObjectHqlUnique(hql);
			}
			
			this.addObjectList((List<Object>) (List) resumen);
			this.addObjectList((List<Object>) (List) promedios);
			
		} catch (Exception ex) {
			log.error("Error al guardar los datos del archivo: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteResumenDisponibilidad(Integer archivoId) {
		try {
			
			String hql = "DELETE FROM ResumenDisponibilidad WHERE archivos.archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
			hql = "DELETE FROM PromedioMensualDispo WHERE archivos.archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
			hql = "DELETE FROM Archivos WHERE archivoId = " + archivoId;
			deleteObjectHqlUnique(hql);
			
		} catch (Exception ex) {
			log.error("Error al guardar los datos del archivo: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}
}
