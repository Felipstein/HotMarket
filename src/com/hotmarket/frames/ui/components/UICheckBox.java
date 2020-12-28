package com.hotmarket.frames.ui.components;

import javax.swing.JCheckBox;

import com.hotmarket.frames.recicle.ConsumerAction;

public class UICheckBox extends JCheckBox {
	
	private static final long serialVersionUID = -7455671332873947419L;
	
	public UICheckBox(String text, int x, int y) {
		super(text);
		this.setBounds(x, y, new UILabel(text, 0, 0).getTextWidth() + 25, UILabel.DEFAULT_HEIGHT);
	}
	
	public void addHeightFix(int heightFix) {
		this.setBounds(getX(), getY(), getWidth(), getHeight() + heightFix);
	}
	
	public void addActionListener(ConsumerAction action) {
		super.addActionListener(action);
	}
	
}