package com.hotmarket.frames.ui.components;

import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class UIButtonGroup extends ButtonGroup {
	
	private static final long serialVersionUID = -9032665392762909305L;
	
	public UIButtonGroup() {}
	
	public UIButtonGroup(AbstractButton... buttons) {
		for(AbstractButton button : buttons) {
			this.add(button);
		}
	}
	
	public UIButtonGroup(Collection<? extends AbstractButton> buttons) {
		buttons.forEach(button -> this.add(button));
	}
	
	public void add(AbstractButton... buttons) {
		for(AbstractButton button : buttons) {
			this.add(button);
		}
	}
	
	public void add(Collection<? extends AbstractButton> buttons) {
		buttons.forEach(button -> this.add(button));
	}
	
	public static UIButtonGroup createButtonGroup() {
		return new UIButtonGroup();
	}
	
	public static UIButtonGroup createButtonGroup(AbstractButton... buttons) {
		return new UIButtonGroup(buttons);
	}
	
	public static UIButtonGroup createButtonGroup(Collection<? extends AbstractButton> buttons) {
		return new UIButtonGroup(buttons);
	}
	
}