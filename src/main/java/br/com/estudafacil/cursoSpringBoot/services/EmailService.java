package br.com.estudafacil.cursoSpringBoot.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.estudafacil.cursoSpringBoot.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);

}
