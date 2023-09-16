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
@Table(name = "CARDAPIO_PARA_SORVETE")
public class SorveteTamanho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	private String descricao;
	Integer quantidadeSabores;

	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;

	public SorveteTamanho() {

	}

	public SorveteTamanho(Integer id, String nome, Double preco, String descricao, Integer quantidadeSabores) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidadeSabores = quantidadeSabores;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidadeSabores() {
		return quantidadeSabores;
	}

	public void setQuantidadeSabores(Integer quantidadeSabores) {
		this.quantidadeSabores = quantidadeSabores;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
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
		SorveteTamanho other = (SorveteTamanho) obj;
		return Objects.equals(id, other.id);
	}
}
