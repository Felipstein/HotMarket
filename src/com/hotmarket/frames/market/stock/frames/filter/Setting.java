package com.hotmarket.frames.market.stock.frames.filter;

public class Setting<T> {
	
	public static enum NameSearchOptions {CONTAINS, STARTS_WITH, EQUALS}
	public static enum SortingOptions {ID_INC, ID_DEC, ALPHABETICAL_ORDER, STOCK_INC, STOCK_DEC, PRICE_INC, PRICE_DEC, DISCOUNT_INC, DISCOUNT_DEC}
	
	public static enum ComponentType {TEXT_FIELD, RADIO_BUTTON, CHECK_BOX}
	
	private boolean ignoreValue;
	
	private T value;
	private ComponentType type;
	
	public Setting(ComponentType type) {
		this.type = type;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	public ComponentType getType() {
		return type;
	}
	
	public boolean ignoreValue() {
		return ignoreValue;
	}
	
	public void setIgnoreValue(boolean ignoreValue) {
		this.ignoreValue = ignoreValue;
	}
	
}