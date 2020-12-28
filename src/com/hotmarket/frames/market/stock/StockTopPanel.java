package com.hotmarket.frames.market.stock;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.hotmarket.client.items.Item;
import com.hotmarket.frames.BackButton;
import com.hotmarket.frames.market.main.MainFrame;
import com.hotmarket.frames.market.stock.frames.StockAddItemFrame;
import com.hotmarket.frames.market.stock.frames.StockEditItemFrame;
import com.hotmarket.frames.market.stock.frames.StockRemoveItemFrame;
import com.hotmarket.frames.market.stock.frames.filter.StockFilterFrame;
import com.hotmarket.frames.optionpanes.JOptionPanesUtil;
import com.hotmarket.frames.recicle.KeyPressedAction;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.NamedUIButton;
import com.hotmarket.frames.ui.components.UITextField;
import com.hotmarket.frames.ui.components.UITextFieldWithLabel;
import com.hotmarket.logger.Logger;
import com.hotmarket.utils.alignments.ButtonAlignmentX;

public class StockTopPanel extends UIPanel {
	
	private static final long serialVersionUID = -2474046285072812523L;
	
	public List<JComponent> componentsToToggle;
	
	public StockTopPanel(StockFrame frame) {
		super(frame, 5, 5, frame.getContentPane().getWidth() - 10, 95);
		this.componentsToToggle = new ArrayList<>();
		this.configurePanel();
		this.setPerfectBorder(null);
		this.configureComponents();
	}
	
	private void configureComponents() {
		this.addComponent("back", new BackButton(e -> {getFrame().dispose();MainFrame.getFrame().reopen();}));
		
		UIPanel p1 = this.addPanel("p1", 133, 12, 115, 80);
		p1.configurePanel();
		p1.setPerfectBorder(null);
		UIPanel p2 = this.addPanel("p2", 258, 12, 115, 80);
		p2.configurePanel();
		p2.setPerfectBorder(null);
		
		this.componentsToToggle.add(p1);
		this.componentsToToggle.add(p2);
		
		int y = this.getHeight() - 40;
		int width = 110, height = 30;
		NamedUIButton newItem = new NamedUIButton("newitem", "Adicionar", 0, y, width, height, e -> new StockAddItemFrame(getFrame()).setVisible(true));
		NamedUIButton downItem = new NamedUIButton("downitem", "Baixa", 0, y, width, height, e -> this.downItems());
		NamedUIButton addStockItem = new NamedUIButton("addstockitem", "Adic. Estoque", 0, y, width, height, e -> this.addStockItems());
		NamedUIButton delItem = new NamedUIButton("delitem", "Remover", 0, y, width, height, e -> this.removeItems());
		NamedUIButton editItem = new NamedUIButton("edititem", "Editar", 0, y, width, height, e -> this.editItem());
		NamedUIButton filter = new NamedUIButton("filter", "Filtrar", 0, y, width, height, e -> new StockFilterFrame(getFrame()).setVisible(true));
		NamedUIButton exitFilter = new NamedUIButton("exitfilter", "Parar de Filtrar", 0, y, width, height, e -> this.stopFilter());
		ButtonAlignmentX alignment = new ButtonAlignmentX(newItem, downItem, addStockItem, delItem, editItem, filter, exitFilter);
		alignment.align(10, 15).forEach(button -> {
			NamedUIButton b = (NamedUIButton) button;
			UIPanel panel;
			if(b.getID().equals("downitem")) {
				panel = p1;
				b.setBounds(2, 43, width, height);
				addComponent(b.getID(), button);
			} else if(b.getID().equals("addstockitem")) {
				panel = p2;
				b.setBounds(2, 43, width, height);
			} else {
				panel = this;
			}
			panel.addComponent(b.getID(), button);
			if(!b.getID().equals("exitfilter") && !b.getID().equals("filter")) {
				this.componentsToToggle.add(button);
			}
		});
		exitFilter.setEnabled(false);
		
		UITextFieldWithLabel f1 = new UITextFieldWithLabel(p1, "Baixas:", false, "1", p1.getComponent("downitem").getX() + width / 2 - 23 - 5, 8, 55, 25);
		f1.addKeyPressedAction(new KeyPressedAction() {
			@Override
			public void onKeyPressed(int keyCode, char keyChar, int status) {
				downItem.setToolTipText("Dar " + f1.getText() + " baixa(s) no estoque do(s) item(ns) selecionado(s)");
			}
		});
		p1.addComponent("f1", f1);
		downItem.setToolTipText("Dar " + f1.getText() + " baixa no estoque do item selecionado");
		this.componentsToToggle.add(f1);
		this.componentsToToggle.add(f1.getLabel());
		
		UITextFieldWithLabel f2 = new UITextFieldWithLabel(p2, "Quantia:", false, "1", p2.getComponent("addstockitem").getX() + width / 2 - 23 - 5, 8, 60, 25);
		f2.addKeyPressedAction(new KeyPressedAction() {
			@Override
			public void onKeyPressed(int keyCode, char keyChar, int status) {
				addStockItem.setToolTipText("Adicionar " + f2.getText() + " unidade(s) no estoque do(s) item(ns) selecionado(s)");
			}
		});
		p2.addComponent("f2", f2);
		addStockItem.setToolTipText("Adicionar " + f2.getText() + " unidade no estoque do item selecionado");
		this.componentsToToggle.add(f2);
		this.componentsToToggle.add(f2.getLabel());
	}
	
	private void editItem() {
		int[] rows = getFrame().bottomPanel.getTable().getSelectedRows();
		if(rows.length == 1) {
//			int row = getFrame().bottomPanel.getTable().getSelectedRow();
			int row = rows[0];
			new StockEditItemFrame(getFrame(), getFrame().bottomPanel.getTable().getItemInRow(row)).setVisible(true);
		} else {
			JOptionPanesUtil.anErrorExcepted(rows.length == 0 ? "Selecione um item para editar." : "Selecione apenas UM item para editar.");
		}
	}
	
	public void stopFilter() {
		this.getFrame().bottomPanel.setDefaultTable();
		this.getFrame().setFilterMode(false);
		this.componentsToToggle.forEach(b -> b.setEnabled(true));
		this.getComponent("exitfilter").setEnabled(false);
	}
	
	public void startFilter() {
		this.getFrame().setFilterMode(true);
		this.componentsToToggle.forEach(b -> b.setEnabled(false));
		this.getComponent("exitfilter").setEnabled(true);
	}
	
	private void removeItems() {
		StockItemsTable table = this.getFrame().bottomPanel.getTable();
		int[] rows = table.getSelectedRows();
		if(rows.length == 0) {
			new StockRemoveItemFrame(getFrame()).setVisible(true);
		} else {
			table.removeItems(rows, true);
		}
	}
	
	private void addStockItems() {
		String value = ((UITextField) this.getPanel("p2").getComponent("f2")).getText();
		int add;
		try {
			add = Integer.parseInt(value);
			if(add <= 0) {
				JOptionPanesUtil.anErrorExcepted("Você não pode definir a unidade de estoque menor ou igual a zero.");
				return;
			}
		} catch(NumberFormatException e) {
			JOptionPanesUtil.anErrorExcepted("\"" + value + "\" não representa como um valor númerico.");
			return;
		}
		StockItemsTable table = this.getFrame().bottomPanel.getTable();
		int[] rows = table.getSelectedRows();
		if(rows.length == 0) {
			JOptionPanesUtil.anErrorExcepted("Primeiro selecione as respectivas linhas dos itens na qual você quer adicionar unidades no estoque.");
			return;
		}
		for(int row : rows) {
			Item item = table.getItemInRow(row);
			item.addAmountStock(add);
			table.getItems().updateItem(item, item.getAmountStock(), StockItemsTable.STOCK_COLUMN);
			Logger.logger.info("Estoque do item " + item.toStringLittle() + " alterado para " + item.getAmountStock() + ".");
		}
	}
	
	private void downItems() {
		String value = ((UITextField) this.getPanel("p1").getComponent("f1")).getText();
		int downs;
		try {
			downs = Integer.parseInt(value);
			if(downs <= 0) {
				JOptionPanesUtil.anErrorExcepted("Você não pode definir baixas menor ou igual a zero.");
				return;
			}
		} catch(NumberFormatException e) {
			JOptionPanesUtil.anErrorExcepted("\"" + value + "\" não representa como um valor númerico.");
			return;
		}
		StockItemsTable table = this.getFrame().bottomPanel.getTable();
		int[] rows = table.getSelectedRows();
		if(rows.length == 0) {
			JOptionPanesUtil.anErrorExcepted("Primeiro selecione as respectivas linhas dos itens na qual você quer dar baixa.");
			return;
		}
		for(int row : rows) {
			Item item = table.getItemInRow(row);
			if(item.getAmountStock() <= 0) {
				JOptionPanesUtil.anErrorExcepted("O item " + item.toStringLittle() + " já está fora de estoque.");
				continue;
			}
			if(downs > item.getAmountStock()) {
				downs = item.getAmountStock();
			}
			item.subtractAmountStock(downs);
			table.getItems().updateItem(item, item.getAmountStock(), StockItemsTable.STOCK_COLUMN);
			Logger.logger.info("Estoque do item " + item.toStringLittle() + " alterado para " + item.getAmountStock());
		}
	}
	
	@Override
	public void onResized(boolean domain) {
		if(domain) {
			this.setBounds(5, 5, getFrame().getContentPane().getWidth() - 10, 95);
		}
	}
	
	@Override
	public StockFrame getFrame() {
		return (StockFrame) super.getFrame();
	}
	
}