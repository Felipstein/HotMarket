package com.hotmarket.frames.market.login;

import java.awt.Dimension;

import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.components.UIButton;

public class LoginFrame extends UIFrame {
	
	private static final long serialVersionUID = -2514741390990464871L;
	
	private DataLoginPanel dataLoginPanel;
	
	public LoginFrame() {
		super("Login", new Dimension(300, 200));
		this.configureFrame(false, false);
		this.exitOnClose();
		this.configureElements();
	}
	
	private void configureElements() {
		this.dataLoginPanel = (DataLoginPanel) this.getContentPane().addPanel("datalogin", new DataLoginPanel(this));
		
		UIButton b1 = new UIButton("Entrar", 10, this.getHeight() - 30, 200, 25);
		
		this.getContentPane().addComponent("b1", b1);
	}
	
	public DataLoginPanel getDataLoginPanel() {
		return dataLoginPanel;
	}
	
}