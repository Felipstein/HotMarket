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
		if(this.name != null) {
			this.name = this.name.trim();
			if(this.name.isEmpty()) {
				this.name = null;
			}
		}
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
	
	@Override
	public String toString() {
		return "Caixa " + number + (name != null ? ", Atendente " + name : "");
	}
	
}