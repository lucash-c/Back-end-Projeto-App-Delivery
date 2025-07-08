package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucashcampos.projetodelivery.domain.enums.EstadoPedido;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;

	private Integer estado = EstadoPedido.EM_PREPARO.getCod();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "endereco_entrega_id")
	private Endereco enderecoDeEntrega;

	@OneToMany(mappedBy = "id.pedido", cascade = CascadeType.REMOVE)
	private Set<ItemPedido> itens = new HashSet<>();

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "loja_id")
	private Loja loja;

	public Pedido() {

	}

	public Pedido(Integer id, Date instante, Usuario usuario, Endereco enderecoDeEntrega) {

		this.id = id;
		this.instante = instante;
		this.usuario = usuario;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Pedido(Integer id, Date instante, Usuario usuario, Endereco enderecoDeEntrega, Loja loja) {

		this.id = id;
		this.instante = instante;
		this.usuario = usuario;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.loja = loja;
	}
//	@JsonIgnore
//	public double getValorTotal() {
//		double soma = 0.0;
//		for (ItemPedido ip : itens) {
//			soma += ip.getSubTotal();
//		}
//		return soma;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Usuario getCliente() {
		return usuario;
	}

	public void setCliente(Usuario usuario) {
		this.usuario = usuario;
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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

//	@Override
//	public String toString() {
//		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		StringBuilder builder = new StringBuilder();
//		builder.append("Pedido número: ");
//		builder.append(getId());
//		builder.append(", Instante: ");
//		builder.append(sdf.format(getInstante()));
//		builder.append(", Cliente: ");
//		builder.append(getCliente().getNome());
//		builder.append(", Situação do pagamento: ");
//		builder.append(getPagamento().getEstado().getDescricao());
//		builder.append(",\nDetalhes:\n ");
//		for (ItemPedido ip : getItens()) {
//			builder.append(ip.toString());
//		}
//
//		builder.append("\nValor total: ");
//		builder.append(nf.format(getValorTotal()));
//		return builder.toString();
//	}

}
