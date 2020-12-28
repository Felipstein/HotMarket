package com.hotmarket.frames.market.stock.frames;

import javax.swing.JOptionPane;

import com.hotmarket.client.items.Item;
import com.hotmarket.frames.market.stock.ModifiedSavedStatus;
import com.hotmarket.frames.market.stock.StockFrame;
import com.hotmarket.frames.market.stock.frames.actions.ColorWarnKeyPressActionWithModified;
import com.hotmarket.frames.market.stock.frames.actions.EditButtonAction;
import com.hotmarket.frames.market.stock.frames.actions.ModifiedKeyPressAction;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.NamedUIButton;
import com.hotmarket.frames.ui.components.UITextField;
import com.hotmarket.frames.ui.components.UITextFieldWithLabel;
import com.hotmarket.utils.alignments.ButtonAlignmentX;

public class StockEditItemFrame extends UIFrame implements ModifiedSavedStatus {
	
	private static final long serialVersionUID = -6719462909803534444L;
	
	private final StockFrame frame;
	private final Item item;
	
	private boolean modified;
	
	public StockEditItemFrame(StockFrame frame, Item item) {
		super("Editar o item " + item.toStringLittle(), 350, 275);
		this.frame = frame;
		this.item = item;
		this.configureFrame(false, false);
		this.configureElements();
	}
	
	private void configureElements() {
		UIPanel p1 = this.getContentPane().addPanel("main", 5, 5, this.getContentPane().getWidth() - 10, this.getContentPane().getHeight() - 10);
		p1.configurePanel();
		p1.setPerfectBorder(null);
		
		NamedUIButton b1 = new NamedUIButton("edit", "Editar", 0, p1.getHeight() - 42, 125, 30, new EditButtonAction(this));
		NamedUIButton b2 = new NamedUIButton("cancel", "Cancelar", 0, p1.getHeight() - 42, 125, 30, e -> this.close());
		
		ButtonAlignmentX alignment = new ButtonAlignmentX(b1, b2);
		alignment.align((p1.getWidth() / 2) - alignment.getTotalWidth(10) / 2, 10).forEach(button -> p1.addComponent(((NamedUIButton) button).getID(), button));
		
		b1.setEnabled(false);
		
		UIPanel p2 = p1.addPanel("data", 10, 10, p1.getWidth() - 20, 205);
		p2.configurePanel();
		p2.setPerfectBorder("Dados");
		p2.loadComponentsKeyListeners();
		
		UITextFieldWithLabel f1 = new UITextFieldWithLabel(p2, "ID:", true, String.valueOf(item.getId()), 20, 35, p2.getWidth() / 2 - 40, 23);
		UITextFieldWithLabel f2 = new UITextFieldWithLabel(p2, "Quantidade:", true, String.valueOf(item.getAmountStock()), p2.getWidth() / 2 + 20, 35, p2.getWidth() / 2 - 40, 23);
		f1.setEnabled(false);
		f1.getLabel().setEnabled(false);
		f2.setKeyPressedAction(new ColorWarnKeyPressActionWithModified(this, f2, false));
		p2.addComponent("f1", f1);
		p2.addComponent("f2", f2);
		
		UITextFieldWithLabel f3 = new UITextFieldWithLabel(p2, "Nome:", true, item.getName(), 20, 88, p2.getWidth() - 40, 23);
		f3.setKeyPressedAction(new ModifiedKeyPressAction(this));
		f3.requestFocus();
		p2.addComponent("f3", f3);
		
		UITextFieldWithLabel f4 = new UITextFieldWithLabel(p2, "Preço:", true, String.valueOf(item.getPrice()), 20, 141, p2.getWidth() / 2 - 40, 30);
		UITextFieldWithLabel f5 = new UITextFieldWithLabel(p2, "Desconto:", true, String.valueOf(item.getDiscount()), p2.getWidth() / 2 + 20, 141, p2.getWidth() / 2 - 40, 30);
		f4.setKeyPressedAction(new ColorWarnKeyPressActionWithModified(this, f4, true));
		f5.setKeyPressedAction(new ColorWarnKeyPressActionWithModified(this, f5, true));
		p2.addComponent("f4", f4);
		p2.addComponent("f5", f5);
	}
	
	public String getValueOfTextField(String textFieldId) {
		return ((UITextField) this.getContentPane().getPanel("main").getPanel("data").getComponent(textFieldId)).getText();
	}
	
	public void close() {
		if(modified) {
			int option = JOptionPane.showConfirmDialog(null, "Você deseja descartar as alterações?", "Descartar alterações", JOptionPane.YES_NO_OPTION);
			if(option == 1) {
				return;
			}
		}
		this.dispose();
	}
	
	@Override
	public void onClosing() {
		this.close();
	}
	
	public Item getItem() {
		return item;
	}
	
	public StockFrame getFrame() {
		return frame;
	}
	
	@Override
	public boolean hasModified() {
		return modified;
	}
	
	@Override
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	
}