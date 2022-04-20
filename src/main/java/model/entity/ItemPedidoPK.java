package model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ItemPedidoPK implements Serializable{

	@Column(name = "ID_PRODUTO", nullable = false)
	private int idProduto;
	
	@Column(name = "ID_PEDIDO", nullable = false)
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

	@Override
	public int hashCode() {
		return Objects.hash(idPedido, idProduto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		return idPedido == other.idPedido && idProduto == other.idProduto;
	}
	
	
}
