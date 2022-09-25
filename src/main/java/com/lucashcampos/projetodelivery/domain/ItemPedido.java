package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucashcampos.projetodelivery.domain.pizza.PizzaAdicional;
import com.lucashcampos.projetodelivery.domain.pizza.PizzaMassa;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Double desconto;
	private Double quantidade;
	private Double preco;

	@ManyToMany
	@JoinTable(name = "PIZZA_ADICIONAIS", joinColumns = { @JoinColumn(name = "item_pedido_id"), @JoinColumn(name = "pedido_id"),
			@JoinColumn(name = "item_id") }, inverseJoinColumns = @JoinColumn(name = "adicional_id"))
	private List<PizzaAdicional> adicionais = new ArrayList<>();

	@ManyToOne
	@JoinTable(name = "PIZZA_MASSA", joinColumns = { @JoinColumn(name = "item_pedido_id"), @JoinColumn(name = "pedido_id"),
			@JoinColumn(name = "item_id") }, inverseJoinColumns = @JoinColumn(name = "massa_id"))
	private PizzaMassa massa;

	public ItemPedido() {

	}

	public ItemPedido(Produto produto, Pedido pedido, Double desconto, Double quantidade, Double preco) {

		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public double getSubTotal() {
		return (preco - desconto)* quantidade;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<PizzaAdicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<PizzaAdicional> adicionais) {
		this.adicionais = adicionais;
	}

	public PizzaMassa getMassa() {
		return massa;
	}

	public void setMassa(PizzaMassa massa) {
		this.massa = massa;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

}
