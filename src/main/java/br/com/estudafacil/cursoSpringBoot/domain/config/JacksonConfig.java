package br.com.estudafacil.cursoSpringBoot.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.estudafacil.cursoSpringBoot.domain.PagamentoComBoleto;
import br.com.estudafacil.cursoSpringBoot.domain.PagamentoComCartao;

@Configuration
public class JacksonConfig {
	// caso estiver com duvidas
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of-interfaceclass-without-hinting-the-pare

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				super.configure(objectMapper);
			}

		};

		return builder;
	}
	
	@Bean
	public JavaMailSender jMS (){
		return new JavaMailSenderImpl();
	}

}
