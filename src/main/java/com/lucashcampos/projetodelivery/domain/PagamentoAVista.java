package com.lucashcampos.projetodelivery.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lucashcampos.projetodelivery.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoAVista")
public class PagamentoAVista extends Pagamento {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoAVista() {

	}

	public PagamentoAVista(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
