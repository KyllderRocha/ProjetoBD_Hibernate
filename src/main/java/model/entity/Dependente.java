package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEPENDENTE")
public class Dependente implements Serializable{

	private static final long serialVersionUID = -1203024531173604962L;

	@EmbeddedId
	protected DependentePK dependentePK = new DependentePK();
		
	@Column(name = "NOME", nullable = false, length = 255)
	private String nome;	

	//@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	@ManyToOne(optional = false , fetch = FetchType.EAGER)
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return dependentePK.getRg();
	}

	public void setRg(String rg) {
		this.dependentePK.setRg(rg);
	}

	
}
