package br.com.estudafacil.cursoSpringBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudafacil.cursoSpringBoot.domain.Pedido;
import br.com.estudafacil.cursoSpringBoot.repositories.PedidoRepository;
import br.com.estudafacil.cursoSpringBoot.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido findPedido(Integer id) {
		Optional<Pedido> objPedido = repo.findById(id);
		return objPedido.orElseThrow(() -> new ObjectNotFoundException("O objeto não foi encontrado" +
		id + ", Tipo : " + Pedido.class.getName()));
		
	}
	
	

}
