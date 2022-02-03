package br.com.estudafacil.cursoSpringBoot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.estudafacil.cursoSpringBoot.domain.Categoria;
import br.com.estudafacil.cursoSpringBoot.domain.Produto;
import br.com.estudafacil.cursoSpringBoot.repositories.CategoriaRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.ProdutoRepository;
import br.com.estudafacil.cursoSpringBoot.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> objProduto = repo.findById(id);
		return objProduto.orElseThrow(() -> new ObjectNotFoundException("O objeto não foi encontrado" +
		id + ", Tipo : " + Produto.class.getName()));
		
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategaroriasIn(nome, categorias, pageRequest);
	}
		

	}

