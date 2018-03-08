package cl.subtel.business.sla;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.service.procesos.ValidaSlaServiceImpl;
import cl.subtel.business.util.UtilidadesArchivos;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.PromedioMensualDispo;
import cl.subtel.model.common.sla.ResumenDisponibilidad;

public class ResumenDisponibilidadSLA implements java.io.Serializable {
	
	private static final long serialVersionUID = 5259764639234709041L;
	protected Logger log = Logger.getLogger(getClass().getName());
	
	private Archivos archivoSLA;	
	private List<ResumenDisponibilidad> resumen;
	private List<PromedioMensualDispo> promedios;
	
	private ValidaSlaService validarSla;

	public ResumenDisponibilidadSLA(Archivos archivo) {
		super();
		this.archivoSLA = archivo;
		this.resumen = new ArrayList<ResumenDisponibilidad>();
		this.promedios = new ArrayList<PromedioMensualDispo>();
	}
	

	public Archivos getArchivoSLA() {
		return archivoSLA;
	}

	public List<ResumenDisponibilidad> getResumen() {
		return resumen;
	}

	public List<PromedioMensualDispo> getPromedios() {
		return promedios;
	}
	
	@Async
	public void setResumen(List<String[]> arrayArchivo) {
		
		for (int i = 0; i < arrayArchivo.size(); i++) {
			
			try {
					
				String[] fila = arrayArchivo.get(i);
				this.setRbdResumen(Integer.parseInt(fila[0]), Arrays.copyOfRange(fila, 8, fila.length - 2));
				this.setPromediosMensuales(Integer.parseInt(fila[0]), fila[fila.length - 2]);
					
			} catch (Exception ex) {
				log.error("Error al cargar el Resumen de Disponibilidad" + ex.getMessage(), ex.getCause());
			}
		}
	}
	
	private void setRbdResumen(Integer rbd, String[] dias) {
		UtilidadesArchivos utilidades = new UtilidadesArchivos();
		for (int i = 0; i < dias.length; i= i+2) {
			try {
				Date fecha= validarSla.valorFecha(dias[i]).getFecha();
				
				BigDecimal valor = utilidades.getValorBigDecimal(dias[i+1]);
				this.resumen.add(new ResumenDisponibilidad(this.archivoSLA, fecha, valor, rbd));
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex.getCause());
			}					
		}
	}

	private void setPromediosMensuales(Integer rbd, String promedio) {
		try {
			UtilidadesArchivos utilidades = new UtilidadesArchivos();
			this.promedios.add(new PromedioMensualDispo(this.archivoSLA, utilidades.getValorBigDecimal(promedio),
					rbd));
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex.getCause());
		}
	}


	public ValidaSlaService getValidarSla() {
		return validarSla;
	}


	public void setValidarSla(ValidaSlaService validarSla) {
		this.validarSla = validarSla;
	}


	
	
	
		
}
