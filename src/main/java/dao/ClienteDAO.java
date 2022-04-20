package dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Cliente;

public class ClienteDAO {
	
	private GenericDAO<Cliente> dao;
	
	public ClienteDAO(EntityManager manager){
		dao = new GenericDAO<Cliente>(manager, Cliente.class);
	}

	public Cliente adiciona(Cliente t) {
		return dao.adiciona(t);
	}

	public void remove(Cliente t) {
		dao.remove(t);
	}

	public Cliente atualiza(Cliente t) {
		return dao.atualiza(t);
	}

	public List<Cliente> listaTodos() {
		return dao.listaTodos();
	}

	public Cliente buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public EntityManager getEntityManager() {
		return this.dao.getEntityManager();
	}

}
