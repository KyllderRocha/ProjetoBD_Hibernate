package builder;

import java.util.Collection;
import java.util.List;

import model.entity.Cliente;
import model.entity.ClientePJ;
import model.entity.Dependente;
import model.entity.Endereco;

public class ClienteJuridicoBuilder {
	
	private String cnpj;
	private String nome;
	private String enderecoCep;
	private String enderecoRua;
	private String enderecoCidade;
	private Dependente dependente;
	private String inscricaoEstadual;
	
	private ClienteJuridicoBuilder() {}
	
	public static ClienteJuridicoBuilder umCliente() {
		return new ClienteJuridicoBuilder();
	}
	
	public ClienteJuridicoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	

	public ClienteJuridicoBuilder comCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}
	
	public ClienteJuridicoBuilder comInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
		return this;
	}

	public ClienteJuridicoBuilder comEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
		return this;
	}
	
	public ClienteJuridicoBuilder comEnderecoRua(String enderecoRua) {
		this.enderecoRua = enderecoRua;
		return this;
	}
	
	public ClienteJuridicoBuilder comEnderecoCidade(String enderecoCidade) {
		this.enderecoCidade = enderecoCidade;
		return this;
	}
	
	public ClienteJuridicoBuilder comDependente(Dependente dependente) {
		this.dependente = dependente;
		return this;
	}
	
	
	public ClientePJ build() {
		ClientePJ cliente = new ClientePJ();
		cliente.setNome(nome);
		List<Dependente> dependenteBanco = cliente.getDepedenteCollection();
		dependenteBanco.add(dependente);
		cliente.setDepedenteCollection(dependenteBanco);
		Endereco endereco = new Endereco();
		endereco.setRua(enderecoRua);
		endereco.setCep(enderecoCep);
		endereco.setCidade(enderecoCidade);
		cliente.setEnderecoCobranca(endereco);
		cliente.setInsc_estadual(inscricaoEstadual);
		cliente.setCnpj(cnpj);
		
		return cliente;
	}

}
