package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;

import com.lucashcampos.projetodelivery.domain.Usuario;
import com.lucashcampos.projetodelivery.services.validation.ClienteUpdate;

@ClienteUpdate
public class UsuarioTableDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cpf_cnpj;
	private String nome;
	private String email;
	private String plano;
	private Integer diaVencimento;
	private String formaPagamento;
	private String obs;
	private Boolean isActive;
	private Boolean isVisible;

	public UsuarioTableDTO() {

	}

	public UsuarioTableDTO(Usuario usuario) {

		this.id = usuario.getId();
		this.cpf_cnpj = usuario.getCpf_cnpj();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.plano = usuario.getPlano();
		this.diaVencimento = usuario.getDiaVencimento();
		this.formaPagamento = usuario.getFormaPagamento();
		this.obs = usuario.getObs();
		this.isActive = usuario.getIsActive();
		this.isVisible = usuario.getIsVisible();
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

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
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
