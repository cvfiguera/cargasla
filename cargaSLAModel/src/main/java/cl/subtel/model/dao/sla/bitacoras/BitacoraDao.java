package cl.subtel.model.dao.sla.bitacoras;

import java.util.List;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.Bitacoras;

public interface BitacoraDao {
	
	public List<Bitacoras> getBitacora(Integer archivosId);

	public Boolean setBitacora(Archivos archivo, List<Bitacoras> bitacoras, Boolean inicio);
	
	public void deleteBitacora(Integer archivoId);
	
}
