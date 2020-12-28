package com.hotmarket.frames.market.stock;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;

import com.hotmarket.client.items.Item;
import com.hotmarket.client.items.ItemList;
import com.hotmarket.frames.market.stock.frames.StockAddItemFrame;
import com.hotmarket.frames.market.stock.frames.StockEditItemFrame;
import com.hotmarket.frames.market.stock.frames.filter.StockFilterFrame;
import com.hotmarket.frames.optionpanes.JOptionPanesUtil;
import com.hotmarket.frames.ui.components.UITable;
import com.hotmarket.logger.Logger;

public class StockItemsTable extends UITable implements KeyListener, MouseListener {
	
	private static final long serialVersionUID = 6642035736361968453L;
	
	public static final int ID_COLUMN = 0;
	public static final int NAME_COLUMN = 1;
	public static final int STOCK_COLUMN = 2;
	public static final int PRICE_COLUMN = 3;
	public static final int DISCOUNT_COLUMN = 4;
	
	private final StockBottomPanel panel;
	
	private DefaultTableModel model;
	
	private ItemList items;
	
	private long lastClick;
	
	public StockItemsTable(StockBottomPanel panel, ItemList items) {
		super(10, 10, panel.getWidth() - 20, panel.getHeight() - 20);
		this.panel = panel;
		this.model = this.createTableModel();
		this.items = items;
		this.items.getItems().forEach(item -> item.setLocation(this.items));
		this.setModel(model);
		this.cellEditable = false;
		this.selectionBackground = new Color(180, 239, 255);
		this.selectionForeground = Color.black;
		this.setFont(new Font("Arial", 0, 11));
		this.loadItems();
		items.setTable(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.resizeColumns();
	}
	
	JPopupMenu getPopupMenu(boolean editEnabled, boolean delEnabled) {
		JPopupMenu menu = new JPopupMenu();
		JMenuItem edit = new JMenuItem("Editar");
		edit.setEnabled(panel.getFrame().isFilterMode() ? false : editEnabled);
		JMenuItem del = new JMenuItem("Deletar");
		del.setEnabled(panel.getFrame().isFilterMode() ? false : delEnabled);
		JMenuItem add = new JMenuItem("Adicionar");
		add.setEnabled(panel.getFrame().isFilterMode() ? false : true);
		JMenuItem filter = new JMenuItem("Filtrar");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StockEditItemFrame(panel.getFrame(), getItemInRow(getSelectedRow())).setVisible(true);
			}
		});
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StockItemsTable.this.removeItem(getSelectedRow(), true);
			}
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StockAddItemFrame(panel.getFrame()).setVisible(true);
			}
		});
		filter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StockFilterFrame(panel.getFrame()).setVisible(true);
			}
		});
		JSeparator s = new JSeparator();
		this.panel.getFrame().topPanel.componentsToToggle.add(s);
		menu.add(edit);
		menu.add(del);
		menu.add(s);
		menu.add(add);
		menu.add(filter);
		return menu;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(getSelectedRows().length > 0) {
			if(e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				this.removeItems(getSelectedRows(), true);
			} else if(e.getKeyCode() == KeyEvent.VK_ENTER && getSelectedRows().length == 1) {
				new StockEditItemFrame(panel.getFrame(), getItemInRow(getSelectedRow())).setVisible(true);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void resizeColumns() {
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
	
/*	public void resizeColumns() {
		this.setAutoResizeMode(AUTO_RESIZE_OFF);
		for(int i = 0; i < getColumnCount(); i++) {
			TableColumn column = columnModel.getColumn(i);
			if(!column.getResizable()) {
				continue;
			}
			Object value = column.getHeaderValue();
			TableCellRenderer cellRenderer = column.getCellRenderer() == null ? 
					tableHeader.getDefaultRenderer() : column.getCellRenderer();
			int headerWidth = cellRenderer.getTableCellRendererComponent(this, value, false, false, -1, i).getPreferredSize().width;
			int rowWidth = 0;
			int maxWidth = column.getMaxWidth();
			for(int j = 0; j < getRowCount(); j++) {
				TableCellRenderer cellRenderer2 = getCellRenderer(j, i);
				Component component = prepareRenderer(cellRenderer2, j, i);
				rowWidth = rowWidth > component.getPreferredSize().width + getIntercellSpacing().width ? rowWidth : component.getPreferredSize().width + getIntercellSpacing().width;
				if(rowWidth >= maxWidth) {
					break;
				}
			}
			int preferredWidth = (headerWidth > rowWidth ? headerWidth : rowWidth) + 6;
			tableHeader.setResizingColumn(column);
			column.setWidth(preferredWidth);
		}
	} */
	
	private void loadItems() {
		this.items.getItems().forEach(item -> {
			this.model.addRow(item.getValues(true));
		});
	}
	
	private DefaultTableModel createTableModel() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new Object[] {"ID", "Nome", "Quantidade", "Preço (R$)", "Desconto (R$)"});
		this.getTableHeader().setReorderingAllowed(false);
		return model;
	}
	
	public ItemList getItems() {
		return items;
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
	
	public void removeItem(int rowOfItem, boolean confirm) {
		this.removeItems(new int[] {rowOfItem}, confirm);
	}
	
	public void removeItems(int[] rowsOfItems, boolean confirm) {
		for(int i = rowsOfItems.length - 1; i >= 0; --i) {
			Item item = getItemInRow(rowsOfItems[i]);
			if(confirm && JOptionPanesUtil.areYouOkBro("Você tem certeza que deseja remover o item " + item.toStringLittle() + " ?", "Remover " + item.getName()) == 1) {
				continue;
			}
			this.getItems().removeItem(item);
			Logger.logger.info("Item " + item.toStringLittle() + " removido com êxito.");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			if(System.currentTimeMillis() <= lastClick + 150) {
				new StockEditItemFrame(panel.getFrame(), getItemInRow(getSelectedRow())).setVisible(true);
			}
			this.lastClick = System.currentTimeMillis();
		} else if (e.getButton() == 3) {
			boolean selected = getSelectedRows().length > 0;
			this.getPopupMenu(selected, selected).show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
}