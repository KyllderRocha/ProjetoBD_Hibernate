package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.DependenteDAO;
import dao.EMFactory;
import model.entity.Dependente;

public class DependenteService implements IServices<Dependente> {
	
	private DependenteDAO dependenteDao;
	private EntityManager entityManager;
	
	public DependenteService() {
		this.dependenteDao = new DependenteDAO(entityManager);
	}
	
	@Override
	public Dependente adicionar(Dependente dependente) {
		
		entityManager = EMFactory.getInstance().getEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			dependenteDao.adiciona(dependente);
			entityManager.getTransaction().commit();
			
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			System.out.println("Erro na requisição:");
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	@Override
	public Dependente obterPorId(Long id) {
		return dependenteDao.buscaPorId(id);
	}

	@Override
	public List<Dependente> obterTudo() {
		return dependenteDao.listaTodos();
	}

	@Override
	public void deletarPorId(Dependente dependente) {
		dependenteDao.remove(dependente);
	}

	@Override
	public Dependente atualizar(Dependente dependente) {
		return dependenteDao.atualiza(dependente);
	}

}