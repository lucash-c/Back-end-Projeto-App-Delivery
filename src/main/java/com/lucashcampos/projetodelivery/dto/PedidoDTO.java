package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.lucashcampos.projetodelivery.domain.Cliente;
import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.ItemPedido;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.Pagamento;
import com.lucashcampos.projetodelivery.domain.Pedido;

public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Informações do Cliente é obrigatório!")
	private Cliente cliente;

	@NotEmpty(message = "Informações de pagamento é obrigatório!")
	private Pagamento pagamento;

	@NotEmpty(message = "Informações de endereço é obrigatório!")
	private Endereco enderecoDeEntrega;

	@Size(min = 1, message = "Deve haver pelo menos um item adicionado!")
	private Set<ItemPedido> itens = new HashSet<>();

	@NotEmpty(message = "Informações da Loja é obrigatório!")
	private Loja loja;

	public PedidoDTO() {

	}

	public PedidoDTO(Pedido obj) {
		id = obj.getId();
		cliente = obj.getCliente();
		enderecoDeEntrega = obj.getEnderecoDeEntrega();
		itens = obj.getItens();
		loja = obj.getLoja();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

}
