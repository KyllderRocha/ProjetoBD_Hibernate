package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;
	private EntityManager em;

	public GenericDAO(EntityManager manager, Class<T> classe) {
		this.em = manager;
		this.classe = classe;
	}

	public T adiciona(T t) {
		em.persist(t);
		return t;
	}

	public void remove(T t) {
		em.remove(em.merge(t));
	}

	public T atualiza(T t) {
		T ax = em.merge(t);
		return ax;
		
	}
	
	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		return lista;
	}

	public T buscaPorId(Long id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		return lista;
	}
	
	public EntityManager getEntityManager(){
		return this.em;
	}

	
	public void beginTransaction() {
		em.getTransaction().begin();
	}
	
	public void rollbackTransaction() {
		em.getTransaction().rollback();
	}
	
	public void flushStatements() {
		em.flush();
	}
	
	public void commitTransaction() {
		em.getTransaction().commit();
	}
	
	public void close() {
		em.close();
	}

}
