package cl.subtel.business.prefactura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;

import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.util.UtilidadesArchivos;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.Prefacturas;

public class Prefactura implements java.io.Serializable {

	private static final long serialVersionUID = 419060954942673073L;
	protected Logger log = Logger.getLogger(getClass().getName());
	private Archivos archivo;
	private List<Prefacturas> prefacturas;
	private Integer diasMes;
	
	private ValidaSlaService validarSla;
	
	public Prefactura(Archivos archivo) {
		super();
		this.archivo = archivo;
		this.prefacturas = new ArrayList<Prefacturas>();
	}

	public Archivos getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivos archivo) {
		this.archivo = archivo;
	}

	public List<Prefacturas> getPrefacturas() {
		return prefacturas;
	}

	public void setPrefacturas(List<String[]> arrayArchivo, Boolean inicio) {
		UtilidadesArchivos utilidades = new UtilidadesArchivos();
		Date fechas =null;
		if (inicio) {
			String[] fecha = arrayArchivo.get(0);
			Calendar cal =  new GregorianCalendar(Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]), 1); 
		}
		
		for (int i = inicio?2:0; i < arrayArchivo.size(); i++) {
			
			String[] fila = arrayArchivo.get(i);
			
			try {
				Prefacturas prefactura;
				if (fila.length>12) {
					fechas = validarSla.valorFecha(fila[3]).getFecha();
					
					prefactura = new Prefacturas(this.archivo, utilidades.getValorInteger(fila[5]), 
							utilidades.getValorBigDecimal(fila[6]), utilidades.getValorBigDecimal(fila[8]),
							utilidades.getValorBigDecimal(fila[7]), utilidades.getValorBigDecimal(fila[9]), 
							this.diasMes, utilidades.getValorBigDecimal(fila[10]), utilidades.getValorBigDecimal(fila[11]),
							fila[2], fila.length>13?fila[13]:null,fechas, 
									utilidades.getValorInteger(fila[4]).toString(), utilidades.getValorInteger(fila[1]));
					
				} else {
					fechas = validarSla.valorFecha(fila[3]).getFecha();
					prefactura = new Prefacturas(this.archivo, utilidades.getValorInteger(fila[5]), 
							utilidades.getValorBigDecimal(fila[6]), null,
							null,null, diasMes, utilidades.getValorBigDecimal(fila[7]), utilidades.getValorBigDecimal(fila[8]),
							fila[2], fila.length>10?fila[10]:null, fechas, 
									utilidades.getValorInteger(fila[4]).toString(), utilidades.getValorInteger(fila[1]));
				}
				
				this.prefacturas.add(prefactura);
				
			} catch (Exception ex) {
				log.error("Error desconocido al cargar la prefactura para el archivo " + this.archivo.getNombreArchivoTx() + " en el Rbd " +  fila[1] + ": " + ex.getMessage(), ex.getCause());
			}
		}
	}

	public ValidaSlaService getValidarSla() {
		return validarSla;
	}

	public void setValidarSla(ValidaSlaService validarSla) {
		this.validarSla = validarSla;
	}
	
	
}
