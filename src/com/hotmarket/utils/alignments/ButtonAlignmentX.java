package com.hotmarket.utils.alignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hotmarket.frames.ui.components.UIButton;

public class ButtonAlignmentX {
	
	private List<UIButton> buttons;
	
	public ButtonAlignmentX() {
		this(new ArrayList<>());
	}
	
	public ButtonAlignmentX(UIButton... buttons) {
		this(new ArrayList<>(Arrays.asList(buttons)));
	}
	
	public ButtonAlignmentX(List<UIButton> buttons) {
		this.buttons = buttons == null ? new ArrayList<>() : buttons;
	}
	
	public List<UIButton> align(int initialX, int split) {
		List<UIButton> buttons = new ArrayList<>(this.buttons);
		int x = initialX;
		for(UIButton button : buttons) {
			int y = button.getY(), width = button.getWidth(), height = button.getHeight();
			button.setBounds(x, y, width, height);
			x += width + split;
		}
		return buttons;
	}
	
	public int getTotalWidth(int split) {
		int width = 0;
		for(UIButton button : buttons) {
			width += button.getWidth() + split;
		}
		return width;
	}
	
	public List<UIButton> getButtons() {
		return buttons;
	}
	
}