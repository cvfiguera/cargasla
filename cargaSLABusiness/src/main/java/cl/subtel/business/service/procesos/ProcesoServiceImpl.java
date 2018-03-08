package cl.subtel.business.service.procesos;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.subtel.business.util.GlobalVar;
import cl.subtel.business.util.Proceso;
import cl.subtel.business.vo.ArchivoVO;
import cl.subtel.business.vo.HistorialArchivoVO;
import cl.subtel.business.vo.ProcesoResumenVO;
import cl.subtel.business.vo.ProcesoVO;
import cl.subtel.model.common.control.Archivo;
import cl.subtel.model.common.control.HistorialArchivo;
import cl.subtel.model.common.control.ProcesoResumen;
import cl.subtel.model.common.control.Procesos;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.model.dao.control.archivo.ArchivoDao;
import cl.subtel.model.dao.control.estados.EstadoDao;
import cl.subtel.model.dao.control.procesos.ProcesosDao;
import cl.subtel.model.dao.security.usuarios.UsuariosDao;
import cl.subtel.model.dao.sla.operadoras.OperadorasDao;

@Service
public class ProcesoServiceImpl implements ProcesoService {

	@Autowired protected ProcesosDao procesoDao;
	@Autowired protected ArchivoDao archivoDao;
	@Autowired protected EstadoDao estadosDao;
	@Autowired protected UsuariosDao usuarioDao;
	@Autowired protected OperadorasDao operadoraDao;
	
	@Override
	public ProcesoVO getProcesoActivo(Integer usuarioId) {
		Procesos proceso = procesoDao.getProcesoActivo(usuarioId, GlobalVar.CARGA_DE_DATOS, GlobalVar.ACTIVO);
		if (proceso!=null) {
			List<ResumenArchivo> archivos= procesoDao.getMaxProcesosDetalle(proceso.getProcesoId());
			Archivo archivo = archivoDao.getArchivoByArchivoId(archivos.get(0).getId(), archivos.get(0).getNombre());
			ProcesoVO procesoVO = new ProcesoVO(proceso.getProcesoId(), proceso.getTiposProceso().getNombre(),
					proceso.getUsuario(), proceso.getFecha(),
					archivo.getOperadora(), Integer.parseInt(archivo.getMes()), 
					Integer.parseInt(archivo.getAno()), (List<ArchivoVO>) (List) archivos);
			return procesoVO;
		}
		return new ProcesoVO();
	}

	@Override
	public List<HistorialArchivoVO> getHistorialArchivo(Integer archivoId) {
		List<HistorialArchivo> list = estadosDao.getHistorialArchivo(archivoId);
		List<HistorialArchivoVO> listVO = new ArrayList<HistorialArchivoVO>();
		
		for(int i=0; i< list.size(); i++) {
			HistorialArchivo itm = list.get(i);
			listVO.add(new HistorialArchivoVO(itm.getId(), itm.getNombre(), itm.getFecha(),
					itm.getEstado(), usuarioDao.getUsuario(itm.getUsuario()).getUsuario()));
		}
		
		return listVO;
	}

	@Override
	public List<ProcesoResumenVO> getProcesos(String fechaIni, String fechaFin) {
		
		List<ProcesoResumen> list = procesoDao.getProcesoByFecha(fechaIni, fechaFin);
		List<ProcesoResumenVO> listVO = new ArrayList<ProcesoResumenVO>();
		
		for(int i=0; i< list.size(); i++) {
			ProcesoResumen itm = list.get(i);
			listVO.add(new ProcesoResumenVO(itm.getId(), itm.getProceso(), 
					operadoraDao.getOperadora(itm.getOperadora()).getNombreSocialTx(), itm.getMes(),
					itm.getAno(), usuarioDao.getUsuario(itm.getUsuario()).getUsuario(),
					itm.getFecha(), itm.getEstado()));
		}
		
		return listVO;
	}
	
	@Override
	public List<ResumenArchivo> getProcesoDetalle(Integer procesoId) {
		return procesoDao.getMaxProcesosDetalle(procesoId);		
	}
}
