package builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.mapping.Set;

import model.entity.Cliente;
import model.entity.ClienteFisico;
import model.entity.Dependente;
import model.entity.Endereco;

public class ClienteFisicoBuilder {
	
	private String nome;
	private String enderecoCep;
	private String enderecoRua;
	private String enderecoCidade;
	private String rg;
	private String cpf;
	private List<Dependente> dependente = new ArrayList<Dependente>();
	
	private ClienteFisicoBuilder() {}
	
	public static ClienteFisicoBuilder umCliente() {
		return new ClienteFisicoBuilder();
	}
	
	public ClienteFisicoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public ClienteFisicoBuilder comRg(String rg) {
		this.rg = rg;
		return this;
	}
	
	public ClienteFisicoBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	public ClienteFisicoBuilder comEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
		return this;
	}
	
	public ClienteFisicoBuilder comEnderecoRua(String enderecoRua) {
		this.enderecoRua = enderecoRua;
		return this;
	}
	
	public ClienteFisicoBuilder comEnderecoCidade(String enderecoCidade) {
		this.enderecoCidade = enderecoCidade;
		return this;
	}
	
	public ClienteFisicoBuilder comDependente(Dependente dependente) {
		this.dependente.add(dependente);
		return this;
	}
	
	
	public ClienteFisico build() {
		ClienteFisico cliente = new ClienteFisico();
		cliente.setNome(nome);
		List<Dependente> dependenteBanco = cliente.getDepedenteCollection();
		dependenteBanco.addAll(dependente);
		cliente.setDepedenteCollection(dependenteBanco);
		Endereco endereco = new Endereco();
		endereco.setRua(enderecoRua);
		endereco.setCep(enderecoCep);
		endereco.setCidade(enderecoCidade);
		cliente.setEnderecoCobranca(endereco);
		cliente.setRg(rg);
		cliente.setCpf(cpf);
		
		return cliente;
	}

}
