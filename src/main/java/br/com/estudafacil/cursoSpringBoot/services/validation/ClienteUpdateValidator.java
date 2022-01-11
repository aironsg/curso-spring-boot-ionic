package br.com.estudafacil.cursoSpringBoot.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.estudafacil.cursoSpringBoot.domain.Cliente;
import br.com.estudafacil.cursoSpringBoot.dto.ClienteDTO;
import br.com.estudafacil.cursoSpringBoot.repositories.ClienteRepository;
import br.com.estudafacil.cursoSpringBoot.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

	
	@Autowired
	ClienteRepository repo;
	
	@Autowired
	HttpServletRequest request;
	
	public void initialize(ClienteUpdate ann) {
		
	}
	
	
	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
	
		Cliente email = repo.findByEmail(objDTO.getEmail());
		if(email != null && !email.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}
		
		
		
		for(FieldMessage message : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message.getMessage())
			.addPropertyNode(message.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
