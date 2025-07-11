package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lucashcampos.projetodelivery.domain.Adicional;
import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.PizzaMassa;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.SorveteCobertura;
import com.lucashcampos.projetodelivery.domain.SorveteSabor;
import com.lucashcampos.projetodelivery.domain.SorveteTamanho;
import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;

public class NewProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String descricao;
	private String codBarras;
	private String imagem;
	private Double preco;
	private Loja loja;
	private Categoria categoria;
	private List<PizzaSaborTamanho> saboresPizza = new ArrayList<>();
	private Integer quantidadeSabor;
	private String observacao;
	private List<Adicional> adicionais = new ArrayList<>();
	private PizzaMassa massa;
	private Integer tipo;
	private SorveteTamanho tamanhoSorvete;
	private List<SorveteSabor> saboresSorvete = new ArrayList<>();
	private List<SorveteCobertura> coberturas;
	private Boolean isActive;
	private Boolean isVisible;

	public NewProdutoDTO() {

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

	public List<PizzaSaborTamanho> getSaboresPizza() {
		return saboresPizza;
	}

	public void setSaboresPizza(List<PizzaSaborTamanho> saboresPizza) {
		this.saboresPizza = saboresPizza;
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

	public TipoProduto getTipo() {
		return TipoProduto.toEnum(tipo);
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo.getCod();
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

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public Integer getQuantidadeSabor() {
		return quantidadeSabor;
	}

	public void setQuantidadeSabor(Integer quantidadeSabor) {
		this.quantidadeSabor = quantidadeSabor;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Produto toProduto() {
		Produto prod = new Produto(null, nome, preco, categoria != null ? categoria : new Categoria());
		prod.setTipo(getTipo() != null ? getTipo() : TipoProduto.COMUM);
		
		if (getDescricao() != null) {
			prod.setDescricao(getDescricao());
		}

		if (getCodBarras() != null) {
			prod.setCodBarras(getCodBarras());
		}
		
		if (getImagem() != null) {
	        prod.setImagem(getImagem());
	    }
		
		prod.setIsVisible(getIsVisible() != null ? getIsVisible() : true);
	    prod.setIsActive(getIsActive() != null ? getIsActive() : true);

		return prod;
	}

}
