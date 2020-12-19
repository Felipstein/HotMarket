package com.hotmarket.frames.market.main;

import java.awt.Dimension;

import com.hotmarket.Main;
import com.hotmarket.client.Cashier;
import com.hotmarket.frames.BackButton;
import com.hotmarket.frames.market.login.LoginFrame;
import com.hotmarket.frames.market.stock.StockFrame;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.UIButton;
import com.hotmarket.frames.ui.components.UILabel;
import com.hotmarket.logger.Logger;

public class MainFrame extends UIFrame {
	
	private static final long serialVersionUID = 5584477235691892633L;
	
	private static MainFrame frame;
	
	private Cashier cashier;
	
	public MainFrame(Cashier cashier) {
		super("HotMarket", new Dimension(400, 200));
		this.cashier = cashier;
		this.configureFrame(false, false);
		this.configureComponents();
		frame = this;
		Logger.logger.info("Usuário registrado: " + cashier.toString());
	}
	
	private void configureComponents() {
		if(cashier.hasName()) {
			UILabel l1 = new UILabel("Caixa " + cashier.getNumber(), (this.pane.getWidth()) / 2, 8);
			UILabel l2 = new UILabel("Atendente " + cashier.getName(), (this.pane.getWidth() / 2), 8 + UILabel.DEFAULT_HEIGHT);
			l1.putCentered(true, false);
			l2.putCentered(true, false);
			this.pane.addComponent("l1", l1);
			this.pane.addComponent("l2", l2);
		} else {
			UILabel l = new UILabel("Caixa " + cashier.getNumber(), (this.pane.getWidth() / 2), 15);
			l.putCentered(true, false);
			this.pane.addComponent("l", l);
		}
		this.pane.addComponent("back", new BackButton(e -> {
			this.dispose();
			new LoginFrame().setVisible(true);
		}));
		UIPanel pane = new UIPanel(frame, 5, 45, this.pane.getWidth() - 10, this.pane.getHeight() - 50);
		pane.configurePanel();
		pane.setPerfectBorder(null);
		
		int width = 150, height = 50;
		UIButton b1 = new UIButton("Nova Venda", (this.getContentPane().getWidth() - 2 * width) / 4, (pane.getHeight() / 2) - (height / 2), width, height);
		UIButton b2 = new UIButton("Estoque", (3 * this.getContentPane().getWidth() - 2 * width) / 4 - 10, (pane.getHeight() / 2) - (height / 2), width, height, e -> {hideClose();StockFrame.open();});
		
		pane.addComponent("b1", b1);
		pane.addComponent("b2", b2);
		this.pane.addPanel("main", pane);
	}
	
	public void reopen() {
		this.setVisible(true);
		this.setEnabled(true);
	}
	
	public void hideClose() {
		this.setVisible(false);
		this.setEnabled(false);
	}
	
	@Override
	public void onClosing() {
		Main.shutdown();
	}
	
	public Cashier getCashier() {
		return cashier;
	}
	
	public static MainFrame getFrame() {
		return frame;
	}
	
}