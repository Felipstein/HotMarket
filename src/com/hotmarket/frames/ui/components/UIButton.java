package com.hotmarket.frames.ui.components;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hotmarket.frames.ui.UIPanel;

public abstract class UIButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = -6914147620239198331L;
	
	private UIPanel panel;
	
	public UIButton(UIPanel panel, String text, int x, int y, int width, int height) {
		super(text);
		this.panel = panel;
		this.setBounds(x, y, width, height);
		this.addActionListener(this);
	}
	
	public UIButton(String text, int x, int y, int width, int height) {
		this(null, text, x, y, width, height);
	}
	
	public UIPanel getPanel() {
		return panel;
	}
	
	public boolean hasPanel() {
		return panel != null;
	}
	
}