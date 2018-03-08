package cl.subtel.business.service.bitacora;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.subtel.business.bitacora.Bitacora;
import cl.subtel.business.service.procesos.ProcesoService;
import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.util.GlobalVar;
import cl.subtel.business.util.ReadFile;
import cl.subtel.business.vo.ArchivoVO;
import cl.subtel.business.vo.BitacoraVO;
import cl.subtel.model.common.control.Archivo;
import cl.subtel.model.common.control.Procesos;
import cl.subtel.model.common.control.ProcesosDetalle;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.dao.control.archivo.ArchivoDao;
import cl.subtel.model.dao.control.estados.EstadoDao;
import cl.subtel.model.dao.control.procesos.ProcesosDao;
import cl.subtel.model.dao.sla.archivos.ArchivosDao;
import cl.subtel.model.dao.sla.bitacoras.BitacoraDao;

@Service
public class BitacoraServiceImpl implements BitacoraService {

	protected Logger log = Logger.getLogger(getClass().getName());
	@Autowired private ProcesosDao procesoDao;
	@Autowired private ArchivoDao archivoDao;
	@Autowired private ArchivosDao archivosDao;
	@Autowired private BitacoraDao bitacoraDao;
	@Autowired private EstadoDao estadosDao;
	
	@Override
	public Boolean cargarBitacora(BitacoraVO nombre, Integer usuarioId) {
		ReadFile readSLA = new ReadFile();
		List<String[]> arrayArchivo;
		Boolean procesado = false;
		Archivo archivo = archivoDao.getArchivoByParameters(GlobalVar.BITACORA, nombre.getOperadora(), 
				nombre.getVersion(), nombre.getMes(), nombre.getYear());
		Procesos proceso = new Procesos(procesoDao.getTipoProceso(GlobalVar.CARGA_DE_DATOS),
				usuarioId, new Date(), GlobalVar.ACTIVO);
		List<ProcesosDetalle> pDetalle = new ArrayList<ProcesosDetalle>();
		pDetalle.add(new ProcesosDetalle(archivo, estadosDao.getEstadoByCodigo(GlobalVar.NO_CARGADO),
				proceso));
		if (archivo != null) {
			if (archivoDao.tieneStatus(archivo.getId(), GlobalVar.VALIDADO)) {	
				
				Archivos archivoSLA = archivosDao.getArchivoByControl(archivo.getId().getArchivoId());
				if(archivoSLA!= null) {
					archivoSLA.setFechaProcesamientoFe(new Date());
					archivoSLA.setNombreArchivoTx(archivo.getId().getNombreArchivo());
					archivoSLA.setVersionNu(archivo.getVersion());
					archivoSLA.setOperadora(archivo.getOperadora());
				} else {
					archivoSLA = new Archivos(archivo.getId().getNombreArchivo(), archivo.getVersion(),
						new Date(), archivo.getId().getArchivoId(), archivo.getOperadora());
				}
				
				arrayArchivo = readSLA.getFileExcel(archivo.getRuta() + "/" + nombre.getNombre(), archivo.getArchivosTipos().getArchivoTipoId());
				
				if (arrayArchivo != null) {
					
					pDetalle.add(new ProcesosDetalle(archivo, estadosDao.getEstadoByCodigo(GlobalVar.CARGANDO),
							proceso));
		
					Bitacora bitacora = new Bitacora(archivoSLA);
					bitacora.setBitacoras(arrayArchivo);
					if (bitacoraDao.setBitacora(archivoSLA, bitacora.getBitacoras(), true));
						procesado = true;
		
				}
			}
		} else procesado = false;
		
		if (procesado) {
			pDetalle.add(new ProcesosDetalle(archivo, estadosDao.getEstadoByCodigo(GlobalVar.CARGADO),
					proceso));
		} else {
			pDetalle.add(new ProcesosDetalle(archivo, estadosDao.getEstadoByCodigo(GlobalVar.ERROR_EN_CARGA),
					proceso));
		}
		
		proceso.setEstatus(GlobalVar.INACTIVO);
		procesoDao.setProceso(proceso);
		procesoDao.setProcesoDetalle(pDetalle);
		
		return procesado;
	}

}
