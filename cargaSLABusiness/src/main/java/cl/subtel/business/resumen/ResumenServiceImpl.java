package cl.subtel.business.resumen;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.subtel.business.vo.ProcesoResumenVO;
import cl.subtel.business.vo.ResumenOperadoraVO;
import cl.subtel.model.common.control.ProcesoResumen;
import cl.subtel.model.common.control.ResumenArchivosOpe;
import cl.subtel.model.common.web.Operadoras;
import cl.subtel.model.dao.control.archivo.ArchivoDao;
import cl.subtel.model.dao.control.procesos.ProcesosDao;
import cl.subtel.model.dao.security.usuarios.UsuariosDao;
import cl.subtel.model.dao.sla.operadoras.OperadorasDao;

@Service
public class ResumenServiceImpl implements ResumenService {

	@Autowired private ProcesosDao procesoDao;
	@Autowired private UsuariosDao usuarioDao;
	@Autowired private OperadorasDao operadoraDao;
	@Autowired private ArchivoDao archivoDao; 
	
	@Override
	public List<ProcesoResumenVO> getUltimosProcesos(Integer cant) {
		List<ProcesoResumen> pResumen = procesoDao.getUltimosProcesos(cant);
		List<ProcesoResumenVO> pResumenVO = new ArrayList<ProcesoResumenVO>();
		
		for (ProcesoResumen proceso: pResumen) {
			pResumenVO.add(new ProcesoResumenVO(proceso.getId(), proceso.getProceso(),
					operadoraDao.getOperadora(proceso.getOperadora()).getAliasTx(),
					proceso.getMes(), proceso.getAno(), usuarioDao.getUsuario(proceso.getUsuario()).getUsuario(),
					proceso.getFecha(), proceso.getEstado()));
			
		}
		return pResumenVO;
	}

	@Override
	public List<ResumenOperadoraVO> getResumenOperadoras(String mes, String anio) {
		List<Operadoras> operadoras = operadoraDao.getOperadoras();
		List<ResumenOperadoraVO> rOperadorasVO = new ArrayList<ResumenOperadoraVO>();
		
		for(Operadoras operadora : operadoras) {
			ResumenArchivosOpe rOpe = archivoDao.getArchivosResumenOpe(operadora.getOperadoraId(), 3, mes, anio);
			ResumenOperadoraVO rOpeVo = new ResumenOperadoraVO();
			rOpeVo.setOperadoraId(operadora.getOperadoraId());
			rOpeVo.setOperadora(operadora.getAliasTx());
			if (rOpe != null) 
				rOpeVo.setValidados(rOpe.getArchivos());
			else
				rOpeVo.setValidados(BigInteger.ZERO);
			rOpe = archivoDao.getArchivosResumenOpe(operadora.getOperadoraId(), 7, mes, anio);
			if (rOpe != null) 
				rOpeVo.setCargados(rOpe.getArchivos());
			else
				rOpeVo.setCargados(BigInteger.ZERO);
			rOpe = archivoDao.getArchivosResumenOpe(operadora.getOperadoraId(), 12, mes, anio);
			if (rOpe != null) 
				rOpeVo.setFacturados(rOpe.getArchivos());
			else
				rOpeVo.setFacturados(BigInteger.ZERO);
			
			rOperadorasVO.add(rOpeVo);
		}
		
		return rOperadorasVO;
	}
}
