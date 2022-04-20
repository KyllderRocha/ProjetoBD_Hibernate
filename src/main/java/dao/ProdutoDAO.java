package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Produto;

public class ProdutoDAO {
	
	private GenericDAO<Produto> dao;
	
	public ProdutoDAO(EntityManager manager){
		dao = new GenericDAO<Produto>(manager, Produto.class);
	}

	public Produto adiciona(Produto t) {
		return dao.adiciona(t);
	}

	public void remove(Produto t) {
		dao.remove(t);
	}

	public Produto atualiza(Produto t) {
		return dao.atualiza(t);
	}

	public List<Produto> listaTodos() {
		return dao.listaTodos();
	}

	public Produto buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public EntityManager getEntityManager() {
		return this.dao.getEntityManager();
	}

}
