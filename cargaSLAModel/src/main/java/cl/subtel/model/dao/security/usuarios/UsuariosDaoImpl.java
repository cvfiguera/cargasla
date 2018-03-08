package cl.subtel.model.dao.security.usuarios;

import org.springframework.stereotype.Repository;

import cl.subtel.model.common.security.Usuarios;
import cl.subtel.model.dao.HibernateControl;
import cl.subtel.model.dao.HibernateWeb;

@Repository
public class UsuariosDaoImpl extends HibernateControl implements UsuariosDao{

	@Override
	public Usuarios getUsuario(int usuarioId) {
	
		String hql = "FROM Usuarios WHERE usuarioId = " + usuarioId;
		Usuarios res = (Usuarios) getObjectHqlUnique(new Usuarios(), hql);
		return res;
	}
}
