package com.hotmarket.client;

public class Cashier {
	
	private int number;
	private String name;
	
	public Cashier(int number, String name) {
		if(number < 0) {
			throw new IllegalArgumentException("O número do caixa não pode ser menor que zero (< 0).");
		}
		this.number = number;
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}
	
	public boolean hasName() {
		return name != null;
	}
	
}