package com.lucashcampos.projetodelivery.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucashcampos.projetodelivery.domain.enums.EspecialidadeLoja;

@Entity
@Table(name = "LOJAS")
public class Loja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String razaoSocial;
	private String cnpjCpf;
	private String nomeResponsavel;
	private String nomeFantasia;

	@ElementCollection(targetClass = Integer.class)
	private List<Integer> especialidades = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	private String telefone;
	private String whatsapp;
	private Double mediaSatisfacao;
	private String logo;
	private String site;
	private String instagram;
	private String facebook;

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<SorveteSabor> sorveteSabores = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<SorveteCobertura> sorveteCoberturas = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<SorveteTamanho> sorveteTamanhos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<PizzaMassa> pizzaMassas = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<PizzaSaborTamanho> pizzaSaboresTamanhos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<Categoria> categorias = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	private List<Adicional> adicionais = new ArrayList<>();

	public Loja() {

	}

	public Loja(Integer id, String razaoSocial, String cnpjCpf, String nomeResponsavel, String nomeFantasia,
			Endereco endereco, String telefone, String whatsapp, Double mediaSatisfacao, String logo, String site,
			String instagram, String facebook, List<Integer> especialidades) {

		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpjCpf = cnpjCpf;
		this.nomeResponsavel = nomeResponsavel;
		this.nomeFantasia = nomeFantasia;
		this.endereco = endereco;
		this.telefone = telefone;
		this.whatsapp = whatsapp;
		this.mediaSatisfacao = mediaSatisfacao;
		this.logo = logo;
		this.site = site;
		this.instagram = instagram;
		this.facebook = facebook;
		this.especialidades = especialidades;

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

	public List<EspecialidadeLoja> getEspecialidades() {
		List<EspecialidadeLoja> listEspecs = new ArrayList<>();

		for (Integer espec : especialidades) {
			listEspecs.add(EspecialidadeLoja.toEnum(espec));
		}

		return listEspecs;
	}

	public void setEspecialidades(List<EspecialidadeLoja> listEspecs) {
		List<Integer> newListEspecs = new ArrayList<>();
		for (EspecialidadeLoja espec : listEspecs) {
			newListEspecs.add(espec.getCod());
		}
		this.especialidades = newListEspecs;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<SorveteSabor> getSorveteSabores() {
		return sorveteSabores;
	}

	public void setSorveteSabores(List<SorveteSabor> sorveteSabores) {
		this.sorveteSabores = sorveteSabores;
	}

	public List<SorveteCobertura> getSorveteCoberturas() {
		return sorveteCoberturas;
	}

	public List<SorveteTamanho> getSorveteTamanhos() {
		return sorveteTamanhos;
	}

	public List<PizzaMassa> getPizzaMassas() {
		return pizzaMassas;
	}

	public List<PizzaSaborTamanho> getPizzaSaboresTamanhos() {
		return pizzaSaboresTamanhos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setSorveteCoberturas(List<SorveteCobertura> sorveteCoberturas) {
		this.sorveteCoberturas = sorveteCoberturas;
	}

	public void setSorveteTamanhos(List<SorveteTamanho> sorveteTamanhos) {
		this.sorveteTamanhos = sorveteTamanhos;
	}

	public void setPizzaMassas(List<PizzaMassa> pizzaMassas) {
		this.pizzaMassas = pizzaMassas;
	}

	public void setPizzaSaboresTamanhos(List<PizzaSaborTamanho> pizzaSaboresTamanhos) {
		this.pizzaSaboresTamanhos = pizzaSaboresTamanhos;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Adicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<Adicional> adicionais) {
		this.adicionais = adicionais;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Loja [id=");
		builder.append(id);
		builder.append(", razaoSocial=");
		builder.append(razaoSocial);
		builder.append(", cnpjCpf=");
		builder.append(cnpjCpf);
		builder.append(", nomeResponsavel=");
		builder.append(nomeResponsavel);
		builder.append(", nomeFantasia=");
		builder.append(nomeFantasia);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", whatsapp=");
		builder.append(whatsapp);
		builder.append(", mediaSatisfacao=");
		builder.append(mediaSatisfacao);
		builder.append(", logo=");
		builder.append(logo);
		builder.append(", site=");
		builder.append(site);
		builder.append(", instagram=");
		builder.append(instagram);
		builder.append(", facebook=");
		builder.append(facebook);

		builder.append("]");
		return builder.toString();
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
		Loja other = (Loja) obj;
		return Objects.equals(id, other.id);
	}

}