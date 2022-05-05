package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1707873275528007093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PEDIDO")
	private Long id;

	@Column(name = "SALDO", nullable = false)
	private double valor;

	@Column(name = "DATA", nullable = false)
	private Date data;

	@Embedded
	private Endereco enderecoCobranca;
	
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	@ManyToOne(optional = false , fetch = FetchType.EAGER)
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedidoCollection = new ArrayList<ItemPedido>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItemPedidoCollection() {
		return itemPedidoCollection;
	}

	public void setItemPedidoCollection(List<ItemPedido> itemPedidoCollection) {
		this.itemPedidoCollection = itemPedidoCollection;
	}
	
	
}
