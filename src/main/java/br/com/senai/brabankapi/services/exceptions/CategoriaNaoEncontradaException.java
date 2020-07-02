package br.com.senai.brabankapi.services.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 4065888956642754924L;

	public CategoriaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	

}
