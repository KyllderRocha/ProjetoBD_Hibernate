package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Carteira;

public class CarteiraDAO {
	
	private GenericDAO<Carteira> dao;
	
	public CarteiraDAO(EntityManager manager){
		dao = new GenericDAO<Carteira>(manager, Carteira.class);
	}

	public Carteira adiciona(Carteira t) {
		return dao.adiciona(t);
	}

	public void remove(Carteira t) {
		dao.remove(t);
	}

	public Carteira atualiza(Carteira t) {
		return dao.atualiza(t);
	}

	public List<Carteira> listaTodos() {
		return dao.listaTodos();
	}

	public Carteira buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public EntityManager getEntityManager() {
		return this.dao.getEntityManager();
	}

}
