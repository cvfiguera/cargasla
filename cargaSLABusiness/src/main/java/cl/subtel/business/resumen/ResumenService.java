package cl.subtel.business.resumen;

import java.util.List;

import cl.subtel.business.vo.ProcesoResumenVO;
import cl.subtel.business.vo.ResumenOperadoraVO;

public interface ResumenService {
	
	public List<ProcesoResumenVO> getUltimosProcesos(Integer cant);
	
	public List<ResumenOperadoraVO> getResumenOperadoras(String mes, String anio);

}
