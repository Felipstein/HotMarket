package com.hotmarket.frames.market.stock;

import java.awt.Dimension;

import com.hotmarket.Main;
import com.hotmarket.client.items.ItemList;
import com.hotmarket.frames.ui.UIFrame;

public class StockFrame extends UIFrame {
	
	private static final long serialVersionUID = -2544785973104601659L;
	
	public final StockTopPanel topPanel;
	public final StockBottomPanel bottomPanel;
	
	public StockFrame() {
		super("Tabela dos itens", 1080, 600);
		this.configureFrame(true, false);
		this.topPanel = new StockTopPanel(this);
		this.bottomPanel = new StockBottomPanel(this);
		this.configureComponents();
		this.setMinimumSize(new Dimension(400, 350));
	}
	
	public ItemList getItems() {
		return bottomPanel.getTable().getItems();
	}
	
	private void configureComponents() {
		this.pane.addPanel("top", topPanel);
		this.pane.addPanel("bottom", bottomPanel);
	}
	
	@Override
	public void onClosing() {
		Main.shutdown();
	}
	
	public static StockFrame open() {
		StockFrame frame = new StockFrame();
		frame.setVisible(true);
		frame.bottomPanel.getTable().requestFocus();
		return frame;
	}
	
}