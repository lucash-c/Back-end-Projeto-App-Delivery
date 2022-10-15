package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.PizzaAdicional;
import com.lucashcampos.projetodelivery.domain.PizzaMassa;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.domain.Produto;

public class NewProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;	

	private String nome;
	private Double preco;	
	private List<Categoria> categorias = new ArrayList<>();
	private List<PizzaSaborTamanho> sabores = new ArrayList<>();
	private String observacao;
	private List<PizzaAdicional> adicionais = new ArrayList<>();
	private PizzaMassa massa;

	public NewProdutoDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<PizzaSaborTamanho> getSabores() {
		return sabores;
	}

	public void setSabores(List<PizzaSaborTamanho> sabores) {
		this.sabores = sabores;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<PizzaAdicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<PizzaAdicional> adicionais) {
		this.adicionais = adicionais;
	}

	public PizzaMassa getMassa() {
		return massa;
	}

	public void setMassa(PizzaMassa massa) {
		this.massa = massa;
	}
	
	public Produto toProduto() {
		return new Produto(null, nome, preco, categorias); 		
	}

}
