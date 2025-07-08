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
	private String tamanho;
	private Boolean cobrarMediaSabores;	
	private Integer maxSabores; // quantidade maxima de partes que a pizza podera ser dividida ou sabores que o front permitira ao cliente escolher
	private Integer pessoas;
	private Integer pedacos;

	@ManyToMany
	@JoinTable(name = "PIZZA_ADICIONAIS", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "adicional_id"))
	private List<Adicional> adicionais = new ArrayList<>();	

	@ManyToOne
	@JoinColumn(name = "massa_id")
	private PizzaMassa massa;

	public Pizza() {

	}

	public Pizza(List<PizzaSaborTamanho> sabores, PizzaMassa massa, List<Adicional> adicionais, String observacao, Integer maxSabores) {
		super();
		super.setId(null);
		this.sabores = sabores;
		this.massa = massa;
		this.adicionais = adicionais;
		this.observacao = observacao;
		super.setTipo(TipoProduto.PIZZA);
		this.maxSabores = maxSabores;
	}
	
	public Pizza(Integer id, List<PizzaSaborTamanho> sabores, PizzaMassa massa, List<Adicional> adicionais, String observacao, Integer maxSabores) {
		super();
		super.setId(id);
		this.sabores = sabores;
		this.massa = massa;
		this.adicionais = adicionais;
		this.observacao = observacao;
		super.setTipo(TipoProduto.PIZZA);
		this.maxSabores = maxSabores;
	}
	
	public Pizza(List<PizzaSaborTamanho> sabores, PizzaMassa massa, List<Adicional> adicionais, String observacao, Integer maxSabores, String tamanho, Boolean cobrarMediaSabores) {
		super();
		super.setId(null);
		this.sabores = sabores;
		this.massa = massa;
		this.adicionais = adicionais;
		this.observacao = observacao;
		super.setTipo(TipoProduto.PIZZA);
		this.maxSabores = maxSabores;
		this.tamanho = tamanho;
		this.cobrarMediaSabores = cobrarMediaSabores;
		
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

	public Integer getMaxSabores() {
		return maxSabores;
	}

	public void setMaxSabores(Integer maxSabores) {
		this.maxSabores = maxSabores;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Boolean getCobrarMediaSabores() {
		return cobrarMediaSabores;
	}

	public void setCobrarMediaSabores(Boolean cobrarMediaSabores) {
		this.cobrarMediaSabores = cobrarMediaSabores;
	}
	
	public Integer getPessoas() {
		return pessoas;
	}

	public void setPessoas(Integer pessoas) {
		this.pessoas = pessoas;
	}

	public Integer getPedacos() {
		return pedacos;
	}

	public void setPedacos(Integer pedacos) {
		this.pedacos = pedacos;
	}
	
	
	
	
	

}
