package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import builder.ClienteFisicoBuilder;
import builder.ClienteJuridicoBuilder;
import model.entity.Cliente;
import model.entity.Dependente;

public class ClienteJuridicoDAOTest {
	
	EntityManager manager;
	ClienteDAO dao;
	
	@BeforeEach
	public void before() {
		manager = TestEMFactory.getInstance().getEntityManager();
		dao = new ClienteDAO(manager);
		manager.getTransaction().begin();
	}
	
	@AfterEach
	public void after() {
		manager.getTransaction().rollback();
		manager.close();
	}
	

	@Test
	public void deveSalvarCliente() {
		Cliente novoCliente = ClienteJuridicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comCnpj("13212322")
				.comInscricaoEstadual("34324")
				.build();

		dao.adiciona(novoCliente);
		
		assertNotNull(novoCliente.getId());
	}
	
	@Test
	public void deveSalvarClienteComDependente() {
		Dependente dependente = new Dependente();
		dependente.setRg("123");
		dependente.setNome("Filho");
		Cliente novoCliente = ClienteJuridicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comCnpj("13212322")
				.comInscricaoEstadual("34324")
				.build();
		
		dao.adiciona(novoCliente);
		assertNotNull(novoCliente.getId());
	}
	
	
	@Test
	public void deveEncontrarPeloId() {
		Cliente novoCliente = ClienteJuridicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Lajedo")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comCnpj("13212322")
				.comInscricaoEstadual("34324")
				.build();
		
		dao.adiciona(novoCliente);

		Cliente cleinteDoBanco = dao.buscaPorId(novoCliente.getId());
		assertNotNull(cleinteDoBanco);
		assertEquals(novoCliente.getNome(), cleinteDoBanco.getNome());
	}

	@Test
	public void naoDeveEncontrarPeloId() {
		Cliente clienteDoBanco = dao.buscaPorId(-1l);
		assertNull(clienteDoBanco);
	}

	@Test
	public void deveDeletarUmCliente() {
		Cliente novoCliente = ClienteJuridicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comCnpj("13212322")
				.comInscricaoEstadual("34324")
				.build();

		dao.adiciona(novoCliente);

		Long idCliente = novoCliente.getId();

		dao.remove(novoCliente);

		manager.flush();

		Cliente clienteDoBanco = dao.buscaPorId(idCliente);
		assertNull(clienteDoBanco);
	}

	@Test
	public void deveAtualizarUmCliente() {
		Cliente novoCliente = ClienteJuridicoBuilder
				.umCliente()
				.comNome("João da Silva")
				.comEnderecoCep("55385000")
				.comEnderecoCidade("Garanhuns")
				.comEnderecoRua("Rua Andre Aluizio dornelas")
				.comCnpj("13212322")
				.comInscricaoEstadual("34324")
				.build();
		
		dao.adiciona(novoCliente);

		Long idCliente = novoCliente.getId();

		novoCliente.setNome("João Ferreira da Silva");
		dao.atualiza(novoCliente);

		manager.flush();

		Cliente clienteDoBanco = dao.buscaPorId(idCliente);
		assertNotNull(clienteDoBanco);
		assertEquals("João Ferreira da Silva", clienteDoBanco.getNome());
	}
}