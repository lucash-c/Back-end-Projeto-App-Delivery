package com.lucashcampos.projetodelivery.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pizza extends Produto {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinColumn(name = "sabor_tamanho_id")
	private List<PizzaSaborTamanho> sabores = new ArrayList<>();

	private String observacao;

	@ManyToMany
	@JoinTable(name = "PIZZA_ADICIONAIS", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "adicional_id"))
	private List<PizzaAdicional> adicionais = new ArrayList<>();

	@ManyToOne
	@JoinTable(name = "PIZZA_MASSA", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "massa_id"))
	private PizzaMassa massa;

	public Pizza() {

	}

	public Pizza(List<PizzaSaborTamanho> sabores, PizzaMassa massa, List<PizzaAdicional> adicionais,
			String observacao) {
		super();
		super.setNome("Pizza " + sabores.get(0).getTamanho());
		this.sabores = sabores;
		this.massa = massa;
		this.adicionais = adicionais;
		this.observacao = observacao;

//		// somando valor da pizza  ====>>>>>  CALCULAR NO FRONT 
//		Double soma = 0.0;
//		for (PizzaSaborTamanho x : sabores) {
//			soma += x.getPreco();
//		}
//		soma = soma / sabores.size();
//
//		for (PizzaAdicional x : adicionais) {
//			soma += x.getPreco();
//		}
//
//		soma = soma + massa.getPreco();
//		super.setPreco(soma);
	}

	public List<PizzaSaborTamanho> getSabores() {
		return sabores;
	}

	public void setSabores(List<PizzaSaborTamanho> sabores) {
		this.sabores = sabores;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
