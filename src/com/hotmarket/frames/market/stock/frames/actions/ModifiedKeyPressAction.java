package com.hotmarket.frames.market.stock.frames.actions;

import com.hotmarket.frames.market.stock.frames.StockAddItemFrame;
import com.hotmarket.frames.recicle.KeyPressedAction;

public class ModifiedKeyPressAction implements KeyPressedAction {
	
	private StockAddItemFrame frame;
	
	public ModifiedKeyPressAction(StockAddItemFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void onKeyPressed(int keyCode, char keyChar, int status) {
		this.frame.hasModified = true;
	}
	
}