package com.lucashcampos.projetodelivery.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;

@Entity
public class Pizza extends Produto {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "PIZZA_SABOR_TAMANHO", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "sabor_tamanho_id"))
	private List<PizzaSaborTamanho> sabores = new ArrayList<>();

	private String observacao;

	@ManyToMany
	@JoinTable(name = "PIZZA_ADICIONAIS", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "adicional_id"))
	private List<Adicional> adicionais = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "massa_id")
	private PizzaMassa massa;

	public Pizza() {

	}

	public Pizza(List<PizzaSaborTamanho> sabores, PizzaMassa massa, List<Adicional> adicionais, String observacao) {
		super();
		super.setNome("Pizza " + sabores.get(0).getTamanho());
		this.sabores = sabores;
		this.massa = massa;
		this.adicionais = adicionais;
		this.observacao = observacao;
		super.setTipo(TipoProduto.PIZZA);
	}

	public List<PizzaSaborTamanho> getSabores() {
		return sabores;
	}

	public void setSabores(List<PizzaSaborTamanho> sabores) {
		this.sabores = sabores;
	}

	public List<Adicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<Adicional> adicionais) {
		this.adicionais = adicionais;
	}

	public PizzaMassa getMassa() {
		return massa;
	}

	public void setMassa(PizzaMassa massa) {
		this.massa = massa;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
