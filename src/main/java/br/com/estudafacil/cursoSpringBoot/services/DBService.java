package br.com.estudafacil.cursoSpringBoot.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudafacil.cursoSpringBoot.domain.Categoria;
import br.com.estudafacil.cursoSpringBoot.domain.Cidade;
import br.com.estudafacil.cursoSpringBoot.domain.Cliente;
import br.com.estudafacil.cursoSpringBoot.domain.Endereco;
import br.com.estudafacil.cursoSpringBoot.domain.Estado;
import br.com.estudafacil.cursoSpringBoot.domain.ItemPedido;
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
import br.com.estudafacil.cursoSpringBoot.repositories.ItemPedidoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.PagamentoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.PedidoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.ProdutoRepository;

@Service
public class DBService {
	

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

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDataBase() throws ParseException {
		

		Categoria categoria1 = new Categoria("Informática");
		Categoria categoria2 = new Categoria("Escritório");
		Categoria categoria3 = new Categoria("Cama mesa e banho");
		Categoria categoria4 = new Categoria("Eletrônicos");
		Categoria categoria5 = new Categoria("Jardinagem");
		Categoria categoria6 = new Categoria("Decoração");
		Categoria categoria7 = new Categoria("Perfumaria");

		Produto produto1 = new Produto("Notebook", 2000.00);
		Produto produto2 = new Produto("Impressora", 800.00);
		Produto produto3 = new Produto("Mouse Gamer", 80.00);

		Produto produto4 = new Produto("Mesa de escritorio", 300.00);
		Produto produto5 = new Produto("Toalha", 50.00);
		Produto produto6 = new Produto("Colcha", 200.00);
		Produto produto7 = new Produto("TV 4k", 2800.00);
		Produto produto8 = new Produto("Roçadeira", 800.00);
		Produto produto9 = new Produto("Abajour", 100.00);
		Produto produto10 = new Produto("Luminaria Pendente", 180.00);
		Produto produto11 = new Produto("Shampoo", 90.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2,produto4));
		categoria3.getProdutos().addAll(Arrays.asList(produto5,produto6));
		categoria4.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3, produto7));
		categoria5.getProdutos().addAll(Arrays.asList(produto8));
		categoria6.getProdutos().addAll(Arrays.asList(produto9,produto10));
		categoria7.getProdutos().addAll(Arrays.asList(produto11));

		produto1.getCategorias().addAll(Arrays.asList(categoria1,categoria4));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2,categoria4));
		produto3.getCategorias().addAll(Arrays.asList(categoria1,categoria4));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));

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

		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 200.00, 2000.00, 1);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 80.00, 2);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 800.00, 1);

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));

		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5,
				categoria6, categoria7));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3,produto4,produto5, produto6, produto7, produto8,
				produto9, produto10, produto11));
		estadoRepository.saveAll(Arrays.asList(estado1, estado2, estado3));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
		
	}

}
