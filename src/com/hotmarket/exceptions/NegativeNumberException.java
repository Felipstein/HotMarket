package com.hotmarket.exceptions;

public class NegativeNumberException extends EException {
	
	private static final long serialVersionUID = 1172694144421604192L;
	
	public NegativeNumberException(String parameter) {
		super("O valor \"" + parameter + "\" não pode ser negativo.");
	}
	
}