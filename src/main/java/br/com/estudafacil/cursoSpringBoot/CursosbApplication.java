package br.com.estudafacil.cursoSpringBoot;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.estudafacil.cursoSpringBoot.domain.Categoria;
import br.com.estudafacil.cursoSpringBoot.domain.Cidade;
import br.com.estudafacil.cursoSpringBoot.domain.Estado;
import br.com.estudafacil.cursoSpringBoot.domain.Produto;
import br.com.estudafacil.cursoSpringBoot.repositories.CategoriaRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.CidadeRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.EstadoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursosbApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursosbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria("Informática");
		Categoria categoria2 = new Categoria("Escritório");
		
		Produto produto1 = new Produto("Notebook", 4500.00);
		Produto produto2 = new Produto("Impressora", 1200.00);
		Produto produto3 = new Produto("Mouse Gamer", 90.00);
		
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		Estado estado1 = new Estado("MinasGerais");
		Estado estado2 = new Estado("São Paulo");
		
		Cidade cidade1 = new Cidade("Uberlândia", estado1);
		Cidade cidade2 = new Cidade("São Paulo", estado2);
		Cidade cidade3 = new Cidade("Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		
	}

}
