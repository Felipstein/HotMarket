package com.hotmarket.frames;

import com.hotmarket.frames.recicle.ConsumerAction;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.UIButton;

public class BackButton extends UIButton {
	
	private static final long serialVersionUID = 3987964392830876678L;
	
	public BackButton(ConsumerAction action) {
		this(null, action);
	}
	
	public BackButton(int x, int y, ConsumerAction action) {
		this(null, x, y, action);
	}
	
	public BackButton(UIPanel panel, ConsumerAction action) {
		this(panel, 5, 5, action);
	}
	
	public BackButton(UIPanel panel, int x, int y, ConsumerAction action) {
		super(panel, "←", x, y, 43, 35, action);
	}
	
}