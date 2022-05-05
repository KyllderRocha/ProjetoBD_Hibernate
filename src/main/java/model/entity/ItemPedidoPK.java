package model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemPedidoPK implements Serializable{

	private static final long serialVersionUID = -8154979449891885974L;

	@Column(name = "ID_PRODUTO", nullable = false)
	private Long idProduto;
	
	@Column(name = "ID_PEDIDO", nullable = false)
	private Long idPedido;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
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
