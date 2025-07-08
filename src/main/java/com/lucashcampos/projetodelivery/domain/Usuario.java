package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucashcampos.projetodelivery.domain.enums.Perfil;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cpf_cnpj;
	private String nome;
	private String email;
	private Integer tipo;
	private String plano;
	private Integer diaVencimento;
	private String formaPagamento;
	private String obs;
	private Boolean isActive;
	private Boolean isVisible;
	private String telefone1;
	private String telefone2;

	@JsonIgnore
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_LOJA", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "loja_id"))
	private List<Loja> lojas = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_endereco", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "endereco_id"))
	private Set<Endereco> enderecos = new HashSet<>();	

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos = new ArrayList<>();

	public Usuario() {

	}

	public Usuario(Integer id, String cpf_cnpj, String nome, String email, TipoCliente tipo, String plano,
			Integer diaVencimento, String formaPagamento, String obs, Boolean isActive, Boolean isVisible, String senha,
			 String telefone1, String telefone2, Set<Perfil> perfis) {

		this.id = id;
		this.cpf_cnpj = cpf_cnpj;
		this.nome = nome;
		this.email = email;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.plano = plano;
		this.diaVencimento = diaVencimento;
		this.formaPagamento = formaPagamento;
		this.obs = obs;
		this.isActive = isActive;
		this.isVisible = isVisible;
		this.senha = senha;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;

		if (perfis != null) {
			perfis.forEach(this::addPerfil);
		}
	}

	public Usuario(Integer id, String cpf_cnpj, String nome, String email, TipoCliente tipo, String senha) {
		this.id = id;
		this.cpf_cnpj = cpf_cnpj;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = (tipo == null) ? null : tipo.getCod();

		addPerfil(Perfil.CLIENTE);
	}

	public Usuario(Integer id, String cpf_cnpj, String nome, String email, TipoCliente tipo, String senha,
			Endereco endereco) {
		this.id = id;
		this.cpf_cnpj = cpf_cnpj;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.enderecos.add(endereco);

		addPerfil(Perfil.CLIENTE);
	}

	public Usuario(Integer id, String cpf_cnpj, String nome, String email, TipoCliente tipo, String senha,
			Endereco endereco, Set<Perfil> perfis) {
		this.id = id;
		this.cpf_cnpj = cpf_cnpj;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.enderecos.add(endereco);

		if (perfis != null) {
			perfis.forEach(this::addPerfil);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	@JsonIgnore
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}	

	public void setPerfis(Set<Perfil> perfis) {	    
	    this.perfis = perfis.stream()
	                        .map(Perfil::getCod) 
	                        .collect(Collectors.toSet()); 
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
}
