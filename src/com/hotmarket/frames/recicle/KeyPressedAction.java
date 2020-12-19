package com.hotmarket.frames.recicle;

@FunctionalInterface
public interface KeyPressedAction {
	
	void onKeyPressed(int keyCode, char keyChar, int status);
	
}