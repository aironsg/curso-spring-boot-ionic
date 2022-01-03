package br.com.estudafacil.cursoSpringBoot.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StardardError{
	
	
	private static final long serialVersionUID = 1L;

	List<FieldMessage> errors = new ArrayList<>();
	
	
	
	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}



	public List<FieldMessage> getErrors() {
		return errors;
	}



	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	
	
	
	

	
	

}
