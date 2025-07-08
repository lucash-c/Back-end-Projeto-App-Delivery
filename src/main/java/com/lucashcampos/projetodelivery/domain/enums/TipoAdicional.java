package com.lucashcampos.projetodelivery.domain.enums;

public enum TipoAdicional {

	PIZZA(1, "Pizza"), SORVETE(2, "Sorvete");

	private int cod;
	private String descricao;

	private TipoAdicional(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoAdicional toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoAdicional x : TipoAdicional.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
