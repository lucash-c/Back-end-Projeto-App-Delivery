package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.Usuario;
import com.lucashcampos.projetodelivery.domain.enums.Perfil;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;

public class UsuarioResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cpf_cnpj;
	private String nome;
	private String email;
	private Integer tipo;
	private List<Endereco> enderecos = new ArrayList<>();
	private String telefone1; 
	private String telefone2; 
	private Set<Integer> perfis = new HashSet<>();
	private Boolean isActive;
	private Boolean isVisible;
	private String token;
	private List<Loja> lojas;

	public UsuarioResponseDTO(Usuario usuario, String token) {
		this.nome = usuario.getNome();
		this.cpf_cnpj = usuario.getCpf_cnpj();
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.tipo = usuario.getTipo().getCod();
		this.enderecos = new ArrayList<>(usuario.getEnderecos());
		this.telefone1 = usuario.getTelefone1();
		this.telefone2 = (usuario.getTelefone2() != null) ? usuario.getTelefone2() : null;
		this.perfis = usuario.getPerfis().stream().map(perfil -> perfil.getCod()) // Transforma cada perfil no código
				.collect(Collectors.toSet()); // Coleta como um conjunto (Set)
		this.token = token;
		this.lojas = usuario.getLojas();
		this.isActive = usuario.getIsActive();
		this.isVisible = usuario.getIsVisible();
	}

	// Getters e Setters

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
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

}