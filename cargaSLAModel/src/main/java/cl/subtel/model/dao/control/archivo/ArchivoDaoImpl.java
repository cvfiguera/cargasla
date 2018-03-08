package cl.subtel.model.dao.control.archivo;

import java.util.List;
import org.springframework.stereotype.Repository;
import cl.subtel.model.common.control.Archivo;
import cl.subtel.model.common.control.ArchivoId;
import cl.subtel.model.common.control.ArchivoVersion;
import cl.subtel.model.common.control.ResumenArchivo;
import cl.subtel.model.common.control.ResumenArchivosOpe;
import cl.subtel.model.dao.HibernateControl;
import cl.subtel.model.dao.control.archivo.ArchivoDao;

@Repository
public class ArchivoDaoImpl extends HibernateControl implements ArchivoDao{

	@Override
	public List<Archivo> getArchivosById(Integer archivoId) {
		String hql = "from Archivo where id.archivoId = " + archivoId + " order by version desc";
		
		return (List<Archivo>) (List) this.getObjetHqlList(new Archivo(), hql);
	}
	
	@Override
	public List<ArchivoVersion> getArchivosValidosById(Integer archivoId) {
		String sql = "select archivo.archivo_id as id, archivos_tipos.archivo_tipo_id as tipoid, archivos_tipos.tipo, archivo.nombre_archivo as nombre, archivo.version, archivo.operadora_id as operadora,"
				+ "		archivo.fecha_ftp as fecha, archivo.ruta from control.archivo "
				+ "inner join (select archivo_id, nombre_archivo, max(codigo) as codigo from control.procesos_detalle pd "
				+ "inner join control.estados e on (pd.estado_archivo_id = e.estado_archivo_id) "
				+ "where pd.archivo_id = " + archivoId + " and e.codigo>=4 group by archivo_id, nombre_archivo) a "
				+ "on (archivo.archivo_id = a.archivo_id and archivo.nombre_archivo = a.nombre_archivo) "
				+ "inner join control.archivos_tipos on (archivo.archivo_tipo_id = archivos_tipos.archivo_tipo_id) "
				+ "order by version desc";
		
		return (List<ArchivoVersion>) (List) this.getObjectListSql(new ArchivoVersion(), sql);
	}
	
	@Override
	public Archivo getArchivoByArchivoId(Integer id, String nombre) {
		String hql = "from Archivo where id.nombreArchivo = :nombre";
		return (Archivo) this.getObjectHqlUniqueWithParam(new Archivo(), hql, nombre);

	}
	
	@Override
	public void setArchivo(Archivo archivo) {
		addObject(archivo);
	}
	
	@Override
	public List<Archivo> getArchivosByListParameter(List<String> nombres) {
		String hql = "FROM Archivo WHERE id.nombreArchivo IN (:nombres)";
		return (List<Archivo>) (List) getObjectListHqlWithParam(new Archivo(), hql, "nombres", nombres);
	}

	@Override
	public List<ResumenArchivo> getArchivosValidados(Integer operadoraId, String mes, String ano) {
		
		String sql = "SELECT b.id, archivos_tipos.tipo, archivo.nombre_archivo as nombre, b.version, archivo.fecha_ftp as fecha, estados.estado  from "
				+ "(SELECT a.id, max(a.estado) as estado, max(archivo.version) as version "
				+ "FROM control.archivo, (SELECT procesos_detalle.archivo_id as id, procesos_detalle.nombre_archivo as nombre, max(estados.codigo) as estado "
				+ "FROM control.estados, control.procesos_detalle WHERE procesos_detalle.estado_archivo_id = estados.estado_archivo_id GROUP BY id, nombre) a "
				+ "WHERE archivo.archivo_id= a.id AND archivo.nombre_archivo= a.nombre GROUP BY id) b INNER JOIN control.archivo ON (archivo.archivo_id = b.id ANd archivo.version= b.version) "
				+ "INNER JOIN control.estados ON (estados.codigo = b.estado) INNER JOIN control.archivos_tipos ON (archivos_tipos.archivo_tipo_id = archivo.archivo_tipo_id) "
				+ "WHERE archivo.operadora_id = " + operadoraId + " AND archivo.mes = '" + mes + "' AND archivo.ano = '" + ano + "' "
				+ "ORDER BY id, codigo";
		
		return (List<ResumenArchivo>) getObjectListSql(new ResumenArchivo(), sql); 
	}
	
	@Override
	public ResumenArchivosOpe getArchivosResumenOpe(Integer operadora, Integer estado, String mes, String ano) {
		
		String sql = "select resumen.operadora, count(resumen.archivo) as archivos from (select a.operadora_id as operadora, a.nombre_archivo as archivo, max(e.codigo) as estado from control.procesos_detalle pd inner join control.procesos p on (p.proceso_id = pd.proceso_id) "
				+ "inner join control.estados e on (pd.estado_archivo_id = e.estado_archivo_id) "
				+ "inner join control.archivo a on (pd.archivo_id = a.archivo_id) "
				+ "where e.codigo > " + estado + " AND a.mes = '" + mes + "' AND a.ano = '" + ano + "' " 
				+ "GROUP BY operadora_id, a.nombre_archivo order by operadora_id asc) as resumen WHERE resumen.operadora = " + operadora + " GROUP BY resumen.operadora;";
		
		return (ResumenArchivosOpe) getObjectSqlUnique(new ResumenArchivosOpe(), sql); 
	}
	
	@Override
	public List<ResumenArchivo> getArchivosByTipo(Integer operadoraId, String mes, String ano, Integer tipo) {
		
		String sql = "SELECT b.id, archivos_tipos.tipo, archivo.nombre_archivo as nombre, b.version, archivo.fecha_ftp as fecha, estados.estado  from "
				+ "(SELECT a.id, max(a.estado) as estado, max(archivo.version) as version "
				+ "FROM control.archivo, (SELECT procesos_detalle.archivo_id as id, procesos_detalle.nombre_archivo as nombre, max(estados.codigo) as estado "
				+ "FROM control.estados, control.procesos_detalle WHERE procesos_detalle.estado_archivo_id = estados.estado_archivo_id GROUP BY id, nombre) a "
				+ "WHERE archivo.archivo_id= a.id AND archivo.nombre_archivo= a.nombre GROUP BY id) b INNER JOIN control.archivo ON (archivo.archivo_id = b.id ANd archivo.version= b.version) "
				+ "INNER JOIN control.estados ON (estados.codigo = b.estado) INNER JOIN control.archivos_tipos ON (archivos_tipos.archivo_tipo_id = archivo.archivo_tipo_id) "
				+ "WHERE archivos_tipos.archivo_tipo_id = " + tipo + " AND archivo.operadora_id = " + operadoraId + " AND archivo.mes = '" + mes + "' AND archivo.ano = '" + ano + "' "
				+ "ORDER BY id, codigo";
		
		return (List<ResumenArchivo>) getObjectListSql(new ResumenArchivo(), sql); 
	}

	@Override
	public Boolean tieneStatus(ArchivoId id, Integer status) {
		String hql = "FROM ProcesosDetalle pd "
				+ "WHERE pd.archivo.id.archivoId = '" + id.getArchivoId() + "' "
				+ "and pd.archivo.id.nombreArchivo = '" + id.getNombreArchivo() + "' "
				+ "and pd.estados.codigo = '" + status + "'";
		if (getObjectHqlUnique(new Archivo(), hql) != null) return true;
		else return false;
	}

	@Override
	public Archivo getArchivoByParameters(Integer tipo, Integer operadora, Integer version, String mes, String anio) {
		String hql = "FROM Archivo a WHERE a.operadora = " + operadora
				+ " AND a.mes = '" + mes + "' AND a.ano = '" + anio + "' "
				+ "AND a.version = " + version + " AND a.archivosTipos.archivoTipoId = " + tipo;
		
		return (Archivo) this.getObjectHqlUnique(new Archivo(), hql);
	}

}
