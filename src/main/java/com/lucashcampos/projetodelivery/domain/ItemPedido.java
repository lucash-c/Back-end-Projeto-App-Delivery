package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucashcampos.projetodelivery.domain.pizza.Pizza;
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
	@JoinTable(name = "PIZZA_ADICIONAIS", joinColumns = { @JoinColumn(name = "item_pedido_id"),
			@JoinColumn(name = "pedido_id"),
			@JoinColumn(name = "item_id") }, inverseJoinColumns = @JoinColumn(name = "adicional_id"))
	private List<PizzaAdicional> adicionais = new ArrayList<>();

	@ManyToOne
	@JoinTable(name = "PIZZA_MASSA", joinColumns = { @JoinColumn(name = "item_pedido_id"),
			@JoinColumn(name = "pedido_id"),
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

		if (massa != null) {
			preco += massa.getPreco();
		}

		for (PizzaAdicional x : adicionais) {
			preco += x.getPreco();
		}
		return (preco - desconto) * quantidade;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
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

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		if (getQuantidade() < 1) {
			builder.append("MEIA ");
		} else {
			builder.append(quantidade.intValue() + " ");
		}

		if (getProduto() instanceof Pizza) {
			Pizza pizza = (Pizza) getProduto();

			builder.append(pizza.getNome() + " " + pizza.getTamanho().getNome());
			builder.append(", Massa: ");
			builder.append(getMassa().getNome());

			if (getAdicionais().size() > 0) {
				builder.append(", Adicionais: ");
				for (PizzaAdicional adicional : getAdicionais()) {
					builder.append(adicional.getNome());
					builder.append(", ");
				}
				builder.delete(builder.length() - 2, builder.length());
			}
		} else {
			builder.append(getProduto().getNome());
		}

		builder.append(", Preço: ");
		builder.append(nf.format(preco));
		builder.append(", SubTotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}
