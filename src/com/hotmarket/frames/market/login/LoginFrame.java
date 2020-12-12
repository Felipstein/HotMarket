package com.hotmarket.frames.market.login;

import java.awt.Dimension;

import javax.swing.JOptionPane;

import com.hotmarket.Main;
import com.hotmarket.frames.market.main.MainFrame;
import com.hotmarket.frames.recicle.ConsumerAction;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.components.UIButton;
import com.hotmarket.frames.ui.components.UIFormattedTextField;

public class LoginFrame extends UIFrame {
	
	private static final long serialVersionUID = -2514741390990464871L;
	
	private DataLoginPanel dataLoginPanel;
	
	public LoginFrame() {
		super("Login", new Dimension(300, 200));
		this.configureFrame(false, false);
		this.exitOnClose();
		this.configureElements();
	}
	
	private void configureElements() {
		this.dataLoginPanel = (DataLoginPanel) this.getContentPane().addPanel("datalogin", new DataLoginPanel(this));
		
		int y = (this.getContentPane().getHeight() + dataLoginPanel.getHeight() - 15) / 2;
		int width = (2 * dataLoginPanel.getWidth() - this.getContentPane().getWidth()) / 2;
		UIButton b1 = new UIButton("Entrar", (this.getContentPane().getWidth() - 2 * width) / 4, y, width, 25);
		UIButton b2 = new UIButton("Sair", (3 * this.getContentPane().getWidth() - 2 * width) / 4, y, width, 25);
		
		ConsumerAction b1Action = null;
		try {
			b1Action = e -> {
				if(!dataLoginPanel.reportedNumber()) {
					JOptionPane.showMessageDialog(null, "Você deve informar o número do caixa primeiro.", "ERRO", JOptionPane.ERROR_MESSAGE);
					((UIFormattedTextField) this.dataLoginPanel.getComponentList().getComponent("tfield1")).setText("");
				} else {
					this.dispose();
					new MainFrame(dataLoginPanel.getCashier());
				}
			};
		} catch(Error e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		b1.addActionListener(b1Action);
		b2.addActionListener(e -> Main.shutdown());
		
		this.getContentPane().addComponent("start", b1);
		this.getContentPane().addComponent("leave", b2);
	}
	
	public DataLoginPanel getDataLoginPanel() {
		return dataLoginPanel;
	}
	
}