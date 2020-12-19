package com.hotmarket.exceptions;

public class EmptyStringException extends EException {
	
	private static final long serialVersionUID = -3143572734855734716L;
	
	public EmptyStringException(String parameter) {
		super("O valor \"" + parameter + "\" não pode estar nulo ou vazio.");
	}
	
}