package cl.subtel.model.dao.sla.prefacturas;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.Prefacturas;
import cl.subtel.model.dao.HibernateSla;
import cl.subtel.model.dao.sla.prefacturas.PrefacturaDAO;

@Repository
public class PrefacturaDaoImpl extends HibernateSla implements PrefacturaDAO{

	protected Logger log = Logger.getLogger(getClass().getName());

	@Override
	public List<Prefacturas> getPrefactura(Integer archivosId) {
		String hql = "FROM Prefacturas WHERE archivos.archivoId = " + archivosId;
		return (List<Prefacturas>) (List) getObjetHqlList(new Prefacturas(), hql);
	}

	@Override
	public Boolean setPrefactura(Archivos archivo, List<Prefacturas> prefacturas, Boolean inicio) {
		try {
			if (inicio) {
				if (archivo.getArchivoId()==0) 
					addObject(archivo);
				else
					updateObject(archivo);
				
				String hql = "DELETE FROM Prefacturas WHERE archivos.archivoId = " + archivo.getArchivoId();
				deleteObjectHqlUnique(hql);		
			}
			this.addObjectList((List<Object>) (List) prefacturas);
		} catch (Exception ex) {
			log.error("Error al borrar los datos de la Prefactura en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public void deletePrefactura(Integer archivosId) {
		String hql = "DELETE FROM Prefacturas WHERE archivos.archivoId = " + archivosId;
		deleteObjectHqlUnique(hql);
		
		hql = "DELETE FROM Archivos WHERE archivoId = " + archivosId;
		deleteObjectHqlUnique(hql);
	}
	
}
