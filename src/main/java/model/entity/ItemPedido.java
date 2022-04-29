package model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO_PRODUTO")
public class ItemPedido {
		
	@EmbeddedId
	protected ItemPedidoPK itemPedidoPK;
		
	@JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO")
	@ManyToOne(optional = false , fetch = FetchType.EAGER)
	private Produto produto;

	@JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
	@ManyToOne(optional = false , fetch = FetchType.EAGER)
	private Pedido pedido;

	@Column(name = "QTD")
	private int qtd;

	@Column(name = "VALOR_UNIDADE")
	private double valor;

	public ItemPedidoPK getItemPedidoPK() {
		return itemPedidoPK;
	}

	public void setItemPedidoPK(ItemPedidoPK itemPedidoPK) {
		this.itemPedidoPK = itemPedidoPK;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
