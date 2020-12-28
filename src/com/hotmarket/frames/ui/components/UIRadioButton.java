package com.hotmarket.frames.ui.components;

import javax.swing.JRadioButton;

import com.hotmarket.frames.recicle.ConsumerAction;

public class UIRadioButton extends JRadioButton {
	
	private static final long serialVersionUID = -4613963911246408789L;
	
	public UIRadioButton(String text, int x, int y) {
		super(text);
		this.setBounds(x, y, new UILabel(text, 0 , 0).getTextWidth() + 25, UILabel.DEFAULT_HEIGHT);
	}
	
	public void addHeightFix(int heightFix) {
		this.setBounds(getX(), getY(), getWidth(), getHeight() + heightFix);
	}
	
	public void addActionListener(ConsumerAction action) {
		super.addActionListener(action);
	}
	
}