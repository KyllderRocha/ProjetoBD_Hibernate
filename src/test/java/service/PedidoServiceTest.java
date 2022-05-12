package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.ClienteFisicoBuilder;
import builder.PedidoBuilder;
import dao.TestEMFactory;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Fornecedor;
import model.entity.ItemPedido;
import model.entity.Pedido;
import model.entity.Produto;

public class PedidoServiceTest {

	EntityManager manager;
	ClienteService clienteService;
	PedidoService service;
	ProdutoService produtoService;
	
	@BeforeEach
	public void before() {
		manager = TestEMFactory.getInstance().getEntityManager();
		clienteService = new ClienteService(manager);
		service = new PedidoService(manager);
		produtoService = new ProdutoService(manager);
		manager.getTransaction().begin();
	}
	
	@AfterEach
	public void after() {
		
	}
	
	@Test
	public void deveCriarPedido() {
		
		Endereco endereco = new Endereco();
		endereco.setCep("55385000");
		endereco.setCidade("Garanhuns");
		endereco.setRua("Rua André Aluizio dornelas");
		
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
		clienteService.adicionar(novoCliente);
		

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(520.0);
		novoProduto.setDescricao("Descrição");
		novoProduto.setFornecedorCollection(fornecedores);
		produtoService.adicionar(novoProduto);

		Pedido novoPedido = PedidoBuilder
				.umPedido()
				.comEnderecoData(endereco, new Date(), novoCliente)
				.build();
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPedido(novoPedido);
		itemPedido.setProduto(novoProduto);
		
		ArrayList<ItemPedido> itens = new ArrayList<ItemPedido>();
		
		novoPedido.setItemPedidoCollection(itens);
		service.adicionar(novoPedido);
	    
		Pedido pedidoBD = service.obterPorId(novoPedido.getId());
	    assertNotNull(pedidoBD);
	    assertEquals(pedidoBD.getValor(), novoPedido.getValor());
	}
	/*
	@Test
	public void deveEncontrarPeloId() {
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
	    service.adicionar(novoCliente);
		
	    Cliente clienteDoBanco = service.obterPorId(novoCliente.getId());
	    assertNotNull(clienteDoBanco);
	    assertEquals(novoCliente.getNome(), clienteDoBanco.getNome());
	}
	
	@Test
	public void naoDeveEncontrarPeloId() {
		Cliente clienteDoBanco = service.obterPorId(-1l);
	    assertNull(clienteDoBanco);
	}
	
	@Test
	public void deveExcluirUmCliente() {
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
	    
		service.adicionar(novoCliente);
		
		Long idCliente = novoCliente.getId();
		
	    service.deletarPorId(novoCliente);
	    
	    Cliente clienteDoBanco = service.obterPorId(idCliente);
	    assertNull(clienteDoBanco);
	}
	
	@Test
	public void deveAtualizarUmCliente() {
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
		service.adicionar(novoCliente);
		
		Long idCliente = novoCliente.getId();
		
		novoCliente.setNome("João Ferreira da Silva");
	    service.atualizar(novoCliente);
	    
	    Cliente clienteDoBanco = service.obterPorId(idCliente);
	    assertNotNull(clienteDoBanco);
	    assertEquals("João Ferreira da Silva", clienteDoBanco.getNome());
	}*/
}