package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucashcampos.projetodelivery.domain.pizza.Pizza;
import com.lucashcampos.projetodelivery.domain.pizza.PizzaAdicional;
import com.lucashcampos.projetodelivery.domain.pizza.PizzaSaborTamanho;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido() {

	}

	public ItemPedido(Produto produto, Pedido pedido, Double desconto, Integer quantidade, Double preco) {

		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public double getSubTotal() {
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		builder.append(quantidade + " ");
		if (getProduto() instanceof Pizza) {
			Pizza pizza = (Pizza) getProduto();

			builder.append(pizza.getNome().toUpperCase());
			builder.append("   --   Preço Un: ");
			builder.append(nf.format(preco));
			builder.append("\nMassa: ");
			builder.append(pizza.getMassa().getNome());

			if (pizza.getSabores().size() > 1) {
				builder.append("\nSABORES:");
				for (PizzaSaborTamanho x : pizza.getSabores()) {
					builder.append("\n1/" + pizza.getSabores().size());
					builder.append(" " + x.getSabor());
				}
			} else {
				builder.append("\nSABOR:");
				builder.append("\n" + pizza.getSabores().get(0).getSabor());
			}

			if (pizza.getAdicionais().size() > 0) {
				builder.append("\n\nAdicionais: ");
				for (PizzaAdicional adicional : pizza.getAdicionais()) {
					builder.append(adicional.getNome());
					builder.append(", ");
				}
				builder.delete(builder.length() - 2, builder.length());
				builder.append("\n\nObs: ");
				builder.append("\n" + pizza.getObservacao() + "\n");
			}
		} else {
			builder.append(getProduto().getNome());
			builder.append("   --   Preço Un: ");
			builder.append(nf.format(preco));
		}

		builder.append("\nSubTotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n-----------------------------------------\n");
		return builder.toString();
	}

}
