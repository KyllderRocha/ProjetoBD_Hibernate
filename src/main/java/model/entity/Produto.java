package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable{

	private static final long serialVersionUID = -1461072103099964794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUTO")
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 255)
	private String nome;

	@Column(name = "DESCRICAO", nullable = false, length = 255)
	private String descricao;

	@Column(name = "VALOR", nullable = false)
	private Double valor;
	
	@JoinTable(name = "fornecedor_produto",
			joinColumns = {@JoinColumn(
					name = "PRODUTO_ID_PROTUDO",
					referencedColumnName = "ID_PRODUTO")},
			inverseJoinColumns = {@JoinColumn(
					name = "PRODUTO_ID_FORNECEDOR",
					referencedColumnName = "ID_FORNECEDOR")})
	@ManyToMany
	@ElementCollection
	private List<Fornecedor> fornecedorCollection = new ArrayList<Fornecedor>();
	
	@OneToMany(mappedBy = "produto")
	@ElementCollection
	private List<ItemPedido> itemPedidoCollection = new ArrayList<ItemPedido>();

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<Fornecedor> getFornecedorCollection() {
		return fornecedorCollection;
	}

	public void setFornecedorCollection(List<Fornecedor> fornecedorCollection) {
		this.fornecedorCollection = fornecedorCollection;
	}
	
	
	
}
