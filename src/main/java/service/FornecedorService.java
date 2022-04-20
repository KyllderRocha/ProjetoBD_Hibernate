package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.FornecedorDAO;
import dao.EMFactory;
import model.entity.Fornecedor;

public class FornecedorService implements IServices<Fornecedor> {
	
	private FornecedorDAO clienteDao;
	private EntityManager entityManager;
	
	public FornecedorService() {
		this.clienteDao = new FornecedorDAO(entityManager);
	}
	
	@Override
	public Fornecedor adicionar(Fornecedor fornecedor) {
		
		entityManager = EMFactory.getInstance().getEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			clienteDao.adiciona(fornecedor);
			entityManager.getTransaction().commit();
			
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			System.out.println("Erro na requisição:");
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	@Override
	public Fornecedor obterPorId(Long id) {
		return clienteDao.buscaPorId(id);
	}

	@Override
	public List<Fornecedor> obterTudo() {
		return clienteDao.listaTodos();
	}

	@Override
	public void deletarPorId(Fornecedor fornecedor) {
		clienteDao.remove(fornecedor);
	}

	@Override
	public Fornecedor atualizar(Fornecedor fornecedor) {
		return clienteDao.atualiza(fornecedor);
	}

}