package com.hotmarket.exceptions;

public class NotNumberException extends EException {
	
	private static final long serialVersionUID = 2051895219831242430L;
	
	public NotNumberException(String parameter) {
		super("O valor \"" + parameter + "\" aceita apenas valores númericos.");
	}
	
}