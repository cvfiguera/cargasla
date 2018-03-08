package cl.subtel.model.dao.control.procesos;

import java.util.List;
import org.springframework.stereotype.Repository;
import cl.subtel.model.common.control.ProcesoResumen;
import cl.subtel.model.common.control.Procesos;
import cl.subtel.model.common.control.ProcesosDetalle;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.model.common.control.TiposProceso;
import cl.subtel.model.dao.HibernateControl;

@Repository
public class ProcesosDaoImpl extends HibernateControl implements ProcesosDao{

	@Override
	public Procesos getProceso(Integer procesoId) {
		String hql = "FROM Procesos WHERE procesoId = " + procesoId;
		Procesos res = (Procesos) getObjectHqlUnique(new Procesos(), hql);
		return res;
	}

	@Override
	public void setProceso(Procesos proceso) {
		addObject(proceso);
	}
	
	@Override
	public void updateProceso(Procesos proceso) {
		updateObject(proceso);
	}

	@Override
	public TiposProceso getTipoProceso(Integer tipoProcesoId) {
		String hql = "FROM TiposProceso WHERE tipoProcesoId = " + tipoProcesoId;
		TiposProceso res = (TiposProceso) getObjectHqlUnique(new TiposProceso(), hql);
		return res;
	}

	@Override
	public void setProcesoDetalle(List<ProcesosDetalle> procesoDetalle) {
		updateObjectList((List<Object>) (List) procesoDetalle);
	}

	@Override
	public Procesos getProcesoActivo(Integer usuarioId, Integer tipoProcesoId, Integer estatus) {
		String hql = "from Procesos p where p.usuario = " + usuarioId 
				+ " and p.tiposProceso.tipoProcesoId = " + tipoProcesoId
				+ " and p.estatus = " + estatus;
		Procesos proceso = (Procesos) getObjectHqlUnique(new Procesos(), hql);
		return proceso;
	}

	@Override
	public List<ProcesosDetalle> getProcesosDetalle(Integer procesoId) {
		String hql = "from ProcesosDetalle pd where pd.procesos.procesoId = " + procesoId;
		List<ProcesosDetalle> procesos = (List<ProcesosDetalle>) (List) getObjetHqlList(new ProcesosDetalle(), hql);
		return procesos;
	}
	
	@Override
	public List<ResumenArchivo> getMaxProcesosDetalle(Integer procesoId) {
		String sql = "select pd.archivo_id as id, at.tipo, pd.nombre_archivo as nombre, ar.version, ar.fecha_ftp as fecha, es.estado "
				+ "from control.procesos_detalle pd "
				+ "inner join control.estados es on (pd.estado_archivo_id = es.estado_archivo_id) "
				+ "inner join (select procesos_detalle.proceso_id, procesos_detalle.archivo_id, procesos_detalle.nombre_archivo, max(codigo) as cod "
				+ "from control.procesos_detalle inner join control.estados on (procesos_detalle.estado_archivo_id = estados.estado_archivo_id) "
				+ "where proceso_id = " + procesoId +" group by procesos_detalle.proceso_id, procesos_detalle.archivo_id, procesos_detalle.nombre_archivo) max "
				+ "on (pd.archivo_id = max.archivo_id and pd.nombre_archivo = max.nombre_archivo and es.codigo = max.cod and pd.proceso_id = max.proceso_id) "
				+ "inner join control.archivo ar on (ar.archivo_id = pd.archivo_id and ar.nombre_archivo = pd.nombre_archivo) "
				+ "inner join control.archivos_tipos at on (ar.archivo_tipo_id = at.archivo_tipo_id)";
		
		List<ResumenArchivo> procesos = (List<ResumenArchivo>) (List) getObjectListSql(new ResumenArchivo(), sql);
		
		return procesos;
	}

	@Override
	public List<Procesos> getProcesosActivos() {
		String hql = "from Procesos pd where pd.estatus=1";
		List<Procesos> procesos = (List<Procesos>) (List) getObjetHqlList(new Procesos(), hql);
		return procesos;
	}

	@Override
	public List<ProcesoResumen> getProcesoByFecha(String fechaIni, String fechaFin) {
		String hql = "SELECT DISTINCT p.procesoId as id, p.tiposProceso.nombre as proceso, "
				+ "pd.archivo.operadora as operadora, pd.archivo.mes as mes, pd.archivo.ano as ano, "
				+ "p.usuario as usuario, p.fecha as fecha, p.estatus as estado "
				+ "FROM Procesos p INNER JOIN p.procesosDetalles pd "
				+ "WHERE p.fecha >= '" + fechaIni + "' AND p.fecha<= '" + fechaFin + "' "
				+ "ORDER BY estado, fecha desc";
		return (List<ProcesoResumen>) (List) getObjetHqlListTransform(new ProcesoResumen(), hql);
	}

	@Override
	public List<ProcesoResumen> getUltimosProcesos(Integer cant) {
		String hql = "select distinct p.proceso_id as id, tp.nombre as proceso, a.operadora_id as operadora, a.mes, a.ano, "
				+ "p.usuario_id as usuario, p.fecha, p.estatus as estado from control.procesos p inner join control.procesos_detalle pd on (p.proceso_id = pd.proceso_id) "
				+ "inner join control.tipos_proceso tp on (p.tipo_proceso_id = tp.tipo_proceso_id) "
				+ "inner join control.archivo a on (pd.archivo_id = a.archivo_id) order by fecha desc limit " + cant;
		
		List<ProcesoResumen> ultimos = (List<ProcesoResumen>) (List) getObjectListSql(new ProcesoResumen(), hql);
		
		return ultimos;
	}
}
