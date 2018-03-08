package cl.subtel.model.dao.control.archivo;

import java.util.List;

import cl.subtel.model.common.control.Archivo;
import cl.subtel.model.common.control.ArchivoId;
import cl.subtel.model.common.control.ArchivoVersion;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.model.common.control.ResumenArchivosOpe;

public interface ArchivoDao {
	
	public List<Archivo> getArchivosById(Integer archivoId);
	
	public List<ArchivoVersion> getArchivosValidosById(Integer archivoId);

	public List<Archivo> getArchivosByListParameter(List<String> nombres);
	
	public Archivo getArchivoByArchivoId(Integer id, String nombre);
	
	public Archivo getArchivoByParameters(Integer tipo, Integer operadora, Integer version, String mes, String anio);
	
	public List<ResumenArchivo> getArchivosValidados(Integer operadoraId, String mes, String ano);
	
	public ResumenArchivosOpe getArchivosResumenOpe(Integer operadora, Integer estado, String mes, String ano);
	
	public List<ResumenArchivo> getArchivosByTipo(Integer operadoraId, String mes, String ano, Integer tipo);

	public Boolean tieneStatus(ArchivoId id, Integer status);
	
	public void setArchivo(Archivo archivo);
}
