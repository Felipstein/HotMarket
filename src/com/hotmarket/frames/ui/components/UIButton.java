package com.hotmarket.frames.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hotmarket.frames.recicle.ConsumerAction;
import com.hotmarket.frames.ui.UIPanel;

public class UIButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = -6914147620239198331L;
	
	private UIPanel panel;
	
	public UIButton(UIPanel panel, String text, int x, int y, int width, int height) {
		this(panel, text, x, y, width, height, null);
	}
	
	public UIButton(UIPanel panel, String text, int x, int y, int width, int height, ConsumerAction action) {
		super(text);
		this.panel = panel;
		this.setBounds(x, y, width, height);
		this.addActionListener(action == null ? this : action);
	}
	
	public UIButton(String text, int x, int y, int width, int height) {
		this(null, text, x, y, width, height);
	}
	
	public UIPanel getPanel() {
		return panel;
	}
	
	public boolean hasPanel() {
		return panel != null;
	}
	
	public void addActionListener(ConsumerAction action) {
		super.addActionListener(action);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
}