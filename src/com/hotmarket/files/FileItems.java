package com.hotmarket.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.hotmarket.client.items.Item;
import com.hotmarket.exceptions.NegativeNumberException;
import com.hotmarket.exceptions.ZeroNumberException;
import com.hotmarket.logger.Logger;
import com.hotmarket.utils.FileManager;

public class FileItems extends File {
	
	private static final long serialVersionUID = -4695704116521445783L;
	
	public static FileItems archive;
	
	public FileItems() {
		super("items.txt");
		this.items = new ArrayList<>();
		this.loadItems();
	}
	
	private ArrayList<Item> items;
	
	public void loadItems() {
		this.checkExistence();
		try {
			FileManager.lines(this).forEach(line -> {
				try {
					items.add(Item.getItem(line));
				} catch (NegativeNumberException | ZeroNumberException e) {
					e.log();
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			Logger.logger.error("Falha ao carregar os items do arquivo items.txt!");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		this.checkExistence();
		try {
			FileManager.appendText(this, item.toString(), true);
		} catch (IOException e) {
			Logger.logger.error("Falha ao adicionar o item \"" + item.toString() + "\" em items.txt!");
			e.printStackTrace();
		}
		this.items.add(item);
	}
	
	public void removeItem(Item item) {
		this.checkExistence();
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
		this.items.remove(item);
	}
	
	public void updateItem(Item item) {
		this.checkExistence();
		Item old = this.getItemById(item.getId());
		try {
			FileManager.setLine(this, indexOf(old), item.toString());
		} catch (IOException e) {
			Logger.logger.error("Falha ao atualizar o item \"" + old.toString() + "\" para \"" + item.toString() + "\" em items.txt!");
			e.printStackTrace();
		}
		int index = indexOf(item);
		this.items.set(index, item);
	}
	
	public int indexOf(Item item) {
		this.checkExistence();
		try {
			int index = 0;
			for(String line : FileManager.lines(this)) {
				int id = Integer.parseInt(line.split("%;%")[0]);
				if(item.getId() == id) {
					return index;
				}
				++index;
			}
		} catch (IOException e) {
			Logger.logger.error("Falha ao carregar os items do arquivo items.txt!");
			e.printStackTrace();
		}
		return -1;
	//	return getItems().indexOf(item);
	}
	
	public Item getItem(int index) {
		this.checkExistence();
		return getItems().get(index);
	}
	
	public Item getItemById(int id) {
		this.checkExistence();
		for(Item item : getItems()) {
			if(item.getId() == id) {
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