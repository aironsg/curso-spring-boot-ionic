package br.com.estudafacil.cursoSpringBoot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.estudafacil.cursoSpringBoot.domain.Categoria;
import br.com.estudafacil.cursoSpringBoot.domain.Cidade;
import br.com.estudafacil.cursoSpringBoot.domain.Cliente;
import br.com.estudafacil.cursoSpringBoot.domain.Endereco;
import br.com.estudafacil.cursoSpringBoot.domain.Estado;
import br.com.estudafacil.cursoSpringBoot.domain.Pagamento;
import br.com.estudafacil.cursoSpringBoot.domain.PagamentoComBoleto;
import br.com.estudafacil.cursoSpringBoot.domain.PagamentoComCartao;
import br.com.estudafacil.cursoSpringBoot.domain.Pedido;
import br.com.estudafacil.cursoSpringBoot.domain.Produto;
import br.com.estudafacil.cursoSpringBoot.domain.enums.EstadoPagamento;
import br.com.estudafacil.cursoSpringBoot.domain.enums.TipoCliente;
import br.com.estudafacil.cursoSpringBoot.repositories.CategoriaRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.CidadeRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.ClienteRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.EnderecoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.EstadoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.PagamentoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		Estado estado3 = new Estado("Pernambuco");
		
		Cidade cidade1 = new Cidade("Uberlândia", estado1);
		Cidade cidade2 = new Cidade("São Paulo", estado2);
		Cidade cidade3 = new Cidade("Campinas", estado2);
		Cidade cidade4 = new Cidade("Caruaru", estado3);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		estado3.getCidades().addAll(Arrays.asList(cidade4));
		
		
		Cliente cliente1 = new Cliente("Airon silva", "airon@teste.com", "000.222.444-55", TipoCliente.PESSOAFISICA);
		cliente1.getTelefone().addAll(Arrays.asList("9999-8888", "5555-7777"));
		
		Endereco endereco1 = new Endereco("Rua lapaz", "338", "aptB", "João Mota", "55010-080", cidade4, cliente1);
		Endereco endereco2 = new Endereco("passo largo", "002", "casa", "não sei onde", "55000-000", cidade4, cliente1);
		
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Pedido pedido1 = new Pedido(simpleDateFormat.parse("20/12/2020"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(simpleDateFormat.parse("21/12/2021"), cliente1, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, pedido2,
				simpleDateFormat.parse("20/10/2021"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		estadoRepository.saveAll(Arrays.asList(estado1,estado2,estado3));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3,cidade4));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		
	}

}
