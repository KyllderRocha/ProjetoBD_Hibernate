package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.PedidoDAO;
import model.entity.Pedido;

public class PedidoService implements IService<Pedido> {
	
	private PedidoDAO dao;
	
	public PedidoService(EntityManager em) {
		this.dao = new PedidoDAO(em);
	}
	
	@Override
	public Pedido adicionar(Pedido pedido) {
		return dao.adiciona(pedido);
	}

	@Override
	public Pedido obterPorId(Long id) {
		return dao.buscaPorId(id);
	}

	@Override
	public List<Pedido> obterTudo() {
		return dao.listaTodos();
	}

	@Override
	public void deletarPorId(Pedido pedido) {
		dao.remove(pedido);
	}

	@Override
	public Pedido atualizar(Pedido pedido) {
		return dao.atualiza(pedido);
	}

}
