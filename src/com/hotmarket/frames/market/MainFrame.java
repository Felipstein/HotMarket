package com.hotmarket.frames.market;

import java.awt.Dimension;

import com.hotmarket.frames.ui.UIFrame;

public class MainFrame extends UIFrame {
	
	private static final long serialVersionUID = 5584477235691892633L;
	
	public MainFrame() {
		super("HotMarket", new Dimension(800, 600));
		this.configureFrame(true, true);
		this.configureComponents();
	}
	
	private void configureComponents() {
		
	}
	
}