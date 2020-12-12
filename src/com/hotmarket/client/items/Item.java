package com.hotmarket.client.items;

import com.hotmarket.files.FileItems;

public class Item {
	
	private int order;
	private String id;
	
	private String name;
	
	private int amountStock;
	private float price;
	
	public Item(int order, String id, String name, int amountStock, float price) {
		this.order = order;
		this.id = id;
		this.name = name;
		this.amountStock = amountStock;
		this.price = price;
	}
	
	public int getOrder() {
		return order;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		FileItems.archive.updateItem(this);
	}
	
	public int getAmountStock() {
		return amountStock;
	}
	
	public void setAmountStock(int amountStock) {
		this.amountStock = amountStock;
		FileItems.archive.updateItem(this);
	}
	
	public void addAmountStock(int add) {
		this.amountStock += add;
		FileItems.archive.updateItem(this);
	}
	
	public void addAmountStock() {
		this.addAmountStock(1);
	}
	
	public void subtractAmountStock(int subtract) {
		this.amountStock -= subtract;
		FileItems.archive.updateItem(this);
	}
	
	public void subtractAmountStock() {
		this.subtractAmountStock(1);
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
		FileItems.archive.updateItem(this);
	}
	
	@Override
	public String toString() {
		return order + "%;%" + id + "%;%" + name + "%;%" + amountStock + "%;%" + price;
	}
	
	public static Item getItem(String inString) {
		String[] split = inString.split("%;%");
		int order = Integer.parseInt(split[0]);
		String id = split[1];
		String name = split[2];
		int amountStock = Integer.parseInt(split[4]);
		float price = Float.parseFloat(split[5]);
		return new Item(order, id, name, amountStock, price);
	}
	
}