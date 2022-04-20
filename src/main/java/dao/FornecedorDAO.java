package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Fornecedor;

public class FornecedorDAO {
	
	private GenericDAO<Fornecedor> dao;
	
	public FornecedorDAO(EntityManager manager){
		dao = new GenericDAO<Fornecedor>(manager, Fornecedor.class);
	}

	public Fornecedor adiciona(Fornecedor t) {
		return dao.adiciona(t);
	}

	public void remove(Fornecedor t) {
		dao.remove(t);
	}

	public Fornecedor atualiza(Fornecedor t) {
		return dao.atualiza(t);
	}

	public List<Fornecedor> listaTodos() {
		return dao.listaTodos();
	}

	public Fornecedor buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public EntityManager getEntityManager() {
		return this.dao.getEntityManager();
	}

}
