package com.hotmarket.frames.ui.components;

import java.awt.Font;

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
		this.setFocusable(false);
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
	
	public static UILabel createLabel(String text, int x, int y, boolean bold) {
		UILabel label = new UILabel(text, x, y);
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), bold ? Font.BOLD : font.getStyle(), font.getSize()));
		if(bold) {
			label.fix(label.getTextWidth() * 2, 0);
		}
		return label;
	}
	
	public static UILabel createLabel(String text, int x, int y, boolean bold, int size) {
		UILabel label = new UILabel(text, x, y);
		Font font = label.getFont();
		label.setFont(new Font(font.getName(), bold ? Font.BOLD : font.getStyle(), size));
		return label;
	}
	
}