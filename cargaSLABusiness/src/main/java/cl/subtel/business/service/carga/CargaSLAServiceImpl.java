package cl.subtel.business.service.carga;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import cl.subtel.business.vo.ArchivoVO;
import cl.subtel.business.vo.Avance;
import cl.subtel.business.vo.EstatusSLA;
import cl.subtel.model.common.control.Archivo;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.dao.control.archivo.ArchivoDao;
import cl.subtel.model.dao.control.estados.EstadoDao;
import cl.subtel.model.dao.control.procesos.ProcesosDao;
import cl.subtel.model.dao.sla.archivos.ArchivosDao;
import cl.subtel.model.dao.sla.detalledisponibilidad.DetalleDisponibilidadDao;
import cl.subtel.model.dao.sla.detallevelocidad.DetalleVelocidadDao;
import cl.subtel.model.dao.sla.operadoras.OperadorasDao;
import cl.subtel.model.dao.sla.prefacturas.PrefacturaDAO;
import cl.subtel.model.dao.sla.resumendisponibilidad.ResumenDisponibilidadDao;
import cl.subtel.model.dao.sla.resumenvelocidad.ResumenVelocidadDao;
import cl.subtel.business.prefactura.Prefactura;
import cl.subtel.business.service.procesos.ProcesoService;
import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.sla.DetalleDisponibilidadSLA;
import cl.subtel.business.sla.DetalleVelocidadSLA;
import cl.subtel.business.sla.ResumenDisponibilidadSLA;
import cl.subtel.business.sla.ResumenVelocidadSLA;
import cl.subtel.business.util.GlobalVar;
import cl.subtel.business.util.Proceso;
import cl.subtel.business.util.ReadFile;
import cl.subtel.business.util.UtilidadesArchivos;

@Service
public class CargaSLAServiceImpl implements CargaSLAService{
	
	protected Logger log = Logger.getLogger(getClass().getName());
	private AsyncResult<Proceso> proceso = new AsyncResult<>(null);
	private AsyncResult<Avance> avance = new AsyncResult<>(null);
	
	@Autowired private ArchivosDao archivosDao;
	@Autowired private ResumenDisponibilidadDao rDisponibilidadDao;
	@Autowired private ResumenVelocidadDao rVelocidadDao;
	@Autowired private DetalleDisponibilidadDao dDisponibilidadDao;
	@Autowired private DetalleVelocidadDao dVelocidadDao;
	@Autowired private ProcesoService pService;
	@Autowired protected ArchivoDao archivoDao;
	@Autowired protected EstadoDao estadosDao;
	@Autowired protected ProcesosDao procesoDao;
	@Autowired protected OperadorasDao operadoraDao;
	@Autowired protected PrefacturaDAO prefacturaDao;
	@Autowired private ValidaSlaService validaSlaService;

	
	
	@Override
	@Async
	public Boolean cargarArchivos(List<String> nombres, Integer usuarioId) {
		
		try {
			
			Proceso proceso = new Proceso();
			this.avance = new AsyncResult<>(new Avance());
			if (!proceso.estaBloqueado(procesoDao.getProcesosActivos(), usuarioId)) {
				this.proceso = new AsyncResult<>(proceso);
				List<Archivo> archivos = archivoDao.getArchivosByListParameter(nombres);
				this.proceso.get().iniciarProceso(archivos, usuarioId, new Date(), 
						procesoDao.getTipoProceso(GlobalVar.CARGA_DE_DATOS), 
						estadosDao.getEstadoByCodigo(GlobalVar.NO_CARGADO));
				
				procesoDao.setProceso(this.proceso.get().getProceso());
				procesoDao.setProcesoDetalle(this.proceso.get().getProcesoDetalle());
				
				for (int i = 0, n=archivos.size(); i < n ; i++) {
					
					Archivo archivo = archivos.get(i);
					System.out.println(archivo.getId().getNombreArchivo());
					this.proceso.get().addStatus(archivo, estadosDao.getEstadoByCodigo(GlobalVar.CARGANDO), 0.00, false, "CARGANDO");
					procesoDao.setProcesoDetalle(this.proceso.get().getProcesoDetalle());
					
					if (archivoDao.tieneStatus(archivo.getId(), GlobalVar.VALIDADO)) {	
						
						log.info("Iniciando la carga del archivo " + (i+1) + " de " + n + "...");
						Integer tipo = archivo.getArchivosTipos().getArchivoTipoId();
						
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
					
						ReadFile readSLA = new ReadFile();
						List<String[]> arrayArchivo;
						Boolean procesado = false;
						
						
						if (tipo == GlobalVar.PREFACTURA)
							arrayArchivo = readSLA.getFileExcel(archivo.getRuta() + "/" + archivo.getId().getNombreArchivo(), tipo);
						else 
							arrayArchivo = readSLA.getFilePlain(archivo.getRuta() + "/" + archivo.getId().getNombreArchivo());
						
						if (arrayArchivo != null) {
							int m = arrayArchivo.size();
							this.avance.get().setValor(((double) 1/m));
							int div =0;
							if (m < 100)
								div = m;
							else
								div = (int)(m * 0.1) + 1;
	
							for (int j = 1; j < m; j = j+div) {
							
								this.avance.get().setArchivoId(archivo.getId().getArchivoId());
								this.avance.get().setValor((((double) ((j+div)>m?m:j+div) / m) * 0.95)*100);
								
								List<String[]> subArchivo = new ArrayList<String[]>(arrayArchivo.subList(j,(j+div)>m?m:j+div));
								procesado = this.saveArchivo(subArchivo, archivo.getArchivosTipos().getArchivoTipoId(), archivoSLA, j==1?true:false);
								if (!procesado) break;
							}
							
							if (procesado)
								this.proceso.get().addStatus(archivo, estadosDao.getEstadoByCodigo(GlobalVar.CARGADO), 100.00, true, "CARGADO");
							else
								this.proceso.get().addStatus(archivo, estadosDao.getEstadoByCodigo(GlobalVar.ERROR_EN_CARGA), 100.00, false, "ERROR EN CARGA BD");
							procesoDao.setProcesoDetalle(this.proceso.get().getProcesoDetalle());
						} else { //Error en lectura
							log.info("Error en lectura del archivo: " + archivo.getId().getNombreArchivo());
							this.proceso.get().addStatus(archivo, estadosDao.getEstadoByCodigo(GlobalVar.ERROR_EN_CARGA), 100.00, false, "ERROR EN LECTURA");
						}
					
					} else { //NO TIENE ESTATUS VALIDADO
						log.info("El archivo no tiene estatus Validado: " + archivo.getId().getNombreArchivo());
						this.proceso.get().addStatus(archivo, estadosDao.getEstadoByCodigo(GlobalVar.ERROR_EN_CARGA), 100.00, false, "ARCHIVO NO VALIDADO");
					}
				}
				
				this.proceso.get().finalizarProceso();
				procesoDao.setProcesoDetalle(this.proceso.get().getProcesoDetalle());
				procesoDao.updateProceso(this.proceso.get().getProceso());
				return true;
			}
			return false;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<ArchivoVO> getArchivos(Integer operadoraId, String month, String year) {
		
		List<ArchivoVO> archivosVO = new ArrayList<ArchivoVO>();
		List<ResumenArchivo> archivos = archivoDao.getArchivosValidados(operadoraId, month, year);
		
		for(int i=0; i<archivos.size(); i++) {
			ResumenArchivo archivo = archivos.get(i);
			if (!archivo.getTipo().equals("BITACORA")) {
				archivosVO.add(new ArchivoVO(archivo.getId(), archivo.getTipo(), archivo.getNombre(), 
					archivo.getVersion(), archivo.getFecha(), archivo.getEstado(), 
					pService.getHistorialArchivo(archivo.getId())));
			}
		}
		return archivosVO;
	}
	
	private Boolean saveArchivo(List<String[]> subArchivo, Integer tipo, Archivos archivoSLA, Boolean inicio) {
		switch (tipo) {
		case GlobalVar.RESUMEN_DISPONIBILIDAD: //RESUMEN DISPONIBILIDAD 
			ResumenDisponibilidadSLA rDisponibilidad = new ResumenDisponibilidadSLA(archivoSLA);
			rDisponibilidad.setValidarSla(validaSlaService);
			rDisponibilidad.setResumen(subArchivo);
			if (!rDisponibilidadDao.setResumenDisponibilidad(archivoSLA, rDisponibilidad.getResumen(), 
					rDisponibilidad.getPromedios(), inicio)) 
				return false;
			break;
		case GlobalVar.RESUMEN_VELOCIDAD: //RESUMEN VELOCIDAD
			ResumenVelocidadSLA rVelocidad = new ResumenVelocidadSLA(archivoSLA);
			rVelocidad.setValidarSla(validaSlaService);
			rVelocidad.setResumen(subArchivo);
			if (!rVelocidadDao.setResumenVelocidad(archivoSLA, rVelocidad.getResumen(), 
					rVelocidad.getPromedios(), inicio)) 
				return true;
			break;
		case GlobalVar.DETALLE_DISPONIBILIDAD: //DETALLE DISPONIBILIDAD
			DetalleDisponibilidadSLA dDisponibilidad = new DetalleDisponibilidadSLA(archivoSLA);
			dDisponibilidad.setValidarSla(validaSlaService);
			dDisponibilidad.setDetalle(subArchivo);
			if (!dDisponibilidadDao.setDetalleDisponibilidad(archivoSLA, 
					dDisponibilidad.getDetalle(), inicio))
				return false; 
			break;
		case GlobalVar.DETALLE_VELOCIDAD_BAJADA: //DETALLE VELOCIDAD BAJADA
			DetalleVelocidadSLA dVelocidadBajada = new DetalleVelocidadSLA(archivoSLA, false);
			dVelocidadBajada.setValidarSla(validaSlaService);
			dVelocidadBajada.setDetalle(subArchivo);
			if (!dVelocidadDao.setDetalleVelocidad(archivoSLA, 
					dVelocidadBajada.getDetalle(), inicio))
				return false;
			break;
		case GlobalVar.RDETALLE_VELOCIDAD_SUBIDA: //DETALLE VELOCIDAD SUBIDA
			DetalleVelocidadSLA dVelocidadSubida = new DetalleVelocidadSLA(archivoSLA, true);
			dVelocidadSubida.setValidarSla(validaSlaService);
			dVelocidadSubida.setDetalle(subArchivo);
			if (!dVelocidadDao.setDetalleVelocidad(archivoSLA, 
					dVelocidadSubida.getDetalle(), inicio))
				return false;
			break;
		case GlobalVar.PREFACTURA: //PREFACTURA
			Prefactura prefactura = new Prefactura(archivoSLA);
			prefactura.setValidarSla(validaSlaService);
			prefactura.setPrefacturas(subArchivo, inicio);
			if (!prefacturaDao.setPrefactura(archivoSLA, prefactura.getPrefacturas(), inicio))
				return false;
			break;
		default: //NO DEFINIDO
			log.error("Tipo de archivo indefinido");
			return false; 
	}
		return true; 
	}

	@Override
	public List<EstatusSLA> getEstatus() {
		
		try {
			if (this.proceso.get() != null) {
				UtilidadesArchivos utilidades = new UtilidadesArchivos();
				List<EstatusSLA> estatus = this.proceso.get().getStatus();
				for (int i=0; i<estatus.size(); i++) {
					EstatusSLA estado = estatus.get(i);
					
					if(!estado.getProcesado() && (estado.getId() == this.avance.get().getArchivoId()))
						estado.setValor(utilidades.getRoundDecimal(this.avance.get().getValor()));
					
					estatus.set(i, estado);
				}
				return estatus;
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new ArrayList<EstatusSLA>();
	}
}
