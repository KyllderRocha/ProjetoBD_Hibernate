package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.ClienteDAO;
import dao.EMFactory;
import dao.TestEMFactory;
import model.entity.Cliente;

public class ClienteService implements IServices<Cliente> {
	
	private ClienteDAO clienteDao;
	private EntityManager entityManager;
	
	public ClienteService() {
		this.clienteDao = new ClienteDAO(this.entityManager);
	}
	
	@Override
	public Cliente adicionar(Cliente cliente) {
		entityManager = EMFactory.getInstance().getEntityManager();

		entityManager.getTransaction().begin();
		
		try {
			clienteDao.adiciona(cliente);
			entityManager.getTransaction().commit();
			
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			System.out.println("Erro na requisição:");
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	@Override
	public Cliente obterPorId(Long id) {
		return clienteDao.buscaPorId(id);
	}

	@Override
	public List<Cliente> obterTudo() {
		return clienteDao.listaTodos();
	}

	@Override
	public void deletarPorId(Cliente cliente) {
		clienteDao.remove(cliente);
	}

	@Override
	public Cliente atualizar(Cliente cliente) {
		return clienteDao.atualiza(cliente);
	}

}