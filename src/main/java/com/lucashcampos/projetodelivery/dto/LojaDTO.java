package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.enums.EspecialidadeLoja;

public class LojaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Razão Social é obrigatória!")
	@Length(min = 3, max = 80, message = "A Razão Social deve ter entre 3 e 80 caracteres!")
	private String razaoSocial;

	@NotEmpty(message = "CNPJ/CPF é obrigatório!")
	@Size(min = 11, max = 14, message = "O CNPJ/CPF deve ter entre 11 e 14 caracteres!")
	@Pattern(regexp = "^[0-9]*$", message = "O CNPJ/CPF deve conter apenas números!")
	private String cnpjCpf;

	@NotEmpty(message = "Nome do Responsável é obrigatório!")
	private String nomeResponsavel;

	@NotEmpty(message = "Nome Fantasia é obrigatório!")
	private String nomeFantasia;

	@NotEmpty(message = "Logradouro é obrigatório!")
	private String logradouro;

	@NotEmpty(message = "Número é obrigatório!")
	private String numero;

	private String complemento;

	@NotEmpty(message = "CEP é obrigatório!")
	@Size(min = 8, max = 8, message = "O CEP deve ter 8 caracteres!")
	@Pattern(regexp = "^[0-9]*$", message = "O CEP deve conter apenas números!")
	private String cep;

	@NotEmpty(message = "Telefone é obrigatório!")
	@Pattern(regexp = "\\d{10,11}", message = "Telefone inválido. Deve conter 10 ou 11 dígitos numéricos.")
	private String telefone;

	@Pattern(regexp = "\\d{10,11}", message = "Whatsapp inválido. Deve conter 10 ou 11 dígitos numéricos contando com o DDD.")
	private String whatsapp;
	private Double mediaSatisfacao;
	private String logo;
	private String site;
	private String instagram;
	private String facebook;

	@Size(min = 1, message = "Deve haver pelo menos uma especialidade adicionada!")
	private List<EspecialidadeLoja> especialidades;

	public LojaDTO() {

	}

	public LojaDTO(Loja obj) {
		id = obj.getId();
		razaoSocial = obj.getRazaoSocial();
		cnpjCpf = obj.getCnpjCpf();
		nomeResponsavel = obj.getNomeResponsavel();
		nomeFantasia = obj.getNomeFantasia();
		logradouro = obj.getEndereco().getLogradouro();
		numero = obj.getEndereco().getNumero();
		complemento = obj.getEndereco().getComplemento();
		cep = obj.getEndereco().getCep();
		telefone = obj.getTelefone();
		whatsapp = obj.getWhatsapp();
		mediaSatisfacao = obj.getMediaSatisfacao();
		logo = obj.getLogo();
		site = obj.getSite();
		instagram = obj.getInstagram();
		facebook = obj.getFacebook();
		especialidades = obj.getEspecialidades();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public Double getMediaSatisfacao() {
		return mediaSatisfacao;
	}

	public void setMediaSatisfacao(Double mediaSatisfacao) {
		this.mediaSatisfacao = mediaSatisfacao;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public List<EspecialidadeLoja> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeLoja> especialidades) {
		this.especialidades = especialidades;
	}

}
