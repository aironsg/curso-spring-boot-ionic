package br.com.estudafacil.cursoSpringBoot.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.estudafacil.cursoSpringBoot.services.exception.DataIntegrityException;
import br.com.estudafacil.cursoSpringBoot.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StardardError> objectNotFound(ObjectNotFoundException e,
														HttpServletRequest request){
		
		StardardError error = new StardardError(HttpStatus.NOT_FOUND.value(),e.getMessage() , System.currentTimeMillis());
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StardardError> dataIntegrity(DataIntegrityException e,
			HttpServletRequest request){
		
		StardardError error = new StardardError(HttpStatus.BAD_REQUEST.value(),e.getMessage() , System.currentTimeMillis());
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
