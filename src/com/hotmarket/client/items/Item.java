package com.hotmarket.client.items;

import java.text.DecimalFormat;

import com.hotmarket.exceptions.NegativeNumberException;
import com.hotmarket.exceptions.ZeroNumberException;
import com.hotmarket.files.FileItems;
import com.hotmarket.frames.market.stock.StockItemsTable;

public class Item {
	
	private int id;
	
	private String name;
	
	private int amountStock;
	private float price;
	
	private float discount;
	
	private boolean tableUpdaterMode;
	private ItemList location;
	
	public Item(int id, String name, int amountStock, float price, float discount) throws NegativeNumberException, ZeroNumberException {
		if(id < 0) {
			throw new NegativeNumberException("id");
		} else if(id == 0) {
			throw new ZeroNumberException("id");
		}
		this.id = id;
		this.name = name;
		this.amountStock = amountStock;
		this.price = price;
		this.discount = discount;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		FileItems.archive.updateItem(this);
		if(tableUpdaterMode && location != null) {
			this.location.updateItem(this, name, StockItemsTable.NAME_COLUMN);
		}
	}
	
	public int getAmountStock() {
		return amountStock;
	}
	
	public void setAmountStock(int amountStock) {
		this.amountStock = amountStock;
		FileItems.archive.updateItem(this);
		if(tableUpdaterMode && location != null) {
			this.location.updateItem(this, amountStock, StockItemsTable.STOCK_COLUMN);
		}
	}
	
	public void addAmountStock(int add) {
		this.amountStock += add;
		FileItems.archive.updateItem(this);
		if(tableUpdaterMode && location != null) {
			this.location.updateItem(this, amountStock, StockItemsTable.STOCK_COLUMN);
		}
	}
	
	public void addAmountStock() {
		this.addAmountStock(1);
	}
	
	public void subtractAmountStock(int subtract) {
		this.amountStock -= subtract;
		FileItems.archive.updateItem(this);
		if(tableUpdaterMode && location != null) {
			this.location.updateItem(this, amountStock, StockItemsTable.STOCK_COLUMN);
		}
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
		if(tableUpdaterMode && location != null) {
			this.location.updateItem(this, price, StockItemsTable.PRICE_COLUMN);
		}
	}
	
	public float getDiscount() {
		return discount;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
		FileItems.archive.updateItem(this);
		if(tableUpdaterMode && location != null) {
			this.location.updateItem(this, discount, StockItemsTable.DISCOUNT_COLUMN);
		}
	}
	
	public Object[] getValues(boolean formatted) {
		DecimalFormat format = formatted ? new DecimalFormat("R$ ###,###.##") : null;
		return new Object[] {id, name, amountStock, formatted ? format.format(price) : price, formatted ? format.format(discount) : discount};
	}
	
	@Override
	public String toString() {
		return id + "%;%" + name + "%;%" + amountStock + "%;%" + price + "%;%" + discount;
	}
	
	public String toStringLittle() {
		return name + "(" + id + ")";
	}
	
	public static Item getItem(String inString) throws NegativeNumberException, ZeroNumberException {
		String[] split = inString.split("%;%");
		int id = Integer.parseInt(split[0]);
		String name = split[1];
		int amountStock = Integer.parseInt(split[2]);
		float price = Float.parseFloat(split[3]);
		float discount = Float.parseFloat(split[4]);
		return new Item(id, name, amountStock, price, discount);
	}
	
	public boolean isTableUpdaterMode() {
		return tableUpdaterMode;
	}
	
	public void startTableUpdaterMode() {
		this.tableUpdaterMode = true;
	}
	
	public void stopTableUpdaterMode() {
		this.tableUpdaterMode = false;
	}
	
	public ItemList getLocation() {
		return location;
	}
	
	public boolean hasLocation() {
		return location != null;
	}
	
	public void setLocation(ItemList location) {
		this.location = location;
	}
	
}