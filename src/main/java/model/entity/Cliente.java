package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
@Table(name = "CLIENTE")
public abstract class Cliente implements Serializable {
	
	private static final long serialVersionUID = 3387642521978418140L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 255)
	private String nome;
	
	@Embedded
	private Endereco enderecoCobranca;
	
	@ElementCollection
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidoCollection = new ArrayList<Pedido>();
	
	
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	private List<Dependente> depedenteCollection = new ArrayList<Dependente>();
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Carteira carteira;
	
	@ElementCollection
	@CollectionTable(name = "EMAIL_CLIENTE",
			joinColumns = { @JoinColumn(name = "ID_CLIENTE")})
	@Column(name = "EMAIL")
	private List<String> emails = new ArrayList<String>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public List<Pedido> getPedidoCollection() {
		return pedidoCollection;
	}

	public void setPedidoCollection(List<Pedido> pedidoCollection) {
		this.pedidoCollection = pedidoCollection;
	}

	public List<Dependente> getDepedenteCollection() {
		
		return depedenteCollection;
	}

	public void setDepedenteCollection(List<Dependente> depedenteCollection) {
		this.depedenteCollection = depedenteCollection;
	}
	
	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "]";
	}

}
