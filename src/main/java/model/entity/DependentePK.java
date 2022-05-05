package model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DependentePK implements Serializable{
	
	private static final long serialVersionUID = 7114490642194158871L;

	@Column(name = "RG", nullable = false, length = 50)
	private String rg;
	
	@Column(name = "ID_CLIENTE", nullable = false)
	private Long cliente_id;
	
	@Override
	public int hashCode() {
		return Objects.hash(rg, cliente_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DependentePK other = (DependentePK) obj;
		return rg == other.rg && cliente_id == other.cliente_id;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}
	
}
