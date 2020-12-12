package com.hotmarket.frames.market.main;

import java.awt.Dimension;

import com.hotmarket.Main;
import com.hotmarket.client.Cashier;
import com.hotmarket.frames.ui.UIFrame;

public class MainFrame extends UIFrame {
	
	private static final long serialVersionUID = 5584477235691892633L;
	
	private static MainFrame frame;
	
	private Cashier cashier;
	
	public MainFrame(Cashier cashier) {
		super("HotMarket", new Dimension(800, 400));
		this.cashier = cashier;
		this.configureFrame(true, true);
		this.configureComponents();
		frame = this;
	}
	
	private void configureComponents() {
		
	}
	
	@Override
	public void onClosing() {
		Main.shutdown();
	}
	
	public Cashier getCashier() {
		return cashier;
	}
	
	public static MainFrame getFrame() {
		return frame;
	}
	
}