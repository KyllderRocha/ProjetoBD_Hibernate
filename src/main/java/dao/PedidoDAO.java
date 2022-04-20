package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Pedido;

public class PedidoDAO {
	
	private GenericDAO<Pedido> dao;
	
	public PedidoDAO(EntityManager manager){
		dao = new GenericDAO<Pedido>(manager, Pedido.class);
	}

	public Pedido adiciona(Pedido t) {
		return dao.adiciona(t);
	}

	public void remove(Pedido t) {
		dao.remove(t);
	}

	public Pedido atualiza(Pedido t) {
		return dao.atualiza(t);
	}

	public List<Pedido> listaTodos() {
		return dao.listaTodos();
	}

	public Pedido buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public EntityManager getEntityManager() {
		return this.dao.getEntityManager();
	}

}
