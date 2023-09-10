package com.lucashcampos.projetodelivery.domain.enums;

public enum EspecialidadeRestaurante {
	PIZZARIA(0, "Pizzaria"), SORVETERIA(1, "Sorveteria"), ESPETARIA(2, "Espetaria"), JAPONESA(2, "Japonesa");

	private int cod;
	private String descricao;

	private EspecialidadeRestaurante (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EspecialidadeRestaurante toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EspecialidadeRestaurante x : EspecialidadeRestaurante.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
