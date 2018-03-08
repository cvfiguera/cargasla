package cl.subtel.model.dao.sla.archivos;

import cl.subtel.model.common.sla.Archivos;

public interface ArchivosDao {
	
	public Archivos getArchivo(int archivoId);

	public Archivos getArchivoByName(String nombre);
	
	public Archivos getArchivoByControl(Integer archivoControlId);

	public Boolean setArchivo(Archivos archivo);

	public void deleteArchivo(Integer archivoId);

}
