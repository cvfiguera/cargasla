package cl.subtel.model.dao.sla.promediosmensuales;

import cl.subtel.model.common.sla.PromedioMensualDispo;

public interface PromedioMensualDispoDao {

	public PromedioMensualDispo getPromedio(int promedioId);
	
	public PromedioMensualDispo getPromedioByArchivo(int archivoId, Integer rbd);

	public Boolean setPromedio(PromedioMensualDispo promedio);
	
	public Boolean updatePromedio(PromedioMensualDispo promedio);
	
	public Boolean deletePromedio(PromedioMensualDispo promedio);
	
	public void deletePromedioByArchivo(int archivoId);
	
}
