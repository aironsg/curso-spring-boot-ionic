package br.com.estudafacil.cursoSpringBoot.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.estudafacil.cursoSpringBoot.domain.enums.TipoCliente;
import br.com.estudafacil.cursoSpringBoot.dto.ClienteNewDTO;
import br.com.estudafacil.cursoSpringBoot.resources.exception.FieldMessage;
import br.com.estudafacil.cursoSpringBoot.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

	
	
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF está incorreto, favor verifique"));
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ está incorreto, favor verifique"));
		}
		
		for(FieldMessage message : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message.getMessage())
				   .addPropertyNode(message.getFieldName())
				   .addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
