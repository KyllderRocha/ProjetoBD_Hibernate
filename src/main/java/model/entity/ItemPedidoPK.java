package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@SuppressWarnings("serial")
@Embeddable
public class ItemPedidoPK implements Serializable{

	@Column(name = "PRODUTO_ID_PRODUTO", nullable = false)
	private int idProduto;
	
	@Column(name = "PEDIDO_ID_PEDIDO", nullable = false)
	private int idPedido;

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
	
}
