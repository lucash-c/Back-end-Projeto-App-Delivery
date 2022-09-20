package com.lucashcampos.projetodelivery.domain.pizza;

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
@Table(name = "SABORES_PARA_PIZZA")
public class PizzaSabor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "tamanho_id")
	private PizzaTamanho tamanho;

	public PizzaSabor() {

	}

	public PizzaSabor(Integer id, String nome, Double preco, String descricao, PizzaTamanho tamanho) {

		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.tamanho = tamanho;
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

	public PizzaTamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(PizzaTamanho tamanho) {
		this.tamanho = tamanho;
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
		PizzaSabor other = (PizzaSabor) obj;
		return Objects.equals(id, other.id);
	}

}
