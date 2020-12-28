package com.hotmarket.frames.market.stock;

import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;

import com.hotmarket.client.items.ItemList;
import com.hotmarket.files.FileItems;
import com.hotmarket.frames.recicle.InputListener;
import com.hotmarket.frames.ui.UIPanel;

public class StockBottomPanel extends UIPanel {
	
	private static final long serialVersionUID = -1007963820901741667L;
	
	private JScrollPane scrollPane;
	private StockItemsTable mainTable;
	
	public StockBottomPanel(StockFrame frame) {
		super(frame, 5, frame.topPanel.getHeight() + 10, frame.getContentPane().getWidth() - 10, frame.getContentPane().getHeight() - frame.topPanel.getHeight() - 15);
		this.configurePanel();
		this.setPerfectBorder("Itens");
		this.configureComponents();
	}
	
	private void configureComponents() {
		this.mainTable = new StockItemsTable(this, new ItemList(FileItems.archive.getItems(), true));
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 15, this.getWidth() - 20, this.getHeight() - 25);
		this.scrollPane.setViewportView(mainTable);
		this.scrollPane.addMouseListener(new InputListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == 3) {
					mainTable.getPopupMenu(false, false).show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		this.addComponent("tablescroll", scrollPane);
	}
	
	@Override
	public void onResized(boolean domain) {
		if(domain) {
			this.setBounds(5, getFrame().topPanel.getHeight() + 10, getFrame().getContentPane().getWidth() - 10, getFrame().getContentPane().getHeight() - getFrame().topPanel.getHeight() - 15);
		} else {
			this.scrollPane.setBounds(10, 15, this.getWidth() - 20, this.getHeight() - 25);
		}
	}
	
	public void setTable(StockItemsTable table) {
		this.scrollPane.setViewportView(table);
	}
	
	public void setDefaultTable() {
		this.scrollPane.setViewportView(this.mainTable);
	}
	
	public StockItemsTable getTable() {
		return mainTable;
	}
	
	@Override
	public StockFrame getFrame() {
		return (StockFrame) super.getFrame();
	}
	
}