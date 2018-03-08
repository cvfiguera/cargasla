package cl.subtel.business.service.bitacora;

import cl.subtel.business.vo.BitacoraVO;


public interface BitacoraService {
	
	public Boolean cargarBitacora(BitacoraVO archivo, Integer usuarioId);

}
