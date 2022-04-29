package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.Fornecedor;
import model.entity.Pedido;
import model.entity.Produto;
public class ProdutoDAOTest {
	
	EntityManager manager;
	ProdutoDAO dao;
	
	@BeforeEach
	public void before() {
		manager = TestEMFactory.getInstance().getEntityManager();
		dao = new ProdutoDAO(manager);
		manager.getTransaction().begin();
	}
	
	@AfterEach
	public void after() {
		manager.getTransaction().rollback();
		manager.close();
	}
	
	@Test
	public void deveSalvarProduto() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(520.0);
		novoProduto.setFornecedorCollection(fornecedores);
		dao.adiciona(novoProduto);
		

		assertNotNull(novoProduto.getId());
	}

	@Test
	public void deveEncontrarPeloId() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(520.0);
		novoProduto.setFornecedorCollection(fornecedores);
		dao.adiciona(novoProduto);
		
		Produto produtoBanco = dao.buscaPorId(novoProduto.getId());
		
		assertNotNull(produtoBanco);
		assertEquals(novoProduto.getNome(), produtoBanco.getNome());
	}
	
	@Test
	public void naoDeveEncontrarPeloId() {
		Produto produtoDoBanco = dao.buscaPorId(-1L);
		assertNull(produtoDoBanco);
	}
	
	@Test
	public void deveDeletarUmCliente() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(520.0);
		novoProduto.setFornecedorCollection(fornecedores);
		dao.adiciona(novoProduto);

		Long produtoId = novoProduto.getId();

		dao.remove(novoProduto);

		manager.flush();

		Produto produtoDoBanco = dao.buscaPorId(produtoId);
		assertNull(produtoDoBanco);
	}
	
	@Test
	public void deveAtualizarUmCliente() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(520.0);
		novoProduto.setFornecedorCollection(fornecedores);
		dao.adiciona(novoProduto);

		Long produtoId = novoProduto.getId();

		novoProduto.setValor(333.0);
		dao.atualiza(novoProduto);

		manager.flush();

		Produto produtoDoBanco = dao.buscaPorId(produtoId);
		assertNotNull(produtoDoBanco);
		assertEquals(333, produtoDoBanco.getValor());
	}
}