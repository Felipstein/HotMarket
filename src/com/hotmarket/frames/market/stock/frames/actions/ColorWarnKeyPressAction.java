package com.hotmarket.frames.market.stock.frames.actions;

import java.awt.Color;

import com.hotmarket.frames.recicle.KeyPressedAction;
import com.hotmarket.frames.ui.components.UITextFieldWithLabel;

public class ColorWarnKeyPressAction implements KeyPressedAction {
	
	private UITextFieldWithLabel textField;
	
	private boolean parseToReal;
	
	public ColorWarnKeyPressAction(UITextFieldWithLabel textField, boolean parseToReal) {
		this.textField = textField;
		this.parseToReal = parseToReal;
	}
	
	@Override
	public void onKeyPressed(int keyCode, char keyChar, int status) {
		if(textField.getText().isEmpty()) {
			this.textField.setBackground(new Color(255, 202, 202));
			this.textField.getLabel().setForeground(Color.red);
			return;
		}
		try {
			if((parseToReal ? Double.valueOf(textField.getText().replace(",", "")) : Integer.valueOf(textField.getText().replace(",", ""))) < 0) {
				this.textField.setBackground(new Color(255, 202, 202));
				this.textField.getLabel().setForeground(Color.red);
				return;
			}
		} catch(NumberFormatException e) {
			this.textField.setBackground(new Color(255, 202, 202));
			this.textField.getLabel().setForeground(Color.red);
			return;
		}
		if(textField.getText().contains(",")) {
			if(textField.getText().split(",").length > 2) {
				this.textField.setBackground(new Color(255, 202, 202));
				this.textField.getLabel().setForeground(Color.red);
				return;
			}
		}
		this.textField.setBackground(Color.white);
		this.textField.getLabel().setForeground(Color.black);
	}
	
}