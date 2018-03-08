package cl.subtel.business.sla;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.util.UtilidadesArchivos;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.PromedioMensualVel;
import cl.subtel.model.common.sla.ResumenVelocidad;

public class ResumenVelocidadSLA implements java.io.Serializable {
	
	private static final long serialVersionUID = 5259764639234709041L;
	protected Logger log = Logger.getLogger(getClass().getName());
	
	private Archivos archivoSLA;	
	private List<ResumenVelocidad> resumen;
	private List<PromedioMensualVel> promedios;
	private Boolean error;
	
	private ValidaSlaService validarSla;
	
	public ResumenVelocidadSLA(Archivos archivoSLA) {
		super();
		this.archivoSLA = archivoSLA;
		this.resumen = new ArrayList<ResumenVelocidad>();
		this.promedios = new ArrayList<PromedioMensualVel>();
		this.error = false;
	}

	public Archivos getArchivoSLA() {
		return archivoSLA;
	}

	public List<ResumenVelocidad> getResumen() {
		return resumen;
	}

	public List<PromedioMensualVel> getPromedios() {
		return promedios;
	}	
	
	public Boolean getError() {
		return error;
	}
	
	@Async
	public void setResumen(List<String[]> arrayArchivo) {
		
		for (int i = 0; i < arrayArchivo.size(); i++) {
			try {
				String[] fila = arrayArchivo.get(i);
				this.setRbdResumen(Integer.parseInt(fila[0]), Arrays.copyOfRange(fila, 10, fila.length - 3));
				this.setPromedios(Integer.parseInt(fila[0]), Arrays.copyOfRange(fila, fila.length - 3, fila.length));
			} catch (Exception ex) {
				log.error("Error al cargar el Resumen de Velocidad" + ex.getMessage(), ex.getCause());
				this.error = true;
			}
		}
	}
	
	private void setRbdResumen(Integer rbd, String[] dias) {
		UtilidadesArchivos utilidades = new UtilidadesArchivos();
		for (int i = 0; i < dias.length; i++) {
			try {
				System.out.println(dias[i]);	
				Date fecha=validarSla.valorFecha(dias[i]).getFecha();
				
				BigDecimal valor1 = utilidades.getValorBigDecimal(dias[i+1]);
				BigDecimal valor2 = utilidades.getValorBigDecimal(dias[i+2]);
				
				this.resumen.add(new ResumenVelocidad(this.archivoSLA, fecha, valor1, valor2, rbd));			
				
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex.getCause());
				this.error = true;
			}
			i = i+2;
		} 		
	}
	
	private void setPromedios(Integer rbd, String[] promedios) {
		try {
			UtilidadesArchivos utilidades = new UtilidadesArchivos();
			this.promedios.add(new PromedioMensualVel(this.archivoSLA, utilidades.getValorBigDecimal(promedios[1]),
				utilidades.getValorBigDecimal(promedios[0]), rbd));
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
