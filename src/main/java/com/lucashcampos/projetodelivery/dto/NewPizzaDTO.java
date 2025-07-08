package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lucashcampos.projetodelivery.domain.Adicional;
import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.PizzaMassa;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;

public class NewPizzaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String codBarras;
	private String descricao;
	private Integer pessoas;
	private Integer pedacos;
	private Loja loja;
	private Double preco;
	private List<PizzaSaborTamanho> sabores = new ArrayList<>();
	private String observacao;
	private List<Adicional> adicionais = new ArrayList<>();
	private Categoria categoria;
	private PizzaMassa massa;
	private Integer maxSabores;
	private String imagem;
	private String tamanho;
	private Boolean isVisible;
	private Boolean isActive;
	private Boolean cobrarMediaSabores;
	
	

	public NewPizzaDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PizzaSaborTamanho> getSabores() {
		return sabores;
	}

	public void setSabores(List<PizzaSaborTamanho> sabores) {
		this.sabores = sabores;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<PizzaSaborTamanho> getSaboresPizza() {
		return sabores;
	}

	public void setSaboresPizza(List<PizzaSaborTamanho> saboresPizza) {
		this.sabores = saboresPizza;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
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

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

}
