package com.hotmarket.frames.ui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hotmarket.frames.recicle.InputListener;

public class UIFrame extends JFrame {
	
	private static final long serialVersionUID = -2517957370941802480L;
	
	public static final int MOUSE_PRESSED = 0;
	public static final int MOUSE_RELEASED = 1;
	public static final int KEY_PRESSED = 0;
	public static final int KEY_RELEASED = 1;
	
	public UIFrame(String title, Dimension dimension) {
		super(title);
		UIPanel panel = new UIPanel(this, dimension);
		this.setContentPane(panel);
		panel.configurePanel();
		this.pack();
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				UIFrame.this.onResized();
			}
			@Override
			public void componentMoved(ComponentEvent e) {
				UIFrame.this.onMoved();
			}
		});
		this.addKeyListener(new InputListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				UIFrame.this.onKeyTyped(e.getKeyCode(), e.getKeyChar(), 0);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				UIFrame.this.onKeyTyped(e.getKeyCode(), e.getKeyChar(), 1);
			}
		});
		this.addMouseListener(new InputListener() {
			public void mousePressed(MouseEvent e) {
				UIFrame.this.onMousePressed(e.getButton(), e.getX(), e.getY(), 0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				UIFrame.this.onMousePressed(e.getButton(), e.getX(), e.getY(), 1);
			}
		});
	}
	
	public void configureFrame(boolean resizable, boolean visible) {
		this.setLocationRelativeTo(null);
		this.setResizable(resizable);
		this.setVisible(visible);
	}
	
	public void onResized() {}
	
	public void onMoved() {}
	
	public void onMousePressed(int mouseButton, int x, int y, int status) {}
	
	public void onKeyTyped(int keyCode, char keyChar, int status) {}
	
	public UIPanel getContentPane() {
		return (UIPanel) super.getContentPane();
	}
	
	public static void loadWindowsForm() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
}