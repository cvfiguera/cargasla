package cl.subtel.model.dao.sla.archivos;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.dao.HibernateSla;
import cl.subtel.model.dao.sla.archivos.ArchivosDao;

@Repository
public class ArchivosDaoImpl extends HibernateSla implements ArchivosDao{
	
	protected Logger log = Logger.getLogger(getClass().getName());

	@Override
	public Archivos getArchivo(int id) {
		String hql = "from Archivos where archivoId = '" + id + "'";
		return (Archivos) getObjectHqlUnique(new Archivos(), hql);
	}
	
	@Override
	public Archivos getArchivoByName(String nombre) {
		String hql = "from Archivos where nombreArchivoTx = '" + nombre + "'";
		return (Archivos) getObjectHqlUnique(new Archivos(), hql);
	}
	
	@Override
	public Archivos getArchivoByControl(Integer archivoControlId) {
		String hql = "from Archivos where archivoControlId = " + archivoControlId;
		return (Archivos) getObjectHqlUnique(new Archivos(), hql);
	}

	@Override
	public Boolean setArchivo(Archivos archivo) {
		try {
			if (archivo.getArchivoId()==0) 
				addObject(archivo);
			else
				updateObject(archivo);
		} catch (Exception ex) {
			log.error("Error al guardar los datos del archivo: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public void deleteArchivo(Integer archivoId) {
		String hql = "DELETE FROM Archivos WHERE archivoId = '" + archivoId + "'";
		deleteObjectHqlUnique(hql);
	}

}
