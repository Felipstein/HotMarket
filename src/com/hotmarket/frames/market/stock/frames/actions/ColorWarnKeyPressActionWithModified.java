package com.hotmarket.frames.market.stock.frames.actions;

import java.awt.Color;

import com.hotmarket.frames.market.stock.frames.StockAddItemFrame;
import com.hotmarket.frames.ui.components.UITextFieldWithLabel;

public class ColorWarnKeyPressActionWithModified extends ModifiedKeyPressAction {
	
	private UITextFieldWithLabel textField;
	
	private boolean parseToReal;
	
	public ColorWarnKeyPressActionWithModified(StockAddItemFrame frame, UITextFieldWithLabel textField, boolean parseToReal) {
		super(frame);
		this.textField = textField;
		this.parseToReal = parseToReal;
	}
	
	@Override
	public void onKeyPressed(int keyCode, char keyChar, int status) {
		if(textField.getText().isEmpty()) {
			this.textField.setBackground(new Color(255, 202, 202));
			this.textField.getLabel().setForeground(Color.red);
			super.onKeyPressed(keyCode, keyChar, status);
			return;
		}
		try {
			if((parseToReal ? Double.valueOf(textField.getText().replace(",", "")) : Integer.valueOf(textField.getText().replace(",", ""))) < 0) {
				this.textField.setBackground(new Color(255, 202, 202));
				this.textField.getLabel().setForeground(Color.red);
				super.onKeyPressed(keyCode, keyChar, status);
				return;
			}
		} catch(NumberFormatException e) {
			this.textField.setBackground(new Color(255, 202, 202));
			this.textField.getLabel().setForeground(Color.red);
			super.onKeyPressed(keyCode, keyChar, status);
			return;
		}
		if(textField.getText().contains(",")) {
			if(textField.getText().split(",").length > 2) {
				this.textField.setBackground(new Color(255, 202, 202));
				this.textField.getLabel().setForeground(Color.red);
				super.onKeyPressed(keyCode, keyChar, status);
				return;
			}
		}
		this.textField.setBackground(Color.white);
		this.textField.getLabel().setForeground(Color.black);
		super.onKeyPressed(keyCode, keyChar, status);
	}
	
}