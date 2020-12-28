package com.hotmarket.utils.alignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hotmarket.frames.ui.components.UIButton;

public class ButtonAlignmentY {
	
	private List<UIButton> buttons;
	
	public ButtonAlignmentY() {
		this(new ArrayList<>());
	}
	
	public ButtonAlignmentY(UIButton... buttons) {
		this(new ArrayList<>(Arrays.asList(buttons)));
	}
	
	public ButtonAlignmentY(List<UIButton> buttons) {
		this.buttons = buttons == null ? new ArrayList<>() : buttons;
	}
	
	public List<UIButton> align(int initialY, int split) {
		List<UIButton> buttons = new ArrayList<>(this.buttons);
		int y = initialY;
		for(UIButton button : buttons) {
			int x = button.getX(), width = button.getWidth(), height = button.getHeight();
			button.setBounds(x, y, width, height);
			y += height + split;
		}
		return buttons;
	}
	
	public int getTotalHeight(int split) {
		int height = 0;
		for(UIButton button : buttons) {
			height += button.getHeight() + split;
		}
		return height;
	}
	
	public List<UIButton> getButtons() {
		return buttons;
	}
	
}