package com.hotmarket.exceptions;

public class ItemNonexistsException extends EException {
	
	private static final long serialVersionUID = -6956684272558613267L;
	
	public ItemNonexistsException(int id) {
		super("O item do ID \"" + id + "\" não foi encontrado ou não existe.");
	}
	
}