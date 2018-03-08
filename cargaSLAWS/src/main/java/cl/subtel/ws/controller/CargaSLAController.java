package cl.subtel.ws.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import cl.subtel.business.service.carga.CargaSLAService;
import cl.subtel.business.vo.ArchivoVO;
import cl.subtel.business.vo.EstatusSLA;
import cl.subtel.business.vo.RespuestaSLA;

@RestController
public class CargaSLAController {
	
	@Autowired
	private CargaSLAService cargaSLA;
	
	@RequestMapping(value = "/archivos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ArchivoVO>> getArchivosByOperadora(@RequestParam(value= "operadora") Integer operadora_id, 
			@RequestParam(value= "month") String month, 
			@RequestParam(value= "year") String year) {
		
			return new ResponseEntity<>(cargaSLA.getArchivos(operadora_id, month, year), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/archivos", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public ResponseEntity<Boolean> cargaArchivosSLA(@RequestBody ArrayList<String> archivosIds, 
			@RequestParam(value="usuario") Integer usuario) {
		return new ResponseEntity<>(cargaSLA.cargarArchivos(archivosIds, usuario), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/archivos/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<EstatusSLA>> getEstatusProceso() {
		
		return new ResponseEntity<>(cargaSLA.getEstatus(), HttpStatus.OK);
	}
}
