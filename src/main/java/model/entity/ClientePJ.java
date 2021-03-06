package model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("juridico")
public class ClientePJ extends Cliente{
	
	private static final long serialVersionUID = -5772485563088677445L;

	@Column(name = "CNPJ", length = 50)
	private String cnpj;

	@Column(name = "INSC_ESTADUAL", length = 50)
	private String insc_estadual;
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInsc_estadual() {
		return insc_estadual;
	}

	public void setInsc_estadual(String insc_estadual) {
		this.insc_estadual = insc_estadual;
	}
}
