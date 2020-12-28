package com.hotmarket.frames.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import com.hotmarket.frames.recicle.InputListener;
import com.hotmarket.frames.recicle.KeyPressedAction;
import com.hotmarket.frames.ui.UIPanel;

public class UITextField extends JTextField implements ActionListener, KeyPressedAction {
	
	private static final long serialVersionUID = 173529740741128940L;
	
	private UIPanel panel;
	
	private List<KeyPressedAction> keyPressedActions;
	
	public UITextField(UIPanel panel, String initialText, int x, int y, int width, int height) {
		super(initialText);
		this.constructor(panel, x, y, width, height);
	}
	
	public UITextField(UIPanel panel, int x, int y, int width, int height) {
		super();
		this.constructor(panel, x, y, width, height);
	}
	
	public UITextField(String initialText, int x, int y, int width, int height) {
		super(initialText);
		this.constructor(null, x, y, width, height);
	}
	
	public UITextField(int x, int y, int width, int height) {
		super();
		this.constructor(null, x, y, width, height);
	}
	
	private void constructor(UIPanel panel, int x, int y, int width, int height) {
		this.panel = panel;
		this.keyPressedActions = new ArrayList<>();
		this.keyPressedActions.add(this);
		this.setBounds(x, y, width, height);
		this.addKeyListener(new InputListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				UITextField.this.keyPressedActions.forEach(action -> action.onKeyPressed(e.getKeyCode(), e.getKeyChar(), 0));
			}
			@Override
			public void keyReleased(KeyEvent e) {
				UITextField.this.keyPressedActions.forEach(action -> action.onKeyPressed(e.getKeyCode(), e.getKeyChar(), 1));
			}
		});
	}
	
	public void addKeyPressedAction(KeyPressedAction keyPressedAction) {
		this.keyPressedActions.add(keyPressedAction);
	}
	
	@Override
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