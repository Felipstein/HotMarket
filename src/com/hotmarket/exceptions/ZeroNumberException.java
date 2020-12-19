package com.hotmarket.exceptions;

public class ZeroNumberException extends EException {
	
	private static final long serialVersionUID = -5448033370345078183L;
	
	public ZeroNumberException(String parameter) {
		super("O valor \"" + parameter + "\" deve ser um número diferente de zero.");
	}
	
}