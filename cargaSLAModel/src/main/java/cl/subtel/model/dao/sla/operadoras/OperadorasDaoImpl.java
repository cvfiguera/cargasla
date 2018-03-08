package cl.subtel.model.dao.sla.operadoras;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cl.subtel.model.common.web.Operadoras;
import cl.subtel.model.dao.HibernateWeb;

@Repository
public class OperadorasDaoImpl extends HibernateWeb implements OperadorasDao{
	
	protected Logger log = Logger.getLogger(getClass().getName());
	
	@Override
	public Operadoras getOperadora(int operadoraId) {
		String hql = "from Operadoras where operadoraId = " + operadoraId;
		Operadoras res = (Operadoras) getObjectHqlUnique(new Operadoras(), hql);
		return res;
	}

	@Override
	public Operadoras getOperadoraByName(String nombre) {
		String hql = "from Operadoras where nombreTx = " + nombre;
		Operadoras res = (Operadoras) getObjectHqlUnique(new Operadoras(), hql);
		return res;
	}

	@Override
	public boolean setOperadora(Operadoras operadora) {
		try {
			addObject(operadora);
		} catch (Exception ex) {
			log.error("Error al guardar los datos de la operadora en la tabla: " + ex.getMessage(), ex.getCause());
			return false;
		}
		return true;
	}

	@Override
	public int maxid() {
		String hql = "from Operadoras order by operadoraId asc";
		List<Operadoras> list = (List<Operadoras>) (List) getObjetHqlList(new Operadoras(), hql);
		if (list.size() > 0) {
			return list.get(list.size() - 1).getOperadoraId() + 1;
		}
		return 1;
	}

	@Override
	public List<Operadoras> getOperadoras() {
		String hql = "from Operadoras order by operadoraId asc";
		List<Operadoras> list = (List<Operadoras>) (List) getObjetHqlList(new Operadoras(), hql);
		return list;
	}

}
