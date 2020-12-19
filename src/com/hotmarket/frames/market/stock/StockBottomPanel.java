package com.hotmarket.frames.market.stock;

import javax.swing.JScrollPane;

import com.hotmarket.client.items.ItemList;
import com.hotmarket.files.FileItems;
import com.hotmarket.frames.ui.UIPanel;

public class StockBottomPanel extends UIPanel {
	
	private static final long serialVersionUID = -1007963820901741667L;
	
	private StockItemsTable table;
	
	public StockBottomPanel(StockFrame frame) {
		super(frame, 5, frame.topPanel.getHeight() + 10, frame.getContentPane().getWidth() - 10, frame.getContentPane().getHeight() - frame.topPanel.getHeight() - 15);
		this.configurePanel();
		this.setPerfectBorder("Itens");
		this.configureComponents();
	}
	
	private void configureComponents() {
		this.table = new StockItemsTable(this, new JScrollPane(), new ItemList(FileItems.archive.getItems(), true));
		this.addComponent("tablescroll", table.getScroll());
	}
	
	@Override
	public void onResized(boolean domain) {
		if(domain) {
			this.setBounds(5, getFrame().topPanel.getHeight() + 10, getFrame().getContentPane().getWidth() - 10, getFrame().getContentPane().getHeight() - getFrame().topPanel.getHeight() - 15);
		} else {
			this.table.onPanelResize();
		}
	}
	
	public StockItemsTable getTable() {
		return table;
	}
	
	@Override
	public StockFrame getFrame() {
		return (StockFrame) super.getFrame();
	}
	
}