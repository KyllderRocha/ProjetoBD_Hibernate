package model.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FORNECEDOR")
public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 500133879181970052L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FORNECEDOR")
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 255)
	private String nome;
	
	@ElementCollection
	@ManyToMany(mappedBy = "fornecedorCollection")
	private Collection<Produto> produtoCollection;

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

	public Collection<Produto> getProdutoCollection() {
		return produtoCollection;
	}

	public void setProdutoCollection(Collection<Produto> produtoCollection) {
		this.produtoCollection = produtoCollection;
	}

	
	
	
}
