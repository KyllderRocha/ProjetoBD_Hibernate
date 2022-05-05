package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable{

	private static final long serialVersionUID = -8790835123222534193L;

	@Column(name = "END_CEP", length = 8)
	private String cep;
	
	@Column(name = "END_RUA", length = 255)
	private String rua;
	
	@Column(name = "END_CIDADE", length = 255)
	private String cidade;
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
