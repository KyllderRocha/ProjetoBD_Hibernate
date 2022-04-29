package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.ClienteFisicoBuilder;
import dao.ClienteDAO;
import dao.TestEMFactory;
import model.entity.Cliente;

public class ClienteServiceTest {

	EntityManager manager;
	Cliente cliente;
	ClienteService service;
	
	@BeforeEach
	public void before() {
		manager = TestEMFactory.getInstance().getEntityManager();
		service = new ClienteService();
		manager.getTransaction().begin();
	}
	
	@AfterEach
	public void after() {
		manager.getTransaction().rollback();
		manager.close();
	}
	
	@Test
	public void deveCriarCliente() {
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Lajedo")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
		service.adicionar(novoCliente);
		assertNotNull(novoCliente.getId());
	}
	
	@Test
	public void deveEncontrarPeloId() {
		Cliente novoCliente = ClienteFisicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comCpf("12096374480")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Lajedo")
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
				.comEnderecoCidade("Lajedo")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
	    
		service.adicionar(novoCliente);
		
		Long idCliente = novoCliente.getId();
		
	    service.deletarPorId(novoCliente);
	    
	    manager.flush();
	    
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
				.comEnderecoCidade("Lajedo")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comRg("10252423")
				.build();
		service.adicionar(novoCliente);
		
		Long idCliente = novoCliente.getId();
		
		novoCliente.setNome("Jo�o Ferreira da Silva");
	    service.atualizar(novoCliente);
	    
	    manager.flush();
	    
	    Cliente clienteDoBanco = service.obterPorId(idCliente);
	    assertNotNull(clienteDoBanco);
	    assertEquals("Jo�o Ferreira da Silva", clienteDoBanco.getNome());
	}
}