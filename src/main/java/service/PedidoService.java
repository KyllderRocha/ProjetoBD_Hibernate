package service;

import java.util.List;

import javax.persistence.EntityManager;

import dao.CarteiraDAO;
import dao.PedidoDAO;
import dao.EMFactory;
import model.entity.Pedido;
import model.entity.Carteira;

public class PedidoService implements IServices<Pedido> {
	
	private CarteiraDAO carteiraDao;
	private PedidoDAO pedidoDao;
	private EntityManager entityManager;
	
	public PedidoService() {
		this.carteiraDao = new CarteiraDAO(entityManager);
		this.pedidoDao = new PedidoDAO(entityManager);
	}
	
	@Override
	public Pedido adicionar(Pedido pedido) {
		
		entityManager = EMFactory.getInstance().getEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			pedidoDao.adiciona(pedido);
			Carteira carteira = pedido.getCliente().getCarteira();
			carteira = adicionarPontos(pedido);
			carteiraDao.atualiza(carteira);
			
			if(verificarSaldoCliente(pedido)) {
				entityManager.getTransaction().commit();
				return pedido;
			}

			entityManager.getTransaction().rollback();
			System.out.println("Você não tem saldo suficiente na carteira");
			
		}catch(Exception ex) {
			entityManager.getTransaction().rollback();
			System.out.println("Erro na requisição:");
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}
	
	private Carteira adicionarPontos(Pedido pedido) {
		entityManager = EMFactory.getInstance().getEntityManager();
		
		int pontos = (int) pedido.getValor() % 10;
		Carteira carteira = pedido.getCliente().getCarteira();
		
		System.out.println("Pontos Ganhos: " + pontos);
		System.out.println("Pontos anteriores na Carteira: " + carteira.getPontos());
		
		carteira.setPontos(pontos);
		
		System.out.println("Pontos Atuais na Carteira: " + carteira.getPontos());
		
		return carteira;
	}
	
	private boolean verificarSaldoCliente(Pedido pedido) {
		entityManager = EMFactory.getInstance().getEntityManager();
		
		Carteira carteira = pedido.getCliente().getCarteira();
		
		System.out.println("Valor do Pedido: " + pedido.getValor());
		System.out.println("Saldo da Carteira: " + carteira.getSaldo());
		
		return carteira.getSaldo() >= pedido.getValor();
	}

	@Override
	public Pedido obterPorId(Long id) {
		return pedidoDao.buscaPorId(id);
	}

	@Override
	public List<Pedido> obterTudo() {
		return pedidoDao.listaTodos();
	}

	@Override
	public void deletarPorId(Pedido pedido) {
		pedidoDao.remove(pedido);
	}

	@Override
	public Pedido atualizar(Pedido pedido) {
		return pedidoDao.atualiza(pedido);
	}

}