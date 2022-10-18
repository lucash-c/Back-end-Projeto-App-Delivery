package com.lucashcampos.projetodelivery.domain.enums;

public enum TipoProduto {

	PIZZA(1, "Pizza"), SORVETE(2, "Sorvete"), COMUM(3, "Comum");

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
