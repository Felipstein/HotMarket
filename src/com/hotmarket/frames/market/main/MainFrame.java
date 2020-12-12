package com.hotmarket.frames.market.main;

import java.awt.Dimension;

import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.logger.Logger;

public class MainFrame extends UIFrame {
	
	private static final long serialVersionUID = 5584477235691892633L;
	
	public MainFrame() {
		super("HotMarket", new Dimension(800, 400));
		this.configureFrame(true, true);
		this.configureComponents();
	}
	
	private void configureComponents() {
		
	}
	
	@Override
	public void onClosing() {
		Logger.logger.info("Finalizando...");
		System.exit(0);
	}
	
}