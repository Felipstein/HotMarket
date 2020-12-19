package com.hotmarket.frames.ui.components;

import com.hotmarket.frames.ui.UIPanel;

public class UITextFieldWithLabel extends UITextField {
	
	private static final long serialVersionUID = -7273466408566002123L;
	
	private final String LABEL_ID;
	private UILabel label;
	
	private boolean below;
	private int space;
	
	{
		this.space = 2;
	}
	
	public UITextFieldWithLabel(UIPanel panel, String labelText, boolean below, String initialText, int x, int y, int width, int height) {
		super(panel, initialText, x, y, width, height);
		if(below) {
			this.label = new UILabel(labelText, x, y);
			this.setBounds(x, y + UILabel.DEFAULT_HEIGHT + 1, width, height);
		} else {
			this.label = new UILabel(labelText, x, y + height / 2);
			this.label.putCentered(false, true);
			this.setBounds(x + label.getWidth() + space, y, width - label.getWidth() - 1, height);
		}
		this.LABEL_ID = labelText.toLowerCase().replace(" ", "-");
		panel.addComponent(LABEL_ID, label);
		this.below = below;
	}
	
	public UITextFieldWithLabel(UIPanel panel, String labelText, boolean below, int x, int y, int width, int height) {
		super(panel, x, y, width, height);
		if(below) {
			this.label = new UILabel(labelText, x, y);
			this.setBounds(x, y + UILabel.DEFAULT_HEIGHT + 1, width, height);
		} else {
			this.label = new UILabel(labelText, x, y + height / 2);
			this.label.putCentered(false, true);
			this.setBounds(x + label.getWidth() + space, y, width - label.getWidth() - 1, height);
		}
		this.LABEL_ID = labelText.toLowerCase().replace(" ", "-");
		panel.addComponent(LABEL_ID, label);
		this.below = below;
	}
	
	public String getTextLabel() {
		return label.getText();
	}
	
	public UILabel getLabel() {
		return label;
	}
	
	public String getLabelId() {
		return LABEL_ID;
	}
	
	public int getTotalWidth() {
		if(!below) {
			return this.getWidth() + label.getWidth() + space;
		} else {
			return this.getWidth();
		}
	}
	
	public int getTotalHeight() {
		if(below) {
			return this.getHeight() + label.getHeight() + 1;
		} else {
			return this.getHeight();
		}
	}
	
}