package cl.subtel.business.sla;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.util.UtilidadesArchivos;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.DetalleDisponibilidad;

public class DetalleDisponibilidadSLA implements java.io.Serializable {
	
	private static final long serialVersionUID = -790903883512321652L;
	protected Logger log = Logger.getLogger(getClass().getName());
	private Archivos archivo;
	private List<DetalleDisponibilidad> detalle;
	private Boolean procesado;
	private ValidaSlaService validarSla;
	
	public DetalleDisponibilidadSLA(Archivos archivo) {
		super();
		this.archivo = archivo;
		this.detalle = new ArrayList<DetalleDisponibilidad>();
	}
	
	public Archivos getArchivo() {
		return archivo;
	}
	
	public List<DetalleDisponibilidad> getDetalle() {
		return detalle;
	}
	
	public void setArchivo(Archivos archivo) {
		this.archivo = archivo;
	}
	
	public Boolean getProcesado() {
		return this.procesado;
	}
	
	public void setDetalle(List<String[]> arrayArchivo) {
		try {
			UtilidadesArchivos utilidades = new UtilidadesArchivos();
			Integer rbd = 0;
			Date fecha = new Date();
			Boolean[] muestras = new Boolean[10];
			for (int i = 0, n = arrayArchivo.size(), hora = 0; i < n; i++) {
				String[] fila = arrayArchivo.get(i);
				if (!rbd.equals((new Integer (fila[1])))) {
					rbd = new Integer (fila[1]);
				}
				
				if(!fecha.equals(validarSla.valorFecha(fila[8]).getFecha())) {
					fecha = validarSla.valorFecha(fila[8]).getFecha();
					muestras = new Boolean[10];
				}
				
				hora = utilidades.getValorInteger(fila[10].substring(0, fila[10].indexOf(":")));
				if (muestras[hora - 8] == null) {
					muestras[hora - 8] = true;
					detalle.add(new DetalleDisponibilidad(this.archivo, validarSla.valorFecha(fila[8] + " " + fila[10]).getFecha(),
							utilidades.getValorInteger(fila[11]), rbd));
				}
			}
		} catch (Exception ex) {
			log.error("Error al cargar el Resumen de Disponibilidad para el Archivo " + this.archivo.getNombreArchivoTx() + ": " + ex.getMessage(), ex.getCause());
			procesado = false;
		}
	}

	public ValidaSlaService getValidarSla() {
		return validarSla;
	}

	public void setValidarSla(ValidaSlaService validarSla) {
		this.validarSla = validarSla;
	}
	
	

}
