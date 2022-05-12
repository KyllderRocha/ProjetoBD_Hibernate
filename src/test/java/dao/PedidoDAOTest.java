package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.ClienteFisicoBuilder;
import builder.PedidoBuilder;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Fornecedor;
import model.entity.ItemPedido;
import model.entity.Pedido;
import model.entity.Produto;
public class PedidoDAOTest {
	
	EntityManager manager;
	PedidoDAO daoPedido;
	ClienteDAO daoCliente;
	ProdutoDAO daoProduto;
	CarteiraDAO daoCarteira;
	
	@BeforeEach
	public void before() {
		manager = TestEMFactory.getInstance().getEntityManager();
		daoPedido = new PedidoDAO(manager);
		daoCliente = new ClienteDAO(manager);
		daoProduto = new ProdutoDAO(manager);
		daoCarteira = new CarteiraDAO(manager);
		manager.getTransaction().begin();
	}
	
	@AfterEach
	public void after() {
		manager.getTransaction().rollback();
		manager.close();
	}
	
	@Test
	public void deveSalvarPedidoComEnderecoData() {
		Endereco endereco = new Endereco();
		endereco.setCep("55385000");
		endereco.setCidade("Garanhuns");
		endereco.setRua("Rua André Aluizio dornelas");
		
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Lajedo")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
		daoCliente.adiciona(novoCliente);

		Pedido novoPedido = PedidoBuilder
				.umPedido()
				.comEnderecoData(endereco, new Date(), novoCliente)
				.build();
		daoPedido.adiciona(novoPedido);
		
		assertNotNull(novoPedido.getId());
	}
	
	@Test
	public void deveSalvarPedido() {
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
		daoCliente.adiciona(novoCliente);
		

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(520.0);
		novoProduto.setDescricao("Descrição");
		novoProduto.setFornecedorCollection(fornecedores);
		daoProduto.adiciona(novoProduto);

		Pedido novoPedido = PedidoBuilder
				.umPedido()
				.comEnderecoData(endereco, new Date(), novoCliente)
				.build();
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPedido(novoPedido);
		itemPedido.setProduto(novoProduto);
		
		ArrayList<ItemPedido> itens = new ArrayList<ItemPedido>();
		
		novoPedido.setItemPedidoCollection(itens);
		daoPedido.adiciona(novoPedido);
		
		assertNotNull(novoPedido.getId());
	}
	
	@Test
	public void deveEncontrarPeloId() {
		Endereco endereco = new Endereco();
		endereco.setCep("55385000");
		endereco.setCidade("Garanhuns");
		endereco.setRua("Rua André Aluizio dornelas");
		
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Lajedo")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
		daoCliente.adiciona(novoCliente);
		

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(320.0);
		novoProduto.setDescricao("Descrição");
		novoProduto.setFornecedorCollection(fornecedores);
		daoProduto.adiciona(novoProduto);

		Pedido novoPedido = PedidoBuilder
				.umPedido()
				.comEnderecoData(endereco, new Date(), novoCliente)
				.build();
		daoPedido.adiciona(novoPedido);
	    

		Pedido pedidoDoBanco = daoPedido.buscaPorId(novoPedido.getId());
		assertNotNull(pedidoDoBanco);
		assertEquals(novoPedido.getId(), pedidoDoBanco.getId());
	}
	
	@Test
	public void naoDeveEncontrarPeloId() {
		Pedido pedidoDoBanco = daoPedido.buscaPorId(-1l);
		assertNull(pedidoDoBanco);
	}
	
	@Test
	public void deveDeletarUmPedido() {
		Endereco endereco = new Endereco();
		endereco.setCep("55385000");
		endereco.setCidade("Garanhuns");
		endereco.setRua("Rua André Aluizio dornelas");
		
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Lajedo")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
		daoCliente.adiciona(novoCliente);
		

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(50.0);
		novoProduto.setDescricao("Descrição");
		novoProduto.setFornecedorCollection(fornecedores);
		daoProduto.adiciona(novoProduto);

		Pedido novoPedido = PedidoBuilder
				.umPedido()
				.comEnderecoData(endereco, new Date(), novoCliente)
				.build();
		daoPedido.adiciona(novoPedido);

		Long pedidoId = novoPedido.getId();

		daoPedido.remove(novoPedido);

		Pedido pedidoDoBanco = daoPedido.buscaPorId(pedidoId);
		assertNull(pedidoDoBanco);
	}
	
	@Test
	public void deveAtualizarUmPedido() {
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
		daoCliente.adiciona(novoCliente);
		

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Lorem Ipsum");
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);

		Produto novoProduto = new Produto();
		novoProduto.setNome("Dolor Sit");
		novoProduto.setValor(123.0);
		novoProduto.setDescricao("Descrição");
		novoProduto.setFornecedorCollection(fornecedores);
		daoProduto.adiciona(novoProduto);

		Pedido novoPedido = PedidoBuilder
				.umPedido()
				.comEnderecoData(endereco, new Date(), novoCliente)
				.build();
		daoPedido.adiciona(novoPedido);


		Long pedidoId = novoPedido.getId();

		novoPedido.setValor(2422.0);
		daoPedido.atualiza(novoPedido);
		
		Pedido clienteDoBanco = daoPedido.buscaPorId(pedidoId);
		assertNotNull(clienteDoBanco);
		assertEquals(2422, clienteDoBanco.getValor());
	}
}