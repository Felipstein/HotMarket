package com.hotmarket.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.hotmarket.Item;
import com.hotmarket.logger.Logger;
import com.hotmarket.utils.FileManager;

public class FileItems extends File {
	
	private static final long serialVersionUID = -4695704116521445783L;
	
	public static FileItems archive;
	
	public FileItems() {
		super("items.txt");
	}
	
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<>();
		try {
			FileManager.lines(this).forEach(line -> items.add(Item.getItem(line)));
		} catch (IOException e) {
			Logger.logger.error("Falha ao carregar os items do arquivo items.txt!");
			e.printStackTrace();
			return null;
		}
		return items;
	}
	
	public void addItem(Item item) {
		try {
			FileManager.appendText(this, item.toString(), true);
		} catch (IOException e) {
			Logger.logger.error("Falha ao adicionar o item \"" + item.toString() + "\" em items.txt!");
			e.printStackTrace();
		}
	}
	
	public void removeItem(Item item) {
		int index = indexOf(item);
		if(index == -1) {
			return;
		}
		try {
			FileManager.removeLine(this, index);
		} catch (IOException e) {
			Logger.logger.error("Falha ao remover o item \"" + item.toString() + "\" de items.txt!");
			e.printStackTrace();
		}
	}
	
	public void updateItem(Item item) {
		Item old = this.getItemByOrder(item.getOrder());
		try {
			FileManager.setLine(this, indexOf(old), item.toString());
		} catch (IOException e) {
			Logger.logger.error("Falha ao atualizar o item \"" + old.toString() + "\" para \"" + item.toString() + "\" em items.txt!");
			e.printStackTrace();
		}
	}
	
	public int indexOf(Item item) {
		return getItems().indexOf(item);
	}
	
	public Item getItem(int index) {
		return getItems().get(index);
	}
	
	public Item getItemByOrder(int order) {
		for(Item item : getItems()) {
			if(item.getOrder() == order) {
				return item;
			}
		}
		return null;
	}
	
	public boolean checkExistence() {
		if(exists()) {
			return true;
		}
		try {
			this.createNewFile();
		} catch (IOException e) {
			Logger.logger.error("Falha ao verificar a existência do arquivo " + getPath() + "!");
			e.printStackTrace();
		}
		return false;
	}
	
}