package com.hotmarket.frames.ui.components;

import javax.swing.JLabel;

import com.hotmarket.frames.ui.UIPanel;

public class UILabel extends JLabel {
	
	public static final int DEFAULT_HEIGHT = 14;
	
	private static final long serialVersionUID = -5393798703695652827L;
	
	private UIPanel panel;
	
	private int initX, initY;
	
	public UILabel(UIPanel panel, String text, int x, int y) {
		super(text);
		this.panel = panel;
		this.initX = x;
		this.initY = y;
		this.setBounds(x, y, getTextWidth(), DEFAULT_HEIGHT);
	}
	
	public UILabel(String text, int x, int y) {
		this(null, text, x, y);
	}
	
	public void putCentered(boolean centeredWidth, boolean centeredHeight) {
		int x = centeredWidth ? this.initX - (getWidth() / 2) : this.initX;
		int y = centeredHeight ? this.initY - (getHeight() / 2) : this.initY;
		this.setBounds(x, y, getWidth(), getHeight());
	}
	
	public void fix(int width, int height) {
		this.setBounds(getX(), getY(), getWidth() + width, getHeight() + height);
	}
	
	public int getTextWidth() {
		return this.getFontMetrics(this.getFont()).stringWidth(this.getText());
	}
	
	public UIPanel getPanel() {
		return panel;
	}
	
}