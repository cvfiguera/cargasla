package cl.subtel.model.dao.sla.promediosmensuales;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import cl.subtel.model.common.sla.PromedioMensualDispo;
import cl.subtel.model.dao.HibernateSla;

@Repository
public class PromedioMensualDispoDaoImpl extends HibernateSla implements PromedioMensualDispoDao {

	protected Logger log = Logger.getLogger(getClass().getName());

	@Override
	public PromedioMensualDispo getPromedio(int promedioId) {

		String hql = "FROM PromedioMensualDispo WHERE promedioId = '" + promedioId + "'";
		return (PromedioMensualDispo) getObjectHqlUnique(new PromedioMensualDispo(), hql);
	}
	
	@Override
	public Boolean setPromedio(PromedioMensualDispo promedio) {
		
		try {
			addObject(promedio);
		} catch (Exception ex) {
			log.error("Error al guardar los datos de la prefactura en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public Boolean updatePromedio(PromedioMensualDispo promedio) {
		
		try {
			updateObject(promedio);
		} catch (Exception ex) {
			log.error("Error al guardar los datos de la prefactura en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public PromedioMensualDispo getPromedioByArchivo(int archivoId, Integer rbd) {
		
		String hql = "FROM PromedioMensualDispo WHERE archivos.archivoId = '" + archivoId + "' AND rbdNu = '" + rbd + "'";
		
		return (PromedioMensualDispo) getObjectHqlUnique(new PromedioMensualDispo(), hql);

	}

	@Override
	public Boolean deletePromedio(PromedioMensualDispo promedio) {
		try {
			deleteObject(promedio);
		} catch (Exception ex) {
			log.error("Error al borrar los datos de la prefactura en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public void deletePromedioByArchivo(int archivoId) {
		// TODO Auto-generated method stub
		String hql = "DELETE FROM PromedioMensualDispo WHERE archivos.archivoId = '" + archivoId + "'";
		deleteObjectHqlUnique(hql);
	}
	
}
