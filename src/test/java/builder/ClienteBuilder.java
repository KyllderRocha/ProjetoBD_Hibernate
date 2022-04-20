package builder;

import model.entity.Cliente;
import model.entity.ClientePJ;

public class ClienteBuilder {
	
	private String nome;
	
	private ClienteBuilder() {}
	
	public static ClienteBuilder umCliente() {
		return new ClienteBuilder();
	}
	
	public ClienteBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Cliente build() {
		Cliente cliente = new ClientePJ();
		cliente.setNome(nome);
		return cliente;
	}

}
