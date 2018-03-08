package cl.subtel.model.dao.control.estados;

import java.util.List;
import org.springframework.stereotype.Repository;
import cl.subtel.model.common.control.Estados;
import cl.subtel.model.common.control.HistorialArchivo;
import cl.subtel.model.dao.HibernateControl;

@Repository
public class EstadoDaoImpl extends HibernateControl implements EstadoDao{

	@Override
	public Estados getEstadoByCodigo(Integer codigo) {
		String hql = "from Estados where codigo = '" + codigo + "'";
		return (Estados) getObjectHqlUnique(new Estados(), hql);
	}

	@Override
	public List<HistorialArchivo> getHistorialArchivo(Integer archivoId) {
		String sql = "SELECT a.id, archivo.nombre_archivo as nombre, archivo.fecha_ftp as fecha, estados.estado, a.usuario "
				+ "FROM control.archivo inner join (SELECT pd.archivo_id as id, pd.nombre_archivo as nombre, p.tipo_proceso_id as proceso, p.usuario_id as usuario, "
				+ "max(es.codigo) as estado FROM control.procesos_detalle pd, control.procesos p, control.estados es "
				+ "WHERE archivo_id = " + archivoId + " AND pd.proceso_id = p.proceso_id AND pd.estado_archivo_id = es.estado_archivo_id "
				+ "GROUP BY id, pd.nombre_archivo, p.usuario_id, p.tipo_proceso_id) a "
				+ "ON (archivo.archivo_id = a.id and archivo.nombre_archivo = a.nombre) inner join control.estados ON (estados.codigo = a.estado) "
				+ "ORDER BY id, fecha, codigo";
		return (List<HistorialArchivo>) getObjectListSql(new HistorialArchivo(), sql); 
	}

}
