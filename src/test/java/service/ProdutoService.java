package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.ProdutoDAO;
import model.entity.Produto;

public class ProdutoService implements IService<Produto> {
	
	private ProdutoDAO dao;
	
	public ProdutoService(EntityManager em) {
		this.dao = new ProdutoDAO(em);
	}
	
	@Override
	public Produto adicionar(Produto produto) {
		return dao.adiciona(produto);
	}

	@Override
	public Produto obterPorId(Long id) {
		return dao.buscaPorId(id);
	}

	@Override
	public List<Produto> obterTudo() {
		return dao.listaTodos();
	}

	@Override
	public void deletarPorId(Produto produto) {
		dao.remove(produto);
	}

	@Override
	public Produto atualizar(Produto produto) {
		return dao.atualiza(produto);
	}

}
