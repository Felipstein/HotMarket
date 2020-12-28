package com.hotmarket.frames.market.stock.frames.actions;

import java.awt.event.ActionEvent;

import com.hotmarket.client.items.Item;
import com.hotmarket.client.items.ItemList;
import com.hotmarket.frames.market.stock.frames.StockEditItemFrame;
import com.hotmarket.frames.optionpanes.JOptionPanesUtil;
import com.hotmarket.frames.recicle.ConsumerAction;
import com.hotmarket.logger.Logger;

public class EditButtonAction implements ConsumerAction {
	
	private StockEditItemFrame frame;
	
	public EditButtonAction(StockEditItemFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(JOptionPanesUtil.areYouOkBro("Tem certeza que deseja prosseguir com essas alterações ?", "Editar") == 1) {
			return;
		}
		String f1 = frame.getValueOfTextField("f1"), f2 = frame.getValueOfTextField("f2"), name = frame.getValueOfTextField("f3"), f4 = frame.getValueOfTextField("f4"), f5 = frame.getValueOfTextField("f5");
		int id = Integer.parseInt(f1);
		if(name.trim().replace(" ", "").isEmpty()) {
			String reason = "O valor \"Nome\" não possuí nenhum valor.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Editar no frame Editar Item. " + reason);
			return;
		}
		int stock;
		try {
			stock = Integer.parseInt(f2);
			if(stock < 0) {
				String reason = "O valor \"Quantidade\" não pode ser negativo.";
				JOptionPanesUtil.anErrorExcepted(reason);
				Logger.logger.error("Falha ao processar a ação do botão Editar no frame Editar Item. " + reason);
				return;
			}
		} catch(NumberFormatException ex) {
			String reason = "O valor \"Quantidade\" não possuí somente valores númericos.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Editar no frame Editar Item. " + reason);
			return;
		}
		float price;
		try {
			price = Float.parseFloat(f4.replace(",", "."));
			if(price < 0) {
				String reason = "O valor \"Preço\" não pode ser negativo.";
				JOptionPanesUtil.anErrorExcepted(reason);
				Logger.logger.error("Falha ao processar a ação do botão Editar no frame Editar Item. " + reason);
				return;
			}
		} catch(NumberFormatException ex) {
			String reason = "O valor \"Preço\" não possuí somente valores númericos.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Editar no frame Editar Item. " + reason);
			return;
		}
		float discount;
		try {
			discount = Float.parseFloat(f5.replace(",", "."));
			if(discount < 0) {
				String reason = "O valor \"Desconto inicial\" não pode ser negativo.";
				JOptionPanesUtil.anErrorExcepted(reason);
				Logger.logger.error("Falha ao processar a ação do botão Editar no frame Editar Item. " + reason);
				return;
			}
		} catch(NumberFormatException ex) {
			String reason = "O valor \"Desconto inicial\" não possuí somente valores númericos.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Editar no frame Editar Item. " + reason);
			return;
		}
		this.frame.setModified(false);
		this.frame.close();
		ItemList items = this.frame.getFrame().bottomPanel.getTable().getItems();
		Item item = items.getItem(id);
		item.startTableUpdaterMode();
		item.setAmountStock(stock);
		item.setName(name);
		item.setPrice(price);
		item.setDiscount(discount);
		item.stopTableUpdaterMode();
		Logger.logger.info("Item " + item.toString() + " editado na lista de items principal.");
	}
	
}