package com.lucashcampos.projetodelivery.domain.pizza;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.lucashcampos.projetodelivery.domain.Produto;

@Entity
public class Pizza extends Produto {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "tamanho_id")
	private PizzaTamanho tamanho;

	@ManyToOne
	@JoinColumn(name = "massa_id")
	private PizzaMassa massa;

	@ManyToMany
	@JoinTable(name = "PIZZA_SABORES", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "sabor_id"))
	private List<PizzaSabor> sabores = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "PIZZA_ADICIONAIS", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "adicional_id"))
	private List<PizzaAdicional> adicionais = new ArrayList<>();

	public Pizza() {

	}

	public Pizza(PizzaTamanho tamanho, PizzaMassa massa) {
		super();
		this.tamanho = tamanho;
		this.massa = massa;
	}

	public PizzaTamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(PizzaTamanho tamanho) {
		this.tamanho = tamanho;
	}

	public PizzaMassa getMassa() {
		return massa;
	}

	public void setMassa(PizzaMassa massa) {
		this.massa = massa;
	}

	public List<PizzaSabor> getSabores() {
		return sabores;
	}

	public void setSabores(List<PizzaSabor> sabores) {
		this.sabores = sabores;
	}

	public List<PizzaAdicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<PizzaAdicional> adicionais) {
		this.adicionais = adicionais;
	}

}
