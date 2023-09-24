package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARDAPIO_PIZZAS")
public class PizzaSaborTamanho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String sabor;
	private String tamanho;
	private String descricao;
	private Integer pedacos;
	private Integer pessoas; // quantidade de pessoas que serve
	private Double preco;
	private Boolean isActive = true;
	private Integer quantidadeSabor; // quantidade maxima de partes que a pizza podera ser dividida ou sabores que o
	 								 // front permitira ao cliente escolher

	@ManyToOne
	@JoinColumn(name = "loja_id")
	private Loja loja;

	public PizzaSaborTamanho() {

	}

	public PizzaSaborTamanho(Integer id, String sabor, String descricao, String tamanho, Integer pedacos,
			Integer pessoas, Double preco, Integer quantidadeSabor) {
		this.id = id;
		this.sabor = sabor;
		this.descricao = descricao;
		this.tamanho = tamanho;
		this.pedacos = pedacos;
		this.pessoas = pessoas;
		this.preco = preco;
		this.quantidadeSabor = quantidadeSabor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getPedacos() {
		return pedacos;
	}

	public void setPedacos(Integer pedacos) {
		this.pedacos = pedacos;
	}

	public Integer getPessoas() {
		return pessoas;
	}

	public void setPessoas(Integer pessoas) {
		this.pessoas = pessoas;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidadeSabor() {
		return quantidadeSabor;
	}

	public void setQuantidadeSabor(Integer quantidadeSabor) {
		this.quantidadeSabor = quantidadeSabor;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PizzaSaborTamanho other = (PizzaSaborTamanho) obj;
		return Objects.equals(id, other.id);
	}
}
