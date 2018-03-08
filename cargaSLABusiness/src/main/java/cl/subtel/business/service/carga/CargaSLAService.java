package cl.subtel.business.service.carga;

import java.util.List;
import java.util.concurrent.Future;

import cl.subtel.business.vo.ArchivoVO;
import cl.subtel.business.vo.EstatusSLA;
import cl.subtel.business.vo.RespuestaSLA;

public interface CargaSLAService {

	public Boolean cargarArchivos(List <String> archivosIds, Integer usuarioId);
	
	public List<ArchivoVO> getArchivos(Integer operadora_id, String month, String year);
	
	public List<EstatusSLA> getEstatus();

}
