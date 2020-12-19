package com.hotmarket.frames.ui.components;

import com.hotmarket.frames.recicle.ConsumerAction;
import com.hotmarket.frames.ui.UIPanel;

public class NamedUIButton extends UIButton {
	
	private static final long serialVersionUID = -1691548206865451393L;
	
	private final String ID;
	
	public NamedUIButton(String id, UIPanel panel, String text, int x, int y, int width, int height) {
		this(id, panel, text, x, y, width, height, null);
	}
	
	public NamedUIButton(String id, String text, int x, int y, int width, int height, ConsumerAction action) {
		this(id, null, text, x, y, width, height, action);
	}
	
	public NamedUIButton(String id, String text, int x, int y, int width, int height) {
		this(id, null, text, x, y, width, height);
	}
	
	public NamedUIButton(String id, UIPanel panel, String text, int x, int y, int width, int height, ConsumerAction action) {
		super(panel, text, x, y, width, height, action);
		this.ID = id;
	}
	
	public String getID() {
		return ID;
	}
	
}