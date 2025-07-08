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

import com.fasterxml.jackson.annotation.JsonSetter;
import com.lucashcampos.projetodelivery.domain.enums.TipoAdicional;

@Entity
@Table(name = "ADICIONAIS")
public class Adicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	private Integer tipo;
	private String tamanho;
	private Boolean isVisible;
	private Boolean isActive;

	@ManyToOne
	@JoinColumn(name = "loja_id")
	private Loja loja;

	public Adicional() {

	}

	public Adicional(Integer id, String nome, Double preco, Integer tipo) {

		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;

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

	public TipoAdicional getTipo() {
		return TipoAdicional.toEnum(tipo);
	}
	
	@JsonSetter("tipo")
	public void setTipo(TipoAdicional tipo) {
		this.tipo = tipo.getCod();
	}	

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
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

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
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
		Adicional other = (Adicional) obj;
		return Objects.equals(id, other.id);
	}

}
