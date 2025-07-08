package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;

import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String codBarras;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer tipo;
	private Boolean isActive;
	private String imagem;
	private Boolean isVisible;
	private CategoriaDTO categoria;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.categoria= obj.getCategoria() != null ? new CategoriaDTO (obj.getCategoria()): null;
		this.descricao = obj.getDescricao();
		this.codBarras = obj.getCodBarras();
		this.preco = obj.getPreco();
		this.imagem = obj.getImagem();
		this.isActive = obj.getIsActive();
		this.tipo = (obj.getTipo() != null) ? obj.getTipo().getCod() : null;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public TipoProduto getTipo() {
		return TipoProduto.toEnum(tipo);
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo.getCod();
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

}
