package com.hotmarket.frames.market.stock.frames;

import com.hotmarket.client.items.Item;
import com.hotmarket.client.items.ItemList;
import com.hotmarket.exceptions.ItemNonexistsException;
import com.hotmarket.exceptions.NegativeNumberException;
import com.hotmarket.exceptions.NotNumberException;
import com.hotmarket.exceptions.ZeroNumberException;
import com.hotmarket.frames.market.stock.StockFrame;
import com.hotmarket.frames.market.stock.frames.actions.ColorWarnKeyPressAction;
import com.hotmarket.frames.optionpanes.JOptionPanesUtil;
import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.NamedUIButton;
import com.hotmarket.frames.ui.components.UITextField;
import com.hotmarket.frames.ui.components.UITextFieldWithLabel;
import com.hotmarket.utils.ButtonAlignmentX;

public class StockRemoveItemFrame extends UIFrame {
	
	private static final long serialVersionUID = -7722340605752102444L;
	
	private final StockFrame frame;
	
	public StockRemoveItemFrame(StockFrame frame) {
		super("Remover um item", 240, 130);
		this.frame = frame;
		this.configureFrame(false, false);
		this.configureElements();
	}
	
	private void configureElements() {
		UIPanel p = this.getContentPane().addPanel("main", 5, 5, this.getContentPane().getWidth() - 10, this.getContentPane().getHeight() - 10);
		p.configurePanel();
		p.setPerfectBorder(null);
		
		UITextFieldWithLabel field = new UITextFieldWithLabel(p, "ID:", true, "1", 20, 20, p.getWidth() - 40, 23);
		field.setKeyPressedAction(new ColorWarnKeyPressAction(field, false));
		p.addComponent("id", field);
		
		NamedUIButton b1 = new NamedUIButton("remove", "Remover", 0, p.getHeight() - 42, 100, 30, e -> this.removeItem());
		NamedUIButton b3 = new NamedUIButton("cancel", "Cancelar", 0, p.getHeight() - 42, 100, 30, e -> this.dispose());
		
		ButtonAlignmentX alignment = new ButtonAlignmentX(b1, b3);
		alignment.align(10, 10).forEach(button -> p.addComponent(((NamedUIButton) button).getID(), button));
	}
	
	public String getIdSelected() {
		return ((UITextField) this.getContentPane().getPanel("main").getComponent("id")).getText();
	}
	
	public String idVerified() throws NegativeNumberException, ZeroNumberException, NotNumberException, ItemNonexistsException {
		String stringId = getIdSelected();
		try {
			int id = Integer.parseInt(stringId);
			if(id < 0) {
				throw new NegativeNumberException("ID");
			} else if(id == 0) {
				throw new ZeroNumberException("ID");
			}
			if(getFrame().bottomPanel.getTable().getItems().getItem(Integer.parseInt(stringId)) == null) {
				throw new ItemNonexistsException(id);
			}
		} catch(NumberFormatException e) {
			throw new NotNumberException("ID");
		}
		return stringId;
	}
	
	public void removeItem() {
		String stringId;
		try {
			stringId = this.idVerified();
		} catch (NegativeNumberException | ZeroNumberException | NotNumberException | ItemNonexistsException e) {
			e.printToClient();
			return;
		}
		ItemList items = getFrame().bottomPanel.getTable().getItems();
		Item item = items.getItem(Integer.parseInt(stringId));
		if(JOptionPanesUtil.areYouOkBro("Você tem certeza que deseja remover o item " + item.toStringLittle() + " ?", "Remover " + item.getName()) == 1) {
			return;
		}
		items.removeItem(item);
		this.dispose();
	}
	
	@Override
	public void onClosing() {
		this.dispose();
	}
	
	public StockFrame getFrame() {
		return frame;
	}
	
}