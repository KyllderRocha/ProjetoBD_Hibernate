package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.EMFactory;
import dao.ProdutoDAO;
import model.entity.Produto;

public class ProdutoService implements IServices<Produto> {
	
	private ProdutoDAO produtoDao;
	private EntityManager entityManager;
	
	public ProdutoService() {
		this.produtoDao = new ProdutoDAO(entityManager);
	}
	
	@Override
	public Produto adicionar(Produto produto) {
		
		entityManager = EMFactory.getInstance().getEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			produtoDao.adiciona(produto);
			entityManager.getTransaction().commit();
			
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			System.out.println("Erro na requisição:");
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	@Override
	public Produto obterPorId(Long id) {
		return produtoDao.buscaPorId(id);
	}

	@Override
	public List<Produto> obterTudo() {
		return produtoDao.listaTodos();
	}

	@Override
	public void deletarPorId(Produto produto) {
		produtoDao.remove(produto);
	}

	@Override
	public Produto atualizar(Produto produto) {
		return produtoDao.atualiza(produto);
	}

}