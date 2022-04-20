package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.CarteiraDAO;
import dao.EMFactory;
import model.entity.Carteira;

public class CarteiraService implements IServices<Carteira> {
	
	private CarteiraDAO carteiraDao;
	private EntityManager entityManager;
	
	public CarteiraService() {
		this.carteiraDao = new CarteiraDAO(entityManager);
	}
	
	@Override
	public Carteira adicionar(Carteira carteira) {
		
		entityManager = EMFactory.getInstance().getEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			carteiraDao.adiciona(carteira);
			entityManager.getTransaction().commit();
			
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			System.out.println("Erro na requisição:");
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	@Override
	public Carteira obterPorId(Long id) {
		return carteiraDao.buscaPorId(id);
	}

	@Override
	public List<Carteira> obterTudo() {
		return carteiraDao.listaTodos();
	}

	@Override
	public void deletarPorId(Carteira carteira) {
		carteiraDao.remove(carteira);
	}

	@Override
	public Carteira atualizar(Carteira carteira) {
		return carteiraDao.atualiza(carteira);
	}

}