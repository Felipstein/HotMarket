package com.hotmarket.frames.market.stock.frames.actions;

import java.awt.Color;

import com.hotmarket.frames.market.stock.frames.filter.StockFilterFrame;
import com.hotmarket.frames.ui.components.UITextFieldWithLabel;

public class DisableAllComponentsPressAction extends ColorWarnKeyPressAction {
	
	private StockFilterFrame frame;
	
	public DisableAllComponentsPressAction(StockFilterFrame frame, UITextFieldWithLabel textField, boolean parseToReal) {
		super(textField, parseToReal);
		this.frame = frame;
	}
	
	@Override
	public void onKeyPressed(int keyCode, char keyChar, int status) {
		if(textField.getText().trim().isEmpty()) {
			this.frame.componentsToDisable.forEach(c -> c.setEnabled(true));
		} else {
			this.frame.textFields.forEach(tf -> {
				if(!((UITextFieldWithLabel) tf).getLabel().getText().equals("ID:")) {
					tf.setText("");
				}
			});
			this.frame.textFields.forEach(tf -> {
				if(!((UITextFieldWithLabel) tf).getLabel().getText().equals("ID:")) {
					tf.setBackground(Color.white);
				}
			});
			this.frame.textFields.forEach(tf -> {
				if(!((UITextFieldWithLabel) tf).getLabel().getText().equals("ID:")) {
					((UITextFieldWithLabel) tf).getLabel().setForeground(Color.black);
				}
			});
			this.frame.checkBoxs.forEach(cb -> cb.setSelected(false));
			this.frame.componentsToDisable.forEach(c -> c.setEnabled(true));
			this.frame.ignoreSettings();
			this.frame.componentsToDisable.forEach(c -> c.setEnabled(false));
		}
		super.onKeyPressed(keyCode, keyChar, status);
	}
	
}