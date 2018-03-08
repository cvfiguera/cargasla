package cl.subtel.ws.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.subtel.business.vo.HistorialArchivoVO;
import cl.subtel.business.vo.ProcesoResumenVO;
import cl.subtel.business.vo.ProcesoVO;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.business.service.procesos.ProcesoService;

@RestController
public class ProcesoController {
	
	@Autowired 
	private ProcesoService procesoService;
	
	@RequestMapping(value = "/procesos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ProcesoResumenVO>> getProcesos(@RequestParam(value= "fechaIni") String fechaIni, 
			@RequestParam(value= "fechaFin") String fechaFin) {
		
		return new ResponseEntity<>(procesoService.getProcesos(fechaIni, fechaFin), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/procesos/{id}/detalle", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ResumenArchivo>> getProcesoDetalle(@PathVariable(value= "id") Integer procesoId) {
		
		return new ResponseEntity<>(procesoService.getProcesoDetalle(procesoId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/procesos/activo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ProcesoVO> getProcesoActivo(@RequestParam(value = "usuario") Integer usuario) {
		
			return new ResponseEntity<>(procesoService.getProcesoActivo(usuario), HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/archivos/{id}/estados", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<HistorialArchivoVO>> getHistorialArchivo(@PathVariable(value="id") Integer archivoId) {
		
			return new ResponseEntity<>(procesoService.getHistorialArchivo(archivoId), HttpStatus.OK);
	}

}
