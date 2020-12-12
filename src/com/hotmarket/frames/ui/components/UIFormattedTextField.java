package com.hotmarket.frames.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import com.hotmarket.frames.recicle.InputListener;
import com.hotmarket.frames.ui.UIPanel;

public class UIFormattedTextField extends JFormattedTextField implements ActionListener {
	
	private static final long serialVersionUID = -6991096326253918593L;
	
	private UIPanel panel;
	
	public UIFormattedTextField(UIPanel panel, MaskFormatter format, int x, int y, int width, int height) {
		super(format);
		this.constructor(panel, x, y, width, height);
	}
	
	public UIFormattedTextField(UIPanel panel, int x, int y, int width, int height) {
		super();
		this.constructor(panel, x, y, width, height);
	}
	
	public UIFormattedTextField(MaskFormatter format, int x, int y, int width, int height) {
		super(format);
		this.constructor(null, x, y, width, height);
	}
	
	public UIFormattedTextField(int x, int y, int width, int height) {
		super();
		this.constructor(null, x, y, width, height);
	}
	
	public UIFormattedTextField(UIPanel panel, String format, int x, int y, int width, int height) throws ParseException {
		super(new MaskFormatter(format));
		this.constructor(panel, x, y, width, height);
	}
	
	public UIFormattedTextField(String format, int x, int y, int width, int height) throws ParseException {
		super(new MaskFormatter(format));
		this.constructor(null, x, y, width, height);
	}
	
	private void constructor(UIPanel panel, int x, int y, int width, int height) {
		this.panel = panel;
		this.setBounds(x, y, width, height);
		this.addKeyListener(new InputListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				UIFormattedTextField.this.onKeyPressed(e.getKeyCode(), e.getKeyChar(), 0);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				UIFormattedTextField.this.onKeyPressed(e.getKeyCode(), e.getKeyChar(), 1);
			}
		});
	}
	
	public void onKeyPressed(int keyCode, char keyChar, int status) {}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	public UIPanel getPanel() {
		return panel;
	}
	
	public boolean hasPanel() {
		return panel != null;
	}
	
}