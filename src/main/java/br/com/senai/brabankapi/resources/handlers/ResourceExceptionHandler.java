package br.com.senai.brabankapi.resources.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.senai.brabankapi.services.exceptions.UsuarioNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<Void> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e,
			HttpServletRequest request) {
		
		e.printStackTrace();
		
		return ResponseEntity.notFound().build();
	}
	
}
