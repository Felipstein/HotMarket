package com.hotmarket.utils.alignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hotmarket.frames.ui.components.UIRadioButton;

public class RadioButtonAlignmentX {
	
	private List<UIRadioButton> buttons;
	
	public RadioButtonAlignmentX() {
		this.buttons = new ArrayList<>();
	}
	
	public RadioButtonAlignmentX(List<UIRadioButton> buttons) {
		this.buttons = buttons == null ? new ArrayList<>() : buttons;
	}
	
	public RadioButtonAlignmentX(UIRadioButton... buttons) {
		this(new ArrayList<>(Arrays.asList(buttons)));
	}
	
	public List<UIRadioButton> align(int initialX, int split) {
		List<UIRadioButton> buttons = new ArrayList<>(this.buttons);
		int x = initialX;
		for(UIRadioButton button : buttons) {
			int y = button.getY(), width = button.getWidth(), height = button.getHeight();
			button.setBounds(x, y, width, height);
			x += width + split;
		}
		return buttons;
	}
	
	public int getTotalWidth(int split) {
		int width = 0;
		for(UIRadioButton button : buttons) {
			width += button.getWidth() + split;
		}
		return width;
	}
	
	public List<UIRadioButton> getButtons() {
		return buttons;
	}
	
}