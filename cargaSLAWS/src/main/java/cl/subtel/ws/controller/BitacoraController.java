package cl.subtel.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cl.subtel.business.service.bitacora.BitacoraService;
import cl.subtel.business.vo.BitacoraVO;

@RestController
public class BitacoraController {
	
	@Autowired BitacoraService bService;
	
	@RequestMapping(value = "/bitacora", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public ResponseEntity<Boolean> cargaArchivosSLA(@RequestBody BitacoraVO archivo, 
			@RequestParam(value="usuario") Integer usuario) {
		return new ResponseEntity<>(bService.cargarBitacora(archivo, usuario), HttpStatus.OK);
	}

}
