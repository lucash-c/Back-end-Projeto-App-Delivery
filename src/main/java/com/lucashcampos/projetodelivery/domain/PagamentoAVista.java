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
	private String trocoPara;
	private String metodo;  // pix, debito ou dinheiro
	

	public PagamentoAVista() {

	}

	public PagamentoAVista(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento, String trocoPara, String metodo) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.trocoPara = trocoPara;
		this.metodo = metodo;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getTrocoPara() {
		return trocoPara;
	}

	public void setTrocoPara(String trocoPara) {
		this.trocoPara = trocoPara;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	

}
