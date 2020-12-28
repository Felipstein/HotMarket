package com.hotmarket.frames.market.stock.frames.actions;

import com.hotmarket.frames.market.stock.ModifiedSavedStatus;
import com.hotmarket.frames.market.stock.frames.StockEditItemFrame;
import com.hotmarket.frames.recicle.KeyPressedAction;

public class ModifiedKeyPressAction implements KeyPressedAction {
	
	private ModifiedSavedStatus frame;
	
	public ModifiedKeyPressAction(ModifiedSavedStatus frame) {
		this.frame = frame;
	}
	
	@Override
	public void onKeyPressed(int keyCode, char keyChar, int status) {
		this.frame.setModified(true);
		if(frame instanceof StockEditItemFrame) {
			String title = ((StockEditItemFrame) frame).getTitle();
			((StockEditItemFrame) this.frame).setTitle(title.startsWith("*") ? title : "*" + title);
			((StockEditItemFrame) this.frame).getContentPane().getPanel("main").getComponent("edit").setEnabled(true);
		}
	}
	
}