package br.com.estudafacil.cursoSpringBoot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.estudafacil.cursoSpringBoot.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*dados cliente*/
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	@Length(min = 5, max = 120, message = "Tamanho Minino de 5 e máximo de 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	@Email(message = "Email obrigatório")
	private String email;
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	private String cpfOrCnpj;
	
	private Integer tipo;
	
	/*dados endereço*/
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	private String numero;
	
	private String complemento;
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	private String bairro;
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	private String cep;
	
	@NotEmpty(message = "Campo de Preenchimento Obrigatório")
	private String telefone1; //inserção obrigatoria
	
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {}

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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	

}
