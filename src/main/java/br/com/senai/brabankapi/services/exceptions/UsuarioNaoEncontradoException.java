package br.com.senai.brabankapi.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 4065888956642754924L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	

}
