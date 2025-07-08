package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lucashcampos.projetodelivery.domain.Adicional;
import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.SorveteCobertura;
import com.lucashcampos.projetodelivery.domain.SorveteSabor;
import com.lucashcampos.projetodelivery.domain.SorveteTamanho;

public class NewSorveteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private String imagem;
	private Double preco;
	private Loja loja;
	private Categoria categoria;	
	private Integer quantidadeSabor;
	private String observacao;
	private List<Adicional> adicionais = new ArrayList<>();
	private SorveteTamanho tamanhoSorvete;
	private List<SorveteSabor> saboresSorvete = new ArrayList<>();
	private List<SorveteCobertura> coberturas;

	
	public NewSorveteDTO() {

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
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

	public SorveteTamanho getTamanhoSorvete() {
		return tamanhoSorvete;
	}

	public void setTamanhoSorvete(SorveteTamanho tamanhoSorvete) {
		this.tamanhoSorvete = tamanhoSorvete;
	}

	public List<SorveteSabor> getSaboresSorvete() {
		return saboresSorvete;
	}

	public void setSaboresSorvete(List<SorveteSabor> saboresSorvete) {
		this.saboresSorvete = saboresSorvete;
	}

	public List<SorveteCobertura> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(List<SorveteCobertura> coberturas) {
		this.coberturas = coberturas;
	}
	
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Integer getQuantidadeSabor() {
		return quantidadeSabor;
	}

	public void setQuantidadeSabor(Integer quantidadeSabor) {
		this.quantidadeSabor = quantidadeSabor;
	}

}
