package com.hotmarket.frames.market.stock;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.hotmarket.client.items.Item;
import com.hotmarket.client.items.ItemList;
import com.hotmarket.frames.ui.components.UITable;

public class StockItemsTable extends UITable {
	
	private static final long serialVersionUID = 6642035736361968453L;
	
	public static final int ID_COLUMN = 0;
	public static final int NAME_COLUMN = 1;
	public static final int STOCK_COLUMN = 2;
	public static final int PRICE_COLUMN = 3;
	public static final int DISCOUNT_COLUMN = 4;
	
	private final StockBottomPanel panel;
	
	private DefaultTableModel model;
	private JScrollPane scroll;
	
	private ItemList items;
	
	public StockItemsTable(StockBottomPanel panel, JScrollPane scroll, ItemList items) {
		super(10, 10, panel.getWidth() - 20, panel.getHeight() - 20);
		this.panel = panel;
		this.model = this.createTableModel();
		this.items = items;
		this.setModel(model);
		this.scroll = scroll;
		this.scroll.setViewportView(this);
		this.scroll.setBounds(10, 15, panel.getWidth() - 20, panel.getHeight() - 25);
		this.cellEditable = false;
		this.selectionBackground = new Color(180, 239, 255);
		this.selectionForeground = Color.black;
		this.setFont(new Font("Arial", 0, 11));
		this.resizeColumns();
		this.loadItems();
		items.setTable(this);
	}
	
	private void resizeColumns() {
		this.setAutoResizeMode(AUTO_RESIZE_OFF);
		this.tableHeader.setResizingColumn(this.getColumnModel().getColumn(0));
		this.getColumnModel().getColumn(0).setWidth(100);
		this.tableHeader.setResizingColumn(this.getColumnModel().getColumn(1));
		this.getColumnModel().getColumn(1).setWidth(575);
		this.tableHeader.setResizingColumn(this.getColumnModel().getColumn(2));
		this.getColumnModel().getColumn(2).setWidth(125);
		this.tableHeader.setResizingColumn(this.getColumnModel().getColumn(3));
		this.getColumnModel().getColumn(3).setWidth(125);
		this.tableHeader.setResizingColumn(this.getColumnModel().getColumn(4));
		this.getColumnModel().getColumn(4).setWidth(125);
	}
	
	private void loadItems() {
		this.items.getItems().forEach(item -> {
			this.model.addRow(item.getValues(true));
		});
	}
	
	private DefaultTableModel createTableModel() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new Object[] {"ID", "Nome", "Quantidade", "Preço", "Desconto"});
		this.getTableHeader().setReorderingAllowed(false);
		return model;
	}
	
	public void onPanelResize() {
		this.scroll.setBounds(10, 15, panel.getWidth() - 20, panel.getHeight() - 25);
	}
	
	public ItemList getItems() {
		return items;
	}
	
	public JScrollPane getScroll() {
		return scroll;
	}
	
	@Override
	public DefaultTableModel getModel() {
		return model;
	}
	
	public Item getItemInRow(int row) {
		int id = (int) this.getValueAt(row, 0);
		return items.getItem(id);
	}
	
	public int getRowOfItem(Item item) {
		for(int row = 0; row < getRowCount(); ++row) {
			int id = (int) this.getValueAt(row, 0);
			if(id == item.getId()) {
				return row;
			}
		}
		return -1;
	}
	
}