package com.lucashcampos.projetodelivery.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;

@Entity
public class Sorvete extends Produto {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "tamanho_id")
	private SorveteTamanho tamanho;

	@ManyToMany
	@JoinTable(name = "SORVETES_SABORES", joinColumns = @JoinColumn(name = "sorvete_id"), inverseJoinColumns = @JoinColumn(name = "sabor_id"))
	private List<SorveteSabor> sabores = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "SORVETE_ADICIONAIS", joinColumns = @JoinColumn(name = "sorvete_id"), inverseJoinColumns = @JoinColumn(name = "adicional_id"))
	private List<Adicional> adicionais = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "SORVETE_COBERTURA", joinColumns = @JoinColumn(name = "sorvete_id"), inverseJoinColumns = @JoinColumn(name = "cobertura_id"))
	private List<SorveteCobertura> coberturas;

	public Sorvete() {

	}

	public Sorvete(SorveteTamanho tamanho, List<SorveteSabor> sabores, List<Adicional> adicionais,
			List<SorveteCobertura> coberturas, Integer tipo) {
		super();
		this.tamanho = tamanho;
		this.sabores = sabores;
		this.adicionais = adicionais;
		this.coberturas = coberturas;
		super.setNome(TipoProduto.toEnum(tipo).getDescricao() +" "+ tamanho.getNome());
		super.setPreco(tamanho.getPreco());
		super.setTipo(TipoProduto.toEnum(tipo));
	}

	public List<SorveteSabor> getSabores() {
		return sabores;
	}

	public void setSabores(List<SorveteSabor> sabores) {
		this.sabores = sabores;
	}

	public List<Adicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<Adicional> adicionais) {
		this.adicionais = adicionais;
	}

	public List<SorveteCobertura> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(List<SorveteCobertura> coberturas) {
		this.coberturas = coberturas;
	}

	public SorveteTamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(SorveteTamanho tamanho) {
		this.tamanho = tamanho;
	}
	
	

}
