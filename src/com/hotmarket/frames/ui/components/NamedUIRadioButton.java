package com.hotmarket.frames.ui.components;

public class NamedUIRadioButton extends UIRadioButton {
	
	private static final long serialVersionUID = -5170229497801808294L;
	
	private final String ID;
	
	public NamedUIRadioButton(String id, String text, int x, int y) {
		super(text, x, y);
		this.ID = id;
	}
	
	public String getID() {
		return ID;
	}
	
}