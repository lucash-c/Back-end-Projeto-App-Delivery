package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;
import java.util.List;

public class CategoriaProdutoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String nome;
    private List<ProdutoDTO> produtos;    
    
    
	public CategoriaProdutoDTO(Integer id, String nome, List<ProdutoDTO> produtos) {		
		this.id = id;
		this.nome = nome;
		this.produtos = produtos;
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
	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
    
}
