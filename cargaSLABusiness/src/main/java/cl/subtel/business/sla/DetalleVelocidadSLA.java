package cl.subtel.business.sla;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import cl.subtel.business.service.procesos.ValidaSlaService;
import cl.subtel.business.util.UtilidadesArchivos;
import cl.subtel.model.common.sla.Archivos;
import cl.subtel.model.common.sla.DetalleDisponibilidad;
import cl.subtel.model.common.sla.DetalleVelocidad;

public class DetalleVelocidadSLA implements java.io.Serializable {

	private static final long serialVersionUID = 4491483224806632400L;
	protected Logger log = Logger.getLogger(getClass().getName());

	private Archivos archivo;
	private List<DetalleVelocidad> detalle;
	private Boolean tipo;
	private ValidaSlaService validarSla;

	public DetalleVelocidadSLA(Archivos archivo, Boolean tipo) {
		super();
		this.archivo = archivo;
		this.tipo = tipo;
		this.detalle = new ArrayList<DetalleVelocidad>();
	}

	public Archivos getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivos archivo) {
		this.archivo = archivo;
	}

	public List<DetalleVelocidad> getDetalle() {
		return this.detalle;
	}

	@Async
	public void setDetalle(List<String[]> arrayArchivo) {
		try {
			UtilidadesArchivos utilidades = new UtilidadesArchivos();
			Integer rbd = 0;
			Date fecha = new Date();
			Boolean[] muestras = new Boolean[10];
			for (int i = 0, hora = 0; i < arrayArchivo.size(); i++) {
				String[] fila = arrayArchivo.get(i);
				if (!rbd.equals((new Integer(fila[1])))) {
					rbd = new Integer(fila[1]);
				}

				if (!fecha.equals(validarSla.valorFecha(fila[9]).getFecha())) {
					fecha = validarSla.valorFecha(fila[9]).getFecha();
					muestras = new Boolean[10];
				}
			
				hora = utilidades.getValorInteger(fila[11].substring(0, fila[11].indexOf(":")));
				if (muestras[hora - 8] == null) {
					muestras[hora - 8] = true;
					detalle.add(new DetalleVelocidad(this.archivo, validarSla.valorFecha(fila[9] + " " + fila[11]).getFecha(), utilidades.getValorBigDecimal(fila[12]),
							this.tipo, utilidades.getValorInteger(fila[1])));
				}
			}
		} catch (Exception ex) {
			log.error("Error al cargar el Resumen de Disponibilidad para el Archivo "
					+ this.archivo.getNombreArchivoTx() + ": " + ex.getMessage(),
					ex.getCause());
		}
	}

	public Boolean getTipo() {
		return tipo;
	}

	public void setTipo(Boolean tipo) {
		this.tipo = tipo;
	}

	public ValidaSlaService getValidarSla() {
		return validarSla;
	}

	public void setValidarSla(ValidaSlaService validarSla) {
		this.validarSla = validarSla;
	}

}
