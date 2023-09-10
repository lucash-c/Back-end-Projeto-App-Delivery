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

import com.lucashcampos.projetodelivery.domain.enums.EspecialidadeRestaurante;

@Entity
@Table(name = "RESTAURANTES")
public class Restaurante implements Serializable {
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

	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<>();

	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<>();

	public Restaurante() {

	}

	public Restaurante(Integer id, String razaoSocial, String cnpjCpf, String nomeResponsavel, String nomeFantasia,
			List<Integer> especialidades) {

		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpjCpf = cnpjCpf;
		this.nomeResponsavel = nomeResponsavel;
		this.nomeFantasia = nomeFantasia;
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

	public List<EspecialidadeRestaurante> getEspecialidades() {
		List<EspecialidadeRestaurante> listEspecs = new ArrayList<>();

		for (Integer espec : especialidades) {
			listEspecs.add(EspecialidadeRestaurante.toEnum(espec));
		}

		return listEspecs;
	}

	public void setEspecialidades(List<EspecialidadeRestaurante> listEspecs) {
		List<Integer> newListEspecs = new ArrayList<>();
		for (EspecialidadeRestaurante espec : listEspecs) {
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
		Restaurante other = (Restaurante) obj;
		return Objects.equals(id, other.id);
	}

}