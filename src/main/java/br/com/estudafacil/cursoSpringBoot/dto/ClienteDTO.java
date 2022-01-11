package br.com.estudafacil.cursoSpringBoot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.estudafacil.cursoSpringBoot.domain.Cliente;
import br.com.estudafacil.cursoSpringBoot.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	@Length(min = 5, max = 120, message = "Tamanho Minino de 5 e máximo de 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	@Email
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
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
