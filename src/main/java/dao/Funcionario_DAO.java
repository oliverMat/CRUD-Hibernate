package dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.teste.util.jpa.Transactional;
import model.Funcionario;

public class Funcionario_DAO {

	@Inject
	private EntityManager manager;

	@Transactional
	public Funcionario inserir(Funcionario f) throws Exception {
		return manager.merge(f);

	}

	@Transactional
	public void excluir(Long id) throws Exception {
		Funcionario f = manager.find(Funcionario.class, id);
		manager.remove(f);
		manager.flush();
	}

	public List<Funcionario> buscarTodos() throws Exception {
		return manager.createQuery("from Funcionario", Funcionario.class).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarFintrados(Funcionario filtro) {
		Session session = manager.unwrap(Session.class);
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Funcionario.class);
		criteria.add(Restrictions.eq("nome", filtro.getNome()));

		return criteria.addOrder(Order.asc("nome")).list();

	}

}