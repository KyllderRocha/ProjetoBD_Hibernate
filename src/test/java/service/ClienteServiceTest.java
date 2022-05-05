package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.ClienteFisicoBuilder;
import dao.TestEMFactory;
import model.entity.Cliente;
import model.entity.ClienteFisico;
import model.entity.Dependente;
import model.entity.Endereco;

public class ClienteServiceTest {

	EntityManager manager;
	Cliente cliente;
	ClienteService service;
	
	@BeforeEach
	public void before() {
		manager = TestEMFactory.getInstance().getEntityManager();
		service = new ClienteService();
	}
	
	@AfterEach
	public void after() {
		
	}
	
	@Test
	public void deveCriarCliente() {
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
	}
	
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
		//Cliente novoCliente = ClienteFisicoBuilder
		//		.umCliente()
		//		.comNome("João da Silva")
		//		.comCpf("12096374480")
		//		.comEnderecoCep("55385000")
		//		.comEnderecoCidade("Garanhuns")
		//		.comEnderecoRua("Rua Andre Aluizio dornelas")
		//		.comRg("10252423")
		//		.build();
		
		ClienteFisico novoCliente = new ClienteFisico();
		novoCliente.setNome("João da Silva");
		Endereco endereco = new Endereco();
		endereco.setRua("Rua Andre Aluizio dornelas");
		endereco.setCep("55385000");
		endereco.setCidade("Garanhuns");
		novoCliente.setEnderecoCobranca(endereco);
		novoCliente.setRg("10252423");
		novoCliente.setCpf("12096374480");
		
		service.adicionar(novoCliente);
		
		Long idCliente = novoCliente.getId();
		
		novoCliente.setNome("João Ferreira da Silva");
	    service.atualizar(novoCliente);
	    
	    Cliente clienteDoBanco = service.obterPorId(idCliente);
	    assertNotNull(clienteDoBanco);
	    assertEquals("Jo�o Ferreira da Silva", clienteDoBanco.getNome());
	}
}