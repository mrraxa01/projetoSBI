package com.mrraxa01.projetoSBI.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mrraxa01.projetoSBI.domain.Cliente;
import com.mrraxa01.projetoSBI.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	@NotEmpty(message="Preenchimento Obrigatório!")
	@Length(min=5,max=120, message = "O tamanho deve ter de 5 a 80 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatório!")
	@Email(message="EMAIL Inválido")
	private String email;
	
	public ClienteDTO() {}
	
	//construtor para instanciar o DTO
	public ClienteDTO(Cliente obj) {
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
