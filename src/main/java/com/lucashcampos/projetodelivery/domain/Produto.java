package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String nome;
	private Double preco;
	private Integer tipo;
	private String imagem;
	private Boolean isActive = true;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.DETACH) // Evitar exclusão em cascata
	@JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "loja_id")
	private Loja loja;

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto", cascade = CascadeType.REMOVE)
	private Set<ItemPedido> itens = new HashSet<>();

	public Produto() {

	}

	public Produto(Integer id, String nome, Double preco, Loja loja) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.loja = loja;

	}

	public Produto(Integer id, String nome, Double preco, String descricao) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;

	}

	public Produto(Integer id, String nome, Double preco, List<Categoria> categorias) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.categorias = categorias;

	}

	public Produto(Integer id, String nome, Double preco, List<Categoria> categorias, Loja loja) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.categorias = categorias;
		this.loja = loja;

	}

	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	@JsonIgnore
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}
