package com.lucashcampos.projetodelivery.domain.enums;

public enum EspecialidadeLoja {
	PIZZARIA(0, "Pizzaria"), SORVETERIA(1, "Sorveteria"), ESPETARIA(2, "Espetaria"), JAPONESA(2, "Japonesa");

	private int cod;
	private String descricao;

	private EspecialidadeLoja (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EspecialidadeLoja toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EspecialidadeLoja x : EspecialidadeLoja.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
