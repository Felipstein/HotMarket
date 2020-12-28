package com.hotmarket.utils.alignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hotmarket.frames.ui.components.UIRadioButton;

public class RadioButtonAlignmentY {
	
	private List<UIRadioButton> buttons;
	
	public RadioButtonAlignmentY() {
		this.buttons = new ArrayList<>();
	}
	
	public RadioButtonAlignmentY(List<UIRadioButton> buttons) {
		this.buttons = buttons == null ? new ArrayList<>() : buttons;
	}
	
	public RadioButtonAlignmentY(UIRadioButton... buttons) {
		this(new ArrayList<>(Arrays.asList(buttons)));
	}
	
	public List<UIRadioButton> align(int initialY, int split) {
		List<UIRadioButton> buttons = new ArrayList<>(this.buttons);
		int y = initialY;
		for(UIRadioButton button : buttons) {
			int x = button.getX(), width = button.getWidth(), height = button.getHeight();
			button.setBounds(x, y, width, height);
			y += height + split;
		}
		return buttons;
	}
	
	public int getTotalHeight(int split) {
		int height = 0;
		for(UIRadioButton button : buttons) {
			height += button.getHeight() + split;
		}
		return height;
	}
	
	public List<UIRadioButton> getButtons() {
		return buttons;
	}
	
}