package com.lucashcampos.projetodelivery.domain.enums;

public enum EstadoPedido {
	EM_PREPARO(1, "Em preparo"), PRONTO(2, "Pronto"), CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;

	private EstadoPedido(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPedido toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (EstadoPedido x : EstadoPedido.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
