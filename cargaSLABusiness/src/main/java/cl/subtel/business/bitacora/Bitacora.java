package cl.subtel.business.bitacora;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.util.UtilidadesArchivos;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.Bitacoras;

public class Bitacora implements java.io.Serializable {

	private static final long serialVersionUID = 4146497432060456143L;
	protected Logger log = Logger.getLogger(getClass().getName());
	private Archivos archivo;
	private List<Bitacoras> bitacoras;
	
	public Bitacora(Archivos archivo) {
		super();
		this.archivo = archivo;
		this.bitacoras = new ArrayList<Bitacoras>();
	}

	public Archivos getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivos archivo) {
		this.archivo = archivo;
	}

	public List<Bitacoras> getBitacoras() {
		return bitacoras;
	}

	public void setBitacoras(List<String[]> arrayArchivo) {
		
		UtilidadesArchivos utilidades = new UtilidadesArchivos();
		
		for (int i = 1; i < arrayArchivo.size(); i++) {
			
			String[] fila = arrayArchivo.get(i);
			
			try {
				Date[] horas = utilidades.getRangoHoras(fila[3], fila[4]);
				
				Bitacoras bitacora = new Bitacoras(this.archivo, utilidades.getValorDate(fila[1]), utilidades.getValorDate(fila[2]), 
						horas[0], horas[1], utilidades.getValorInteger(fila[5]), utilidades.getValorInteger(fila[0]));
				
				bitacoras.add(bitacora);
				
			} catch (Exception ex) {
				log.error("Error desconocido al cargar la bitacora para el archivo " + this.archivo.getNombreArchivoTx() + " en el Rbd " +  fila[0] + ": " + ex.getMessage(), ex.getCause());
			}
		}
	}


}
