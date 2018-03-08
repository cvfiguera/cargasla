package cl.subtel.business.util;

public class GlobalVar {
	
	//PROCESOS
	public static final int VALIDACION = 1;
	public static final int CARGA_DE_DATOS= 2;
	public static final int FACTURACION = 3;
	
	//MAX PROCESOS PERMITIDOS
	public static final int MAX_PROCESOS_CARGA = 4;
	public static final int MAX_PROCESOS_VALIDACION = 2;
	public static final int MAX_PROCESOS_FACTURACION = 2;
	
	//ESTADO PROCESO
	public static final int ACTIVO = 1;
	public static final int INACTIVO = 0;
	
	//ESTADOS
	public static final int VALIDADO = 4;
	public static final int VALIDANDO = 2;
	public static final int NO_VALIDADO = 1;
	public static final int ERROR_EN_VALIDACION = 3;
	public static final int CARGADO = 8;
	public static final int CARGANDO = 6;
	public static final int NO_CARGADO = 5;
	public static final int REPROCESADO = 9;
	public static final int ERROR_EN_CARGA = 7;
	
	//TIPOS DE ARCHIVOS
	public static final int RESUMEN_DISPONIBILIDAD = 1;
	public static final int RESUMEN_VELOCIDAD = 2;
	public static final int DETALLE_DISPONIBILIDAD = 3;
	public static final int DETALLE_VELOCIDAD_BAJADA = 4;
	public static final int RDETALLE_VELOCIDAD_SUBIDA = 5;
	public static final int PREFACTURA = 6;
	public static final int BITACORA = 7;
	public static final int PREFACTURACION = 8;
}
