package cl.subtel.model.dao.sla.prefacturas;

import java.util.List;

import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.Prefacturas;

public interface PrefacturaDAO {

	public List<Prefacturas> getPrefactura(Integer archivosId);

	public Boolean setPrefactura(Archivos archivo, List<Prefacturas> prefacturas, Boolean inicio);
	
	public void deletePrefactura(Integer archivosId);
}
