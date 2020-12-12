package com.hotmarket.frames.ui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	
	public UIFrame(String title, int width, int height) {
		this(title, new Dimension(width, height));
	}
	
	public UIFrame(String title, Dimension dimension) {
		super(title);
		UIPanel contentPane = new UIPanel(this, dimension);
		contentPane.configurePanel();
		this.setContentPane(contentPane);
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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent var1) {
				UIFrame.this.onClosing();
			}
		});
	}
	
	protected void exitOnClose() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void configureFrame(boolean resizable, boolean visible) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(resizable);
		this.setVisible(visible);
	}
	
	public void onResized() {}
	
	public void onMoved() {}
	
	public void onMousePressed(int mouseButton, int x, int y, int status) {}
	
	public void onKeyTyped(int keyCode, char keyChar, int status) {}
	
	public void onClosing() {}
	
	public void onDisable() {}
	
	public void onEnable() {}
	
	@Override
	public void setEnabled(boolean enabled) {
		if(enabled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
		super.setEnabled(enabled);
	}
	
	@Override
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