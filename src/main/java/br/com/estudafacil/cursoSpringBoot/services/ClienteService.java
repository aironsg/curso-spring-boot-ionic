package br.com.estudafacil.cursoSpringBoot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudafacil.cursoSpringBoot.domain.Cidade;
import br.com.estudafacil.cursoSpringBoot.domain.Cliente;
import br.com.estudafacil.cursoSpringBoot.domain.Endereco;
import br.com.estudafacil.cursoSpringBoot.domain.enums.TipoCliente;
import br.com.estudafacil.cursoSpringBoot.dto.ClienteDTO;
import br.com.estudafacil.cursoSpringBoot.dto.ClienteNewDTO;
import br.com.estudafacil.cursoSpringBoot.repositories.ClienteRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.EnderecoRepository;
import br.com.estudafacil.cursoSpringBoot.services.exception.DataIntegrityException;
import br.com.estudafacil.cursoSpringBoot.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) { // GET
		Optional<Cliente> objCliente = repo.findById(id);
		return objCliente.orElseThrow(() -> new ObjectNotFoundException(
				"O cliente não foi encontrado" + id + ", Tipo : " + Cliente.class.getName()));

	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) { // PUT
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

	public void delete(Integer id) { // DELETE
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possivel deletar um cliente, pois ele tem associações de dependência");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	/* sobrecarga de metodo para fazer a inserção de novos clientes */
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cliente = new Cliente(objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOrCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cidade = new Cidade(objDTO.getCidadeId());
		Endereco endereco = new Endereco(objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getBairro(),
				objDTO.getComplemento(), objDTO.getCep(), cidade, cliente);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefone().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {

			cliente.getTelefone().add(objDTO.getTelefone2());
		}

		if (objDTO.getTelefone3() != null) {

			cliente.getTelefone().add(objDTO.getTelefone3());
		}

		return cliente;

	}

}
