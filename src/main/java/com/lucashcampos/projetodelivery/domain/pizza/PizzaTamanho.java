package com.lucashcampos.projetodelivery.domain.pizza;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAMANHOS_PARA_PIZZA")
public class PizzaTamanho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer pedacos;
	private Integer pessoas; // quantidade de pessoas que serve

	public PizzaTamanho() {

	}

	public PizzaTamanho(Integer id, String nome, Integer pedacos, Integer pessoas) {

		this.id = id;
		this.nome = nome;
		this.pedacos = pedacos;
		this.pessoas = pessoas;
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
		PizzaTamanho other = (PizzaTamanho) obj;
		return Objects.equals(id, other.id);
	}

}
