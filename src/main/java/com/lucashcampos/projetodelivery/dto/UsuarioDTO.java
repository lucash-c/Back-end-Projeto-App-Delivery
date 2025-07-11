package com.lucashcampos.projetodelivery.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lucashcampos.projetodelivery.domain.Usuario;
import com.lucashcampos.projetodelivery.services.validation.ClienteUpdate;

@ClienteUpdate
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 120, message = "O nome deve conter no minimo 3 caracteres!")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido!")
	private String email;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();

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

}
