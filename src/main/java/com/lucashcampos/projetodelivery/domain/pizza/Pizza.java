package com.lucashcampos.projetodelivery.domain.pizza;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lucashcampos.projetodelivery.domain.Produto;

@Entity
public class Pizza extends Produto {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "tamanho_id")
	private PizzaTamanho tamanho;

	private String descricao;

	public Pizza() {

	}

	public Pizza(String nome, PizzaTamanho tamanho, String descricao, Double preco) {
		super();
		super.setNome(nome);
		this.tamanho = tamanho;
		super.setPreco(preco);
		this.descricao = descricao;
	}

	public PizzaTamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(PizzaTamanho tamanho) {
		this.tamanho = tamanho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
