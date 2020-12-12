package com.hotmarket.frames.market.login;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import com.hotmarket.frames.ui.UIFrame;
import com.hotmarket.frames.ui.UIPanel;
import com.hotmarket.frames.ui.components.UIFormattedTextField;
import com.hotmarket.frames.ui.components.UILabel;
import com.hotmarket.frames.ui.components.UITextField;

public class DataLoginPanel extends UIPanel {
	
	private static final long serialVersionUID = 1660709403509663284L;
	
	public DataLoginPanel(UIFrame frame) {
		super(frame, 10, 10, frame.getContentPane().getWidth() - 20, frame.getContentPane().getHeight() - 55);
		this.configurePanel();
		this.setPerfectBorder("Dados do caixa");
		this.configureElements();
	}
	
	private void configureElements() {
		this.addComponent("label1", new UILabel("Número do caixa:", 15, 30));
		this.addComponent("label2", new UILabel("Nome do atendente: (opcional)", 15, 85));
		
		MaskFormatter format;
		try {
			format = new MaskFormatter("");
		} catch (ParseException e) {
			format = new MaskFormatter();
			e.printStackTrace();
		}
		format.setValidCharacters("0123456789");
		
		UIFormattedTextField textField1 = new UIFormattedTextField(format, 15, 45, this.getWidthContentPane() - 50, 20);
		UITextField textField2 = new UITextField(15, 100, this.getWidthContentPane() - 50, 20);
		
		this.addComponent("tfield1", textField1);
		this.addComponent("tfield2", textField2);
		
	}
	
}