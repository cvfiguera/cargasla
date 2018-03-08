package cl.subtel.model.dao.sla.promediosmensuales;

import cl.subtel.model.common.sla.PromedioMensualVel;

public interface PromedioMensualVelDao {

	public PromedioMensualVel getPromedio(int promedioId);
	
	public PromedioMensualVel getPromedioByArchivo(int archivoId, Integer rbd);

	public Boolean setPromedio(PromedioMensualVel promedio);
	
	public Boolean updatePromedio(PromedioMensualVel promedio);
	
	public Boolean deletePromedio(PromedioMensualVel promedio);
	
	public void deletePromedioByArchivo(int archivoId);
	
}
