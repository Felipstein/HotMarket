package com.hotmarket.frames.market.stock.frames.actions;

import java.awt.event.ActionEvent;

import com.hotmarket.client.items.Item;
import com.hotmarket.frames.market.stock.StockItemsTable;
import com.hotmarket.frames.market.stock.frames.StockAddItemFrame;
import com.hotmarket.frames.optionpanes.JOptionPanesUtil;
import com.hotmarket.frames.recicle.ConsumerAction;
import com.hotmarket.logger.Logger;

public class AddButtonAction implements ConsumerAction {
	
	private StockAddItemFrame frame;
	
	public AddButtonAction(StockAddItemFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String f2 = frame.getValueOfTextField("f2"), name = frame.getValueOfTextField("f3"), f4 = frame.getValueOfTextField("f4"), f5 = frame.getValueOfTextField("f5");
		if(name.trim().replace(" ", "").isEmpty()) {
			String reason = "O valor \"Nome\" não possuí nenhum valor.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Adicionar no frame Adicionar Item. " + reason);
			return;
		}
		int stock;
		try {
			stock = Integer.parseInt(f2);
			if(stock < 0) {
				String reason = "O valor \"Quantidade\" não pode ser negativo.";
				JOptionPanesUtil.anErrorExcepted(reason);
				Logger.logger.error("Falha ao processar a ação do botão Adicionar no frame Adicionar Item. " + reason);
				return;
			}
		} catch(NumberFormatException ex) {
			String reason = "O valor \"Quantidade\" não possuí somente valores númericos.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Adicionar no frame Adicionar Item. " + reason);
			return;
		}
		float price;
		try {
			price = Float.parseFloat(f4.replace(",", "."));
			if(price < 0) {
				String reason = "O valor \"Preço\" não pode ser negativo.";
				JOptionPanesUtil.anErrorExcepted(reason);
				Logger.logger.error("Falha ao processar a ação do botão Adicionar no frame Adicionar Item. " + reason);
				return;
			}
		} catch(NumberFormatException ex) {
			String reason = "O valor \"Preço\" não possuí somente valores númericos.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Adicionar no frame Adicionar Item. " + reason);
			return;
		}
		float discount;
		try {
			discount = Float.parseFloat(f5.replace(",", "."));
			if(discount < 0) {
				String reason = "O valor \"Desconto inicial\" não pode ser negativo.";
				JOptionPanesUtil.anErrorExcepted(reason);
				Logger.logger.error("Falha ao processar a ação do botão Adicionar no frame Adicionar Item. " + reason);
				return;
			}
		} catch(NumberFormatException ex) {
			String reason = "O valor \"Desconto inicial\" não possuí somente valores númericos.";
			JOptionPanesUtil.anErrorExcepted(reason);
			Logger.logger.error("Falha ao processar a ação do botão Adicionar no frame Adicionar Item. " + reason);
			return;
		}
		this.frame.setModified(false);
		this.frame.close();
		StockItemsTable table = this.frame.getFrame().bottomPanel.getTable();
		Item item = table.getItems().addItem(name, stock, price, discount);
		table.resizeColumns();
		Logger.logger.info("Item " + item.toString() + " adicionado na lista de items principal.");
	}
	
}