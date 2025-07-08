package com.lucashcampos.projetodelivery.domain.enums;

public enum TipoProduto {

	PIZZA(0, "Pizza"), SORVETE(1, "Sorvete"), COMUM(2, "Comum"), BEBIDA(3, "Bebida"), LANCHE(4, "Lanche");

	private int cod;
	private String descricao;

	private TipoProduto(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoProduto toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoProduto x : TipoProduto.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
