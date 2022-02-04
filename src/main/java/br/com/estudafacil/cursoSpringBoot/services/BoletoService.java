package br.com.estudafacil.cursoSpringBoot.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.estudafacil.cursoSpringBoot.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instanteDoPedido) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(instanteDoPedido);
		calendario.add(Calendar.MONTH, 7);
		pagamentoComBoleto.setDataPagamento(calendario.getTime());
	}

}
