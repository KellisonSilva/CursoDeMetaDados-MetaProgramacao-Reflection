package br.com.biblioteca.modelo;

import br.com.bibliotecaBase.anotacao.NomeTagXml;

@NomeTagXml("Book")
public class Livro {

	@NomeTagXml("name")
	private String nome;
	@NomeTagXml("type")
	private String tipo;
	@NomeTagXml("price")
	private Double preco;
	

	public Livro(String nome, String tipo, Double preco) {
		this.nome = nome;
		this.tipo = tipo;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "[Livro=  " + nome + ", valor = " + preco + ", TipoL =  " + tipo + "]";
	}
}
