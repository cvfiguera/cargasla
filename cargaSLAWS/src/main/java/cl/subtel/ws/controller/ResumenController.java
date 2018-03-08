package cl.subtel.ws.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cl.subtel.business.resumen.ResumenService;
import cl.subtel.business.vo.ProcesoResumenVO;
import cl.subtel.business.vo.ResumenOperadoraVO;

@RestController
public class ResumenController {
	
	@Autowired 
	private ResumenService resumenService;
	
	@RequestMapping(value = "/resumen/procesos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ProcesoResumenVO>> getResumenProcesos(@RequestParam(value= "n") Integer n) {
		
		return new ResponseEntity<>(resumenService.getUltimosProcesos(n), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resumen/operadoras", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ResumenOperadoraVO>> getResumenOperadoras(@RequestParam(value= "mes") String mes,
			@RequestParam(value= "anio") String anio) {
		
		return new ResponseEntity<>(resumenService.getResumenOperadoras(mes, anio), HttpStatus.OK);
	}

}
