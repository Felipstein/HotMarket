package com.hotmarket.frames.market.main;

import java.awt.Color;

import javax.swing.JSeparator;

import com.hotmarket.Main;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.UILabel;

public class AboutFrame extends UIFrame {
	
	private static final long serialVersionUID = -252576170975474963L;
	
	private UIFrame openedBy;
	
	private AboutFrame() {
		super("Sobre", 425, 250);
		this.configureFrame(false, false);
		this.configureElements();
	}
	
	private void configureElements() {
		UIPanel top = this.getContentPane().addPanel("top", 0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight() / 3 - 5);
		UIPanel bottom = this.getContentPane().addPanel("bottom", 0, this.getContentPane().getHeight() / 3 - 5, this.getContentPane().getWidth(), this.getContentPane().getHeight() - (this.getContentPane().getHeight() / 3));
		
		top.configurePanel();
		bottom.configurePanel();
		top.setBackground(Color.white);
		
		JSeparator s = new JSeparator();
		s.setBounds(0, 0, this.getContentPane().getWidth(), 1);
		bottom.add(s);
		
		top.addComponent("l1", UILabel.createLabel("Isso é apenas teste bem boiolado!", 7, 14, true));
		top.addComponent("l2", new UILabel("Versão: " + Main.VERSION, 20, 45));
		top.addComponent("l3", new UILabel("HotMarket", top.getWidth() / 2 + 75, 45));
		
		bottom.addComponent("l1", new UILabel("blablablablablablablabla", 25, 20));
		bottom.addComponent("l2", new UILabel("blablablablablabla", 25, 40));
		bottom.addComponent("l3", new UILabel("blablablablablablablablabla", 25, 60));
		bottom.addComponent("l4", new UILabel("blablablablablablablablablabla", 25, 80));
	}
	
	public void close() {
		this.openedBy.setEnabled(true);
		this.dispose();
	}
	
	@Override
	public void onClosing() {
		this.close();
	}
	
	public static AboutFrame openFrame(UIFrame openedBy) {
		AboutFrame frame = new AboutFrame();
		frame.openedBy = openedBy;
		frame.openedBy.setEnabled(false);
		frame.setVisible(true);
		return frame;
	}
	
}