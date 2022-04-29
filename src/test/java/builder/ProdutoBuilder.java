package builder;

import model.entity.Produto;

/**
 * @author Mikaelbr073
 *
 */
public class ProdutoBuilder {

	private String nome;
	private double valor;
	private String descricao;

	public ProdutoBuilder() {
	}

	public static ProdutoBuilder umProduto() {
		return new ProdutoBuilder();

	}

	public ProdutoBuilder comNomeValor(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
		return this;

	}

	public ProdutoBuilder completo(String nome, double valor, String descricao) {
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		return this;

	}

	public Produto build() {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setValor(valor);
		produto.setDescricao(descricao);
		return produto;
	}

}
