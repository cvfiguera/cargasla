package cl.subtel.model.dao.sla.promediosmensuales;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import cl.subtel.model.common.sla.PromedioMensualVel;
import cl.subtel.model.dao.HibernateSla;

@Repository
public class PromedioMensualVelDaoImpl extends HibernateSla implements PromedioMensualVelDao {

	protected Logger log = Logger.getLogger(getClass().getName());

	@Override
	public PromedioMensualVel getPromedio(int promedioId) {

		String hql = "FROM PromedioMensualVel WHERE promedioId = '" + promedioId + "'";
		return (PromedioMensualVel) getObjectHqlUnique(new PromedioMensualVel(), hql);
	}
	
	@Override
	public Boolean setPromedio(PromedioMensualVel promedio) {
		
		try {
			addObject(promedio);
		} catch (Exception ex) {
			log.error("Error al guardar los datos de la prefactura en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public Boolean updatePromedio(PromedioMensualVel promedio) {
		
		try {
			updateObject(promedio);
		} catch (Exception ex) {
			log.error("Error al guardar los datos de la prefactura en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public PromedioMensualVel getPromedioByArchivo(int archivoId, Integer rbd) {
		
		String hql = "FROM PromedioMensualVel WHERE archivos.archivoId = '" + archivoId + "' AND rbdNu = '" + rbd + "'";
		
		return (PromedioMensualVel) getObjectHqlUnique(new PromedioMensualVel(), hql);

	}

	@Override
	public Boolean deletePromedio(PromedioMensualVel promedio) {
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
		String hql = "DELETE FROM PromedioMensualVel WHERE archivos.archivoId = '" + archivoId + "'";
		deleteObjectHqlUnique(hql);
	}
	
}
