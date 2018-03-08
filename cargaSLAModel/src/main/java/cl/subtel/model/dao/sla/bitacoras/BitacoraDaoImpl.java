package cl.subtel.model.dao.sla.bitacoras;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.Bitacoras;
import cl.subtel.model.dao.HibernateSla;
import cl.subtel.model.dao.sla.bitacoras.BitacoraDao;

@Repository
public class BitacoraDaoImpl extends HibernateSla implements BitacoraDao{

	protected Logger log = Logger.getLogger(getClass().getName());

	@Override
	public List<Bitacoras> getBitacora(Integer archivosId) {
		String hql = "FROM Bitacoras WHERE archivos.archivosId = " + archivosId;
		return (List<Bitacoras>) (List) this.getObjetHqlList(new Bitacoras(), hql);
	}

	@Override
	public Boolean setBitacora(Archivos archivo, List<Bitacoras> bitacoras, Boolean inicio) {
		try {
			if (inicio) {
				if (archivo.getArchivoId()==0) 
					addObject(archivo);
				else
					updateObject(archivo);
				
				String hql = "DELETE FROM Bitacoras WHERE archivos.archivoId = " + archivo.getArchivoId();
				deleteObjectHqlUnique(hql);		
			}
			this.addObjectList((List<Object>) (List) bitacoras);
		} catch (Exception ex) {
			log.error("Error al borrar los datos del resumen de disponibilidad en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public void deleteBitacora(Integer archivoId) {
		String hql = "DELETE FROM Bitacoras WHERE archivos.archivoId = " + archivoId;
		deleteObjectHqlUnique(hql);
		
		hql = "DELETE FROM Archivos WHERE archivoId = " + archivoId;
		deleteObjectHqlUnique(hql);
	}
}
