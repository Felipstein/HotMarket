package com.hotmarket.client.items;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.hotmarket.exceptions.NegativeNumberException;
import com.hotmarket.exceptions.ZeroNumberException;
import com.hotmarket.files.FileItems;
import com.hotmarket.frames.market.stock.StockItemsTable;

public class ItemList {
	
	private StockItemsTable table;
	
	private List<Item> items;
	private boolean archiveModifier;
	
	public ItemList(boolean archiveModifier) {
		this(new ArrayList<>(), archiveModifier);
	}
	
	public ItemList(List<Item> items, boolean archiveModifier) {
		this.items = items == null ? new ArrayList<>() : items;
		this.archiveModifier = archiveModifier;
	}
	
	public Item addItem(String name, int amountStock, float price, float discount) {
		return this.addItem(getFreeId(), name, amountStock, price, discount);
	}
	
	public int addItemGetId(String name, int amountStock, float price, float discount) {
		return this.addItem(getFreeId(), name, amountStock, price, discount).getId();
	}
	
	public Item addItem(int id, String name, int amountStock, float price, float discount) {
		Item item;
		try {
			item = new Item(id, name, amountStock, price, discount);
		} catch (NegativeNumberException | ZeroNumberException e) {
			e.printToClient();
			return null;
		}
		item.setLocation(this);
		this.items.add(item);
		if(archiveModifier) {
			FileItems.archive.addItem(item);
		}
		if(table != null) {
			this.table.getModel().addRow(item.getValues(true));
		}
		return item;
	}
	
	public void addItem(Item item) {
		item.setLocation(this);
		this.items.add(item);
		if(archiveModifier) {
			FileItems.archive.addItem(item);
		}
		if(table != null) {
			this.table.getModel().addRow(item.getValues(true));
		}
	}
	
	public void updateItem(Item item, Object modifiedTo, int currentColumn) {
		int id = item.getId();
		int indexOf = this.items.indexOf(getItem(id));
		int row = this.table.getRowOfItem(getItem(id));
		this.items.set(indexOf, item);
		if(table != null) {
			if(currentColumn == StockItemsTable.PRICE_COLUMN || currentColumn == StockItemsTable.DISCOUNT_COLUMN) {
				modifiedTo = new DecimalFormat("R$ ###,###.##").format((float) modifiedTo);
			}
			this.table.getModel().setValueAt(modifiedTo, row, currentColumn);
		}
	}
	
	public void removeItem(int id) {
		this.removeItem(getItem(id));
	}
	
	public void removeItem(Item item) {
		this.items.remove(item);
		if(archiveModifier) {
			FileItems.archive.removeItem(item);
		}
		if(table != null) {
			this.table.getModel().removeRow(table.getRowOfItem(item));
			this.table.resizeColumns();
		}
	}
	
	public Item getItem(int id) {
		for(Item item : items) {
			if(item.getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public boolean hasItem(int id) {
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
	
	public int getFreeId() {
		int id = 1;
		while(getItem(id) != null) {
			++id;
		}
		return id;
	}
	
	public boolean isArchiveModifier() {
		return archiveModifier;
	}
	
	public boolean hasTable() {
		return table != null;
	}
	
	public void setTable(StockItemsTable table) {
		this.table = table;
	}
	
	public StockItemsTable getTable() {
		return table;
	}
	
}