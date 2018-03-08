package cl.subtel.model.dao.control.estados;

import java.util.List;

import cl.subtel.model.common.control.Estados;
import cl.subtel.model.common.control.HistorialArchivo;

public interface EstadoDao {

	public Estados getEstadoByCodigo(Integer codigo);
	
	public List<HistorialArchivo> getHistorialArchivo(Integer archivoId);
}
