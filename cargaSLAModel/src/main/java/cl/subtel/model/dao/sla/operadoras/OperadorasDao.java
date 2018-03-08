package cl.subtel.model.dao.sla.operadoras;

import java.util.List;

import cl.subtel.model.common.web.Operadoras;

public interface OperadorasDao {

	public List<Operadoras> getOperadoras();
	
	public Operadoras getOperadora(int operadoraId);

	public Operadoras getOperadoraByName(String nombre);

	public boolean setOperadora(Operadoras operadora);

	public int maxid();
}
