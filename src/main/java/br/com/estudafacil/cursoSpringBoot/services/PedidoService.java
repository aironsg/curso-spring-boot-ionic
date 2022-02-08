package br.com.estudafacil.cursoSpringBoot.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudafacil.cursoSpringBoot.domain.ItemPedido;
import br.com.estudafacil.cursoSpringBoot.domain.PagamentoComBoleto;
import br.com.estudafacil.cursoSpringBoot.domain.Pedido;
import br.com.estudafacil.cursoSpringBoot.domain.enums.EstadoPagamento;
import br.com.estudafacil.cursoSpringBoot.repositories.ItemPedidoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.PagamentoRepository;
import br.com.estudafacil.cursoSpringBoot.repositories.PedidoRepository;
import br.com.estudafacil.cursoSpringBoot.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> objPedido = repo.findById(id);
		return objPedido.orElseThrow(() -> new ObjectNotFoundException("O objeto não foi encontrado" +
		id + ", Tipo : " + Pedido.class.getName()));
		
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreço());
			ip.setPedido(obj);
		}
		System.out.println(obj);
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}
	
	
	

}
