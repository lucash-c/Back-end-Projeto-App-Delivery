package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public PizzaSaborTamanho() {

	}

	public PizzaSaborTamanho(Integer id, String sabor, String descricao, String tamanho, Integer pedacos, Integer pessoas, Double preco) {
		this.id = id;
		this.sabor = sabor;
		this.descricao= descricao;
		this.tamanho = tamanho;
		this.pedacos = pedacos;
		this.pessoas = pessoas;
		this.preco = preco;
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
