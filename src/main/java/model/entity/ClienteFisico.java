package model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fisico")
public class ClienteFisico extends Cliente{

	private static final long serialVersionUID = 4634213152908997299L;

	@Column(name = "RG", length = 50)
	private String rg;

	@Column(name = "CPF", length = 11)
	private String cpf;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
