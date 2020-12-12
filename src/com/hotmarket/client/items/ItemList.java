package com.hotmarket.client.items;

import java.util.ArrayList;
import java.util.List;

import com.hotmarket.files.FileItems;

public class ItemList {
	
	private List<Item> items;
	
	public ItemList() {
		this.items = new ArrayList<>();	
	}
	
	public Item addItem(String id, String name, int amountStock, float price) {
		return this.addItem(getFreeOrder(), id, name, amountStock, price);
	}
	
	public Item addItem(int order, String id, String name, int amountStock, float price) {
		Item item = new Item(order, id, name, amountStock, price);
		this.items.add(item);
		FileItems.archive.addItem(item);
		return item;
	}
	
	public void removeItem(int order) {
		this.removeItem(getItem(order));
	}
	
	public void removeItem(String id) {
		this.removeItem(getItem(id));
	}
	
	public void removeItem(Item item) {
		this.items.remove(item);
		FileItems.archive.removeItem(item);
	}
	
	public Item getItem(int order) {
		for(Item item : items) {
			if(item.getOrder() == order) {
				return item;
			}
		}
		return null;
	}
	
	public boolean hasItem(int order) {
		return getItem(order) != null;
	}
	
	public Item getItem(String id) {
		for(Item item : items) {
			if(item.getId().equalsIgnoreCase(id)) {
				return item;
			}
		}
		return null;
	}
	
	public boolean hasItem(String id) {
		return getItem(id) != null;
	}
	
	public List<Item> getItems(String name) {
		List<Item> items = new ArrayList<>();
		for(Item item : items) {
			if(item.getName().equalsIgnoreCase(name)) {
				items.add(item);
			}
			if(item.getName().toLowerCase().contains(name.toLowerCase())) {
				items.add(item);
			}
		}
		return items;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public int getFreeOrder() {
		int order = 0;
		while(getItem(order) != null) {
			++order;
		}
		return order;
	}
	
}