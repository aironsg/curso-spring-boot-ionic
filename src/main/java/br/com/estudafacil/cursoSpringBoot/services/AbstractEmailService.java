package br.com.estudafacil.cursoSpringBoot.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudafacil.cursoSpringBoot.domain.Pedido;

@RestController
public abstract class AbstractEmailService implements EmailService {
	
//	@Value("${default.sender}")
//	private String sender;
	
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimPleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimPleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom("airon_gm@hotmail.com");
		sm.setSubject("Pedido Confirmado! Código: " + obj.getId());
		sm.setText(obj.toString());
		return sm;
	}
	
	
	
	

}
